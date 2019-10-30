/**
 * \ *
 * 
 * @Author: Durgesh Mudras
 * @Date : 21-11-2018
 * 
 */

/*
 * 
 * Role Master Started..
 * 
 * 
 */

function refreshList(id) {
	var storeId = id;
	$.ajax({
		type : "POST",
		// cache: false,
		url : 'groupLoader',
		data : {
			"storeId" : storeId
		},
		headers : {
			"X-CSRF-TOKEN" : $("input[name='_csrf']").val()
		},
		success : function(response) {
			$('.myFooTable').footable();
			$("#refreshDiv").html(response);
			return true;
		}
	});
}

function editGroup(groupId, storeId) {
	// alert(" Group Id :" + groupId + "Store ID :" + storeId);
	$.ajax({
		type : "POST",
		cache : false,
		url : 'editGroup',
		async : false,
		data : {
			"groupId" : groupId,
			"storeId" : storeId
		},
		headers : {
			"X-CSRF-TOKEN" : $("input[name='_csrf']").val()
		},
		success : function(response) {
			$('#groupModal').modal({
				show : true
			});
			$(".modalIn").html(response);
		}
	});
	$("#myStoreID option[id=" + storeId + "]").attr("selected", "selected");
}

function deleteGroup(groupId, storeId) {
	var r = confirm("Would you like to delete Group!!!");
	if (r == true) {
		$.ajax({
			type : "POST",
			cache : false,
			url : 'deleteGroup',
			async : false,
			data : {
				"groupId" : groupId,
				"storeId" : storeId
			},
			headers : {
				"X-CSRF-TOKEN" : $("input[name='_csrf']").val()
			},
			success : function(response) {
				// alert("Response :" + response);
				return true;
			}
		});
	}
}
 