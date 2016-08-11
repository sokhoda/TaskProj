<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="generalErrorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>	
<html>
<head>
 <!--  <link rel="stylesheet" href="js/bower_components/bootstrap/dist/css/bootstrap.min.css"> -->
  <!-- <script src="js/bower_components/jquery/dist/jquery.min.js"></script> -->
  <!-- <script src="js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script> -->
  <link rel="stylesheet" href="js/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <script src="js/bower_components/jquery/dist/jquery.min.js"></script>
  <script src="js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
   
  <style>
     <%@include file='proj.css' %>
  </style>
    <script src="js/FillClick.js"></script>
</head> 
<body>

<form action="Controller" method="post" id="myForm">
<input class="hidden" id ="command" name="command" type="text" value="">
<input class="hidden" id ="language" name="language" type="text" value="">
<div class="container-fluid bg-grey">

 		<c:if test="${message != null}">
			<div class="alert alert-${message.type}" role="alert"><fmt:message key="${message.text}"  bundle="${bndmessages}"/></div>
		</c:if>
		<div id="alerts"></div>
 	   <h2><span class="glyphicon glyphicon-log-in"></span> <fmt:message key="userlogin.header" bundle="${bndmmenu}"/>
 	   </h2>
 	   <div class="pull-right">
	      	<div class="dropdown">
			  <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Lang
			  	<span class="caret"></span>
			  </button>
			  <ul class="dropdown-menu">
				  <c:forTokens items="${langstring}" delims="," var="item">
				     <li><a href="" onclick="return setCommand1(event)" id="${item}">${item}</a></li>
		    	  </c:forTokens>
			  </ul>
			</div>
		</div>
  	   <div class="form-group ">
          <label for= "reg1" class="lb-lg"><fmt:message key="label.userlogin" bundle="${bndmmenu}"/></label>
          <input class="form-control input-lg" id="reg1" type="text" value="" name="userlogin">
       </div>
  	   <div class="form-group ">
          <label for= "reg1" class="lb-lg"><fmt:message key="label.userpassword" bundle="${bndmmenu}"/></label>
          <input class="form-control input-lg" id="reg1" type="password" value="" name="userpassword">
       </div>

<div class="text-center">
  <div class="btn-group">
	  <button type="button" class="btn1  btn-default btn-lg btn-info" onclick="setCommand3('Login');"> 
	  	<fmt:message key="button.login"  bundle="${bndmmenu}"/> 
	  </button>
  </div>
 </div>
   <footer style="margin-top:10px; text-align: center">&copy; <fmt:message key="allRights"  bundle="${bndmmenu}"/></footer>
</div>
 </form>
</body>

<script type="text/javascript">
</script>
</html>