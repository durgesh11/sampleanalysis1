var $ = jQuery.noConflict();

$(function() {
	$(".mater-menu").on(
			'click',
			function() {

				$(this).parent('li').siblings().find(".inner-menu-content")
						.removeClass("sub-menu");
				// $(this).parent('li').siblings().addClass("sub-menu1");
				$(this).next().toggleClass("sub-menu");

			});

	$('.btn-search').click(function() {
		$('.searchbar').toggleClass('clicked');
		$('.stage').toggleClass('faded');

		if ($('.searchbar').hasClass('clicked')) {
			$('.btn-extended').focus();
		}
	});

	$("footer").load("footer.html");

	// 4) table data.

	$("#sereg").click(function() {
		$(".sg").addClass("intro");
	});

	$(".btn-filter").click(function() {
		$(".filters").toggle("show");
	});

	// search

	// search end

	// popup
	// $('.popup-modal').magnificPopup({
	// type: 'inline',
	// preloader: false,
	// focus: '#username',
	// modal: true
	// });
	// $(document).on('click', '.popup-modal-dismiss', function (e) {
	// e.preventDefault();
	// $.magnificPopup.close();
	// });
	// popup end

	// delete row
	$(".tabdelete").on('click', '.btnDelete', function() {
		$(this).closest('tr').remove();
	});
	// delete row end

	$('.filterable .filters input')
			.keyup(
					function(e) {
						/* Ignore tab key */
						var code = e.keyCode || e.which;
						if (code == '9')
							return;
						/* Useful DOM data and selectors */
						var $input = $(this), inputContent = $input.val()
								.toLowerCase(), $panel = $input
								.parents('.filterable'), column = $panel.find(
								'.filters th').index($input.parents('th')), $table = $panel
								.find('.table'), $rows = $table
								.find('tbody tr');
						console.log("$input :"+$input);
						console.log(column);
						console.log("$rows :"+$rows);

						/* Dirtiest filter function ever ;) */
						var $filteredRows = $rows.filter(function() {
							console.log("$(this).find('td').eq(column).text() :"+$(this).find('td').eq(column).text());
							var value = $(this).find('td').eq(column).text()
									.toLowerCase();
							return value.indexOf(inputContent) === -1;
						});
						/* Clean previous no-result if exist */
						$table.find('tbody .no-result').remove();
						/*
						 * Show all rows, hide filtered ones (never do that
						 * outside of a demo ! xD)
						 */
						$rows.show();
						$filteredRows.hide();
						/* Prepend no-result row if all rows are filtered */
					});

	$('.filterInputBox1 input')
			.keyup(
					function(e) {
						/* Ignore tab key */
						var code = e.keyCode || e.which;
						if (code == '9')
							return;
						/* Useful DOM data and selectors */
						var $input = $(this), inputContent = $input.val()
								.toLowerCase(), $panel = $input
								.parents('.filterable'),
						// column = $panel.find('.filters
						// th').index($input.parents('th')),
						column = $input.data('id'), $table = $panel
								.find('.table'), $rows = $table
								.find('tbody tr');

						// console.log(column)

						var $filteredRows = $rows.filter(function() {
							var value = $(this).find('td').eq(column).text()
									.toLowerCase();
							return value.indexOf(inputContent) === -1;
						});
						/* Clean previous no-result if exist */
						$table.find('tbody .no-result').remove();
						/*
						 * Show all rows, hide filtered ones (never do that
						 * outside of a demo ! xD)
						 */
						$rows.show();
						$filteredRows.hide();
						/* Prepend no-result row if all rows are filtered */
					});

	// =============================================================

	// $('#employeePrivileges').footable({
	// "columns": $.get('content/columns-privileges.json'),
	// "rows": $.get('content/rows.json'),
	// "breakpoints": {
	// "x-small": 480,
	// "small": 768,
	// "medium": 992,
	// "large": 1200,
	// "x-large": 1400
	// }

	// });

	// $('#terminalKeyLlist').footable({
	// /*"columns": $.get('content/columns-terminal-key.json'),
	// "rows": $.get('content/rows.json'),*/
	// "breakpoints": {
	// "x-small": 480,
	// "small": 768,
	// "medium": 992,
	// "large": 1200,
	// "x-large": 1400
	// }
	// });

	// $("#myInput").on("keyup", function() {
	//    var value = $(this).val().toLowerCase();
	//    $("#myTable tr").filter(function() {
	//      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	//    });
	//  });

});

//Validation....

