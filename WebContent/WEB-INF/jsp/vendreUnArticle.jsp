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
		<div class="jumbotron">
			<div class="row">
				<div class="col-sm-3">
					<div class="row">
						<img src="https://bootsnipp.com/bootstrap-builder/libs/builder/icons/image.svg" height="128" width="128" style="" />
					</div>
				</div>
				<div class="col-sm-6">
					<form action="vendreUnArticle">
						<div class="row">
							<h1 style="">Nouvelle vente</h1>
						</div>
						<div class="row">
							<div class="form-group">
								<label>Article :<br></label>
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label>Description :<br></label>
								<textarea class="form-control"></textarea>
							</div>
						</div>
						<div class="row">
							<button type="button" class="btn btn-primary" style="">UPLOADER</button>
						</div>
						<div class="row">
							<label>Mise à prix :</label>
							<select class="form-control">
								<option value="value1">Text 1</option>
								<option value="value2">Text 2</option>
							</select>
						</div>
						<div class="row">
							<div class="form-group">
								<label>Fin de l'enchère :</label>
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="row">
							<h3>Retrait</h3>
							<div class="row">
									<div class="form-group">
									<label>Rue :<br></label>
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="row">
									<div class="form-group">
									<label>Code postal :<br></label>
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="row">
									<div class="form-group">
									<label>Ville :<br></label>
									<input type="text" class="form-control">
								</div>
							</div>
							
						</div>
						<div class="row">
							<div class="col-sm-3">
								<a class="btn btn-lg" href="#"><button type="submit" class="btn btn-primary" style="background-color: #0062cc; text-align: left; float: none;">Publier</button></a>
							</div>
							<div class="col-sm-3">
								<a class="btn btn-lg" href="#"><button type="submit" class="btn btn-primary" style="background-color: rgb(0, 128, 255); text-align: left; float: none;">Enregistrer</button></a>
							</div>
							<div class="col-sm-3">
								<a class="btn btn-lg" href="listeEncheres"><button type="button" class="btn btn-primary" style="background-color: rgb(255,0,0); text-align: left; float: none;">Annuler</button></a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>