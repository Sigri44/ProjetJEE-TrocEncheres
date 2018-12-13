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
					<div class="col-md-11 col-lg-6 offset-md-1 offset-lg-3">
						<div class="row">
							<input type="hidden" id="hiddenId" name="hiddenId" value="${vente.noVente}"/>
							<h2 class="center">
								${vente.nomArticle}
							</h2>
						</div>
						<div class="row">
							<div class="col-sm-12 col-md-4 col-lg-4">
								<label>Description :</label>
							</div>
							<div class="col-sm-12 col-md-8 col-lg-8">${vente.description}</div>
						</div>
						<div class="row">
							<div class="col-sm-4 col-md-4 col-lg-4">
								<label>Meilleure offre :</label>
							</div>
							<div class="col-sm-12 col-md-8 col-lg-8">${vente.prixVente }</div>
						</div>
						<div class="row">
							<div class="col-sm-4 col-md-4 col-lg-4">
								<label>Mise à prix :</label>
							</div>
							<div class="col-sm-12 col-md-8 col-lg-8">${vente.miseAPrix}</div>
						</div>
						<div class="row">
							<div class="col-sm-4 col-md-4 col-lg-4">
								<label>Fin de la vente :</label>
							</div>
							<div class="col-sm-12 col-md-8 col-lg-8">${vente.dateFinEnchere}</div>
						</div>
						<div class="row">
							<div class="col-sm-12 col-md-12 col-lg-12">
								<div class="row">
									<div class="col-sm-4 col-md-4 col-lg-4">
										<label>Lieu de retrait :</label>
									</div>
									<div class="col-sm-12 col-md-8 col-lg-8">
										${vente.retrait.rue}
									</div>
								</div>
							</div>
							<div class="col-sm-12 col-md-8 col-lg-8 offset-md-4 offset-lg-4 alignCPdetail">
								<div class="row">${vente.retrait.codePostal} ${vente.retrait.ville}</div>
							</div>					
						</div>
						<div class="row">
							<div class="col-sm-4 col-md-4 col-lg-4">
								<label>Vendeur :</label>
							</div>
							<div class="col-sm-12 col-md-8 col-lg-8">${vente.vendeur.pseudo}</div>
						</div>
						<div class="row">
							<div class="col-sm-4 col-md-4 col-lg-4">
								<label>Proposition :</label>
							</div>
							<div class="col-sm-12 col-md-8 col-lg-8">
								<input type="number" id="proposition" name="proposition" value="${minOffre}" min="${minOffre}">
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