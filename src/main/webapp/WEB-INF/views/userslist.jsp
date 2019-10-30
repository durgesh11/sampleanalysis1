<%@include file="header.jsp"%>
<html class="no-js" lang="">
<body>
	<input type="hidden" id="menuId"
		data-value="@Request.RequestContext.HttpContext.Session[" menu"]" />
	<!--[if lte IE 9]>
    <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
  <![endif]-->

	<!-- Add your site or application content here -->

	<!-- header -->

	<div id="headerResponse"></div>
	<!-- <header> </header> -->

	<!-- header end -->


	<!-- dashboard chart -->
	<section class="mainframe">
		<section class="container">

			<div class="hedaings comm-btn ">
				<h1>Welcome to ${sessionScope.employee.firstName} ${sessionScope.employee.lastName}</h1>
			</div>
			<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
			<div></div>
			<div></div>
		</section>
	</section>

	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />

	<!-- dashboard chart end -->

	<!-- footer -->
	<footer> </footer>
	<!-- footer end -->

	<script
		src="<c:url value='/layout/js/vendor/modernizr-3.6.0.min.js' />"></script>
	<script src="<c:url value='/layout/js/vendor/jquery-3.2.1.min.js' />"></script>
	<script src="<c:url value='/layout/js/bootstrap.js' />"></script>
	<script src="https://unpkg.com/popper.js"></script>
	<script src="<c:url value='/layout/js/chartist.min.js' />"></script>
	<script src="https://cdn.jsdelivr.net/npm/gijgo@1.9.6/js/gijgo.min.js"
		type="text/javascript"></script>
	<script src="<c:url value='/layout/js/plugins.js' />"></script>
	<script src="<c:url value='/layout/js/main.js' />"></script>


</body>


</html>
