<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="fr.eni.model.Vente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
			<div class="jumbotron">
				<div class="row">
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<input class="form-control form-control-sm ml-3 w-75" type="text"
							placeholder="Search" aria-label="Search">
						<form class="form-inline">
							<i class="fa fa-search" aria-hidden="true"></i>
						</form>
						<div class="form-group">
							<label style="display: inline-block;">Catégories :</label> <select
								class="form-control" style="display: inline;">
								<option value="value1">Toutes</option>
								<option value="value2">Informatique</option>
								<option value="value3">Démerdes-toi Kéké !</option>
							</select>
						</div>
					</div>
					<div class="col-sm-3"></div>
				</div>
				<div class="row">
					<div class=" col-sm-12">
						<a class="btn btn-lg" href="recherche"><button type="button"
								class="btn btn-primary"
								style="background-color: #0062cc; text-align: left;">Recherche</button></a>
					</div>
				</div>
				<div class="row">
					<c:forEach items="${toutesVentes}" var="vente">
				        <div class="col-md-6">
								<div class="card flex-md-row mb-4 box-shadow h-md-250">
									<div class="card-body d-flex flex-column align-items-start">
										<strong class="d-inline-block mb-2 text-primary">${vente.nomArticle}</strong>
										<p>Fin de l'enchère : ${vente.dateFinEnchere}</p>
										<p>Retrait : ${vente.retrait.rue}
										${vente.retrait.ville}
										</p>
										<p>Prix : ${vente.miseAPrix}</p>
										
										<p>Vendeur : <a  href="detailVendeur?=">${vente.vendeur.pseudo}</a></p>
									
									
									</div>
									<img class="card-img-left flex-auto d-none d-lg-block"
										data-src="holder.js/200x250?theme=thumb"
										alt="Thumbnail [200x250]" style="width: 200px; height: 250px;"
										src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22200%22%20height%3D%22250%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20200%20250%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_167832c802b%20text%20%7B%20fill%3A%23eceeef%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A13pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_167832c802b%22%3E%3Crect%20width%3D%22200%22%20height%3D%22250%22%20fill%3D%22%2355595c%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%2256.203125%22%20y%3D%22131%22%3EThumbnail%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E"
										data-holder-rendered="true">
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