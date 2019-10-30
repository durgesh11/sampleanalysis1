<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html class=" lang="">
<head>
<style>
.error { 
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}

#message {
	display: none;
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
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<%-- <link rel="stylesheet"
	href="<c:url value='/layout/css/demo_table.css' />"> --%>

</head>


<body>
	<header> </header>

	<!-- header end -->
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />

	<c:set var="pleaseSelect">
		<spring:message code="Message.pleaseSelect" />
	</c:set>



	<section class="mainframe">
		<section class="container">

			<div class="hedaings">

				<c:choose>
					<c:when test="${edit == true}">
						<h2>Update Employee</h2>
					</c:when>
					<c:otherwise>
						<h2>Add New Employee</h2>
					</c:otherwise>
				</c:choose>


				<div class="comm-btn emp">

					<form
						action="${pageContext.request.contextPath}/master-employees.html"
						method="get">

						<input type="submit" value="Employee Master"
							class="btn btn-primary main-btn-hov">

					</form>
				</div>
			</div>
		</section>
	</section>
	<br />



	<div class="container">

		<form:form method="POST" class="form-horizontal" autocomplete="off"
			commandName="employee"
			action="${pageContext.request.contextPath}/employeeForm">

			<form:hidden path="id" />
			
			 

			<!--  fake fields are a workaround for chrome/opera autofill getting the wrong fields -->
			<input id="usernames" style="display: none" type="text"
				name="fakeusernameremembered">
			<input id="passwords" style="display: none" type="password"
				name="fakepasswordremembered">

			<div class="form-group">
				<label class="control-label col-sm-2"><b>User Name :</b></label> <input 
					type="text" name="userName" class="value" pattern="[A-Za-z]{1,50}"
					title="User Name should be alphabets" value="${employee.userName}"
					placeholder="Enter Employee Name" required />
				<form:errors path="userName" class="error" />
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><b>First Name :</b></label> <input
					type="text" name="firstName" class="value" pattern="[A-Za-z]{1,50}"
					title="First Name should be alphabets"  value="${employee.firstName}"
					placeholder="Enter First Name" required />
				<form:errors path="firstName" class="error" />
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><b>Last Name :</b></label> <input
					title="Last Name should be alphabets" type="text" name="lastName"
					class="value" pattern="[A-Za-z]{1,50}"  value="${employee.lastName}"
					placeholder="Enter Last Name" required />
				<form:errors path="lastName" class="error" />
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><b>Telephone :</b></label> <input
					type="text" name="telephone" class="value"
					pattern="[7-9]{1}[0-9]{9}"
					title="Telephone should be in 10 digit Number format"  value="${employee.telephone}"
					placeholder="Enter Telephone" required />
				<form:errors path="telephone" class="error" />
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><b>E-mail Address
						:</b></label> <input type="text" id="prevent_autofill" autocomplete="off"
					style="display: none" tabindex="-1" />
				<!-- <input type="text" name="emailAddress"  style="display:none;">  -->
				<input type="text" name="emailAddress" class="value"
					placeholder="Enter E-mail Address" autocomplete="nope" value="${employee.emailAddress}"
					pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required />
				<form:errors path="emailAddress" class="error" />
			</div>


			<div class="form-group">
				<label class="control-label col-sm-2"><b> Role :</b></label> <select
					class="value" name="role.id" required  title="Please Select"  >
					<option value="">--Select--</option>
					<c:forEach items="${roleMasterList}" var="roles"
						varStatus="counter">
						<option value="${roles.id}" selected="selected">${roles.name}</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><b>Password :</b></label> <input
					type="password" class="value" id="password" autocomplete="nope"
					name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" 
					title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
					placeholder="Enter New Password" required />
				<form:errors path="password" class="error" />
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><b>Confirmed
						Password :</b></label> <input type="password" name="confirmPassword"
					class="value" id="confirm_password" autocomplete="off"  
					placeholder="Enter Confirm Password" required>
				<%-- <input type="text"  name="confirmPassword" class="value" placeholder="Enter Confirm Password"   /> --%>
				<form:errors path="confirmPassword" class="error" />

				<div>
					<h4>&nbsp;</h4>
					<b><span id='matchMessage'></span></b>
				</div>

				<div id="message">
					<h1>Password must contain the following:</h1>
					<p id="letter" class="invalid">
						A <b>lowercase</b> letter
					</p>
					<p id="capital" class="invalid">
						A <b>capital (uppercase)</b> letter
					</p>
					<p id="number" class="invalid">
						A <b>number</b>
					</p>
					<p id="length" class="invalid">
						Minimum <b>8 characters</b>
					</p>
				</div>
			</div>





			<div class="  text-center">
				<div class="col-sm-offset-2 col-sm-10">

					<c:choose>
						<c:when test="${edit == true}">
							<input type="submit" name="update"
								class="btn btn-primary main-btn-hov" value="Update Employee" />
						</c:when>
						<c:otherwise>
							<input type="submit" name="new"
								class="btn btn-primary main-btn-hov" value="Add New Employee" />
						</c:otherwise>
					</c:choose>


					<!-- <button type="submit" class="btn btn-primary main-btn-hov">Submit</button>
					
					&nbsp;
					<input type="submit" name="renew" class="button" value="Renew" /> -->
				</div>
			</div>
		</form:form>
	</div>



	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />


	<!-- footer -->
	<footer> </footer>
	<!-- footer end -->

	<script src="<c:url value='/layout/js/vendor/jquery-3.2.1.min.js' />"></script>

	<script src="<c:url value='/layout/js/jquery.js' />"></script>
	<script src="<c:url value='/layout/js/jquery.dataTables.js' />"></script>
	<script src="<c:url value='/layout/js/main.js' />"></script>
	<script src="<c:url value='/layout/js/bootstrap.js' />"></script>

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



	var password = document.getElementById("password"), confirm_password = document
			.getElementById("confirm_password");

	function validatePassword() {
		if (password.value != confirm_password.value) {
			confirm_password.setCustomValidity("Passwords Don't Match");
		} else {
			confirm_password.setCustomValidity('');
		}
	}

	password.onchange = validatePassword;
	confirm_password.onkeyup = validatePassword;
</script>
</body>
</html>
