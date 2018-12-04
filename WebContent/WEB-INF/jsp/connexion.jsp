<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		   <div class="form-group" style="">
		      <label>Identifiant :</label>
		      <input type="text" class="form-control">
		   </div>
		   <form style="">
		      <div class="form-group">
			   <form style="">
				   <div class="form-group"><label>Mot de passe :</label>
				   	<input type="text" class="form-control">
				   </div>
			   </form>
			   </div>
		   </form>
			<p><button type="button" class="btn btn-primary" style="">Connexion</button>
			   <label class="checkbox" style="position: static; display: inline-block; left: auto; right: auto; float: right;">
			   <input type="checkbox">&nbsp;Se souvenir de moi
					<h6 style="top: auto; color: rgb(0, 128, 255); font-weight: 400; text-align: center; text-decoration-line: underline; margin-top: auto;" contenteditable="true" spellcheckker="false">Mot de passe oublié</h6>
		   </label></p>
		   <a class="btn btn-lg btn-success" href="inscription" role="button">Créer un compte</a>
		</div>
		<div class="row marketing">
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
	<!-- /container -->
</body>
</html>