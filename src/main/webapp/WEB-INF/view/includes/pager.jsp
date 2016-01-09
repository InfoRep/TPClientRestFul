<!-- pager -->
<div class="pager tablesorter-pager">
	Page: <select aria-disabled="false" class="gotoPage"><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option></select>		<img aria-disabled="true" tabindex="0" src="${pageContext.request.contextPath}/resources/plugins/tablesorter/first.png" class="first disabled" alt="First" title="First page">
	<img aria-disabled="true" tabindex="0" src="${pageContext.request.contextPath}/resources/plugins/tablesorter/prev.png" class="prev disabled" alt="Prev" title="Previous page">
	<span class="pagedisplay">1 - 10 / 50 (50)</span> <!-- this can be any element, including an input -->
	<img aria-disabled="false" tabindex="0" src="${pageContext.request.contextPath}/resources/plugins/tablesorter/next.png" class="next" alt="Next" title="Next page">
	<img aria-disabled="false" tabindex="0" src="${pageContext.request.contextPath}/resources/plugins/tablesorter/last.png" class="last" alt="Last" title="Last page">
	<select aria-disabled="false" class="pagesize">
		<option value="10">10</option>
		<option value="20">20</option>
		<option value="30">30</option>
		<option value="40">40</option>
	</select>
</div>