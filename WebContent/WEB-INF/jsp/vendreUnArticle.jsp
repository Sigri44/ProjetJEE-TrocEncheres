<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<head>
	<link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js" type="text/javascript"></script>
	<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js" type="text/javascript"></script>
    <jsp:include page="header.jsp"/>
    <script type="text/javascript" async>
		function readURL(input) {
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();
		
		        reader.onload = function (e) {
		            $('#blah').attr('src', e.target.result);
		        };
		        
		        reader.readAsDataURL(input.files[0]);
		    }
		}
	</script>
</head>

<body>
	<div class="container">
		<div class="header clearfix">
			<h3 class="text-muted">TrocEncheres.org</h3>
		</div>
		<div class="jumbotron">
			<div class="row">
				<div class="col-sm-3">
					<div class="row" >
						<div id="imageVente">
							<img id="blah" src="#" alt="imageVente" />
						</div>						
					</div>
				</div>
				<div class="col-sm-6">
					<form action="vendreUnArticle">
						<h1 class="center">Nouvelle vente</h1>
						<div class="form-group">
							<label>Article :<br></label>
							<input type="text" class="form-control">
						</div>
						<div class="form-group">
							<label>Description :<br></label>
							<textarea class="form-control noResize"></textarea>
						</div>
						<label>Photo de l'article</label>
						<input type="file" onchange="readURL(this);" name="file" id="file" accept="image/*"/>
						<label>Mise à prix : </label>
							<input value="1" type="number" name="price" min="1" >							
						<div class="form-group">
							<label>Fin de l'enchère :</label>
							<input type="date" onkeydown="return false" id="start" name="trip-start" value="${dateJour}" min="${dateJour}" >
						</div>
						<h3>Retrait</h3>
						<div class="col-sm-12">
							<div class="form-group">
								<label>Rue :<br></label>
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<label>Code postal :<br></label>
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<label>Ville :<br></label>
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="row">
							<div class="col-sm-3">
								<a class="btn btn-lg" href="#"><button type="submit" class="btn lienBouton vert">Publier</button></a>
							</div>
							<div class="col-sm-3">
								<a class="btn btn-lg" href="#"><button type="submit" class="btn lienBouton bleu">Enregistrer</button></a>
							</div>
							<div class="col-sm-3">
								<a class="btn btn-lg" href="listeEncheres"><button type="button" class="btn lienBouton red">Annuler</button></a>
							</div>
						</div>
					</form>
				</div>
				
			</div>
		</div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>