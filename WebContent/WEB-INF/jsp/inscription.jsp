<!DOCTYPE html>
<head>
    <jsp:include page="header.jsp"/>
</head>

  <body>
   <div class="container">
      <div class="header clearfix">
         <h3 class="text-muted">TrocEncheres.org</h3>
      </div>
      <div class="jumbotron"><h1 style="">Créer un compte</h1>
         <div class="row" style="">
	         <form style="">
	            <div class="col-sm-6">					
					   <div class="form-group" style="display: block;"><label>Pseudo :<br></label>
					      <input required type="text" class="form-control" style="position: static; display: block;">
					   </div>
					   <div class="form-group" style="display: block;"><label>Prénom :<br></label>
					   	<input type="text" class="form-control" style="position: static; display: block;" required>
					   </div>
					   <div class="form-group" style="display: block;"><label>Téléphone :<br></label>
					      <input type="text" class="form-control" style="position: static; display: block;" required>
					   </div>
					   <div class="form-group" style="display: block;"><label>Code Postal :<br></label>
					      <input type="text" class="form-control" style="position: static; display: block;" required>
					   </div>
					   <div class="form-group" style="display: block;"><label>Mot de passe :<br></label>
					      <input type="password" class="form-control" style="position: static; display: block;" required>
					   </div>				
	           		<a class="btn btn-lg" href="#"><button type="submit" class="btn btn-primary" style="background-color: rgb(255, 0, 0); text-align: left;">Créer</button></a>
	            </div>
	            <div class="col-sm-6">				
						<div class="form-group" style="display: block;"><label>Nom :<br></label>
							<input type="text" class="form-control" style="position: static; display: block;" required>
						</div>
						<div class="form-group" style="display: block;"><label>Email :<br></label>
							<input type="text" class="form-control" style="position: static; display: block;" required>
						</div>
						<div class="form-group" style="display: block;"><label>Rue :<br></label>
							<input type="text" class="form-control" style="position: static; display: block;" required>
						</div>
						<div class="form-group" style="display: block;"><label>Ville :<br></label>
							<input type="text" class="form-control" style="position: static; display: block;" required>
						</div>
						<div class="form-group" style="display: block;"><label>Confirmation :<br></label>
							<input type="password" class="form-control" style="position: static; display: block;" required>
					   </div>
					
	            	<a class="btn btn-lg" href="connexion" role="button" style="background-color: rgb(255, 0, 0); text-align: right;">Annuler</a>
	            </div>
            </form>
         </div>
         <p style="position: fixed;"></p>
      </div>
      <div class="row marketing">
      </div>
      <jsp:include page="footer.jsp"/>
   </div>
   <!-- /container -->
</body>
</html>