<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*"  %>
    <%@ page import="com.UBC417.A2.Data.Flight" %>
    <%@ page import="com.google.appengine.api.datastore.Entity" %>
    <%@ page import="com.UBC417.A2.Data.Seat" %>
    <%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link href="Site.css" rel="Stylesheet" type="text/css"/>
</head>
<body>
	
	
	<div class="wrapper">
		<div id="Content">
			<div id="Header"><H1>Flights - V4</H1></div>
			<hr/>
			<div id="MainContent">

				<% 
				Iterable<Entity> flight1Seats = (Iterable<Entity>)request.getAttribute("flight1Seats");
				Iterable<Entity> flight2Seats = (Iterable<Entity>)request.getAttribute("flight2Seats");
				Iterable<Entity> flight3Seats = (Iterable<Entity>)request.getAttribute("flight3Seats");
				Iterable<Entity> flight4Seats = (Iterable<Entity>)request.getAttribute("flight4Seats");
				%>
				
				<form action="ReserveSeat" method="post">
				
				<input type="hidden" name="Flight1" value="<%=request.getParameter("Flight1") %>"/>
				<input type="hidden" name="Flight2" value="<%=request.getParameter("Flight2") %>"/>
				<input type="hidden" name="Flight3" value="<%=request.getParameter("Flight3") %>"/>
				<input type="hidden" name="Flight4" value="<%=request.getParameter("Flight4") %>"/>
				
				<table>
					<tr>
						<td><%=request.getParameter("Flight1")%></td>
						<td>
							<select name="Seat1ID">
								<option value="">Please select a seat.</option>
							<% for( Entity e : flight1Seats ) { %>
								<option><%=e.getKey().getName() %></option>
							<%} %>
							</select>
						</td>
						<td><%=request.getParameter("Flight2")%></td>
						<td>
							<select name="Seat2ID">
								<option value="">Please select a seat.</option>
							<% for( Entity e : flight2Seats ) { %>
								<option><%=e.getKey().getName() %></option>
							<%} %>
							</select>
						</td>
						<td><%=request.getParameter("Flight3")%></td>
						<td>
							<select name="Seat3ID">
								<option value="">Please select a seat.</option>
							<% for( Entity e : flight3Seats ) { %>
								<option><%=e.getKey().getName() %></option>
							<%} %>
							</select>
						</td>
						<td><%=request.getParameter("Flight4")%></td>
						<td>
							<select name="Seat4ID">
								<option value="">Please select a seat.</option>
							<% for( Entity e : flight4Seats ) { %>
								<option><%=e.getKey().getName() %></option>
							<%} %>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">First Name:</td>
						<td align="left"><input type="text" name="FirstName"/></td>
					</tr>
					<tr>
						<td align="right">Last Name:</td>
						<td align="left"><input type="text" name="LastName"/></td>
					</tr>
					<tr>
						<td align="right"></td>
						<td align="left"><input type="checkbox" name="waitList">Add me to a waiting list if one or more seats are unavailable?</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Reserve Seat"/></td>
					</tr>
				</table>
				</form>
				
			</div><!-- end MainContent -->
		</div><!-- end Content -->
	</div><!-- end wrapper -->
</body>
</html>