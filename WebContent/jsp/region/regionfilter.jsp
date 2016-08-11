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
<input class="hidden" id="regioncountryfilter" type="text" name="regioncountryfilter" value="">
<input class="hidden" id='noItemSelected' value="<fmt:message key="NOITEM_SELECTED" bundle="${bndmessages}"/>">

<div class="container-fluid bg-grey">
 	    <c:if test="${message != null}">
			<div class="alert alert-${message.type}" role="alert"><fmt:message key="${message.text}" bundle="${bndmessages}"/></div>
		</c:if>
		<div id="alerts"></div>
 	   <h2><fmt:message key="regionfilter.header" bundle="${bndmmenu}"/>
       </h2>
      
		<div class="form-group ">
		  <c:set var="cntrarray" value="${fn:split(regioncountryfilter, ',')}" />
          <label for= "country" class="lb-lg" ><fmt:message key="label.countryname" bundle="${bndmmenu}"/>
          </label>
	      
	      <select size="10" multiple class="form-control input-lg selectpicker sm" id="notEmptselect" name="country" 
	      		onchange="getMultiSel();">
            <option disabled>select item</option>
   				<c:forEach var="cntr" items="${countries}" varStatus="cnt">
					<option value="${cntr.cntrId}"
		 				<c:forEach var="cn" items="${cntrarray}">
    	    	          	<c:if test="${cn == cntr.cntrId}">selected</c:if>
                		</c:forEach>
					>${cntr.cntrName}</option>
				</c:forEach>
           </select>
      </div>
		
  	   <div class="form-group ">
          <label for= "reg1" class="lb-lg"><fmt:message key="label.namepattern" bundle="${bndmmenu}"/></label>
         <input class="form-control input-lg" id="reg1" type="text" value="${regionnamepattern}" name="regionnamepattern">
       </div>

  <div class="text-center">
    <div class="btn-group"> 
	  <button type="button" class="btn1  btn-default btn-lg btn-info" 
	  				onclick="getMultiSel(); setCommand4('ShRegionList');"> 
	  	<fmt:message key="button.showlist"  bundle="${bndmmenu}"/> 
	  </button>
	  <button type="button" class="btn1   btn-default btn-lg btn-warning"  onclick="setCommand3('Back2MainMenu')">
	  	<fmt:message key="button.mainmenu"  bundle="${bndmmenu}"/>
	  </button>
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
	 $('#regioncountryfilter').val(selected.join(','));
  };
</script>
</html>