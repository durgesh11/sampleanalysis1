
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

	<c:set var="pleaseSelect">
		<spring:message code="Message.pleaseSelect" />
	</c:set>



	<section class="mainframe">
		<section class="container">

			<div class="hedaings">
				<h2>Employee Master</h2>
				<div class="comm-btn emp">

					<form:form action="add-new-employee" method="get">

						<input type="submit"
							value="<spring:message code="Add.New" /> Employee"
							class="btn btn-primary main-btn-hov">

					</form:form>

				</div>
			</div>
		</section>
	</section>
	<br />

	<div class="container">
		<table cellpadding="0" cellspacing="0" border="0" data-sorting="false"
			class="display table       " id="example">

			<thead>
				<tr class="footable-header">
					<th class="">Sr no.</th>
					<th class="">User Name</th>
					<th class="">First Name</th>
					<th class="">Last Name</th>
					<th class="">Email Address</th>
					<th data-sortable="false">edit</th>
					<th data-sortable="false">Delete</th>
				</tr>
			</thead>
			<tbody class="" id=" ">
				<c:forEach items="${employeeList}" var="employee" varStatus="counter">
					<c:if test=""> </c:if>
					<tr >
						<td >${counter.count}</td>
						<td >${employee.userName}</td>
						<td >${employee.firstName}</td>
						<td >${employee.lastName}</td>
						<td >${employee.emailAddress}</td>
						<td ><a href="editEmployee/${employee.id}"
							class="edit" ><span
								class="icon-pencil black"></span></a></td>
						<td><a href="#" class="edit"
							onclick="return deleteEmployee('${employee.id}')"><span
								class="icon-delete btnDelete red"> </span></a></td>
					</tr>

				</c:forEach>
			</tbody>
			<tfoot class="">
				<tr>
					<td colspan="5" class="text-center"></td>
				</tr>
			</tfoot>

		</table>
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
	<%-- 	<script
		src="<c:url value='/layout/js/vendor/modernizr-3.6.0.min.js' />"></script>
	

	<script src="https://unpkg.com/popper.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/gijgo@1.9.6/js/gijgo.min.js" 		type="text/javascript"></script>--%>


	<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {

			var oTable = $('#example').dataTable();

			//	loadData();

		});

		/* 	$(document).on('hide.bs.modal','#myModal', function () {
		      //  alert('');
				//Do stuff here
			}); */

		function deleteEmployee(employeeId) {

			$('#modal-title').text('Any title you want!');
			var fired = false;
			$('#myModal').modal({
				backdrop : 'static',
				keyboard : false,
				'title' : 'Rank'
			}).on('click', '#delete', function(e) {
				//alert("delete Called.."+roleId);
				if (employeeId != -1) {
					$.ajax({
						type : "POST",
						//cache : false,
						url : 'deleteEmployee',
						async : false,
						data : {
							"employeeId" : employeeId
						},
						headers : {
							"X-CSRF-TOKEN" : $("input[name='_csrf']").val()
						},
						success : function(response) {
							  alert("Response :" + response);
							location.reload();
						}
					});
				}
			}).on('click', '#close', function(e) {
				employeeId = -1;
			});

		}

	</script>


	<div id="confirm" class="modal hide fade">
		<div class="modal-body">Are you sure?</div>
		<div class="modal-footer">
			<button type="button" data-dismiss="modal" class="btn btn-primary"
				id="delete">Delete</button>
			<button type="button" data-dismiss="modal" class="btn">Cancel</button>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title text-center">
						<b>Delete Employee</b>
					</h4>
					<button type="button" class="close" id="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<p>Are you sure?</p>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-danger"
						id="delete">Delete</button>
					&nbsp;
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="close">Close</button>
				</div>
			</div>

		</div>
	</div>
</body>
</html>
