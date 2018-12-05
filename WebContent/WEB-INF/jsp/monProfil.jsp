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
	      	<h1 style="">Mon Profil</h1>
	      	<div class="form-group">
	      		<form>
		        	<div class="row">
						<div class="col-sm-6">					
							<div class="form-group" style="display: block;"><label>Pseudo :<br></label>
								<input name="pseudo" value="${user.pseudo}" required type="text" class="form-control" style="position: static; display: block;">
							</div>
							<div class="form-group" style="display: block;"><label>Prénom :<br></label>
								<input name="prenom" value="${user.prenom}" type="text" class="form-control" style="position: static; display: block;" required>
							</div>
							<div class="form-group" style="display: block;"><label>Téléphone :<br></label>
								<input name="telephone" value="${user.telephone}" type="text" class="form-control" style="position: static; display: block;" required>
							</div>
							<div class="form-group" style="display: block;"><label>Code Postal :<br></label>
								<input name="codePostal" value="${user.codePostal}" type="text" class="form-control" style="position: static; display: block;" required>
							</div>
							<div class="form-group" style="display: block;"><label>Mot de passe :<br></label>
								<input name="mdp" type="password" class="form-control" style="position: static; display: block;" >
							</div>
                          	<div class="form-group" style="display: block;"><label>Crédit : ${user.credit} </label></div>
						</div>
						<div class="col-sm-6">				
							<div class="form-group" style="display: block;"><label>Nom :<br></label>
								<input name="nom" value="${user.nom}" type="text" class="form-control" style="position: static; display: block;" required>
							</div>
							<div class="form-group" style="display: block;"><label>Email :<br></label>
								<input name="email" value="${user.mail}" type="text" class="form-control" style="position: static; display: block;" required>
							</div>
							<div class="form-group" style="display: block;"><label>Rue :<br></label>
								<input name="rue" value="${user.rue}" type="text" class="form-control" style="position: static; display: block;" required>
							</div>
							<div class="form-group" style="display: block;"><label>Ville :<br></label>
								<input name="ville" value="${user.ville}" type="text" class="form-control" style="position: static; display: block;" required>
							</div>
							<div class="form-group" style="display: block;"><label>Confirmation :<br></label>
								<input name="mdpConf" type="password" class="form-control" style="position: static; display: block;" >
							</div>
						</div>
					</div>
                  	<div class="row">
						<div class="col-sm-3">
                          <a class="btn btn-lg" href="#"><button type="submit" class="btn btn-primary" style="background-color: rgb(255, 0, 0); text-align: left;">Enregistrer</button></a>
                     	</div>
                      	<div class="col-sm-6">
                          <a class="btn btn-lg" href="supprProfil"><button type="button" class="btn btn-primary" style="background-color: rgb(255, 0, 0); text-align: left; float: none;">Supprimer mon compte</button></a>
                     	</div>
                     	<div class="col-sm-3">
		                    <a class="btn btn-lg" href="profil"><button type="button" class="btn btn-primary" style="background-color: #0062cc; text-align: left; float: none;">Retour</button></a>
		              	</div>
                  </div>
				</form>
				
			</div>
		</div>
      <jsp:include page="footer.jsp"/>
   </div>
</body>
</html>