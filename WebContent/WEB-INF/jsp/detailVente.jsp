<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<jsp:include page="header.jsp" /> 
</head>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="header clearfix">
			<jsp:include page="menu.jsp" />
		</div>
		<div class="jumbotron">
			<h1 class="center">Detail vente</h1>
			<c:if test= "${not empty successEnchere}">
				<p class="alert alert-success">${successEnchere}</p>
			</c:if>	
			<form action="detailVente" method="POST">
			<div class="row">
				<div class="col-sm-3">
					
				</div>
				<div class="col-sm-6 ">
					<div class="row">
						<input type="hidden" id="hiddenId" name="hiddenId" value="${vente.noVente}"/>
						<h2 class="center">
							${vente.nomArticle}
						</h2>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<label>Description :<br></label>
						</div>

						<div class="col-sm-4 offset-sm-1">${vente.description}</div>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<label>Meilleure offre :<br></label>
						</div>
						<div class="col-sm-4 offset-sm-1">${vente.prixVente }</div>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<label>Mise à prix :<br></label>
						</div>
						<div class="col-sm-4 offset-sm-1">${vente.miseAPrix}</div>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<label>Fin de la vente :<br></label>
						</div>
						<div class="col-sm-4 offset-sm-1">${vente.dateFinEnchere}</div>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<label>Lieu de retrait :<br></label>
						</div>

						<div class="col-sm-4 offset-sm-1">${vente.retrait.rue}
							${vente.retrait.ville} ${vente.retrait.codePostal}</div>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<label>Vendeur :<br></label>
						</div>
						<div class="col-sm-4 offset-sm-1">${vente.vendeur.pseudo}</div>
					</div>
					<div class="row">
					
						<div class="col-sm-3">
							<label>Proposition :<br></label>
						</div>
						<div class="col-sm-4 offset-sm-1">
							<input type="number" id="proposition" name="proposition" value="${minOffre}" min="${minOffre}"> <br>
							<br>
							<button type="submit" class="btn btn-primary lienBouton blue">Enchérir</button>
						</div>
					
						
					</div>

					<div class="row">
						<div class="col-sm-6">
							<a class="btn btn-lg" href="listeEncheres">
							<button type="button" class="btn btn-primary lienBouton red">Back</button></a>
						</div>
					</div>
				</div>
			</div>
			</form>

		</div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>