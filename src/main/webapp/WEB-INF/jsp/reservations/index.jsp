<%@page import="fr.guidesmvc.frontoffice.reserver.ReserverModel"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	List<ReserverModel> reservations = (List<ReserverModel>) request.getAttribute("reservations");
%>
<body>
<%
	out.println("<h1>Liste des Réservations ["+ reservations.size() +"]</h1>");

	out.println("<code>");
	if (reservations.size() > 0) {
		for (ReserverModel r : reservations) {
			out.println(r);
			out.println("<br><br>");
		}
	} else {
		out.println("Aucune réservation.");		
	}
	out.println("</code>");
%>
</body>
</html>