$(function() {

	// datepicker
	$('.datepicker-from').datepicker({
		format : "dd.mm.yyyy", // check change
		changeMonth : true,
		changeYear : true,
		uiLibrary : 'bootstrap4'
	});

	$('.datepicker-to').datepicker({
		format : "dd.mm.yyyy",// check change
		changeMonth : true,
		changeYear : true,
		uiLibrary : 'bootstrap4'
	});

	// datepicker
	$('.datepicker-fromBusiness').datepicker({
		// dateFormat : 'dd-mm-yyyy',
		format : "dd.mm.yyyy",// check change
		changeMonth : true,
		changeYear : true,
		uiLibrary : 'bootstrap4'
	});

	$('.datepicker-toBusiness').datepicker({
		// dateFormat : 'dd-mm-yyyy',
		format : "dd.mm.yyyy",// check change
		changeMonth : true,
		changeYear : true,
		uiLibrary : 'bootstrap4'
	});

	// datepicker end

	$('#timepicker').timepicker();
	$('#timepicker1').timepicker();

});