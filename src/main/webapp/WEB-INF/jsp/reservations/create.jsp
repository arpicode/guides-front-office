<%@page import="fr.guidesmvc.frontoffice.concerner.ConcernerModel"%>
<%@page import="fr.guidesmvc.frontoffice.abris.AbriModel"%>
<%@page import="fr.guidesmvc.frontoffice.reserver.ReserverModel"%>
<%@page import="fr.guidesmvc.frontoffice.sommets.SommetModel"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="../partials/_header.jsp" />

<%
	Long selectedSommet = (Long) request.getAttribute("selectedSommet");
	Long selectedAbri = (Long) request.getAttribute("selectedAbri");
	ConcernerModel dernierSommet = (ConcernerModel) request.getAttribute("dernierSommet");
	boolean isLastStep = request.getAttribute("isLastStep") != null;
	String formAction = isLastStep ? request.getContextPath() + "/reservations" : "";
%>

<h1 class="mb-4">Créer une réservation</h1>

<%
	ReserverModel derniereReservation = (ReserverModel) request.getAttribute("derniereReservation");
	if (derniereReservation != null) {
		out.println("");
		out.println("<legend class='fw-light fs-5'>Dernière réservation</legend>");
		out.println("<table class='table'>");
		out.println("<tr><th>Date</th><th>Sommet</th><th>Abri</th><th>Statut</th></tr>");
		out.println("<td>"+ derniereReservation.getDateReserver() +"</td>");			
		out.println("<td>"+ dernierSommet.getSommetConcerner().getNomSommets() +"</td>");			
		out.println("<td>"+ derniereReservation.getAbriReserve().getNomAbris() +"</td>");			
		out.println("<td>"+ derniereReservation.getStatutReserver() +"</td>");			
		out.println("</table>");
	} else {
		out.println("<p>1<sup>ère</sup> réservation.</p>");						
	}
%>

<%-- <form action="<%= request.getContextPath() %>/reservations" method="POST"> --%>
<form action="<%=formAction%>" method="POST">
	<%-- Date Réservation : <input type="date" value="<%= request.getAttribute("dateReservation") %>"> +NbRéservations (TODO)<br> --%>
	<input type="hidden" name="randonneeId" value="<%= request.getAttribute("randonneeId") %>">
	<input type="hidden" name="dateReservation" value="<%= request.getAttribute("dateReservation") %>">
	
	<%
		// Si on est à l'étape de saisie du sommet.
		out.println("<label for='sommets' class='form-label'>Sommet</label>");
		
		if (selectedSommet == 0) {
			// Afficher le select des sommets.
			List<SommetModel> sommets = (List<SommetModel>) request.getAttribute("sommets");
			
			out.println("");
			out.println("");
			//out.println("<label for='sommets' class='form-label'>Sommet</label>");
			out.println("<select name='selectedSommet' id='sommets' class='form-select form-select-sm mb-4'>");
			for (SommetModel s : sommets) {
				out.println("<option value='"+s.getCodeSommets()+"'>"+ s.getNomSommets() +"</option>");
			}
			out.println("</select>");
			out.println("<button type='submit' class='btn btn-success btn-sm'>Continuer<i class='bi bi-arrow-right ms-2'></i></button>");
		} else {
			// Afficher le sommet déjà sélectionné.
			SommetModel sommet = (SommetModel) request.getAttribute("sommet");
			
			if (sommet != null) {
				out.println("<input type='text' value='"+ sommet.getNomSommets() +"' class='form-control form-control-sm' readonly>");
                out.println("<input type='hidden' name='selectedSommet' value='"+ sommet.getCodeSommets() +"'>");
			} else {
				out.println("<code>Erreur sommet.</code>");
				out.println("");
				out.println("");
			}
			
			// Si on est à l'étape de saisie de l'abri.
			out.println("<label for='abris' class='form-label mt-3'>Abri</label>");
					
			if (selectedAbri == 0) {
				// Afficher le select des abris.
				List<AbriModel> abris = (List<AbriModel>) request.getAttribute("abris");
				
				out.println("<select name='selectedAbri' id='abris' class='form-select form-select-sm mb-4'>");
				for (AbriModel a : abris) {
					out.println("<option value='"+a.getCodeAbris()+"'>"+ a.getNomAbris() +"</option>");
				}
				out.println("</select>");
				out.println("<button type='submit' class='btn btn-success btn-sm'>Continuer<i class='bi bi-arrow-right ms-2'></i></button>");
			} else {
				// Afficher l'abri sélectionné.
				AbriModel abri = (AbriModel) request.getAttribute("abri");
				
				if (abri != null) {
					out.println("<input type='text' value='"+ abri.getNomAbris() +"' class='form-control form-control-sm' readonly>");
	                out.println("<input type='hidden' name='selectedAbri' value='"+ abri.getCodeAbris() +"'>");
				} else {
					out.println("<code>Erreur abri.</code>");
					out.println("");
					out.println("");
				}
				
				// Si l'abri sélectionné est de type refuge.
				if (abri != null && "refuge".equals(abri.getTypeAbris())) {
					out.println("<label class=\"form-label mt-3\" for=\"statut\">Statut</label>");
                    out.println("<select id=\"statut\" name=\"statut\" class='form-select form-select-sm'>");
                    out.println("<option value='en attente'>En attente</option>");
                    out.println("<option value='confirmé'>Confirmé</option>");
                    out.println("</select>");
				}
				
				out.println("<div class='text-end mt-4'>");
				out.println("<button type='submit' class='btn btn-primary btn-sm'>Enregister</button>");
				out.println("</div>");
			}
		}
	%>

</form>
	
<jsp:include page="../partials/_footer.jsp" />
