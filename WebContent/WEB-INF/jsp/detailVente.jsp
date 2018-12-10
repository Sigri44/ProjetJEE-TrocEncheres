<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<jsp:include page="header.jsp" />
</head>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="header clearfix">
			<jsp:include page="menu.jsp" />
		</div>
		<div class="jumbotron">
			<h1 class="center">Detail vente</h1>
			<div class="row">
				<div class="col-sm-6 offset-sm-4">
					<div class="row">
						<div class="col-sm-4 offset-sm-1">${vente.nomArticle}</div>
					</div>
					<div class="row">
						<div class="col-sm-4 offset-sm-1">${vente.description}</div>
					</div>
					
					<div class="row">
						<div class="col-sm-6">
							<a class="btn btn-lg" href="listeEncheres"><button
									type="button" class="btn btn-primary lienBouton red">Back</button></a>
						</div>
					</div>
				</div>
			</div>

		</div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>