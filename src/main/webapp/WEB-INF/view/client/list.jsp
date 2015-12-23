<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page session="false" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="pageTitle">Liste clients</jsp:attribute>

    <jsp:attribute name="title">Liste des clients : (${fn:length(clients)})</jsp:attribute>
    
    <jsp:body>
   		<div class="row">
        	<div class="col-md-12">
		        <form method="post" action="effacerJouet.htm"> 
			        <table class="table">
				  		<tr>
						 	<th>Numero</th>
							<th>Nom</th>
						 	<th>Adresse</th>
						 	<th>Code postal</th>
						 	<th>Ville</th>
						 	<th>Type de piece d'identitee</th>
						 	<th>Numero piece</th>
						 	<th></th>
						 	<th></th>
				 		</tr>
					 	
					 	<c:forEach  items="${clients}"  var="item" >
					 	<tr class="text-center">
					     	<td><a target="_blank" href="edit/${item.num}">${item.num}</a></td>					     	
						  	<td>${item.nom}</td>
						  	<td>${item.adrRue}</td>
						  	<td>${item.cp}</td>
						  	<td>${item.ville}</td>
						  	<td>${item.piece}</td>
						  	<td>${item.numPiece}</td>
						  	<td><a target="_blank" href="edit/${item.num}" class="btn btn-primary">Modifier</a></td>
						  	<td><a href="delete/${item.num}" class="btn btn-danger">Supprimer</a></td>
					  	</tr>
					 	</c:forEach>
					</table>
				</form>
			</div>
		</div>
    </jsp:body>
</t:layout>

