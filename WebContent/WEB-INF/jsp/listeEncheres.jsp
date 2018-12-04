<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<head>
	<jsp:include page="header.jsp"/>
</head>
<body>
	<div class="container">
		<div class="header clearfix">
			<jsp:include page="menu.jsp"/>
			<h3 class="text-muted">TrocEncheres.org</h3>
		</div>
		<div class="jumbotron">
			<div class="jumbotron">
				<div class="row">
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<input class="form-control form-control-sm ml-3 w-75" type="text" placeholder="Search" aria-label="Search">
						<form class="form-inline">
							<i class="fa fa-search" aria-hidden="true"></i>
						</form>
						<div class="form-group">
							<label style="display: inline-block;">Catégories :</label>
							<select class="form-control" style="display: inline;">
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
						<a class="btn btn-lg" href="recherche"><button type="button" class="btn btn-primary" style="background-color: #0062cc; text-align: left;">Recherche</button></a>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<h3>col-sm-6</h3>
					</div>
					<div class="col-sm-6">
						<h3>col-sm-6</h3>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>