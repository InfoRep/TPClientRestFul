<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page session="false" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="pageTitle">Liste clients</jsp:attribute>

    <jsp:attribute name="title">
	    Liste des clients 
	    <c:if test="${not empty nomClientSearch}">qui contiennent "${nomClientSearch}"</c:if>
	     : (${fn:length(clients)})
    </jsp:attribute>
    

    <jsp:attribute name="javascripts">
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/plugins/tablesorter/jquery.tablesorter.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/plugins/tablesorter/jquery.tablesorter.pager.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/plugins/tablesorter/custom.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				initTableSorter($("#mytable"), {
					7: {
						sorter: false
					},
					8: { 
						sorter: false
					}
				})			
			});	
		</script>
    </jsp:attribute>

    <jsp:attribute name="css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/plugins/tablesorter/theme.blue.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/plugins/tablesorter/jquery.tablesorter.pager.css">
    </jsp:attribute>

    <jsp:body>
   		<div class="row">
        	<div class="col-md-12">
		        <table class="tablesorter" id="mytable">
			        <thead>
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
					</thead>
					<tbody>
					 	<c:forEach  items="${clients}"  var="item" >
					 	<tr class="text-center">
					     	<td><a href="edit/${item.num}" class="link" style="text-decoration: none">${item.num}</a></td>					     	
						  	<td>${item.nom}</td>
						  	<td>${item.adrRue}</td>
						  	<td>${item.cp}</td>
						  	<td>${item.ville}</td>
						  	<td>${item.piece}</td>
						  	<td>${item.numPiece}</td>
						  	<td><a href="edit/${item.num}" class="btn btn-primary">Modifier</a></td>
						  	<td><a href="delete/${item.num}" class="btn btn-danger">Supprimer</a></td>
					  	</tr>
					 	</c:forEach>
					</tbody>
				</table>

				<jsp:include page="../includes/pager.jsp"></jsp:include>
			</div>
		</div>
    </jsp:body>
</t:layout>

