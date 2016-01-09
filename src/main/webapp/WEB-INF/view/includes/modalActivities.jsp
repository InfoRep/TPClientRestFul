<div class="modal fade" id="modalActSej" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body" style="max-height: 200px; overflow: auto;">
      	<table class="table">
      		<thead>
      			<tr>
      				<th>Code sport</th>
      				<th>Libelle sport</th>
      				<th>Date</th>
      				<th>Nombre de fois</th>
      			</tr>
      		</thead>
      		<tbody>
      		</tbody>
      	</table>
      </div>
      <div class="modal-footer">
        <div style="display:inline">
          <a class="btn btn-default" href="${pageContext.request.contextPath}/activity/add">Ajouter une nouvelle activite</a>
        </div>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->