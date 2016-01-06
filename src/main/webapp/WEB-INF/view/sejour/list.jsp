<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page session="false" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="pageTitle">Liste sejours</jsp:attribute>

    <jsp:attribute name="title">Liste des séjours : (${fn:length(sejours)})</jsp:attribute>
    
    <jsp:body>
   		<div class="row">
        	<div class="col-md-12">
		        <table class="table">
			  		<tr>
					 	<th>Numero</th>
						<th>Numero du client</th>
					 	<th>Numero de l'emplacement</th>
					 	<th>Date de debut du sejour</th>
					 	<th>Date de fin du sejour</th>
					 	<th>Nombre de personnes</th>
					 	<th></th>
					 	<th></th>
			 		</tr>
				 	
				 	<c:forEach  items="${sejours}"  var="item" >
				 	<tr class="text-center">
				     	<td><a target="_blank" href="edit/${item.num}">${item.num}</a></td>					     	
					  	<td>${item.client.num}</td>
					  	<td>${item.emplacement.num}</td>
					  	<td><fmt:formatDate type="time" value="${item.dateDeb}" /></td>
					  	<td><fmt:formatDate type="time" value="${item.dateFin}" /></td>
					  	<td>${item.nbPersonnes}</td>
					  	<td><a target="_blank" href="edit/${item.num}" class="btn btn-primary">Modifier</a></td>
					  	<td><a href="delete/${item.num}" class="btn btn-danger">Supprimer</a></td>
				  	</tr>
				 	</c:forEach>
				</table>
			</div>
		</div>
    </jsp:body>
</t:layout>

