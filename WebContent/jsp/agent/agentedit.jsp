<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ page errorPage="../generalErrorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
<!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<link rel="stylesheet"	href="js/bower_components/bootstrap/dist/css/bootstrap.min.css">
<script src="js/bower_components/jquery/dist/jquery.min.js"></script>
<script	src="js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<style> <%@include file='../proj.css'%>
</style>

<script src="js/FillClick.js"></script> 
</head>
<body>

<form action="Controller" method="post" id="myForm">
	<input class="hidden" id="command" name="command" type="text" value="">
	<input class="hidden" id="agId" name="agId" type="text"	value="${agent.agId}">
	
	<input class="hidden" id="agentagtypefilter" type="text" name="agentagtypefilter" value="${agentagtypefilter}">
	<input class="hidden" id="agentnamepattern" type="text" name="agentnamepattern" value="${agentnamepattern}">

	<div class="container-fluid bg-grey">
		<c:if test="${message != null}">
			<div class="alert alert-${message.type}" role="alert"><fmt:message key="${message.text}"  bundle="${bndmessages}"/></div>
		</c:if>

		<h2>
			<span class="glyphicon glyphicon-user"></span>
			<fmt:message key="agentedit.header${mode}" bundle="${bndmmenu}" />
		</h2>
		<div class="form-group ">
			<label for="agenttype" class="lb-lg"><fmt:message
					key="label.agtypename" bundle="${bndmmenu}" /></label> 
			<select size="1" class="form-control input-lg selectpicker sm" id="agenttype" name="agenttype">
				<option disabled>select item</option>
				<c:forEach var="agt" items="${agtypes}" varStatus="cnt">
						<option value="${agt.id}"
						<c:if test="${agt.id == agent.agtId}"> selected </c:if>
						>${agt.agtName}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group ">
			<label for="reg1" class="lb-lg"><fmt:message key="label.agentname" bundle="${bndmmenu}" />
			</label> 
			<input class="form-control input-lg" id="reg1" type="text"
				value="${agent.agName}" name="agentname">
		</div>
		<div class="form-group ">
			<label for="reg1" class="lb-lg"><span class="glyphicon glyphicon-home"></span> 
			<fmt:message key="label.agentaddress" bundle="${bndmmenu}" /></label> 
			<input	class="form-control input-lg" id="reg1" type="text"
				value="${agent.agAddress}" name="agentaddress">
		</div>
		<div class="form-group ">
			<label for="reg1" class="lb-lg"><span class="glyphicon glyphicon-phone"></span> 
			<fmt:message key="label.agentphone" bundle="${bndmmenu}" /></label>
			 <input	class="form-control input-lg" id="reg1" type="text"
				value="${agent.agPhone}" name="agentphone">
		</div>
		<div class="form-group ">
			<label for="reg1" class="lb-lg"><span
				class="glyphicon glyphicon-envelope"></span> <fmt:message key="label.agentemail" bundle="${bndmmenu}" />
			</label> 
				<input class="form-control input-lg" id="reg1" type="email"
				value="${agent.agEmail}" name="agentemail">
		</div>
		<div class="form-group ">
			<label for="reg1" class="lb-lg"><fmt:message key="label.agentpassport" bundle="${bndmmenu}" />
			</label> 
			<input	class="form-control input-lg" id="reg1" type="text"	value="${agent.agPassport}" 
			name="agentpassport">
		</div>
		<div class="form-group ">
			<label for="reg1" class="lb-lg"><span
				class="glyphicon glyphicon-globe"></span> <fmt:message key="label.agentwww" bundle="${bndmmenu}" />
			</label> 
			<input class="form-control input-lg" id="reg1" type="text"
				value="${agent.agWww}" name="agentwww">
		</div>
		<div class="form-group ">
			<label for="reg1" class="lb-lg"><fmt:message key="label.agentcountry" bundle="${bndmmenu}" />
			</label>
			 <input	class="form-control input-lg" id="reg1" type="text"
				value="${agent.agCountry}" name="agentcountry">
		</div>
		<div class="form-group ">
			<label for="reg1" class="lb-lg"><fmt:message key="label.agenttag" bundle="${bndmmenu}" />
			</label>
			 <input	class="form-control input-lg" id="reg1" type="text"
				value="${agent.agTag}" name="agenttag">
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
				setCommand3('AgentCreate');
			} else {
				setCommand3('AgentUpdate');
			}
		});
		$('#btnList').on('click', function() {
			var agentagtypefilter = $('#agentagtypefilter').val();
			var agentnamepattern = $('#agentnamepattern').val();
			if ('${mode}' == 0) {
				if (agentagtypefilter == '' && agentnamepattern == ''){
				 setCommand3('ShAgentFilter');
				}else{
				 setCommand3('ShAgentList');
				}
			} else {
				 setCommand3('ShAgentList');
			}
		});
	});
</script>
</html>