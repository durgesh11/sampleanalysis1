<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.accordion {
	background-color: #eee;
	color: #444;
	cursor: pointer;
	padding: 18px;
	width: 100%;
	border: none;
	text-align: left;
	outline: none;
	font-size: 15px;
	transition: 0.4s;
}

.active, .accordion:hover {
	background-color: #ccc;
}

.accordion:after {
	content: '\002B';
	color: #777;
	font-weight: bold;
	float: right;
	margin-left: 5px;
}

.active:after {
	content: "\2212";
}

.panel {
	padding: 0 18px;
	background-color: white;
	max-height: 0;
	overflow: hidden;
	transition: max-height 0.2s ease-out;
}
</style>
</head>
<body>



	<br />
	<br />
	<br />

	<br />
	<h2>Employee Privileges:</h2>
	<br />
	<br />

	<form:form method="POST" class="form-horizontal"
		commandName="employeeRolePrivileges"
		action="saveEmployeeRolePrivileges">


		<c:forEach items="${menuHeaderList}" var="menuHeader">
			<div class="accordion">
				<spring:message code="${menuHeader.i18nKeyMessage}" />
			</div>
			<div class="panel" id="checkboxes">
				<p>
					<c:forEach items="${rolePrivilegesList}" var="rolePrivileges">

						<c:choose>
							<c:when
								test="${rolePrivileges.menu.menuHeader.id eq menuHeader.id}">

								<%-- 	<input type="checkbox" class="value" id="${rolePrivileges.id}" value="${rolePrivileges.hidden}" name="${ rolePrivileges.id}"    /> --%>
								<input type="hidden" id="roleId" name="roleId"
									value="${rolePrivileges.roleId}" />

								<c:choose>
									<c:when test="${rolePrivileges.hidden eq true }">

										<form:checkbox path="multipleRole"
											value="${rolePrivileges.id}" />

									</c:when>
									<c:otherwise>
										<form:checkbox path="multipleRole"
											value="${rolePrivileges.id}" checked="checked" />

									</c:otherwise>
								</c:choose>
								<b><spring:message
										code="${rolePrivileges.menu.i18nKeyMessage}" /></b>
								<br>
							</c:when>
						</c:choose>

					</c:forEach>
				</p>
			</div>
		</c:forEach>

		<input type="submit" value="Submit">
	</form:form>

	<script>
		var acc = document.getElementsByClassName("accordion");
		var i;

		for (i = 0; i < acc.length; i++) {
			acc[i].addEventListener("click", function() {
				this.classList.toggle("active");
				var panel = this.nextElementSibling;
				if (panel.style.maxHeight) {
					panel.style.maxHeight = null;
				} else {
					panel.style.maxHeight = panel.scrollHeight + "px";
				}
			});
		}
	</script>

	<script type="text/javascript">
		$(document).ready(function() {

		});
	</script>

</body>
</html>
