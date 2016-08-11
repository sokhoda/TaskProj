<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="../generalErrorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
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
	<input class="hidden" id="utypeId" name="utypeId" type="text"	value="${utype.uTypeId}">
	<input class="hidden" id="utypenamepattern" type="text" name="utypenamepattern" value="${utypenamepattern}">

	<div class="container-fluid bg-grey">
		<c:if test="${message != null}">
			<div class="alert alert-${message.type}" role="alert"><fmt:message key="${message.text}"  bundle="${bndmessages}"/></div>
		</c:if>

		<h2>
			<span class="glyphicon glyphicon-user"></span>
			<fmt:message key="utypeedit.header${mode}" bundle="${bndmmenu}" />
		</h2>
		<div class="form-group ">
          <label for= "reg1" class="lb-lg" ><fmt:message key="label.utypename" bundle="${bndmmenu}"/></label>
          <input class="form-control input-lg" id="reg1" type="text" name="utypename" value="${utype.uTypeName}">
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
				setCommand3('UserTypeCreate');
			} else {
				setCommand3('UserTypeUpdate');
			}
		});
		$('#btnList').on('click', function() {
				 setCommand3('ShUserTypeList');
		});
	});
</script>
</html>