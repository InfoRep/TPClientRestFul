function initTableSorter(jqueryElem, headers)
{
	$(jqueryElem)
		.tablesorter({
			theme: 'blue',
			widthFixed: true,
			headerTemplate : '{content} {icon}', // new in v2.7. Needed to add the bootstrap icon!
			"headers": headers,
			widgets: ['zebra', 'filter']
		})
		.tablesorterPager({
			container: $(".pager"),
			output: '{startRow} - {endRow} / {filteredRows} ({totalRows})',
			fixedHeight: true,
			removeRows: false,
			cssGoto: '.gotoPage'
		}); 
}