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
		<div class="jumbotron col-sm-12" style="display: block;">
		<c:if test= "${not empty inscription}">
			<p class="alert alert-success">${inscription}</p>
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
					<a class="btn btn-lg" href="listeEncheres">
						<input type="submit"  class="btn btn-primary" style="background-color: #28a745;" />
					</a>
				</form>
				<div class="col-sm-3"></div>
			</div>
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-3">
					<!-- ancien emplacement du submit -->
					
				</div>
				<div class="col-sm-3">
					<div class="form-group">
					   <label class="checkbox" style="position: static; display: inline-block; left: auto; right: auto; float: right;">
							<input type="checkbox">&nbsp;Se souvenir de moi
						</label>
					</div>
					<div class="form-group">
						<a style="top: auto; color: rgb(0, 128, 255); font-weight: 400; text-align: center; text-decoration-line: underline; margin-top: auto;">Mot de passe oubli�</a>
					</div>
				</div>
				<div class="col-sm-3"></div>
			</div>
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
					<a class="btn btn-lg" href="inscription"><button type="button" class="btn btn-primary" style="background-color: #0062cc; text-align: left; float: none;">Cr�er un compte</button></a>
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>