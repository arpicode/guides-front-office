<%@page import="fr.guidesmvc.frontoffice.concerner.ConcernerModel"%>
<%@page import="fr.guidesmvc.frontoffice.reserver.ReserverModel"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>La Compagnie des Guides</title>
</head>
<%
	ReserverModel reservation = (ReserverModel) request.getAttribute("reservation");
	ConcernerModel sommetConcerner = (ConcernerModel) request.getAttribute("sommetConcerner");
%>

<body>
	<h1>Réservation</h1>
	<%
		out.println("<code>");
		if (reservation != null) {
			out.println("<p>"+ reservation +"</p>");
			out.println("<p>"+ sommetConcerner +"</p><br>");
		} else {
			out.println("<p>Pas de réservation.</p>");
		}
		out.println("</code>");
	%>
</body>

</html>