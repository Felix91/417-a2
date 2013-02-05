package com.UBC417.A2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.UBC417.A2.Data.Seat;
import com.UBC417.A2.Data.SeatReservation;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@SuppressWarnings("serial")
public class ReserveSeatServlet extends HttpServlet {
	

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// Get parameters
		String Flight1 = req.getParameter("Flight1");		
		String Seat1ID = req.getParameter("Seat1ID");
		String Flight2 = req.getParameter("Flight2");		
		String Seat2ID = req.getParameter("Seat2ID");
		String Flight3 = req.getParameter("Flight3");		
		String Seat3ID = req.getParameter("Seat3ID");
		String Flight4 = req.getParameter("Flight4");		
		String Seat4ID = req.getParameter("Seat4ID");
		
		String FirstName = req.getParameter("FirstName");
		String LastName = req.getParameter("LastName");
		
		String forwardTo = "/seatConfirmation.jsp";
		try {
			//if (!Seat.ReserveSeat(Flight, SeatID, FirstName, LastName)) {
			if (!Seat.ReserveSeats(Flight1, Seat1ID, Flight2, Seat2ID, Flight3, Seat3ID, Flight4, Seat4ID, FirstName, LastName)) {
				// seat not reserved, show error page
				forwardTo = "/reserveSeatError.jsp";
			}
		} catch (EntityNotFoundException e) {
			// seat not found, show error page
			forwardTo = "/reserveSeatError.jsp";
		} catch (Exception e) {
			// Do nothing
			e.printStackTrace();
		}

		// redirect to final page
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(forwardTo);
		rd.forward(req, resp);
	}
}
