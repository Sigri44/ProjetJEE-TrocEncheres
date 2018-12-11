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
		<div class="jumbotron col-sm-12">
		<c:if test= "${not empty inscription}">
			<p class="alert alert-success">${inscription}</p>
		</c:if>		
		<c:if test= "${not empty suppressionCompte}">
			<p class="alert alert-success">${suppressionCompte}</p>
		</c:if>	
			<div class="row">
	        	<div class="col-sm-3"></div>
				<form action="connexion" method="post" class="col-sm-6">
					<div class="form-group">
					   <label>Identifiant :</label>	
					   <c:if test= "${not empty erreurs['wrongPass']}">
							<c:set var="identifiant" value="${saisie['identifiant']}"/>
						</c:if>		
						<c:if test= "${empty erreurs['wrongPass']}">
							<c:set var="identifiant" value=""/>
						</c:if>					
						<input name="identifiant" value="${identifiant}" type="text" class="form-control" maxlength="50">									   
					   <c:if test= "${not empty erreurs['notExistIdentifiant']}">
							<p class="alert alert-danger">${erreurs['notExistIdentifiant']}</p>
						</c:if>
					</div>
					<div class="form-group">
						<label>Mot de passe :</label>
						<input name="password" type="password" class="form-control" maxlength="30">
						<c:if test= "${not empty erreurs['wrongPass']}">
							<p class="alert alert-danger">${erreurs['wrongPass']}</p>
						</c:if>
					</div>
					<div class="row">
						<div class="col-sm-3 col-md-5 offset-sm-1 offset-md-6 offset-lg-0">
							<a class="btn btn-lg" href="listeEncheres">
								<button type="submit" class="btn vert lienBouton fatBoutonHalf">Connexion</button>
							</a>
						</div>
						<div class="col-sm-3 col-md-7 offset-md-7 offset-lg-0">
							<div class="row">
								<div class="col-sm-12 col-md-12">
									<label class="checkbox">
										<input type="checkbox">&nbsp;Se souvenir de moi
									</label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 col-md-12">
									<a class="mdpOublie link" href="#">Mot de passe oublié</a>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="row">
				<div class="col-sm-12 offset-sm-3">
					<a class="btn btn-lg" href="inscription">
						<button type="button" class="btn bleu lienBouton fatBoutonFull">Créer un compte</button>
					</a>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>