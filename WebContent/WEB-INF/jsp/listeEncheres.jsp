<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
	<jsp:include page="header.jsp" />
</head>
<body>
	<div class="container">
		<div class="header clearfix">
			<jsp:include page="menu.jsp" />
		</div>
		<div class="jumbotron">
			<c:if test="${not empty publication}">
				<p class="alert alert-success">${publication}</p>
			</c:if>
			<div class="jumbotron">
				<form action="recherche" method="POST" class="col-sm-12 col-md-12 col-lg-12">
					<div class="row">
						<div class="form-group col-sm-12 col-md-5 col-lg-4">
							<h4>Filtres :</h4>
							<div class="row">
								<div class="col-sm-12 col-md-12 col-lg-12">
									<label class="checkbox">
										<input type="checkbox" name="mySales">&nbsp;Mes ventes
									</label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 col-md-12 col-lg-12">
									<label class="checkbox">
										<input type="checkbox" name="ongoingAuctions">&nbsp;Mes enchères en cours
									</label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 col-md-12 col-lg-12">
									<label class="checkbox">
										<input type="checkbox" name="myPurchases">&nbsp;Mes acquisitions
									</label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 col-md-12 col-lg-12">
									<label class="checkbox">
										<input type="checkbox" name="otherAuctions">&nbsp;Autres enchères
									</label>
								</div>
							</div>
						</div>
						<div class="col-sm-12 col-md-7 col-lg-5">
							<div class="row">
								<div class="form-group col-sm-12 col-md-12 col-lg-12">
									<input class="form-control form-control-sm ml-3 w-90" name="nomArticle" type="text" placeholder="Le nom de l'article contient" aria-label="Search">
									<div class="form-inline">
										<i class="fa fa-search" aria-hidden="true"></i>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 col-md-12 col-lg-4">
									<label>Catégories :</label>
								</div>
								<div class="form-group col-sm-12 col-md-12 col-lg-12">
									<select class="form-control">
										<option value="value1">Toutes</option>
										<c:forEach items="${toutesCategorie}" var="cat">
											<option value="${cat.libelle}">${cat.libelle}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
					</div>
					<div class="row">
						<div class=" col-sm-12 col-md-12 col-lg-12">
							<a class="btn btn-lg" href="recherche">
								<button type="submit" class="btn btn-primary bleu fatBoutonHalf">Recherche</button>
							</a>
						</div>
					</div>
				</form>
				<div class="row">
					<c:forEach items="${toutesVentes}" var="vente">
						<div class="col-sm-12 col-md-12 col-lg-6">
							<%--
							<div class="card flex-md-row mb-4 box-shadow h-md-250">
							--%>
							<div class="card flex-md-row mb-4 col-sm-12 col-md-12">
								<img class="card-img-left flex-auto mt-4 col-sm-12 col-md-6 col-lg-5 thumbnail" data-src="holder.js/200x250?theme=thumb" alt="Thumbnail [200x250]" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22200%22%20height%3D%22250%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20200%20250%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_167832c802b%20text%20%7B%20fill%3A%23eceeef%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A13pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_167832c802b%22%3E%3Crect%20width%3D%22200%22%20height%3D%22250%22%20fill%3D%22%2355595c%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%2256.203125%22%20y%3D%22131%22%3EThumbnail%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" data-holder-rendered="true">
								<%--
								<img class="card-img-left flex-auto d-none d-lg-block thumbnail" data-src="holder.js/250x250?theme=thumb" alt="Thumbnail [250x250]" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22250%22%20height%3D%22250%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20200%20250%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_167832c802b%20text%20%7B%20fill%3A%23eceeef%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A13pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_167832c802b%22%3E%3Crect%20width%3D%22200%22%20height%3D%22250%22%20fill%3D%22%2355595c%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%2256.203125%22%20y%3D%22131%22%3EThumbnail%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" data-holder-rendered="true">
								<div class="card-body d-flex flex-column align-items-start">
								--%>
								
								<div class="card-body col-sm-12 col-md-6 col-lg-7">
									<a href="detailVente?noVente=${vente.noVente}">
										<strong class="d-inline-block mb-2 text-primary">
											${vente.nomArticle}
										</strong>
									</a>
									<p>Fin de l'enchère : ${vente.dateFinEnchere}</p>
									<p>Retrait : ${vente.retrait.rue} ${vente.retrait.ville}</p>
									<p>Prix : ${vente.miseAPrix}</p>
									<p>
										Vendeur :
										<a href="detailVendeur?pseudo=${vente.vendeur.pseudo}">${vente.vendeur.pseudo}</a>
									</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>