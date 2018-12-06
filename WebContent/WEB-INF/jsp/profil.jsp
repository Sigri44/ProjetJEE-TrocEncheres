<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<head>
    <jsp:include page="header.jsp"/>
</head>

<body>
	<div class="container">
		<div class="header clearfix">
		   <h3 class="text-muted">TrocEncheres.org</h3>
		</div>
		<c:if test= "${not empty modification}">
			<p class="alert alert-success">${modification}</p>
		</c:if>	
		<div class="jumbotron">
	      	<h1 class="center">${user.pseudo}</h1>
        	<div class="row">
				<div class="col-sm-6 offset-sm-4">
					<div class="row">
						<div class="col-sm-3">
							<label>Pseudo :<br></label>
						</div>
						<div class="col-sm-4 offset-sm-1">
							${user.pseudo}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<label>Nom :<br></label>
						</div>
						<div class="col-sm-4 offset-sm-1">
							${user.nom}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<label>Prénom :<br></label>
						</div>
						<div class="col-sm-4 offset-sm-1">
							${user.prenom}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<label>Email :<br></label>
						</div>
						<div class="col-sm-4 offset-sm-1">
							${user.mail}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<label>Téléphone :<br></label>
						</div>
						<div class="col-sm-4 offset-sm-1">
							${user.telephone}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<label>Rue :<br></label>
						</div>
						<div class="col-sm-4 offset-sm-1">
							${user.rue}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<label>Code Postal :<br></label>
						</div>
						<div class="col-sm-4 offset-sm-1">
							${user.codePostal}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<label>Ville :<br></label>
						</div>
						<div class="col-sm-4 offset-sm-1">
							${user.ville}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<a class="btn btn-lg" href="monProfil"><button type="button" class="btn btn-primary bleu">Modifier</button></a>
						</div>
						<div class="col-sm-4 offset-sm-1">
							<a class="btn btn-lg" href="listeEncheres"><button type="button" class="btn btn-primary red">Retour</button></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	    <jsp:include page="footer.jsp"/>
   </div>
</body>
</html>