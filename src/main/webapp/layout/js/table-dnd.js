 

$(function() {

	// table dnd(drag n drop)
	$(".myFooTable").tableDnD();
	// table dnd(drag n drop) end

	$(".myFooTable").tableDnD(
			{
				onDragClass : "myDragClass",
				dragStyles : {
					'color' : 'green',
					'font-style' : 'italic'
				},
				onDrop : function(table, row) {
					// alert();

					var rows = table.tBodies[0].rows;
					// alert(rows.length);
					var debugStr = "Row dropped was " + row.id
							+ ". New order: ";
					// alert($.tableDnD.serialize());

					var concat = "";
					var ids = "";

					for (var i = 0; i < rows.length; i++) {
						// debugStr += concat+rows[i].id;

						ids += concat + rows[i].id + "";
						concat = ",";

					}

					var groupIdss = "";
					var concat1 = "";
					for (var j = 0; j < rows.length; j++) {
						groupIdss += concat1+ $(".myFooTable #" + rows[j].id+ " input.hide").val();
						concat1 = ",";
					}
					var finalId = ids + "#" + groupIdss;
					$("#debugArea").html(debugStr);
					dragAndDrop(finalId);
				},
				onDragStart : function(table, row) {
					$("#debugArea").html("Started dragging row " + row.id);
				}
			});
});



function dragAndDrop(ids) {
	var text = $(".pagination li.active  a.footable-page-link").text();
	$.ajax({
		type : "POST",
		url : 'dragAndDrop',
		data : {
			"ids" : ids
		},
		headers : {
			"X-CSRF-TOKEN" : $("input[name='_csrf']").val()
		},
		success : function(response) {
			return true;
		}
	});
}
