package com.UBC417.A2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.UBC417.A2.Data.Seat;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class SelectSeatServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		String flight1 = req.getParameter("Flight1");
		String flight2 = req.getParameter("Flight2");
		String flight3 = req.getParameter("Flight3");
		String flight4 = req.getParameter("Flight4");
		
		Iterable<Entity> flight1Seats = Seat.GetSeats(flight1);
		Iterable<Entity> flight2Seats = Seat.GetSeats(flight2);
		Iterable<Entity> flight3Seats = Seat.GetSeats(flight3);
		Iterable<Entity> flight4Seats = Seat.GetSeats(flight4);
		
		req.setAttribute("flight1Seats", flight1Seats);
		req.setAttribute("flight2Seats", flight2Seats);
		req.setAttribute("flight3Seats", flight3Seats);
		req.setAttribute("flight4Seats", flight4Seats);
		
		//redirect to index.jsp
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/selectSeats.jsp");
		rd.forward(req, resp);
	}

}
