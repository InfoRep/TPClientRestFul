//activites chargement
function initManageActivities()
{
	$(".showActivites").click(function() {
		var numSej = $(this).attr("sej");

		$.ajax({
			url: $("#urlDataActivites").val()+"activite/get/bySejour/"+numSej
		})
		.success(function(resp) {
			if (!resp.valid) 
			{
				alert("Probleme lors de la recupération des activites, merci de reessayer plus tard");
				return;
			}

			$("#modalActSej h4.modal-title").text("Activites du sejour "+numSej);
			$("#noActivity").remove();
			
			var table = $("#modalActSej div.modal-body table tbody").empty();

			if (resp.msg.length > 0)
			{
				$.each(resp.msg, function() {
					var row = $("<tr>");
					row
						.attr("id", "act"+this.id)
						.append($("<td>").addClass("text-center").text(this.sport.codeSport))
						.append($("<td>").addClass("text-center").text(this.sport.libelleSport))
						.append($("<td>").addClass("text-center").text(this.dateJour))
						.append($("<td>").addClass("text-center").text(this.nbloc));

					var delLink = $("<i class='glyphicon glyphicon-remove' style='cursor:pointer'></i>")
											.attr("myid", this.id);
					delLink.click(function() {
						var id = "act"+$(this).attr("myid");
						var urlDelete = $("#urlDataActivites").val()+"activite/delete/"+$(this).attr("myid");
						$.ajax({
							url: urlDelete
						}).success(function(resp) {
							if (!resp.valid)
							{
								alert("Impossible de supprimer cette activite, merci reessayer plus tard.");
								return;
							}

							$("#"+id).remove();
						}).fail(function(r) {
							alert("Impossible de supprimer cette activite, merci reessayer plus tard.");
						});
					});
					
					row.append($("<td>").addClass("text-center").append(delLink));
				
					table.append(row);
				});
			} else 
				$("#modalActSej div.modal-body").append($("<div>").attr("id", "noActivity").css("text-align", "center").text("Aucune activite pour ce sejour"));

			$("#modalActSej").modal();
		})
		.fail(function(r) {
			alert("Erreur lors de la recuperation des activites. Veuillez reessayer plus tard");
		});
	});
}