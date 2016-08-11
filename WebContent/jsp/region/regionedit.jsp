<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="../generalErrorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
	<input class="hidden" id="regId" name="regId" type="text"	value="${region.regId}">
	<input class="hidden" id="regioncountryfilter" type="text" name="regioncountryfilter" value="${regioncountryfilter}">
	<input class="hidden" id="regionnamepattern" type="text" name="regionnamepattern" value="${regionnamepattern}">

	<div class="container-fluid bg-grey">
		<c:if test="${message != null}">
			<div class="alert alert-${message.type}" role="alert"><fmt:message key="${message.text}"  bundle="${bndmessages}"/></div>
		</c:if>

		<h2>
			<fmt:message key="regionedit.header${mode}" bundle="${bndmmenu}" />
		</h2>
		<div class="form-group ">
			<label for="country" class="lb-lg"><fmt:message
					key="label.countryname" bundle="${bndmmenu}" /></label> 
			<select size="1" class="form-control input-lg selectpicker sm" id="country" name="country">
				<option disabled>select item</option>
				<c:forEach var="cntr" items="${countries}" varStatus="cnt">
					<option value="${cntr.cntrId}" 
					<c:if test="${cntr.cntrId == region.cntrId}"> selected </c:if>
					>${cntr.cntrName}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group ">
			<label for="reg1" class="lb-lg"><fmt:message key="label.regionname" bundle="${bndmmenu}" />
			</label> 
			<input class="form-control input-lg" id="reg1" type="text"
				value="${region.regName}" name="regionname">
		</div>
		<div class="form-group ">
			<label for="reg1" class="lb-lg"><fmt:message key="label.regiontag" bundle="${bndmmenu}" /></label> 
			<input	class="form-control input-lg" id="reg1" type="text"
				value="${region.regTag}" name="regiontag">
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
				setCommand3('RegionCreate');
			} else {
				setCommand3('RegionUpdate');
			}
		});
		$('#btnList').on('click', function() {
				var regioncountryfilter = $('#regioncountryfilter').val();
				var regionnamepattern = $('#regionnamepattern').val();
			if ('${mode}' == 0) {
				if (regioncountryfilter == '' && regionnamepattern == ''){
				 setCommand3('ShRegionFilter');
				}else{
				 setCommand3('ShRegionList');
				}
			} else {
				 setCommand3('ShRegionList');
			}
		});
		
	});
</script>
</html>