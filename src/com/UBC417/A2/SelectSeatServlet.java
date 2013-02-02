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

		String flight = req.getParameter("Flight");
		
		Iterable<Entity> flightSeats = Seat.GetFreeSeats(flight);
		
		req.setAttribute("flightSeats", flightSeats);
		
		//redirect to index.jsp
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/selectSeats.jsp");
		rd.forward(req, resp);
	}

}
