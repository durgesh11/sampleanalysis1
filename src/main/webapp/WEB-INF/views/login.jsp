<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html  >
<head>

 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Login</title>
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="shortcut icon" href="<c:url value='/layout/favicon.ico' />"
	type="image/x-icon">
<link rel="icon" href="<c:url value='/layout/favicon.ico' />"
	type="image/x-icon">
<link rel="manifest" href="site.webmanifest">
<link rel="apple-touch-icon" href="icon.png">
<!-- Place favicon.ico in the root directory -->

<%-- <link rel="stylesheet" href="<c:url value='/layout/css/magnific-popup.css' />"> --%>
<link rel="stylesheet"
	href="<c:url value='/layout/css/bootstrap.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/layout/css/footable.bootstrap.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/layout/css/glyphicons/bootstrap-glyphicons.css' />">
<link rel="stylesheet" href="<c:url value='/layout/css/main.css' />">
 
</head>

<body >


	<div class="log-bg">
		<a href="http://www.aarav.co"><img
			src="<c:url value='/layout/img/AARAV-LOGO.png' />" alt="logo">
		</a>
	</div>

	<div class="log-head">
		<ul class="d-flex justify-content-between pl-0">
			<li><a href="http://www.aarav.co">Home</a></li>
			<li><a href="http://www.aarav.co/about-us/">About Us</a></li>
			<li><a href="http://www.aarav.co/fragrances/">Fragrances</a></li>
			<li><a href="http://www.aarav.co/flavors/">Flavors</a></li>
			<li><a href="http://www.aarav.co/contact-us/">Contact Us</a></li>
		</ul>
	</div>

	<!-- login -->
	<c:url var="loginUrl" value="/login" />
	<form action="${loginUrl}" method="post" class="form-horizontal" accept-charset="ISO-8859-1">
		<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
		<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<div class="mainframe">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-lg-5 log-in">
						<h2>LOGIN</h2>
						<div class="prot-sec">

							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p>
										<spring:message code="usrname.invalid" />
									</p>

								</div>
							</c:if>
							<c:if test="${param.logout != null}">
								<div class="alert alert-success">
									<p>
										<spring:message code="logged.out" />
									</p>
								</div>
							</c:if>
							<c:if test="${param.sessionOut != null}">
								<div class="alert alert-success">
									<p>
										<spring:message code="Login.Session.Expires" />
									</p>
								</div>
							</c:if>

							<h4>User Name</h4>
							 <input type="text" class="form-control"
								id="username" name="emailAddress" class="value"
								placeholder="Enter User Name" required>

							<!-- <input type="text" name="" class="value"> -->
							<h4>Password</h4>
							<input type="password"
								class="form-control" id="password" name="password" class="value"
								placeholder="Enter Password" required>

							<div class="comm-btn">
								<!-- <a href="forgot-password.html">Forgot Password?</a> -->
								<label></label>
								 <input type="submit" class="btn btn-block btn-primary btn-default" value="Login">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- login end-->


	<!-- footer -->
	<footer> </footer>
	
	
	<!-- footer end -->

	<script src="<c:url value='/layout/js/vendor/modernizr-3.6.0.min.js' />"></script>
	<script src="<c:url value='/layout/js/vendor/jquery-3.2.1.min.js' />"></script>
	<script src="<c:url value='/layout/js/bootstrap.js' />"></script>
	<script src="https://unpkg.com/popper.js"></script>
	<script src="<c:url value='/layout/js/plugins.js' />"></script>
	<script src="<c:url value='/layout/js/main.js' />"></script>



</body>
</html>