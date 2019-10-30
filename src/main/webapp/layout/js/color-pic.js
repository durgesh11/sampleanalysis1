$(function(){
	var color;
	var element;
	// color picker
	$(".colorpicker").spectrum({
	     preferredFormat: "hex",
	    showInput: true,
	    showPalette: true,
	    palette: [["red", "rgba(0, 255, 0, .5)", "rgb(0, 0, 255)"]],
	    hide: function(color) {
		    c = color.toHexString(); // #ff0000
		    console.log(color);
			setColor(c);
		}
	});

	// color picker end
	function setColor(c){
		console.log(c);
		console.log(element);
		$('#'+element).css('border-top','5px solid '+c);
	};

	$(document).on('click','.del-colr a.colorPicBox',function(e){
		element = $(this).attr('data-id');
		console.log(element);
		e.preventDefault();
	});

	$(".colorpicker").on('beforeShow.spectrum', function(e, tinycolor) {
		element = $(this).parent().attr('data-id');
		// element = element+':before'
	});


});

