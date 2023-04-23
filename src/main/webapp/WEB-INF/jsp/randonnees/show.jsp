<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="fr.guidesmvc.frontoffice.concerner.ConcernerModel"%>
<%@page import="fr.guidesmvc.frontoffice.reserver.ReserverModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Optional"%>
<%@page import="fr.guidesmvc.frontoffice.randonnees.RandonneeModel"%>

<jsp:include page="../partials/_header.jsp" />

<%
	RandonneeModel randonnee = ((Optional<RandonneeModel>) request.getAttribute("randonnee")).orElse(null);
	List<ReserverModel> reservations = (List<ReserverModel>) request.getAttribute("reservations");
	List<ConcernerModel> sommets = (List<ConcernerModel>) request.getAttribute("sommets");

	if (randonnee != null) {
		// Afficher les infos de la randonnée.
		out.println("<h1 class='mb-3'>Randonnée <small class='fw-light text-secondary'>#" + randonnee.getCodeRandonnees() + "</small></h1>");
		out.println("<p class='fw-light text-secondary mb-1'>Du <span class='fw-semibold text-dark'>"+ randonnee.getDateDebutRandonnees() +"</span> au <span class='fw-semibold text-dark'>"+ randonnee.getDateFinRandonnees() +"</span></p>");
		out.println("<p class='fw-light text-secondary mb-1'>Organisateur&nbsp;: <span class='fw-semibold text-dark'>"+ randonnee.getGuideResponsable().getPrenomGuides() +" "+ randonnee.getGuideResponsable().getNomGuides() +"</span></p>");
		
		out.println("<form action='"+ request.getContextPath() +"/randonnees/"+ randonnee.getCodeRandonnees() +"' method='POST' class='row align-items-center mb-3'>");
		out.println("<div class='col-auto'>");
		out.println("<label for='participants' class='col-form-label fw-light text-secondary'>Participants</label>");
		out.println("</div>");
		out.println("<div class='col-auto'>");
		out.println("<input name='nbPersonnes' type='number' class='form-control form-control-sm' style='width: 4rem;' value='" + randonnee.getNbPersonnesRandonnees() + "' id='participants'>");
		out.println("</div>");
		out.println("<div class='col-auto'>");
		out.println("<button type='submit' class='btn btn-primary btn-sm'><i class='bi bi-arrow-clockwise me-2'></i>Modifier</button>");
		out.println("</div>");
		out.println("</form>");
		
		if (reservations != null) {
			out.println("<h4 class='mb-3'>"+ reservations.size() +" réservations</h4>");
			
			// Afficher le bouton pour ajouter une réservation.
			out.println("<form action='"+request.getContextPath()+"/reservations/create' method='POST'>");
			out.println("<input type='hidden' name='randonneeId' value='"+ randonnee.getCodeRandonnees() +"'>");
			out.println("<button class='btn btn-success btn-sm' type='submit'><i class='bi bi-plus-lg me-2'></i>Ajouter</button>");
			out.println("</form>");
		
			if (reservations.size() > 0) {
				// Afficher la table des réservations.
	            out.println("<table class=\"table table-sm align-middle table-hover mt-3\"><thead>");
	            out.println("<tr><th>Date</th><th>Sommet</th><th>Abris</th><th style='width: 1%;'>Statut</th><th style='width: 1%;'>Action</th></tr></thead>");
	            out.println("<tbody class=\"table-group-divider\">");
	
	            int currentIndex = 0;
				for (ReserverModel res : reservations) {
					String statut = res.getStatutReserver();
					out.println("<tr>");
					out.println("<td>"+ res.getDateReserver() +"</td>");
					out.println("<td>"+ sommets.get(currentIndex).getSommetConcerner().getNomSommets() +"</td>");
					out.println("<td>"+ res.getAbriReserve().getNomAbris() +"</td>");
					
					out.println("<td class='text-center'>");
					if ("refuge".equals(res.getAbriReserve().getTypeAbris())) {
						// Affilcher le select du statut.
						out.println("<form action='"+request.getContextPath()+"/reservations/"+randonnee.getCodeRandonnees()+"/"+res.getDateReserver()+"' method='POST'>");
						out.println("<div class='d-flex gap-1'>");
						out.println("<select name='statut' class='form-select form-select-sm' style='width: 7rem;'>");
						out.println("<option value='en attente'"+ ("en attente".equals(statut) ? "selected" : "") +">En attente</option>");
						out.println("<option value='confirmé'"+ ("confirmé".equals(statut) ? "selected" : "") +">Confirmé</option>");
						out.println("</select>");
						out.println("<button type='submit' class='btn btn-primary btn-sm'><i class='bi bi-arrow-clockwise'></i></button>");
						out.println("</div>");
						out.println("</form>");
					} else {
						out.println("<span class='text-secondary'>n/a</span>");
					}
					out.println("</td>");
					
					out.println("<td>");
					out.println("<div class='d-flex gap-1 justify-content-end'>");
					out.println("<form action='"+request.getContextPath()+"/reservations/"+ res.getDateReserver() +"' method='POST'>");				
					out.println("<input type='hidden' name='randonneeId' value='"+randonnee.getCodeRandonnees()+"'>");
					out.println("<button type='submit' class='btn btn-danger btn-sm'><i class='bi bi-trash'></i></button>");
					out.println("</form>");
					out.println("</div>");
					out.println("</td>");
					out.println("</tr>");
					
					currentIndex++;
				}
	            
	            out.println("</tbody></table></div></div>");
				
			} else {
				// Pas de réservations à afficher.
	            out.println("<div class=\"alert alert-info mt-3\">Aucune réservation.</div>");
			}
			
			out.println("<a href='"+request.getContextPath()+"/randonnees' class='btn btn-secondary btn-sm mt-4'><i class='bi bi-arrow-left me-2'></i>Retour</a>");
			out.println("");
			out.println("");
			out.println("");
		}
	}
%>

<jsp:include page="../partials/_footer.jsp" />
