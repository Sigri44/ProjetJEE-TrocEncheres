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
	      	<h1 style="">Profil de "Pseudo du Geek"</h1>
	      	<div class="form-group">
	      		<form>
		        	<div class="row">
						<div class="col-sm-12">
							<div class="form-group" style="display: block;"><label>Pseudo :<br></label>
								#PseudoDuGeek
							</div>
							<div class="form-group" style="display: block;"><label>Adresse :<br></label>
								#AdresseDuGeek
							</div>
							<div class="form-group" style="display: block;"><label>Téléphone :<br></label>
								#TéléphoneDuGeek
							</div>
						</div>
					</div>
                  	<div class="row">
                     	<div class="col-sm-6">
                           <a class="btn btn-lg" href="listEncheres"><button type="button" class="btn btn-primary" style="background-color: rgb(255, 0, 0); text-align: left; float: none;">Back</button></a>
                     	</div>
                  </div>
				</form>
			</div>
		</div>
	    <jsp:include page="footer.jsp"/>
   </div>
</body>
</html>