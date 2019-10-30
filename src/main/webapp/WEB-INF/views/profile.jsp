<!DOCTYPE html>
<%@include file="header.jsp"%>

<html class="no-js" lang="">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
#message {
  display:none;
  background: #f1f1f1;
  color: #000;
  position: relative;
  padding: 10px;
  margin-top: 1px;
}

#message p {
  padding: 10px 35px;
  font-size: 13px;
}

/* Add a green text color and a checkmark when the requirements are right */
.valid {
  color: green;
}

.valid:before {
  position: relative;
  left: -35px;
  content: "\2713";
}

/* Add a red text color and an "x" when the requirements are wrong */
.invalid {
  color: red;
}

.invalid:before {
  position: relative;
  left: -35px;
  content: "\2715";
}
</style>
</head>
<body>
	<form:form modelAttribute="profileForm" id="profileFormId"
		action="saveProfile?${_csrf.parameterName}=${_csrf.token}"
		method="post" enctype="multipart/form-data">
		
		
		
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
			<input type="hidden" value="${employee.chainOwner}" name="employee.chainOwner"/>  

		<section class="mainframe">
			<section class="container">

				<div class="hedaings">
					<h2>Profile</h2>
					<div class="comm-btn">
					
						<input type="submit" value="Save" onclick="return saveProfile1();"
							class="btn btn-primary main-btn-hov"/>
					</div>
				</div>

				<div class="prot-sec">

					<!-- Closure period -->

					<div class="rep text-center set-2">
						<h3>General Information</h3>
					</div>
					
				 

					<div class="row set-in">
						<div class="col-lg-2 col-md-4">
							<h4>Name</h4>
							<input type="text" disabled="disabled"
								title="${profileForm.chainOwnerName}"
								value="${profileForm.chainOwnerName}" class="value w-100"
								placeholder="Name" />
						</div>
						<div class="col-lg-2 col-md-4">
							<h4>Mobile</h4>
							<input type="text" name="" disabled="disabled"
								title="${profileForm.chainOwnerCustomerNumber}"
								value="${profileForm.chainOwnerCustomerNumber}"
								class="value w-100" placeholder="Enter Mobile Number">
						</div>
						<div class="col-lg-2 col-md-4">
							<h4>Email</h4>
							<input type="text" value="${profileForm.emailAddress}"
								title="${profileForm.emailAddress}" disabled="disabled" name=""
								class="value w-100" placeholder="Select Email Address">
						</div>
					</div>



					<div class="row set-in">
						<div class="col-lg-4">
							<h4>Street</h4>
							<input type="text" name="" class="value w-100"
								value="${profileForm.chainOwnerAddress}"
								title="${profileForm.chainOwnerAddress}" disabled="disabled"
								placeholder="Flat / House No. / Building...">
						</div>
						<div class="col-lg-2 col-md-4">
							<h4>City</h4>
							<input type="text" name="" class="value w-100"
								value="${profileForm.chainOwnerCity}"
								title="${profileForm.chainOwnerCity}" disabled="disabled"
								placeholder="Enter City">
						</div>
						<div class="col-lg-2 col-md-4">
							<h4>Pincode</h4>
							<input type="text" name="" class="value w-100"
								value="${profileForm.chainOwnerPostalCode}"
								title="${profileForm.chainOwnerPostalCode}" disabled="disabled"
								placeholder="Enter Pincode">
						</div>
					</div>



					<!-- Closure period end-->


					<!-- Upload User Image Starts-->

					<div class="rep text-center">
						<h3>Upload Profile Picture</h3>
					</div>


					<div class="row mag-box set-lab">
						<div class="col-md-4">
							<label>Upload Profile Picture</label>
						</div>

						 <input type="file" id="upphotos" name="file"
							accept="image/png, image/jpeg"> <img id="blah" src="#"
							alt="" /> 
					</div>
 
					<!-- Upload User Image Ends-->

 

					<!-- Miscellaneous-->

					<div class="rep text-center">
						<h3>Change Password</h3>
					</div>

					<div class="row set-in">
						<div class="col-lg-2 col-md-6">
							<h4>Current Password</h4>
							<input type="password" name="currentPassword" class="value w-100"
								placeholder="Enter Current Password"/>
						</div>
						
						<div class="col-lg-2 col-md-6">
							<h4>Temporary Code</h4>
							<input type="password" name="tempCode" value="" class="value w-100"
								placeholder="Enter Temporary Code"/>
						</div>
						<div class="col-lg-2 col-md-6">
						<h4>Generate code </h4>
 						 <button onclick=" return generateTempCode()"  class="btn btn-primary main-btn-hov" id="generateTempCodeId"  value="GENARE BUTTON...">Generate Temp code</button> 
						
						</div>
						
						
					</div>

					<div class="row set-in">
						<div class="col-lg-3 col-md-6">
							<h4>New Password</h4>
							    <input type="password"  class="value w-100" id="password" name="newPassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"  
							     placeholder="Enter New Password"
							     />
							<!-- <input type="text" name="" class="value w-100"
								placeholder="Enter New Password"> -->
						</div>
						<div class="col-lg-3 col-md-6">
							<h4>Re - Enter New Password</h4>
							<input type="password" name="reEnterNewPassword" id="confirm_password" class="value w-100"
								placeholder="Re-Enter New Password"/>
						</div>
						<div >
						<h4> &nbsp;</h4>
						<b><span id='matchMessage'></span></b>
						</div>
						 
						<div id="message">
						 		 <h1>Password must contain the following:</h1>
						  			<p id="letter" class="invalid">A <b>lowercase</b> letter</p>
						  			<p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
						 			<p id="number" class="invalid">A <b>number</b></p>
						  			<p id="length" class="invalid">Minimum <b>8 characters</b></p>
						</div>
						
					</div>

					<!-- Miscellaneous end-->

				</div>


			</section>
		</section>

	<spring:message code="ChangePassword.TemporaryCode.Validity"></spring:message>

	</form:form>

 

	<!-- footer -->
	<footer> </footer>
	<!-- footer end -->

	 	<script
		src="<c:url value='/layout/js/vendor/modernizr-3.6.0.min.js' />"></script>
	<script src="<c:url value='/layout/js/vendor/jquery-3.2.1.min.js' />"></script>
	<script src="<c:url value='/layout/js/bootstrap.js' />"></script>

	<script src="<c:url value='/layout/js/plugins.js' />"></script>
