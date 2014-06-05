<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Home Page
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="${pageContext.request.contextPath}/cfHome">Test Home page</a>
<br/>

<a href="${pageContext.request.contextPath}/frmInput">Form submit and Validation test</a>
<br/>


<a href="${pageContext.request.contextPath}/restGet?id=10">AJAX Get test</a>
<br/>

<a href="${pageContext.request.contextPath}/comFed">Community Fed Home</a>
<br/>
</body>
<a href="${pageContext.request.contextPath}/hbTest">Hiberante Test</a>
<br/>
</body>


</html>
