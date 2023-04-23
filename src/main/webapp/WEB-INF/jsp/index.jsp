<%@page import="fr.guidesmvc.frontoffice.guides.GuideModel"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="./partials/_header.jsp" />

<%

	GuideModel guide = (GuideModel) request.getSession().getAttribute("guide");

	if (guide != null) {
		out.print("<h1>Bienvenue, <span class='text-secondary'>"+ guide.getPrenomGuides() + " "+ guide.getNomGuides() +"</span>.</h1>");
		out.print("<p>Accédez à vos randonnées par la barre de navigation.</p>");
	} else {
		out.print("<div class='h-100 p-5 bg-light border rounded-3'>");
		out.print("<h2>La Compagnie des Guides</h2>");
		out.print("<p>Bienvenue sur le front-office de La Compagnie des Guides. Vous devez être connecter en tant que Guide pour utiliser l'application.</p>");
		out.print("");
		out.print("");
		out.print("");
	}
%>

<jsp:include page="./partials/_footer.jsp" />

