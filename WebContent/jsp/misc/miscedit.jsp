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
	<input class="hidden" id="mscId" name="mscId" type="text" value="${misc.mscId}">
    <input class="hidden" id="cntrId" name="cntrId" type="text"	value="${cntrId}">
	<input class="hidden" id="regId" name="regId" type="text"	value="${regId}">
	
    <input class="hidden" id="misccountryfilter" type="text" name="misccountryfilter" value="${misccountryfilter}">
	<input class="hidden" id="miscregionfilter" type="text" name="miscregionfilter" value="${miscregionfilter}">
	<input class="hidden" id="miscnamepattern" type="text" name="miscnamepattern" value="${miscnamepattern}">

	<div class="container-fluid bg-grey">
		<c:if test="${message != null}">
			<div class="alert alert-${message.type}" role="alert"><fmt:message key="${message.text}" bundle="${bndmessages}"/></div>
		</c:if>

		<h2>
			<fmt:message key="miscedit.header${mode}" bundle="${bndmmenu}" />
		</h2>
<!-- 		 country -->
		<div class="form-group ">
			<label for="country" class="lb-lg"><fmt:message
					key="label.countryname" bundle="${bndmmenu}" /></label> 
			<select size="1" class="form-control input-lg selectpicker sm" id="country" name="country"
			onchange="onCountryChange(event,'OnCountryChange')">
				<option disabled>select item</option>
				<c:forEach var="cntr" items="${countries}" varStatus="cnt">
					<option value="${cntr.cntrId}" 
						<c:if test="${cntr.cntrId == cntrId}">selected</c:if>
						>${cntr.cntrName}</option>
				</c:forEach>
			</select>
		</div>
		<!-- 		 region -->
		<div class="form-group ">
			<label for="region" class="lb-lg"><fmt:message
					key="label.regionname" bundle="${bndmmenu}" /></label> 
			<select size="1" class="form-control input-lg selectpicker sm" id="region" name="region">
				<option disabled>select item</option>
				<c:forEach var="reg" items="${regions}" varStatus="cnt">
					<option value="${reg.regId}" 
					<c:if test="${reg.regId == regId}">selected</c:if>
					>${reg.regName}</option>
				</c:forEach>
			</select>
		</div>
		
		<div class="form-group ">
			<label for="reg1" class="lb-lg"><fmt:message key="label.miscname" bundle="${bndmmenu}" />
			</label> 
			<input class="form-control input-lg" id="reg1" type="text"
				value="${misc.mscName}" name="miscname">
		</div>
		<div class="form-group ">
			<label for="reg1" class="lb-lg"><fmt:message key="label.misctag" bundle="${bndmmenu}" />
			</label>
			 <input	class="form-control input-lg" id="reg1" type="text"
				value="${misc.mscTag}" name="misctag">
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
		<footer style="margin-top:10px; text-align:center">&copy; <fmt:message key="allRights" bundle="${bndmmenu}"/></footer>
	</div>
	</form>
</body>
<script type="text/javascript">
$(function() {
	$('#crtupd').on('click', function() {
		var cntrid = $('#country').find("option:selected").val();
		var regid=$('#region').find("option:selected").val();
		
		$('#cntrId').val(cntrid);
		$('#regId').val(regid);
		if ('${mode}' == 0) {
			setCommand3('MiscCreate');
		} else {
			setCommand3('MiscUpdate');
		}
	});
	
	$('#btnList').on('click', function() {
		var miscregionfilter = $('#miscregionfilter').val();
		var miscnamepattern = $('#miscnamepattern').val();
		if ('${mode}' == 0) {
			if (miscregionfilter == '' && miscnamepattern == ''){
			 setCommand3('ShMiscFilter');
			}else{
			 setCommand3('ShMiscList');
			}
		} else {
			 setCommand3('ShMiscList');
		}
	});
});

function onCountryChange(event, val){
	$('#command').val(val);
	var selected = $(event.target).find("option:selected").val();
	$('#cntrId').val(selected);
	$('#myForm').submit();
	return false;
}
</script>
</html>