var pleaseChoose = $('#pleaseChooseId').val();





function getUsers(storeObj,tempTerminalObj,terminalObj,userId) {
	var terminal="-1";
	//alert("terminalObj :"+terminalObj+" ---"+userId);
	if(tempTerminalObj>0 && terminalObj !=null){
		terminal=terminalObj.toString();	
	}
 //alert("terminal :"+terminal);
	$.ajax({
		type : "POST",
		//cache: false,
		url : 'getUsersList',
		data : {
			"storeId" : storeObj.value,
			"terminalId" : terminal
		},
		//    contentType: "application/json",
		headers : {
			"X-CSRF-TOKEN" : $("input[name='_csrf']").val()
		},
		error: function (xhr, textStatus, errorThrown) {
		    if (xhr.status == 403 || textStatus == 'parsererror' && xhr.responseText.match('rememberMe').length > 0) {
		        alert('Your session has timed out.');
		        window.location = 'sessionOut';
		    }  
		},
		success : function(data) {
			//alert(data);
			var obj = JSON.parse(data);
			document.getElementById(""+userId).innerHTML = "";
			var select1 = document.getElementById(""+userId);
			var option1 = document.createElement("option");
			
			option1.text =pleaseChoose;
			option1.value = "-1";
			option1.selected="selected";
			select1.add(option1);
			for (var i = 0; i < obj.UserList.length; i++) {
				var select = document.getElementById(""+userId);
				var option = document.createElement("option");
				option.text = obj.UserList[i] ;
				option.value = obj.UserList[i];
				select.add(option);
			}
		}
	});
}


function getUsersForCancellation(storeObj,tempTerminalObj,terminalObj,userId) {
	var terminal="-1";
	//alert("terminalObj :"+terminalObj+" ---"+userId);
	if(tempTerminalObj>0 && terminalObj !=null){
		terminal=terminalObj.toString();	
	}
 //alert("terminal :"+terminal);
	$.ajax({
		type : "POST",
		//cache: false,
		url : 'getUsersListForCancellation',
		data : {
			"storeId" : storeObj.value,
			"terminalId" : terminal
		},
		//    contentType: "application/json",
		headers : {
			"X-CSRF-TOKEN" : $("input[name='_csrf']").val()
		},
		error: function (xhr, textStatus, errorThrown) {
		    if (xhr.status == 403 || textStatus == 'parsererror' && xhr.responseText.match('rememberMe').length > 0) {
		        alert('Your session has timed out.');
		        window.location = 'sessionOut';
		    }  
		},
		success : function(data) {
			//alert(data);
			var obj = JSON.parse(data);
			document.getElementById(""+userId).innerHTML = "";
			var select1 = document.getElementById(""+userId);
			var option1 = document.createElement("option");
			
			option1.text =pleaseChoose;
			option1.value = "-1";
			option1.selected="selected";
			select1.add(option1);
			for (var i = 0; i < obj.UserList.length; i++) {
				var select = document.getElementById(""+userId);
				var option = document.createElement("option");
				option.text = obj.UserList[i] ;
				option.value = obj.UserList[i];
				select.add(option);
			}
		}
	});
}


function getDistinctMonth(storeObj,tempTerminalObj,terminalObj,monthId) {
	var terminal="-1";
//	alert("terminalObj :"+terminalObj+" ---"+monthId+"tempTerminalObj :"+tempTerminalObj);
	var storeId="-1";
	if(tempTerminalObj>0){
		storeId=storeObj.value;
	}
	
	if( terminalObj !=null){
		terminal=terminalObj.toString();	
	}
	
//	alert("terminal ID :"+terminal+" storeId :"+storeObj.value);
 
	$.ajax({
		type : "POST",
		//cache: false,
		url : 'getDistinctMonth',
		data : {
			"storeId" : storeObj.value,
			"terminalId" : terminal
		},
		//    contentType: "application/json",
		headers : {
			"X-CSRF-TOKEN" : $("input[name='_csrf']").val()
		},
		error: function (xhr, textStatus, errorThrown) {
		    if (xhr.status == 403 || textStatus == 'parsererror' && xhr.responseText.match('rememberMe').length > 0) {
		        alert('Your session has timed out.');
		        window.location = 'sessionOut';
		    }  
		},
		success : function(data) {
		//	alert(data);
			var obj = JSON.parse(data);
			document.getElementById(""+monthId).innerHTML = "";
			var select1 = document.getElementById(""+monthId);
			var option1 = document.createElement("option");
			
			option1.text =pleaseChoose;
			option1.value = "-1";
			option1.selected="selected";
			select1.add(option1);
			for (var i = 0; i < obj.MonthList.length; i++) {
				var select = document.getElementById(""+monthId);
				var option = document.createElement("option");
				option.text = obj.MonthList[i] ;
				option.value = obj.MonthList[i];
				select.add(option);
			}
		}
	});
}


