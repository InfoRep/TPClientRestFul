<%@ page import="client.model.Client" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page session="false" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:layout>
	<jsp:attribute name="pageTitle">Saisie d'un séjour</jsp:attribute>	

    <jsp:attribute name="title">
    	Formulaire <c:if test="${type == 'ajout'}">d'ajout</c:if><c:if test="${type == 'modif'}">de modification</c:if> d'un séjour
    </jsp:attribute>
    
    <jsp:attribute name="javascripts">
    	<c:if test="${type == 'modif'}">
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/manageActivities.js"></script>
				<script type="text/javascript">		
					$(document).ready(function() 
					{
						//manage activities
						initManageActivities();
					});	
				</script>
			</script>
		</c:if>
    </jsp:attribute>
    
    <jsp:body>
	  	<form method="post" action="${pageContext.request.contextPath}/sejour/persist" class="form-horizontal" id="form">
	  		<input type="hidden" name="type" value="${type}"  id="type"/>
	  		
	  		<c:if test="${type == 'modif'}">
	  		<div class="form-group">
	  			<label for="idSej" class="control-label col-md-2">Numéro</label>
	  			<div class="col-md-1">
	  				<input class="form-control" type="number" name="num" value="${sejour.num}" id="idSej" required readonly />
	  			</div>
	  		</div>
	  		</c:if> 
	  		
	  		<div class="form-group">
	  			<label for="nbPersonnes" class="control-label col-md-2">Nombre de personnes</label>
	  			<div class="col-md-1">
	  				<input class="form-control" type="number" name="nbPersonnes" value="${sejour.nbPersonnes}" id="nbPersonnes" required />
	  			</div>
	  		</div>
	  		
	  		<div class="form-group">
	  			<label for="dateDeb" class="control-label col-md-2">Date de début</label>
	  			<div class="col-md-2">
	  				<input type="text" class="form-control date" name="dateDeb" value="<fmt:formatDate pattern="yyyy-MM-dd"  value="${sejour.dateDeb}" />" id="dateDeb" required />
	  			</div>
	  		</div>
	  		
	  		<div class="form-group">
	  			<label for="dateFin" class="control-label col-md-2">Date de fin</label>
	  			<div class="col-md-2">
	  				<input type="text" class="form-control date" name="dateFin" value="<fmt:formatDate pattern="yyyy-MM-dd"  value="${sejour.dateFin}" />" id="dateFin" required />
	  			</div>
	  		</div>	  
	  		
	  		<div class="form-group">
	  			<label for="client" class="control-label col-md-2">Client</label>
	  			<div class="col-md-2">
	  				<select name="client" id="client" class="form-control">
  					<c:forEach  items="${clients}"  var="item" >
  						<option value="${item.num}" <c:if test="${item.num == sejour.client.num}">selected</c:if>>${item.nom}</option>
  					</c:forEach>
	  				</select>
	  			</div>
	  		</div>		

	  		<div class="form-group">
	  			<label for="emplacement" class="control-label col-md-2">Emplacement</label>
	  			<div class="col-md-2">
	  				<select name="emplacement" id="emplacement" class="form-control">
  					<c:forEach  items="${emplacements}"  var="item" >
  						<option value="${item.num}" <c:if test="${item.num == sejour.emplacement.num}">selected</c:if>>${item.type.lib} - ${item.surface}m² - ${item.nbPersMax}pers</option>
  					</c:forEach>
	  				</select>
	  			</div>
	  		</div>

	  		<c:if test="${type == 'modif'}">
		  		<input type="hidden" value="${urlDataActivites}" id="urlDataActivites" />
	    		<jsp:include page="../includes/modalActivities.jsp"></jsp:include>
	    		<div class="form-group text-center">
		  			<a href="#" class="btn btn-default showActivites" sej="${sejour.num}">Gérer les activités</a>
		  		</div>
		  	</c:if>
	  		
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
