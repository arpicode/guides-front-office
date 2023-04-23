<%@page import="java.util.List"%>
<%@page import="fr.guidesmvc.frontoffice.guides.GuideModel"%>
<%@page import="java.util.Optional"%>
<%@page import="fr.guidesmvc.frontoffice.randonnees.RandonneeModel"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>La Compagnie des Guides</title>
</head>
<%
	GuideModel guide = ((Optional<GuideModel>) request.getAttribute("guide")).orElse(null);
	List<RandonneeModel> randonnees = (List<RandonneeModel>) request.getAttribute("randonnees");

%>
<body>

<%
	if (guide != null) {
		out.println("<h1>Randonnées du Guide #"+ guide.getCodeGuides() +" ["+ randonnees.size() +"]</h1>");
		
		out.println("<code>");
		if (randonnees.size() > 0) {
			for (RandonneeModel r : randonnees) {
				out.println(r);
			}
		} else {
			out.println("Aucune randonnée.");
		}
		out.println("</code>");
		
		out.println("");
		out.println("");
	}
%>

</body>
</html>