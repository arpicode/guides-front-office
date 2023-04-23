<%@page import="fr.guidesmvc.frontoffice.sommets.SommetModel"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>La Compagnie des Guides</title>
</head>
<body>
	<h1>Liste des Sommets</h1>
	<code>
	<%
		Iterable<SommetModel> sommets = (Iterable<SommetModel>) request.getAttribute("sommets");
	
		for (SommetModel s : sommets) {
			out.println("<p>" + s + "</p>");
		}
	%>
	</code>
</body>
</html>