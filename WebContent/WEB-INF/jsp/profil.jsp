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
	      	<h1 style="">Pseudo du Geek</h1>
	      	<div class="form-group">
	      		<form>
		        	<div class="row">
						<div class="col-sm-12">
							<div class="form-group" style="display: block;"><label>Pseudo :<br></label>
								#PseudoDuGeek
							</div>
							<div class="form-group" style="display: block;"><label>Nom :<br></label>
								#NomDuGeek
							</div>
							<div class="form-group" style="display: block;"><label>Prénom :<br></label>
								#PrénomDuGeek
							</div>
							<div class="form-group" style="display: block;"><label>Email :<br></label>
								#EmailDuGeek
							</div>
							<div class="form-group" style="display: block;"><label>Téléphone :<br></label>
								#TéléphoneDuGeek
							</div>
							<div class="form-group" style="display: block;"><label>Rue :<br></label>
								#RueDuGeek
							</div>
							<div class="form-group" style="display: block;"><label>Code Postal :<br></label>
								#CodePostalDuGeek
							</div>
							<div class="form-group" style="display: block;"><label>Ville :<br></label>
								#VilleDuGeek
							</div>
						</div>
					</div>
                  	<div class="row">
                      	<div class="col-sm-6">
                      		<a class="btn btn-lg" href="monProfil"><button type="button" class="btn btn-primary" style="background-color: #0062cc; text-align: left;">Modifier</button></a>
                     	</div>
                     	<div class="col-sm-6">
                           <a class="btn btn-lg" href="listEncheres"><button type="button" class="btn btn-primary" style="background-color: rgb(255, 0, 0); text-align: left; float: none;">Retour</button></a>
                     	</div>
                  </div>
				</form>
			</div>
		</div>
	    <jsp:include page="footer.jsp"/>
   </div>
</body>
</html>