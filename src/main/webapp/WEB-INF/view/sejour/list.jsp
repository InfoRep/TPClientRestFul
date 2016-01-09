<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page session="false" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="pageTitle">Liste sejours</jsp:attribute>

    <jsp:attribute name="title">Liste des séjours : (${fn:length(sejours)})</jsp:attribute>
    
    <jsp:attribute name="javascripts">
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/plugins/tablesorter/jquery.tablesorter.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/plugins/tablesorter/jquery.tablesorter.pager.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/plugins/tablesorter/custom.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/manageActivities.js"></script>
		<script type="text/javascript">		
			$(document).ready(function() 
			{
				//Table sorter
				initTableSorter($("#mytable"), {
					9: {
						sorter: false
					},
					8: { 
						sorter: false
					}
				})

				//manage activities
				initManageActivities();
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
        		<input type="hidden" value="${urlDataActivites}" id="urlDataActivites" />
        		<jsp:include page="../includes/modalActivities.jsp"></jsp:include>

		        <table class="tablesorter" id="mytable">
			        <thead>
				  		<tr>
						 	<th>Numero</th>
							<th>Numero du client</th>
							<th>Nom du client</th>
						 	<th>Numero de l'emplacement</th>
						 	<th>Type d'emplacement</th>
						 	<th>Date de debut du sejour</th>
						 	<th>Date de fin du sejour</th>
						 	<th>Nombre de personnes</th>
						 	<th>Activités</th>
						 	<th></th>
						 	<th></th>
				 		</tr>
					</thead>
					<tbody>
					 	<c:forEach  items="${sejours}"  var="item" >
					 	<tr class="text-center">
					     	<td><a href="edit/${item.num}" class="link" style="text-decoration: none">${item.num}</a></td>					     	
						  	<td class="num">${item.client.num}</td>
						  	<td>${item.client.nom}</td>
						  	<td>${item.emplacement.num}</td>
						  	<td>${item.emplacement.type.lib}</td>
						  	<td><fmt:formatDate pattern="yyyy-MM-dd" value="${item.dateDeb}" /></td>
						  	<td><fmt:formatDate pattern="yyyy-MM-dd" value="${item.dateFin}" /></td>
						  	<td>${item.nbPersonnes}</td>
						  	<td><a href="#" class="btn btn-default showActivites" sej="${item.num}">Voir activités</a></td>
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

