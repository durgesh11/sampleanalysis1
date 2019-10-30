<%@include file="header.jsp"%>
<html class="no-js" lang="">
<body>
	<form:form modelAttribute="settingForm" id="settingFormId"
		action="saveSetting?${_csrf.parameterName}=${_csrf.token}"
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<section class="mainframe">
			<section class="container">
				<div class="hedaings set-head">
					<h2>Settings</h2>
					<div class="comm-btn">


						<button type="button"
							class="btn btn-primary hq-hist bor-set main-btn"
							 >CANCEL</button>

						<input type="submit" value="Save" class="btn btn-primary hq-hist bor-set main-btn" > 
						 

					</div>
				</div>
				<div class="prot-sec">

					<div class="rep text-center set-2">
						<h3>General</h3>
					</div>

					<input type="hidden"
						value="${employee.chainOwner.showGraphs}"
						id="showGraphsIds" />
 
					<c:forEach var="listGraphObj" items="${listGraph}" varStatus="loop">
						<c:choose>
							<c:when
								test="${listGraphObj.i18nKeyMessage != null   && loop.index %2==0}">
								<div class="row  mag-box set-lab">

									<div class="col-md-4">
										<div class="form-group">
											<input type="checkbox" id="box${loop.index+1}"
												name="i18nKeyMessage" value="${listGraphObj.id}">

											<%--   <form:checkbox path="i18nKeyMessage"
												value="${listGraphObj.i18nKeyMessage}"
												id="box${loop.index+1}" />  --%>

											<spring:message code="${ listGraphObj.i18nKeyMessage}"
												var="i18nKeyMessageVar" />
											<%-- 											<label> ${i18nKeyMessageVar } </label> --%>
											<label for="box${loop.index+1}" id="box${loop.index+1}">${loop.index+1}
												${i18nKeyMessageVar }</label>
										</div>
									</div>
							</c:when>
							<c:when
								test="${listGraphObj.i18nKeyMessage != null   && loop.index %2!=0}">

								<div class="col-md-4">
									<div class="form-group">

										<input type="checkbox" id="box${loop.index+1}"
											name="i18nKeyMessage" value="${listGraphObj.id}">
										<%--  <form:checkbox path="i18nKeyMessage"
												value="${listGraphObj.i18nKeyMessage}"
												id="box${loop.index+1}" />  --%>
										<spring:message code="${ listGraphObj.i18nKeyMessage}"
											var="i18nKeyMessageVar" />
										<%-- <label> ${i18nKeyMessageVar } </label> --%>
										<label for="box${loop.index+1}" id="box${loop.index+1}">${loop.index+1}
											${i18nKeyMessageVar }</label>
									</div>
								</div>
				</div>
				</c:when>

				</c:choose>
				</c:forEach>
				</div>
				<div class="rep text-center set-2">
					<h3>Upload</h3>
				</div>


				<div class="row mag-box set-lab">
					<div class="col-md-4">
						<label>Upload Company Logo</label>
					</div>

					<input type="file" id="logoId" name="file"
						accept="image/png, image/jpeg" onchange="readURL(this);">
					<img id="blah" src="#" alt="your image" />
				</div>
				</div>

				</div>


			</section>
		</section>

	</form:form>

	<!-- footer -->

	<footer> </footer>


	<!-- footer end -->



	<script
		src="<c:url value='/layout/js/vendor/modernizr-3.6.0.min.js' />"></script>
	<script src="<c:url value='/layout/js/vendor/jquery-3.2.1.min.js' />"></script>
	<script src="<c:url value='/layout/js/bootstrap.js' />"></script>

	<script src="<c:url value='/layout/js/plugins.js' />"></script>

	<script src="<c:url value='/layout/js/main.js' />"></script>
	<script src="<c:url value='/layout/js/master.js' />"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			var showGraphsId = $('#showGraphsIds').val();
			var showGraphsIds = showGraphsId.split(",");
			for (var i = 0; i < showGraphsIds.length; i++) {
				$("#box" + showGraphsIds[i]).attr("checked", "true");
			}
		});
	</script>

</body>

</html>