function getDistinctDate(storeObj,tempTerminalObj,terminalObj,dateId) {
	var terminal="-1";
	
	if(tempTerminalObj>0 && terminalObj !=null){
		terminal=terminalObj.toString();;	
	}
	//alert("getDistinctDate :"+terminal);
	$.ajax({
		type : "POST",
		//cache: false,
		url : 'getDistinctTime',
		data : {
			"storeId" : storeObj.value,
			"terminalId" : terminal
		},
		//    contentType: "application/json",
		headers : {
			"X-CSRF-TOKEN" : $("input[name='_csrf']").val()
		},
		error: function (xhr, textStatus, errorThrown) {
		    if (xhr.status == 403 || textStatus == 'parsererror' && xhr.responseText.match('rememberMe').length > 0) {
		        alert('Your session has timed out.');
		        window.location = 'sessionOut';
		    }  
		},
		success : function(data) {
			var obj = JSON.parse(data);
			document.getElementById(""+dateId).innerHTML = "";
			var select1 = document.getElementById(""+dateId);
			var option1 = document.createElement("option");
			
			option1.text =pleaseChoose;
			option1.value = "-1";
			option1.selected="selected";
			select1.add(option1);
			for (var i = 0; i < obj.DateList.length; i++) {
				var select = document.getElementById(""+dateId);
				var option = document.createElement("option");
	 
				option.text = obj.DateList[i] ;
				option.value = obj.DateList[i];
				select.add(option);
			}
		}
	});
}
 
function getClosureList(storeObj, tempTerminalObj, terminalObj, closureId) {

	var terminal = "-1";
	if (tempTerminalObj > 0 && terminalObj != null) {
		terminal = terminalObj.toString();

		if (storeObj.value !== "-1") {
			$.ajax({
						type : "POST",
						// cache: false,
						url : 'getClosure',
						data : {
							"storeId" : storeObj.value,
							"terminalId" : terminal
						},
						// contentType: "application/json",
						headers : {
							"X-CSRF-TOKEN" : $("input[name='_csrf']").val()
						},
						error : function(xhr, textStatus, errorThrown) {
							if (xhr.status == 403
									|| textStatus == 'parsererror'
									&& xhr.responseText.match('rememberMe').length > 0) {
								alert('Your session has timed out.');
								window.location = 'sessionOut';
							}
						},
						success : function(data) {
							var obj = JSON.parse(data);
							document.getElementById("" + closureId).innerHTML = "";
							var select1 = document.getElementById(""
									+ closureId);
							var option1 = document.createElement("option");

							option1.text = pleaseChoose;
							option1.value = "-1";
							option1.selected = "selected";
							select1.add(option1);
							for (var i = 0; i < obj.ClosureList.length; i++) {
								var select = document.getElementById(""
										+ closureId);
								var option = document.createElement("option");
								option.text = obj.ClosureList[i].closureTime;
								option.value = obj.ClosureList[i].closureTime;
								select.add(option);
							}
						}
					});
		}
	}

}

function getTables(storeObj,tempTerminalObj,terminalObj,slTable) {
	var terminal="-1";
	
	//alert("storeObj.value :"+storeObj.value);
	if(tempTerminalObj>0 && terminalObj !=null){
		terminal=terminalObj;	
	}
	//alert("getDistinctDate :"+terminal);
	$.ajax({
		type : "POST",
		//cache: false,
		url : 'getTables',
		data : {
			"storeId" : storeObj.value,
			"terminalId" : terminal
		},
		//    contentType: "application/json",
		headers : {
			"X-CSRF-TOKEN" : $("input[name='_csrf']").val()
		},
		error: function (xhr, textStatus, errorThrown) {
		    if (xhr.status == 403 || textStatus == 'parsererror' && xhr.responseText.match('rememberMe').length > 0) {
		        alert('Your session has timed out.');
		        window.location = 'sessionOut';
		    }  
		},
		success : function(data) {
			//alert(data);
			var obj = JSON.parse(data);
			document.getElementById(""+slTable).innerHTML = "";
			var select1 = document.getElementById(""+slTable);
			var option1 = document.createElement("option");
			
			option1.text =pleaseChoose;
			option1.value = "-1";
			option1.selected="selected";
			select1.add(option1);
			for (var i = 0; i < obj.tableList.length; i++) {
				var select = document.getElementById(""+slTable);
				var option = document.createElement("option");
	 
				option.text = obj.tableList[i].name;
				option.value = obj.tableList[i].name;
				select.add(option);
			}
		}
	});
}

