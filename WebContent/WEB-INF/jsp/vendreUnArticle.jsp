<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<head>
    <jsp:include page="header.jsp"/>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js" type="text/javascript"></script>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js" type="text/javascript"></script>
    <script type="text/javascript" async>
		function readURL(input) {	
			$("#imageVente").show();			
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
			<jsp:include page="menu.jsp" />
		</div>
		<div class="jumbotron">
			<div class="row">
				<div class="col-sm-3">
					<div id="imageVente">
						<img id="blah" src="#" alt="image" />
					</div>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-6">
					<form enctype="multipart/form-data" action="vendreUnArticle" method="POST">
					<h1 class="center">Nouvelle vente</h1>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Article :<br></label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<input type="text" name="nomArticle" class="form-control" required>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Categorie : </label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<select name="categorie">
									<c:forEach items="${categories}" var="categorie">
										<option value="${categorie.noCategorie}">${categorie.libelle}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Description :<br></label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<textarea name="description" class="form-control noResize" required></textarea>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Photo de l'article</label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<input type="file" onchange="readURL(this);" name="file" id="file" accept="image/*"/>	
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Mise à prix : </label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<input value="1" type="number" id="prix" name="prix" min="1" required >	
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Fin de l'enchère :</label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<input type="date" name="finEnchere" onkeydown="return false" id="start" name="trip-start" value="${dateJour}" min="${dateJour}" required>
							</div>
						</div>
						<fieldset>
							<legend><h3>Retrait</h3></legend>
							<!-- TODO soit tous les champ de retrait vides, soit tous remplis -->
							<div class="form-group row">
								<label class="col-sm-4 col-md-4 col-lg-5">Utiliser mon adresse :</label>
								<div class="col-sm-6 col-md-6 col-lg-6">
									<input type="checkbox" id="boxRetrait" name="boxRetrait" checked >
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-3">Rue :</label>
								<div class="col-sm-9 col-md-9 col-lg-9">
									<input type="text" value="${utilisateur.rue}" name="rue" id="rue" class="form-control" disabled>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-3">Code postal :</label>
								<div class="col-sm-9 col-md-9 col-lg-9">
									<input type="text" value="${utilisateur.codePostal}" name="codePostal" id="codePostal" class="form-control" disabled>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-3">Ville :</label>
								<div class="col-sm-9 col-md-9 col-lg-9">
									<input type="text" value="${utilisateur.ville}" name="ville" id="ville" class="form-control" disabled>
								</div>
							</div>
							<input type="hidden" id="rueH" value="${utilisateur.rue}" />
							<input type="hidden" id="codePostalH" value="${utilisateur.codePostal}" />
							<input type="hidden" id="villeH" value="${utilisateur.ville}" />
							<script>
							$('#boxRetrait').change(function() {
								if ($(this).is(':checked')) {
									$('#rue').val(document.getElementById('rueH').value);
									$('#codePostal').val(document.getElementById('codePostalH').value);
									$('#ville').val(document.getElementById('villeH').value);
									$('#rue').attr('disabled',this.checked);							    
								    $('#codePostal').attr('disabled',this.checked);							    
								    $('#ville').attr('disabled',this.checked);
								    $("#rue").attr("required", false);
									$("#codePostal").attr("required", false);
									$("#ville").attr("required", false);
								}
								if ($(this).is(':not(:checked)')){
									$('#rue').val('');
									$('#codePostal').val('');
									$('#ville').val('');
									$("#rue").prop('disabled', false);
									$("#codePostal").prop('disabled', false);
									$("#ville").prop('disabled', false);
									$('#rue').attr('placeholder','Entrez la rue');
									$('#codePostal').attr('placeholder','Entrez le code postal');
									$('#ville').attr('placeholder','Entrez la ville');
									$("#rue").attr("required", true);
									$("#codePostal").attr("required", true);
									$("#ville").attr("required", true);
								}
							});
							</script>
						</fieldset>
						<div class="row">
							<div class="col-sm-4">
								<a class="btn btn-lg" ><button type="submit" class="btn lienBouton vert">Publier</button></a>
							</div>
							<div class="col-sm-4">
								<a class="btn btn-lg" href="#"><button type="submit" class="btn lienBouton bleu">Enregistrer</button></a>
							</div>
							<div class="col-sm-4">
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