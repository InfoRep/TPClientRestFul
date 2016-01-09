<%@ page import="client.model.Client" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page session="false" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:layout>
	<jsp:attribute name="pageTitle">Saisie d'un client</jsp:attribute>	

    <jsp:attribute name="title">
    	Formulaire <c:if test="${type == 'ajout'}">d'ajout</c:if><c:if test="${type == 'modif'}">de modification</c:if> d'un client
    </jsp:attribute>
    
    <jsp:attribute name="javascripts">
    	<c:if test="${type == 'modif'}">
    		<script type="text/javascript">
   				
    		</script>
    	</c:if>
    </jsp:attribute>
    
    <jsp:body>
	  	<form method="post" action="${pageContext.request.contextPath}/client/persist" class="form-horizontal" id="form">
	  		<input type="hidden" name="type" value="${type}"  id="type"/>
	  		
	  		<c:if test="${type == 'modif'}">
	  		<div class="form-group">
	  			<label for="idCli" class="control-label col-md-2">Numéro</label>
	  			<div class="col-md-1">
	  				<input class="form-control" type="number" name="num" value="${client.num}" id="idCli" required readonly />
	  			</div>
	  		</div>
	  		</c:if> 
	  		
	  		<div class="form-group">
	  			<label for="nomCli" class="control-label col-md-2">Nom</label>
	  			<div class="col-md-2">
	  				<input type="text" class="form-control" placeholder="Nom" name="nom" value="${client.nom}" id="nomCli" required />
	  			</div>
	  		</div>
	  		
	  		<div class="form-group">
	  			<label for="adrRue" class="control-label col-md-2">Adresse</label>
	  			<div class="col-md-2">
	  				<input type="text" class="form-control" placeholder="Adresse" name="adrRue" value="${client.adrRue}" id="adrRue" required />
	  			</div>
	  		</div>
	  		
	  		<div class="form-group">
	  			<label for="cp" class="control-label col-md-2">Code postal</label>
	  			<div class="col-md-2">
	  				<input type="text" class="form-control" placeholder="Code postal" name="cp" value="${client.cp}" id="cp" required />
	  			</div>
	  		</div>

	  		<div class="form-group">
	  			<label for="ville" class="control-label col-md-2">Ville</label>
	  			<div class="col-md-2">
	  				<input type="text" class="form-control" placeholder="Ville" name="ville" value="${client.ville}" id="ville" required />
	  			</div>
	  		</div>

	  		<div class="form-group">
	  			<label for="piece" class="control-label col-md-2">Type de piece d'identitee</label>
	  			<div class="col-md-2">
	  				<select name="piece" id="piece" class="form-control">
	  					<option value="Cl" <c:if test="${client.piece == 'Cl'}">selected</c:if>>Cl</option>
	  					<option value="PS" <c:if test="${client.piece == 'PS'}">selected</c:if>>PS</option>
	  					<option value="PC" <c:if test="${client.piece == 'PC'}">selected</c:if>>PC</option>
	  				</select>
	  			</div>
	  		</div>
	  		
	  		<div class="form-group">
	  			<label for="numPiece" class="control-label col-md-2">Numero piece</label>
	  			<div class="col-md-2">
	  				<input type="text" class="form-control" placeholder="Numero piece" name="numPiece" value="${client.numPiece}" id="numPiece" required />
	  			</div>
	  		</div>
	  		
	  		<div class="form-group text-right">
  				<c:if test="${type == 'ajout'}">
				    <input type="submit" class="btn btn-primary" value="Ajouter" />
  					<input type="reset" class="btn btn-warning" value="Reset" />
				</c:if>
		        <c:if test="${type == 'modif'}">
				   <input type="submit" class="btn btn-primary" value="Modifier" />
				</c:if>
				
				<a href="../list" class="btn btn-default">Retour</a>
	  		</div>
	  	</form>
    </jsp:body>
</t:layout>
