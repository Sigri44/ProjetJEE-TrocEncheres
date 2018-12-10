<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<head>
	<%--<link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" /> --%>
	<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js" type="text/javascript"></script>
	<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js" type="text/javascript"></script>
    <jsp:include page="header.jsp"/>
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
				<div id="imageVente" >
									<img id="blah" src="#" alt="image" />
								</div>		
				</div>
				<div class="col-sm-6">
					<form action="vendreUnArticle" method="POST">
						<h1 class="center">Nouvelle vente</h1>
						<div class="form-group">
							<label>Article :<br></label>
							<input type="text" name="nomArticle" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Description :<br></label>
							<textarea name="desc" class="form-control noResize" required></textarea>
						</div>
						<div class="form-group">
							<div class="row">
								<label>Photo de l'article</label>
								<input type="file" onchange="readURL(this);" name="file" id="file" accept="image/*"/>
							</div>		
						</div>
						
						<div class="form-group">
							<label>Mise à prix : </label>
							<input value="1" type="number" id="price" name="price" min="1" required >	
						</div>						
						<div class="form-group">
							<label>Fin de l'enchère :</label>
							<input type="date" name="finEnchere" onkeydown="return false" id="start" name="trip-start" value="${dateJour}" min="${dateJour}" required>
						</div>
						<h3>Retrait</h3>
						
						<!-- TODO soit tous les champ de retrait vides, soit tous remplis -->
						<div class="col-sm-12">
							<div class="form-group">
								<label>Utiliser mon adresse :<br></label>
								<input type="checkbox" id="boxRetrait" name="boxRetrait" checked >
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<label>Rue :<br></label>
								<input type="text"  value="${utilisateur.rue}" name="rue" id="rue" class="form-control" disabled>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<label>Code postal :<br></label>
								<input type="text"  value="${utilisateur.codePostal}" name="codePostal" id="codePostal" class="form-control" disabled>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<label>Ville :<br></label>
								<input type="text"  value="${utilisateur.ville}" name="ville" id="ville" class="form-control" disabled>
							</div>
						</div>
						<input type="hidden" id="rueH" value="${utilisateur.rue}"/>
						<input type="hidden" id="codePostalH" value="${utilisateur.codePostal}"/>
						<input type="hidden" id="villeH" value="${utilisateur.ville}"/>
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
						<div class="row">
							<div class="col-sm-3">
								<a class="btn btn-lg" ><button type="submit" class="btn lienBouton vert">Publier</button></a>
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