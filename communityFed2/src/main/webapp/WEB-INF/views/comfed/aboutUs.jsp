<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Community Fed - Welcome</title>
<link href='http://fonts.googleapis.com/css?family=Gentium+Basic'
	rel='stylesheet' type='text/css'>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/styles.css"
	rel="stylesheet" type="text/css" />

<script
	src="${pageContext.request.contextPath}/resources/jquery-1.7.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/jquery.flexslider-min.js"></script>

</head>
<body>

	<div class="container">
		<header>
		<h1>
			Community Fed - About Us
		</h1>
		<jsp:include page="navList.jsp"></jsp:include> <br class="clearfloat" />
		</header>
		<section> <jsp:include page="slider.jsp"></jsp:include> <br
			class="clearfloat" />
			
			About us page.
		</section>
		<footer>
		<p>Copyright &copy; Community Fed Inc. All rights reserved.</p>
		</footer>
	</div>
</body>
</html>