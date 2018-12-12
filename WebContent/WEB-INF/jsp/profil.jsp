<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<head>
    <jsp:include page="header.jsp"/>
</head>

<body>
	<div class="container">
		<div class="header clearfix">
		   <jsp:include page="menu.jsp" />
		</div>
		<c:if test= "${not empty modification}">
			<p class="alert alert-success">${modification}</p>
		</c:if>	
		<div class="jumbotron">
	      	<h1 class="center">${user.pseudo}</h1>
        	<div class="row">
				<div class="col-sm-12 col-md-8 col-lg-6 offset-md-2 offset-lg-4">
					<div class="row">
						<div class="col-sm-6 col-md-4 col-lg-4">
							<label>Pseudo :</label>
						</div>
						<div class="col-sm-6 col-md-8 col-lg-6">
							${user.pseudo}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-md-4 col-lg-4">
							<label>Nom :</label>
						</div>
						<div class="col-sm-6 col-md-8 col-lg-6">
							${user.nom}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-md-4 col-lg-4">
							<label>Prénom :</label>
						</div>
						<div class="col-sm-6 col-md-8 col-lg-6">
							${user.prenom}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-md-4 col-lg-4">
							<label>Email :</label>
						</div>
						<div class="col-sm-6 col-md-8 col-lg-6">
							${user.mail}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-md-4 col-lg-4">
							<label>Téléphone :</label>
						</div>
						<div class="col-sm-6 col-md-8 col-lg-6">
							${user.telephone}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-md-4 col-lg-4">
							<label>Rue :</label>
						</div>
						<div class="col-sm-6 col-md-8 col-lg-6">
							${user.rue}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-md-4 col-lg-4">
							<label>Code Postal :</label>
						</div>
						<div class="col-sm-6 col-md-8 col-lg-6">
							${user.codePostal}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-md-4 col-lg-4">
							<label>Ville :</label>
						</div>
						<div class="col-sm-6 col-md-8 col-lg-6">
							${user.ville}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-md-6 col-lg-4">
							<a class="btn btn-lg" href="monProfil"><button type="button" class="btn btn-primary bleu">Modifier</button></a>
						</div>
						<div class="col-sm-6 col-md-6 col-lg-6">
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