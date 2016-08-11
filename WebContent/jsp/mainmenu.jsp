<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="generalErrorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="bean.Document" %>

<!DOCTYPE html>
<html >
<head>
  <title>Cargo Transp. Environment</title>
      <script src="js/FillClick.js"></script>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="js/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <script src="js/bower_components/jquery/dist/jquery.min.js"></script>
  <script src="js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<body>

<form action="Controller" method="post" id="myForm">
 	   
<jsp:include page="date.jsp"></jsp:include>

<input class="hidden" id ="command" name="command" type="text" value="">
<input class="hidden" id ="language" name="language" type="text" value="">

<script type="text/javascript">
</script>
<nav class="navbar navbar-inverse">
  <div class="container-fluid bg-grey">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"><fmt:message key="website" bundle="${bndmmenu}"/></a>
    </div>
    <ul class="nav navbar-nav">
   <!--       USERS -->
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href=""><fmt:message key="users" bundle="${bndmmenu}"/><span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="" onclick="return setCommand3('ShUserFilter')"><fmt:message key="users.userlist" bundle="${bndmmenu}"/></a></li>
            <li><a href="" onclick="return setCommand3('ShUserCreate')"><fmt:message key="users.createuser" bundle="${bndmmenu}"/></a></li>
            <li><a href="" onclick="return setCommand3('ShUserTypeFilter')"><fmt:message key="utypes.utypelist" bundle="${bndmmenu}"/></a></li>
            <li><a href="" onclick="return setCommand3('ShUserTypeCreate')"><fmt:message key="utypes.createutype" bundle="${bndmmenu}"/></a></li>
        </ul>
      </li>
	<!--      DOCUMENTS -->
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href=""><fmt:message key="documents" bundle="${bndmmenu}"/><span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="" onclick="return setCommand3('ShDocumentFilter')"><fmt:message key="documents.documentlist" bundle="${bndmmenu}"/></a></li>
            <li><a href="" onclick="return setCommand3('ShDocumentCreate')"><fmt:message key="documents.createdocument" bundle="${bndmmenu}"/></a></li>
        </ul>
      </li>
     <!--      AGENTS --> 
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href=""><fmt:message key="agents" bundle="${bndmmenu}"/><span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="" onclick="return setCommand3('ShAgentFilter')"><fmt:message key="agents.agentlist" bundle="${bndmmenu}"/></a></li>
            <li><a href="" onclick="return setCommand3('ShAgentCreate')"><fmt:message key="agents.createagent" bundle="${bndmmenu}"/></a></li>
            <li><a href="" onclick="return setCommand3('ShAgTypeFilter')"><fmt:message key="agtypes.agtypelist" bundle="${bndmmenu}"/></a></li>
            <li><a href="" onclick="return setCommand3('ShAgTypeCreate')"><fmt:message key="agtypes.createagtype" bundle="${bndmmenu}"/></a></li>
        </ul>
      </li>
      <!--      CARGOS --> 
<%--        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href=""><fmt:message key="cargos" bundle="${bndmmenu}"/><span class="caret"></span></a> --%>
<!--         <ul class="dropdown-menu"> -->
<%--             <li><a href="" onclick="return setCommand3('ShCargoCat')"><fmt:message key="cargos.cargolist" bundle="${bndmmenu}"/></a></li> --%>
<%--             <li><a href="" onclick="return setCommand3('ShCargoCreate')"><fmt:message key="cargos.createcargo" bundle="${bndmmenu}"/></a></li> --%>
<!--         </ul> -->
<!--       </li> -->
      <!--      COUNTRIES --> 
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="countries" bundle="${bndmmenu}"/><span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="" onclick="return setCommand3('ShCountryFilter')"><fmt:message key="countries.countrylist" bundle="${bndmmenu}"/></a></li>
            <li><a href="" onclick="return setCommand3('ShCountryCreate')"><fmt:message key="countries.createcountry" bundle="${bndmmenu}"/></a></li>
        </ul>
      </li>
        <!--      REGIONS --> 
       <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="regions" bundle="${bndmmenu}"/><span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="" onclick="return setCommand3('ShRegionFilter')"><fmt:message key="regions.regionlist" bundle="${bndmmenu}"/></a></li>
            <li><a href="" onclick="return setCommand3('ShRegionCreate')"><fmt:message key="regions.createregion" bundle="${bndmmenu}"/></a></li>
        </ul>
      </li>
       <!--      MISCS --> 
       <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="miscs" bundle="${bndmmenu}"/><span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="" onclick="return setCommand3('ShMiscFilter')"><fmt:message key="miscs.misclist" bundle="${bndmmenu}"/></a></li>
            <li><a href="" onclick="return setCommand3('ShMiscCreate')"><fmt:message key="miscs.createmisc" bundle="${bndmmenu}"/></a></li>
        </ul>
      </li>
	<!--LOGOUT -->    
    <li class="" ><a href=""onclick="return setCommand3('Logout')"><fmt:message key="logout" bundle="${bndmmenu}"/></a></li>
    <!--LANGUAGE -->  
	   <li class="dropdown  active"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Lang<span class="caret"></span></a>
        <ul class="dropdown-menu ">
		   <c:forTokens items="${langstring}" delims="," var="item">
		     <li><a href="" onclick="return setCommand1(event)" id="${item}">${item}</a></li>
    	   </c:forTokens>
        </ul>
      </li>
    </ul>
  </div>    
</nav>

<div class="container">
  <h3><fmt:message key="welcome" bundle="${bndmmenu}"/></h3>
  <p></p>
   <footer style="margin-top:10px; text-align: center">&copy; <fmt:message key="allRights"  bundle="${bndmmenu}"/></footer>
</div>
    </form>
</body>
</html>

