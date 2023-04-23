<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../partials/_header.jsp" />

<h1 class='mb-3'>Créer une Randonnée</h1>

<form action="<%= request.getContextPath() %>/randonnees" method="POST">
	<div class='row align-items-center mb-2'>
		<div class='col-auto'>
			<label for='dateDebut' class='col-form-label'>Date de début</label>
		</div>
		<div class='col-auto'>
			<input name='dateDebut' type='date' class='form-control form-control-sm' id='dateDebut'>
		</div>
	</div>
	
	<div class='row align-items-center mb-3'>
		<div class='col-auto'>
			<label for='participants' class='col-form-label'>Nombre de personnes</label>
		</div>
		<div class='col-auto'>
			<input name='nbPersonnes' type='number' min='1' class='form-control form-control-sm' style='width: 5rem;' value='1' id='participants'>
		</div>
	</div>
	
	<div class='col-auto'>
		<button class="btn btn-primary btn-sm" type="submit">Créer</button>
	</div>
</form>

<jsp:include page="../partials/_footer.jsp" />
