<%@ page import="client.model.Client" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page session="false" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:layout>
	<jsp:attribute name="pageTitle">Facture sejour</jsp:attribute>	

    <jsp:attribute name="title">
    	Formulaire facturation d'un sejour
    </jsp:attribute>
    
    <jsp:attribute name="javascripts">
   		<script type="text/javascript">
  			$(document).ready(function() {
  				$("#btnFacture").click(function() {
  					window.location.href = window.location.href + "/"+$("#sejour").val();
  				});
  			});

   		</script>
    </jsp:attribute>
    
    <jsp:body>
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
			   <input type="submit" class="btn btn-primary" value="Facturer" id="btnFacture" />
				
				<a href="../sejour/list" class="btn btn-default">Retour</a>
	  		</div>
	  	</form>
    </jsp:body>
</t:layout>
