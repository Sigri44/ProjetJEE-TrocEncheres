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
	      	<h1 class="center">Créer un compte</h1>
	      	<div class="form-group">
	      		<form action="Inscription" method="post">
		        	<div class="row">
						<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label>Pseudo :<br></label>
							<c:if test= "${not empty saisie['pseudo']}">
								<input name="pseudo" value="${saisie['pseudo']}" required type="text" class="form-control">
							</c:if>
							<c:if test= "${empty saisie['pseudo']}">
								<input name="pseudo" type="text" class="form-control" required tabindex="1" maxlength="30">
							</c:if>
							<c:if test= "${not empty erreurs['pseudo']}">
								<p class="alert alert-danger">${erreurs['pseudo']}</p>
							</c:if>
							<c:if test= "${not empty erreurs['existPseudo']}">
								<p class="alert alert-danger">${erreurs['existPseudo']}</p>
							</c:if>
						</div>
						<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label>Nom :<br></label>
							<c:if test= "${not empty saisie['nom']}">
								<input name="nom" type="text" value="${saisie['nom'] }" class="form-control" required>
							</c:if>
							<c:if test= "${empty saisie['nom']}">
								<input name="nom" type="text" class="form-control" required tabindex="2" maxlength="30">
							</c:if>
							<c:if test= "${not empty erreurs['nom']}">
								<p class="alert alert-danger">${erreurs['nom']}</p>
							</c:if>
						</div>
						<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label>Prénom :<br></label>
							<c:if test= "${not empty saisie['prenom']}">
								<input name="prenom" type="text"  value="${saisie['prenom']}" class="form-control" required>
							</c:if>
							<c:if test= "${empty saisie['prenom']}">
								<input name="prenom" type="text"  class="form-control" required tabindex="3" maxlength="30">
							</c:if>
							<c:if test= "${not empty erreurs['prenom']}">
								<p class="alert alert-danger">${erreurs['prenom']}</p>
							</c:if>
						</div>
						<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label>Email : <br></label>
							<c:if test= "${not empty saisie['email']}">
								<input name="email" type="text" value="${saisie['email'] }" class="form-control" required>
							</c:if>
							<c:if test= "${empty saisie['email']}">
								<input name="email" type="text" class="form-control" required tabindex="4" maxlength="50">
							</c:if>
							<c:if test= "${not empty erreurs['email']}">
								<p class="alert alert-danger">${erreurs['email']}</p>
							</c:if>
							<c:if test= "${not empty erreurs['existMail']}">
								<p class="alert alert-danger">${erreurs['existMail']}</p>
							</c:if>
						</div>
						<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label>Téléphone :<br></label>
							<c:if test= "${not empty saisie['telephone']}">
								<input name="telephone" type="text" value="${saisie['telephone'] }" class="form-control" required>
							</c:if>
							<c:if test= "${empty saisie['telephone']}">
								<input name="telephone" type="text"  class="form-control" required tabindex="5" maxlength="15">
							</c:if>
							<c:if test= "${not empty erreurs['telephone']}">
								<p class="alert alert-danger">${erreurs['telephone']}</p>
							</c:if>
							<c:if test= "${not empty erreurs['existTel']}">
								<p class="alert alert-danger">${erreurs['existTel']}</p>
							</c:if>
						</div>
						<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label>Code Postal :<br></label>
							<c:if test= "${not empty saisie['telephone']}">
								<input name="codePostal" type="text" value="${saisie['codePostal']}" class="form-control" required>
							</c:if>
							<c:if test= "${empty saisie['telephone']}">
								<input name="codePostal" type="text" class="form-control" required  tabindex="7" maxlength="10">
							</c:if>
							<c:if test= "${not empty erreurs['codePostal']}">
								<p class="alert alert-danger">${erreurs['codePostal']}</p>
							</c:if>
						</div>
								
						<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label>Rue :<br></label>
							<c:if test= "${not empty saisie['rue']}">
								<input name="rue" type="text" value="${saisie['rue'] }" class="form-control" required>
							</c:if>
							<c:if test= "${empty saisie['rue']}">
								<input name="rue" type="text"  class="form-control" required tabindex="6" maxlength="30">
							</c:if>
							<c:if test= "${not empty erreurs['rue']}">
								<p class="alert alert-danger">${erreurs['rue']}</p>
							</c:if>
						</div>
						<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label>Ville :<br></label>
							<c:if test= "${not empty saisie['ville']}">
								<input name="ville" value="${saisie['ville']}" type="text"  class="form-control" required>
							</c:if>
							<c:if test= "${empty saisie['ville']}">
								<input name="ville" type="text"  class="form-control" required tabindex="8" maxlength="30">
							</c:if>
							<c:if test= "${not empty erreurs['ville']}">
								<p class="alert alert-danger">${erreurs['ville']}</p>
							</c:if>
						</div>
						<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label>Mot de passe :<br></label>
							<input name="mdp" type="password" class="form-control" required tabindex="9" maxlength="30">
							<c:if test= "${not empty erreurs['mdp']}">
								<p class="alert alert-danger">${erreurs['mdp']}</p>
							</c:if>
						</div>		
						<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label>Confirmation mot de passe :<br></label>
							<input name="mdpConf" type="password" class="form-control" required tabindex="10" maxlength="30">
							<c:if test= "${not empty erreurs['mdp']}">
								<p class="alert alert-danger">${erreurs['mdp']}</p>
							</c:if>
						</div>
						<a class="btn btn-lg" href="#"><button type="submit" class="btn lienBouton bleu">Créer</button></a>
						<a class="btn btn-lg" href="connexion"><button type="button" class="btn lienBouton red">Annuler</button></a>
					</div>
				</form>
			</div>
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>