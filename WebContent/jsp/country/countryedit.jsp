<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ page errorPage="../generalErrorPage.jsp" %>
 
<!DOCTYPE html>
<html>
<head>
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
	<input class="hidden" id="agtId" name="cntrId" type="text"	value="${country.cntrId}">
	<input class="hidden" id="countrynamepattern" type="text" name="countrynamepattern" value="${countrynamepattern}">

		
	<div class="container-fluid bg-grey">
		<c:if test="${message != null}">
			<div class="alert alert-${message.type}" role="alert"><fmt:message key="${message.text}"  bundle="${bndmessages}"/></div>
		</c:if>

		<h2>
			<fmt:message key="countryedit.header${mode}" bundle="${bndmmenu}" />
		</h2>
		<div class="form-group ">
          <label for= "reg1" class="lb-lg" ><fmt:message key="label.countryname" bundle="${bndmmenu}"/></label>
          <input class="form-control input-lg" id="reg1" type="text" name="countryname" value="${country.cntrName}">
       </div>
       
       <div class="form-group ">
          <label for= "reg1" class="lb-lg" ><fmt:message key="label.countrytag" bundle="${bndmmenu}"/></label>
          <input class="form-control input-lg" id="reg1" type="text" name="countrytag" value="${country.cntrTag}">
       </div>
       
       <div class="text-center" >
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
				setCommand3('CountryCreate');
			} else {
				setCommand3('CountryUpdate');
			}
		});
		$('#btnList').on('click', function() {
				 setCommand3('ShCountryList');
		});
		
	});
</script>
</html>