
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
<link rel="stylesheet"
	href="<c:url value='/layout/css/demo_table.css' />">

</head>


<body>
	<header> </header>

	<!-- header end -->
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />

 


	<section class="mainframe">
		<section class="container">

			<div class="hedaings">
				<h2>Employee Privileges</h2>
				<div class="comm-btn emp"></div>
			</div>
		</section>
	</section>
	<br />

	<div class="container">
		<div class="form-group">
			<label class="control-label col-sm-2"><b> Role :</b></label> <select
				class="value" name="role.id" onchange="return loadData(this)"
				title="Please Select" required>
				<option value="">--Select--</option>
				<c:forEach items="${roleMasterList}" var="roles" varStatus="counter">
					<option value="${roles.id}"  >${roles.name}</option>
				</c:forEach>
			</select>
		</div>

		<div id="refreshDiv11" style="display: none">
			<%@include file="master-employee-privileges-list.jsp"%>
		</div>
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
 
	<script src="<c:url value='/layout/js/main.js' />"></script>
	<script src="<c:url value='/layout/js/bootstrap.js' />"></script>
 


	<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {

		});

		function loadData(roleId) {
				 $.ajax({
					type : 'POST',
					url : 'show-employee-privileges',
					//contentType : "text/plain",
					data : {
						"roleId": roleId.value
					},
					headers : {
						"X-CSRF-TOKEN" : $("input[name='_csrf']").val()
					},
					success : function(data) {
						//console.log(data);
						$("#refreshDiv11").show();
						$("#refreshDiv11").html(data);
						//populateDataTable(myJsonData);
					},
					error : function(e) {
						console.log("There was an error with your request...");
						console.log("error: " + JSON.stringify(e));
					}
				}); 
		}
	</script>

</body>
</html>