function resetDateFields(id){
	if(id != "slClosure"){
		document.getElementById("slClosure").selectedIndex = 0;
	}
	if(id != "slDaily"){
		document.getElementById("slDaily").selectedIndex = 0;
	}
	if(id != "slMonth"){
		document.getElementById("slMonth").selectedIndex = 0;
	}
	if(id != "txtFromDate" && id != "txtToDate"){
		document.getElementById("txtFromDate").value = "";
		document.getElementById("txtToDate").value = "";
	}
	if(id != "txtBusiFromDate" && id != "txtBusiToDate"){
		document.getElementById("txtBusiFromDate").value = "";
		document.getElementById("txtBusiToDate").value = "";
	}
}
 
function getDistinctDateForCancellation(storeObj,tempTerminalObj,terminalObj,dateId) {
	var terminal="-1";
	
	if(tempTerminalObj>0 && terminalObj !=null){
		terminal=terminalObj.toString();;	
	}
	//alert("getDistinctDate :"+terminal);
	$.ajax({
		type : "POST",
		//cache: false,
		url : 'getDistinctTimeForCancellation',
		data : {
			"storeId" : storeObj.value,
			"terminalId" : terminal
		},
		//    contentType: "application/json",
		headers : {
			"X-CSRF-TOKEN" : $("input[name='_csrf']").val()
		},
		error: function (xhr, textStatus, errorThrown) {
			if (xhr.status == 403 || textStatus == 'parsererror' && xhr.responseText.match('rememberMe').length > 0) {
				alert('Your session has timed out.');
				window.location = '<c:url value="/sessionOut" />';
			}  
		},
		success : function(data) {
			var obj = JSON.parse(data);
			document.getElementById(""+dateId).innerHTML = "";
			var select1 = document.getElementById(""+dateId);
			var option1 = document.createElement("option");
			
			option1.text =pleaseChoose;
			option1.value = "-1";
			option1.selected="selected";
			select1.add(option1);
			for (var i = 0; i < obj.DateList.length; i++) {
				var select = document.getElementById(""+dateId);
				var option = document.createElement("option");
				
				option.text = obj.DateList[i] ;
				option.value = obj.DateList[i];
				select.add(option);
			}
		}
	});
}


function getDistinctMonthForCancellation(storeObj,tempTerminalObj,terminalObj,monthId) {
	var terminal="-1";
	//alert("terminalObj :"+terminalObj+" ---"+monthId);
//	if(tempTerminalObj>0 && terminalObj !=null){
//		terminal=terminalObj.toString();	
//	}
	var storeId="-1";
	if(tempTerminalObj>0){
		storeId=storeObj.value;
	}
	
	if( terminalObj !=null){
		terminal=terminalObj.toString();	
	}
	
	$.ajax({
		type : "POST",
		//cache: false,
		url : 'getDistinctMonthForCancellation',
		data : {
			"storeId" : storeObj.value,
			"terminalId" : terminal
		},
		//    contentType: "application/json",
		headers : {
			"X-CSRF-TOKEN" : $("input[name='_csrf']").val()
		},
		error: function (xhr, textStatus, errorThrown) {
			if (xhr.status == 403 || textStatus == 'parsererror' && xhr.responseText.match('rememberMe').length > 0) {
				alert('Your session has timed out.');
				window.location = '<c:url value="/sessionOut" />';
			}  
		},
		success : function(data) {
			//alert(data);
			var obj = JSON.parse(data);
			document.getElementById(""+monthId).innerHTML = "";
			var select1 = document.getElementById(""+monthId);
			var option1 = document.createElement("option");
			
			option1.text =pleaseChoose;
			option1.value = "-1";
			option1.selected="selected";
			select1.add(option1);
			for (var i = 0; i < obj.MonthList.length; i++) {
				var select = document.getElementById(""+monthId);
				var option = document.createElement("option");
				option.text = obj.MonthList[i] ;
				option.value = obj.MonthList[i];
				select.add(option);
			}
		}
	});
}