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
	      	<h1 style="text-align: center">${user.pseudo}</h1>
	      	<div class="form-group" style="text-align: center">
	      		<form>
		        	<div class="row">
						<div class="col-sm-12">
							<div class="form-group" style="display: block;"><label>Pseudo :<br></label>
								${user.pseudo}
							</div>
							<div class="form-group" style="display: block;"><label>Nom :<br></label>
								${user.nom}
							</div>
							<div class="form-group" style="display: block;"><label>Prénom :<br></label>
								${user.prenom}
							</div>
							<div class="form-group" style="display: block;"><label>Email :<br></label>
								${user.mail}
							</div>
							<div class="form-group" style="display: block;"><label>Téléphone :<br></label>
								${user.telephone}
							</div>
							<div class="form-group" style="display: block;"><label>Rue :<br></label>
								${user.rue}
							</div>
							<div class="form-group" style="display: block;"><label>Code Postal :<br></label>
								${user.codePostal}
							</div>
							<div class="form-group" style="display: block;"><label>Ville :<br></label>
								${user.ville}
							</div>
						</div>
					</div>
                  	<div class="row">
                      	<div class="col-sm-6">
                      		<a class="btn btn-lg" href="monProfil"><button type="button" class="btn btn-primary" style="background-color: #0062cc; text-align: left;">Modifier</button></a>
                     	</div>
                     	<div class="col-sm-6">
                           <a class="btn btn-lg" href="listeEncheres"><button type="button" class="btn btn-primary" style="background-color: rgb(255, 0, 0); text-align: left; float: none;">Retour</button></a>
                     	</div>
                  </div>
				</form>
			</div>
		</div>
	    <jsp:include page="footer.jsp"/>
   </div>
</body>
</html>