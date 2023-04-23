<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="../partials/_header.jsp" />

<h1>Login</h1>

<form class="me-auto" action="<%= request.getContextPath() %>/authenticate" method="POST" style="width: 20rem;">
	<label class="form-label">Email</label>
	<input class="form-control form-control-sm" name="email" type="email" value="bidule.truc@mail.fr">
	
	<label class="form-label mt-3">Mot de passe</label>
	<input class="form-control form-control-sm" name="passwd" type="password" value="$pass">
	 
	<button class="btn btn-sm btn-primary mt-4" type="submit">Se connecter</button>
</form>

<jsp:include page="../partials/_footer.jsp" />
