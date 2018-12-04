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
	      	<h1 style="">Créer un compte</h1>
	      	<div class="form-group">
	      		<form action="Inscription" method="post">
		        	<div class="row">
						<div class="col-sm-6">					
							<div class="form-group" style="display: block;"><label>Pseudo :<br></label>
								<input name="pseudo" required type="text" class="form-control" style="position: static; display: block;">
							</div>
							<div class="form-group" style="display: block;"><label>Prénom :<br></label>
								<input name="prenom" type="text" class="form-control" style="position: static; display: block;" required>
							</div>
							<div class="form-group" style="display: block;"><label>Téléphone :<br></label>
								<input name="telephone" type="text" class="form-control" style="position: static; display: block;" required>
							</div>
							<div class="form-group" style="display: block;"><label>Code Postal :<br></label>
								<input name="codePostal" type="text" class="form-control" style="position: static; display: block;" required>
							</div>
							<div class="form-group" style="display: block;"><label>Mot de passe :<br></label>
								<input name="mdp" type="password" class="form-control" style="position: static; display: block;" required>
							</div>				
							<a class="btn btn-lg" href="#"><button type="submit" class="btn btn-primary" style="background-color: #0062cc; text-align: left;">Créer</button></a>
						</div>
						<div class="col-sm-6">				
							<div class="form-group" style="display: block;"><label>Nom :<br></label>
								<input name="nom" type="text" class="form-control" style="position: static; display: block;" required>
							</div>
							<div class="form-group" style="display: block;"><label>Email :<br></label>
								<input name="email" type="text" class="form-control" style="position: static; display: block;" required>
								
							</div>
							<div class="form-group" style="display: block;"><label>Rue :<br></label>
								<input name="rue" type="text" class="form-control" style="position: static; display: block;" required>
							</div>
							<div class="form-group" style="display: block;"><label>Ville :<br></label>
								<input name="ville" type="text" class="form-control" style="position: static; display: block;" required>
							</div>
							<div class="form-group" style="display: block;"><label>Confirmation :<br></label>
								<input name="mdpConf" type="password" class="form-control" style="position: static; display: block;" required>
							</div>
							<a class="btn btn-lg" href="connexion"><button type="button" class="btn btn-primary" style="background-color: rgb(255, 0, 0); text-align: left;">Annuler</button></a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>