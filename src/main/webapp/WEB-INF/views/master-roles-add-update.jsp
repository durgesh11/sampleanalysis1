
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
       					  <h2>Update Role</h2>
					</c:when>
					<c:otherwise>
     					   <h2>Add New Role</h2>
					</c:otherwise>
				</c:choose>


				<div class="comm-btn emp">

					<form action="${pageContext.request.contextPath}/master-roles.html" method="get">

						<input type="submit" value="Role Master"
							class="btn btn-primary main-btn-hov">

					</form>
				</div>
			</div>
		</section>
	</section>
	<br />



	<div class="container">

		<form:form method="POST" class="form-horizontal" commandName="roleMaster" action="${pageContext.request.contextPath}/roleForm">
			
				<form:hidden path="id" />

			
			<div class="form-group">
				<label class="control-label col-sm-2"><b>Role Name :</b></label>
				<form:input path="name" class="value" placeholder="Enter Role Name" />
				<form:errors path="name" class="error" />
			</div>

			<div class="  text-center">
				<div class="col-sm-offset-2 col-sm-10">
					
					<c:choose>
					<c:when test="${edit == true}">
       					  <input type = "submit" name = "update" class="btn btn-primary main-btn-hov" value="update"/>
					</c:when>
					<c:otherwise>
     					  <input type = "submit" name = "new" class="btn btn-primary main-btn-hov" value="Add New Role"/>
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


</body>
</html>
