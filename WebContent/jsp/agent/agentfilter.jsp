<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="../generalErrorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html>	
<html>
<head>
  <link rel="stylesheet" href="js/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <script src="js/bower_components/jquery/dist/jquery.min.js"></script>
  <script src="js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
   
  <style>
     <%@include file='../proj.css' %>
  </style>
    <script src="js/FillClick.js"></script>
</head> 
<body>

<form action="Controller" method="post" id="myForm">
<input class="hidden" id ="command" name="command" type="text" value="">
<input class="hidden" id="agentagtypefilter" type="text" name="agentagtypefilter" value="">

<input class="hidden" id='noItemSelected' value="<fmt:message key="NOITEM_SELECTED" bundle="${bndmessages}"/>">

<div class="container-fluid bg-grey">
 		<c:if test="${message != null}">
			<div class="alert alert-${message.type}" role="alert"><fmt:message key="${message.text}" bundle="${bndmessages}"/></div>
		</c:if>
		<div id="alerts"></div>
 	   <h2><fmt:message key="agentfilter.header" bundle="${bndmmenu}"/>
 	   </h2>
      
		<div class="form-group ">
		  <c:set var="agtypearray" value="${fn:split(agentagtypefilter, ',')}" />
          <label for= "agenttype" class="lb-lg"><fmt:message key="label.agtypename" bundle="${bndmmenu}"/></label>
	      
	      <select size="10" multiple class="form-control input-lg selectpicker sm" id="notEmptselect" name="agenttype" 
	          onchange="getMultiSel();">
            <option disabled>select item</option>
             <c:forEach var="agt" items="${agtypes}" varStatus="cnt">
				<option value="${agt.id}"
					<c:forEach var="aagt" items="${agtypearray}">
               			<c:if test="${aagt == agt.id}">selected</c:if>
               		</c:forEach>				
				>${agt.agtName}</option>
			 </c:forEach>
        </select>
      </div>
		
  	   <div class="form-group ">
          <label for= "reg1" class="lb-lg"><fmt:message key="label.namepattern" bundle="${bndmmenu}"/></label>
         <input class="form-control input-lg" id="reg1" type="text" value="${agentnamepattern}" name="agentnamepattern">
       </div>

<div class="text-center">
  <div class="btn-group">
    <c:if test="${mode != selectmode}"> 
	  <button type="button" class="btn1  btn-default btn-lg btn-info" onclick="getMultiSel();  setCommand4('ShAgentList');"> 
	  	<fmt:message key="button.showlist" bundle="${bndmmenu}"/> 
	  </button>
	  <button type="button" class="btn1   btn-default btn-lg btn-warning"  onclick="setCommand3('Back2MainMenu')">
	  	<fmt:message key="button.mainmenu" bundle="${bndmmenu}"/>
	  </button>
	 </c:if>
	 <c:if test="${mode == selectmode }">
	   <button type="button" class="btn1  btn-default btn-lg btn-info" 
	   		onclick="getMultiSel(); setCommand4('DocInitDocSelector')"><fmt:message key="button.ok"  bundle="${bndmmenu}"/> 
	   </button>
	 </c:if>
  </div>
 </div>
   <footer style="margin-top:10px; text-align: center">&copy; <fmt:message key="allRights"  bundle="${bndmmenu}"/></footer>
</div>
 </form>
</body>

<script type="text/javascript">
function getMultiSel() {
	var selected =[];
         var brands = $('#notEmptselect option:selected');
	 $(brands).each(function(index, select){
     	selected.push($(select).val());
	 });
	 $('#agentagtypefilter').val(selected.join(','));
  };
</script>
</html>