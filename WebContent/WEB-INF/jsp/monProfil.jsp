<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<head>
    <jsp:include page="header.jsp"/>
</head>

<body>
	<div class="container">
		<div class="header clearfix">
		   <jsp:include page="menu.jsp" />
		</div>
		<div class="jumbotron">
	      	<h1>Mon Profil</h1>
	      	<div class="form-group">
	      		<form action="modifProfil" method="POST">
		        	<div class="row">
		        		<div class="col-sm-12 col-md-6 col-lg-6">
		        			<div class="form-group">
		        				<div class="row">
		        					<div class="col-sm-12 col-md-12 col-lg-12">
		        						<label>Pseudo :</label>
		        					</div>
		        				</div>
		        				<div class="row">
		        					<div class="col-sm-12 col-md-12 col-lg-12">
										<input name="pseudo" value="${user.pseudo}" type="text" class="form-control" required>
									</div>
								</div>
							</div>
							<c:if test= "${not empty erreurs['pseudo']}">
								<p class="alert alert-danger">${erreurs['pseudo']} : ${saisie['pseudo']}</p>
							</c:if>
							<c:if test= "${not empty erreurs['existPseudo']}">
								<p class="alert alert-danger">${erreurs['existPseudo']} : ${saisie['pseudo']}</p>
							</c:if>
		        		</div>
		        		<div class="col-sm-12 col-md-6">
		        			<div class="form-group">
		        				<div class="row">
		        					<div class="col-sm-12 col-md-12 col-lg-12">
		        						<label>Nom :</label>
		        					</div>
		        				</div>
		        				<div class="row">
		        					<div class="col-sm-12 col-md-12 col-lg-12">
										<input name="nom" value="${user.nom}" type="text" class="form-control" required>
									</div>
								</div>
							</div>
							<c:if test= "${not empty erreurs['nom']}">
								<p class="alert alert-danger">${erreurs['nom']}</p>
							</c:if>
		        		</div>
		        	</div>
		        	<div class="row">
		        		<div class="col-sm-12 col-md-6 col-lg-6">
		        			<div class="form-group">
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
			        					<label>Prénom :</label>
			        				</div>
		        				</div>
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
										<input name="prenom" value="${user.prenom}" type="text" class="form-control" required>
									</div>
								</div>
							</div>
							<c:if test= "${not empty erreurs['prenom']}">
								<p class="alert alert-danger">${erreurs['prenom']} : ${saisie['prenom']}</p>
							</c:if>
		        		</div>
		        		<div class="col-sm-12 col-md-6 col-lg-6">
		        			<div class="form-group">
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
		        						<label>Email :</label>
		        					</div>
		        				</div>
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
										<input name="email" value="${user.mail}" type="text" class="form-control" required>
									</div>
								</div>
							</div>
							<c:if test= "${not empty erreurs['email']}">
								<p class="alert alert-danger">${erreurs['email']}</p>
							</c:if>
							<c:if test= "${not empty erreurs['existMail']}">
								<p class="alert alert-danger">${erreurs['existMail']}</p>
							</c:if>
		        		</div>
		        	</div>
		        	<div class="row">
		        		<div class="col-sm-12 col-md-6 col-lg-6">
		        			<div class="form-group">
		        				<div class="row">
		        					<div class="col-sm-12 col-md-12 col-lg-12">
		        						<label>Téléphone :</label>
		        					</div>
		        				</div>
		        				<div class="row">
		        					<div class="col-sm-12 col-md-12 col-lg-12">
										<input name="telephone" value="${user.telephone}" type="text" class="form-control" required>
									</div>
								</div>
							</div>
							<c:if test= "${not empty erreurs['telephone']}">
								<p class="alert alert-danger">${erreurs['telephone']} : ${saisie['telephone']}</p>
							</c:if>
							<c:if test= "${not empty erreurs['existTel']}">
								<p class="alert alert-danger">${erreurs['existTel']} : ${saisie['telephone']}</p>
							</c:if>
		        		</div>
		        		<div class="col-sm-12 col-md-6 col-lg-6">
		        			<div class="form-group">
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
		        						<label>Rue :</label>
		        					</div>
		        				</div>
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
										<input name="rue" value="${user.rue}" type="text" class="form-control" required>
									</div>
								</div>
							</div>
							<c:if test= "${not empty erreurs['rue']}">
								<p class="alert alert-danger">${erreurs['rue']}</p>
							</c:if>
		        		</div>
		        	</div>
		        	<div class="row">
		        		<div class="col-sm-12 col-md-6 col-lg-6">
		        			<div class="form-group">
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
		        						<label>Code Postal :</label>
		        					</div>
		        				</div>
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
										<input name="codePostal" value="${user.codePostal}" type="text" class="form-control" required>
									</div>
								</div>
							</div>
							<c:if test= "${not empty erreurs['codePostal']}">
								<p class="alert alert-danger">${erreurs['codePostal']} : ${saisie['codePostal']}</p>
							</c:if>
		        		</div>
		        		<div class="col-sm-12 col-md-6 col-lg-6">
		        			<div class="form-group">
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
		        						<label>Ville :</label>
		        					</div>
		        				</div>
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
										<input name="ville" value="${user.ville}" type="text" class="form-control" required>
									</div>
								</div>
							</div>
							<c:if test= "${not empty erreurs['ville']}">
								<p class="alert alert-danger">${erreurs['ville']}</p>
							</c:if>
		        		</div>
		        	</div>
		        	<div class="row">
		        		<div class="col-sm-12 col-md-6 col-lg-6">
		        			<div class="form-group">
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
		        						<label>Mot de passe :</label>
		        					</div>
		        				</div>
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
										<input name="mdp" type="password" class="form-control">
									</div>
								</div>
							</div>
							<c:if test= "${not empty erreurs['mdp']}">
								<p class="alert alert-danger">${erreurs['mdp']}</p>
							</c:if>
		        		</div>
		        		<div class="col-sm-12 col-md-6 col-lg-6">
		        			<div class="form-group">
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
		        						<label>Confirmation :</label>
		        					</div>
		        				</div>
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
										<input name="mdpConf" type="password" class="form-control">
									</div>
								</div>
							</div>
							<c:if test= "${not empty erreurs['mdp']}">
								<p class="alert alert-danger">${erreurs['mdp']}</p>
							</c:if>
		        		</div>
		        	</div>
		        	<div class="row">
		        		<div class="col-sm-12 col-md-12 col-lg-12">
		        			<div class="form-group">
		        				<div class="row">
			        				<div class="col-sm-12 col-md-12 col-lg-12">
		        						<label>Crédit : ${user.credit} </label>
		        					</div>
		        				</div>
		        			</div>
		        		</div>
		        	</div>
		        	<div class="row">
		        		<div class="col-sm-12 col-md-4 col-lg-4">
                          <a class="btn btn-lg"><button type="submit" class="btn btn-primary vert">Enregistrer</button></a>
		        		</div>
		        		<div class="col-sm-12 col-md-4 col-lg-4">
                          <a class="btn btn-lg" href="supprProfil"><button type="button" class="btn btn-primary red">Supprimer mon compte</button></a>
		        		</div>
		        		<div class="col-sm-12 col-md-4 col-lg-4">
		                    <a class="btn btn-lg" href="profil"><button type="button" class="btn btn-primary bleu">Retour</button></a>
		        		</div>
		        	</div>
				</form>		
			</div>
		</div>
      <jsp:include page="footer.jsp"/>
   </div>
</body>
</html>