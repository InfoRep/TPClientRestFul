<%@ page import="client.model.Client" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page session="false" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:layout>
	<jsp:attribute name="pageTitle">Saisie d'une activité</jsp:attribute>	

    <jsp:attribute name="title">
    	Formulaire d'ajout d'une activité
    </jsp:attribute>
     
    <jsp:body>
	  	<form method="post" action="${pageContext.request.contextPath}/activity/persist" class="form-horizontal" id="form">
	  			  		
	  		<div class="form-group">
	  			<label for="date" class="control-label col-md-2">Date</label>
	  			<div class="col-md-2">
	  				<input type="text" class="form-control date" name="date" value="" id="date" required />
	  			</div>
	  		</div>

	  		<div class="form-group">
	  			<label for="nb" class="control-label col-md-2">Nombre d'unite</label>
	  			<div class="col-md-1">
	  				<input class="form-control" type="number" name="nbUnite" id="nb" min="0" required  />
	  			</div>
	  		</div>

	  		<div class="form-group">
	  			<label for="sport" class="control-label col-md-2">Sport</label>
	  			<div class="col-md-2">
	  				<select name="sport" id="sport" class="form-control">
  					<c:forEach  items="${sports}"  var="item" >
  						<option value="${item.code}">${item.libelle}</option>
  					</c:forEach>
	  				</select>
	  			</div>
	  		</div>

	  		<div class="form-group">
	  			<label for="sejour" class="control-label col-md-2">Sejour</label>
	  			<div class="col-md-2">
	  				<select name="sejour" id="sejour" class="form-control">
  					<c:forEach  items="${sejours}"  var="item" >
  						<option value="${item.num}">${item.num}) du <fmt:formatDate pattern="yyyy-MM-dd"  value="${item.dateDeb}" /> au <fmt:formatDate pattern="yyyy-MM-dd"  value="${item.dateFin}" /></option>
  					</c:forEach>
	  				</select>
	  			</div>
	  		</div>	

	  		<div class="form-group text-right">
			    <input type="submit" class="btn btn-primary" value="Ajouter" />
					<input type="reset" class="btn btn-warning" value="Reset" />
				<a href="../sejour/list" class="btn btn-default">Retour</a>
	  		</div>
	  	</form>
    </jsp:body>
</t:layout>
