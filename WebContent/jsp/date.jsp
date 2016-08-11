<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="../generalErrorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mt" uri="/WEB-INF/tlds/myinfotag.tld" %>

<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>
   <fmt:message key="today" bundle="${bndmmenu}" var="text"/>
   <mt:timestmp label="${text}"/>
</p>
</body>
</html>