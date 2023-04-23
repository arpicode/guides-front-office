<%@page import="fr.guidesmvc.frontoffice.guides.GuideModel"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="fr.guidesmvc.frontoffice.randonnees.RandonneeModel"%>

<jsp:include page="../partials/_header.jsp" />

<%
	GuideModel guide = (GuideModel) request.getSession().getAttribute("guide");
%>

<h1>Randonnées de <span class="text-secondary"><%=guide.getPrenomGuides()%> <%=guide.getNomGuides()%></span></h1>
<form action="randonnees/create" method="POST" class="my-3">
    <button class="btn btn-sm btn-success" type="submit"><i class="bi bi-plus-lg me-1"></i>
        Créer une randonnée
    </button>
</form>

<%
Iterable<RandonneeModel> randonnees = (Iterable<RandonneeModel>) request.getAttribute("randonnees");

if (randonnees.iterator().hasNext()) {
	// Afficher la table des randonnées.
	out.print("<table class='table table-sm table-hover'><thead><tr>");
	out.print("<th scope='col'>#</th>");
	out.print("<th scope='col'>Date Début</th>");
	out.print("<th scope='col'>Date Fin</th>");
	out.print("<th style='width: 1%;' class='px-3' scope='col'>Participants</th>");
	out.print("<th style='width: 1%;' class='text-center' scope='col'>Actions</th>");
	out.print("</tr></thead><tbody class='table-group-divider'>");
	
    for (RandonneeModel r : randonnees) {
        out.print("<tr class='align-middle'>");
        out.print("<th scope='row'>"+r.getCodeRandonnees()+"</td>");
        out.print("<td>"+ r.getDateDebutRandonnees() +"</td>");
        out.print("<td>"+ r.getDateFinRandonnees() +"</td>");
        out.print("<td class='text-center'>"+ r.getNbPersonnesRandonnees() +"</td>");
        out.print("<td>");
        out.print("<div class='d-flex gap-1'>");
        out.print("<a class='btn btn-sm btn-primary' href='randonnees/"+ r.getCodeRandonnees() +"'><i class='bi bi-pencil'></i></a>");
        out.print("");
        out.print("");
        out.print("");
        out.print("<form action='"+ request.getContextPath() +"/randonnees/delete/"+ r.getCodeRandonnees() +"' method='POST'>");
        out.print("<button type='submit' class='btn btn-sm btn-danger'><i class='bi bi-trash'></i></button>");
        out.print("</form>");
        out.print("</div>");
        out.print("</td>");
        out.print("</tr>");
    }

	out.print("</tbody></table>");
} else {
	// Pas de randonnée à afficher.
	out.print("<div class='alert alert-info'><i class='bi bi-info-circle-fill me-3' style='vertical-align:-4px;font-size:1.5rem;'></i>");
	out.print("Il n'y a aucune randonnées.");
	out.print("</div>");
}

%>

<code>
<%
	//for (RandonneeModel r : randonnees) {
	//	out.println("<p>" + r + " <a href='"+request.getContextPath()+"/randonnees/"+r.getCodeRandonnees()+"'>Voir</a></p>");
	//}
%>
</code>
	
<jsp:include page="../partials/_footer.jsp" />