<script src="<c:url value='/layout/js/fineCrop.js' />"></script>
<script src="<c:url value='/layout/js/master.js' />"></script>

  <script>

var status=false;
var myInput = document.getElementById("password");
var letter = document.getElementById("letter");
var capital = document.getElementById("capital");
var number = document.getElementById("number");
var length = document.getElementById("length");

// When the user clicks on the password field, show the message box
myInput.onfocus = function() {
  document.getElementById("message").style.display = "block";
}

// When the user clicks outside of the password field, hide the message box
myInput.onblur = function() {
  document.getElementById("message").style.display = "none";
}

// When the user starts to type something inside the password field
myInput.onkeyup = function() {
  // Validate lowercase letters
  var lowerCaseLetters = /[a-z]/g;
  if(myInput.value.match(lowerCaseLetters)) {  
    letter.classList.remove("invalid");
    letter.classList.add("valid");
  } else {
    letter.classList.remove("valid");
    letter.classList.add("invalid");
  }
  
  // Validate capital letters
  var upperCaseLetters = /[A-Z]/g;
  if(myInput.value.match(upperCaseLetters)) {  
    capital.classList.remove("invalid");
    capital.classList.add("valid");
  } else {
    capital.classList.remove("valid");
    capital.classList.add("invalid");
  }

  // Validate numbers
  var numbers = /[0-9]/g;
  if(myInput.value.match(numbers)) {  
    number.classList.remove("invalid");
    number.classList.add("valid");
  } else {
    number.classList.remove("valid");
    number.classList.add("invalid");
  }
  
  // Validate length
  if(myInput.value.length >= 8) {
    length.classList.remove("invalid");
    length.classList.add("valid");
  } else {
    length.classList.remove("valid");
    length.classList.add("invalid");
  }

  if(myInput.value.match(lowerCaseLetters && upperCaseLetters && numbers) && myInput.value.length >= 8) {
 		status=true; 	 		
  } else {
		status=false;
	} 

  
}

/* $('  #confirm_password').on('keyup', function () {
    if ($('#password').val() == $('#confirm_password').val()) {
        $('#matchMessage').html('Matching').css('color', 'green');
    } else {
    	 $('#matchMessage').html('Not Matching').css('color', 'red');
     }

    alert("status :"+status);
          
}); */

 

</script>
</body>

</html>
