<%@include file="includes.jsp"%>

<body>

	<c:set var="selectAll">
		<spring:message code="DataTable.All" />
	</c:set>
	<c:set var="pleaseChoose">
		<spring:message code="ArchiveSales.PleaseChoose" />
	</c:set>

	<input type="hidden" id="pleaseChooseId" value="${pleaseChoose}" />
	<input type="hidden" id="selectAllId" value="${selectAll}" />
	<%-- <%=request.getSession().getAttribute("menu")%> --%>
	<section class="container header">
		<div class="logo">
			<%-- <h1>
				<a href="index.html"> 	 <img
					src="getCompanyLogo/${sessionScope.employee.id}" 
					class="img-fluid" alt="Logo" width="75" height="75"/>
				</a>
				<p>
					<img src="getCompanyLogo/${sessionScope.employee.id}" class="test"
						alt="logo"
						onerror="this.onerror=null; this.src='<c:url value='/layout/img/blank_profile.jpg' />'">
				</p>
			</h1> --%>
 
				 <a href="${pageContext.request.contextPath}"> <img src="<c:url value='/layout/img/AARAV-LOGO.png' />" style="background-color: #00414f"
					alt="aarav logo"> </a>
			 
	 
		</div>




		<div class="navbar">
			<ul class="nav-menu">
				<!-- <li class="nav-item"><a href="index.html">Dashboard</a></li> -->


				<c:forEach var="menuHeader" items="${sessionScope.menuHeaderList}">
					<li class="nav-item navigation-item inner-menu  "><a href="#"
						class="mater-menu">
							<div>
								<b> <spring:message code="${menuHeader.i18nKeyMessage}"/> </b>
							</div>
					</a>
						<ul class="inner-menu-content">
							<div >
								<c:forEach var="employeeRolePrivileges"
									items="${sessionScope.employeeRolePrivilegesList}">
									<c:choose>
										<c:when test="${employeeRolePrivileges.menu.menuHeader.id eq menuHeader.id}">
											<li class="inner-menu-li"><a
																href="${ employeeRolePrivileges.menu.url}"><spring:message code="${employeeRolePrivileges.menu.i18nKeyMessage}"/> 
															</a>
											</li>
										</c:when>
									</c:choose>

								</c:forEach>

							</div>
						</ul></li>
				</c:forEach>

 
				<li class="nav-item navigation-item inner-menu bell"><a
					href="#" class="mater-menu"> <!-- <img src="img/bell.png" alt="bell icon"> -->
						<span class="icon-bell"></span><span class="badge badge-primary">5</span>
				</a>


					<ul class="inner-menu-content sub-pag">
						<div class="noti">
							<li class="inner-menu-li"><a href="#">Notification 1</a></li>
							<li class="inner-menu-li"><a href="#">Notification 2</a></li>
							<li class="inner-menu-li"><a href="#">Notification 3</a></li>
							<li class="inner-menu-li"><a href="#">Notification 4</a></li>
							<li class="inner-menu-li"><a href="#">Notification 5</a></li>
						</div>

					</ul>
				</li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">
						<div class="user-img">
							<img src="getUserProfileImage/${sessionScope.employee.id}"
								class="user-img" alt="Logo" width="100" height="70"
								onerror="this.onerror=null; this.src='<c:url value='/layout/img/blank_profile.jpg' />'" />
							<%-- <img src="<c:url value='/layout/img/user.jpg' />" alt="user-img"> --%>
						</div> <span class="user-name">${sessionScope.employee.userName}</span><b
						class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						 
						<!-- 			<li class="dropdown-item"><a href="profile.html"><span
								class="icon-Asset-22"></span>Profile</a></li>
						<li class="dropdown-item"><a href="setting.html"><span
								class="icon-Asset-21"></span>Settings</a></li> -->
						<li class="dropdown-item"><a href="<c:url value="/logout" />"><span
								class="icon-Asset-9"></span>Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</section>
</body>

</html>
