package com.UBC417.A2.Helpers;

import java.util.Date;

import com.UBC417.A2.Data.Seat;
import com.UBC417.A2.Data.SeatReservation;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;

public class Worker {

	public static void Process() throws Exception 
	{
		// Retry transaction using info stored in SeatReservation entity
		Iterable<Entity> reservations = SeatReservation.GetReservations();
		for(Entity r : reservations )
		{
			// Get parameters
			String Flight1 = (String) r.getProperty("Flight1");
			String Flight2 = (String) r.getProperty("Flight2");
			String Flight3 = (String) r.getProperty("Flight3");
			String Flight4 = (String) r.getProperty("Flight4");
			String Flight1Seat = (String) r.getProperty("Flight1Seat");
			String Flight2Seat = (String) r.getProperty("Flight2Seat");
			String Flight3Seat = (String) r.getProperty("Flight3Seat");
			String Flight4Seat = (String) r.getProperty("Flight4Seat");
			String FirstName = (String) r.getProperty("FirstName");
			String LastName = (String) r.getProperty("LastName");
			
			// Retry transaction
			Seat.ReserveSeats(Flight1, Flight1Seat, Flight2, Flight2Seat, Flight3, Flight3Seat, Flight4, Flight4Seat, FirstName, LastName);
			
			// Remove reservation. If transaction failed, Seat.ReserveSeats will add a new reservation into the datastore again
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			Key k = r.getKey();
			ds.delete(k);
		}
	}
}
