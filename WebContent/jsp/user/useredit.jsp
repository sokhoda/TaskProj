<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ page errorPage="../generalErrorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
<!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<link rel="stylesheet"
	href="js/bower_components/bootstrap/dist/css/bootstrap.min.css">
<script
	src="js/bower_components/jquery/dist/jquery.min.js"></script>
<script
	src="js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<style> <%@include file='../proj.css'%>
</style>

<script src="js/FillClick.js"></script> 
</head>
<body>

<form action="Controller" method="post" id="myForm">
	<input class="hidden" id="command" name="command" type="text" value="">
	<input class="hidden" id="userId" name="userId" type="text"	value="${user.userId}">
	<input class="hidden" id="userutypefilter" type="text" name="userutypefilter" value="${userutypefilter}">
	<input class="hidden" id="userloginpattern" type="text" name="userloginpattern" value="${userloginpattern}">

	
	<jsp:useBean id="user" class="bean.User" scope="request"/>
	
	<div class="container-fluid bg-grey">
		<c:if test="${message != null}">
			<div class="alert alert-${message.type}" role="alert"><fmt:message key="${message.text}"  bundle="${bndmessages}"/></div>
		</c:if>

		<h2>
			<span class="glyphicon glyphicon-user"></span>
			<fmt:message key="useredit.header${mode}" bundle="${bndmmenu}" />
		</h2>
		<div class="form-group ">
			<label for="utype" class="lb-lg"><fmt:message key="label.utypename" bundle="${bndmmenu}" /></label> 
			<select size="1" class="form-control input-lg selectpicker sm" id="utype" name="utype">
				<option disabled>select item</option>
				<c:forEach var="utype" items="${utypes}" varStatus="cnt">
					<option value="${utype.uTypeId}" 
					<c:if test="${utype.uTypeId == user.uTypeId}">selected</c:if>
					>${utype.uTypeName}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group ">
			<label for="reg1" class="lb-lg"><fmt:message key="label.userlogin" bundle="${bndmmenu}" />
			</label> 
			<input class="form-control input-lg" id="reg1" type="text"
				value="<jsp:getProperty name="user" property="userLogin"/>" name="userlogin">
		</div>
		<div class="form-group ">
			<label for="reg1" class="lb-lg"><fmt:message key="label.userpassword" bundle="${bndmmenu}" />
			</label> 
			<input	class="form-control input-lg" id="reg1" type="password"	
				 value="<jsp:getProperty name="user" property="userPass"/>" name="userpass">
		</div>
	<div class="text-center">
		<div class="btn-group">
			<button type="button" id="crtupd"  class="btn1  btn-default btn-lg btn-success">
				<fmt:message key="button.crtupd${mode}" bundle="${bndmmenu}" />
			</button>
			<button type="button" id="btnList" class="btn1 btn-default btn-lg btn-info" >
				<fmt:message key="button.showlist" bundle="${bndmmenu}" />
			</button>
			<button type="button" class="btn1   btn-default btn-lg btn-warning"
				onclick="setCommand3('Back2MainMenu')">
				<fmt:message key="button.mainmenu" bundle="${bndmmenu}" />
			</button>
		</div>
	</div>
		<footer style="margin-top:10px; text-align: center">&copy; <fmt:message key="allRights"  bundle="${bndmmenu}"/></footer>
	</div>
	</form>
</body>
<script type="text/javascript">
	$(function() {
		$('#crtupd').on('click', function() {
			if ('${mode}' == 0) {
				setCommand3('UserCreate');
			} else {
				setCommand3('UserUpdate');
			}
		});
		$('#btnList').on('click', function() {
			var userutypefilter = $('#userutypefilter').val();
			var userloginpattern = $('#userloginpattern').val();
			if ('${mode}' == 0) {
				if (userutypefilter == '' && userloginpattern == ''){
				 setCommand3('ShUserFilter');
				}else{
				 setCommand3('ShUserList');
				}
			} else {
				 setCommand3('ShUserList');
			}
		});
	});
</script>
</html>