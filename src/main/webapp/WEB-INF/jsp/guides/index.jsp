<%@page import="fr.guidesmvc.frontoffice.guides.GuideModel"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>La Compagnie des Guides</title>
</head>
<body>
	<h1>Liste des Guides</h1>
	<code>
	<%
		Iterable<GuideModel> guides = (Iterable<GuideModel>) request.getAttribute("guides");
	
		for (GuideModel g : guides) {
			out.println("<p>" + g + " <a href='" + request.getContextPath() + "/guides/" + g.getCodeGuides() +"'>Voir</a></p>");
		}
	%>
	</code>
</body>
</html>