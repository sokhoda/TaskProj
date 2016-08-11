<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>ERROR</title>
    <link rel="stylesheet" href="js/bower_components/bootstrap/dist/css/bootstrap.min.css">
	<script src="js/bower_components/jquery/dist/jquery.min.js"></script>
	<script	src="js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<body>

<div style="margin-left: 20px">
    <h1 class="text-danger">
        <fmt:message key="errormessage" bundle="${bndmessages}"/>
    </h1>
    <h4>
    	<%=   exception.toString()    %>
    </h4>
    </div>
</body>
</html>
