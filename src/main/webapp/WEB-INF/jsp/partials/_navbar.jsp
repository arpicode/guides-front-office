<%@page import="fr.guidesmvc.frontoffice.guides.GuideModel"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
	GuideModel guide = (GuideModel) request.getSession().getAttribute("guide");
%>


<nav class="navbar fixed-top navbar-expand-sm navbar-dark bg-dark" aria-label="Navigation principale">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/">
            <img class="rounded-1" src="<%=request.getContextPath()%>/images/logo-48.png" alt="LCdG" width="" height="32">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <%
                    if (guide != null) {
                        out.println("<li class=\"nav-item\">");
                        out.println("<a href=\""+ request.getContextPath() +"/randonnees\" class=\"nav-link\">Randonnées</a>");
                        out.println("</li>");
                    }
                %>
            </ul>

            <ul class="navbar-nav">
                <li class="nav-item dropdown me-4">
                    <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown" aria-expanded="false">
                        <%
	                        if (guide == null) {
	                        	out.println("<i class='bi bi-person-fill'></i>");
	                        	out.println("Utilisateur");
	                        } else {
	                        	out.println("<i class='bi bi-person-fill text-success'></i>");
	                        	out.println(guide.getPrenomGuides() + " " + guide.getNomGuides());
	                        }
                        %> 
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <%
                            if (guide == null) {
                                out.println("<li><a class='dropdown-item' href='"+ request.getContextPath() +"/login'><i class='bi bi-box-arrow-in-right me-2'></i>Se connecter</a></li>");
                            } else {
                                out.println("<li><form action='"+ request.getContextPath() +"/logout' method='POST'>");
                                out.println("<button class=\"btn btn-link text-decoration-none text-reset\" type=\"submit\"><i class=\"bi bi-power me-2\"></i>Déconnexion</button>");
                                out.println("</form></li>");
                            }
                        %>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
