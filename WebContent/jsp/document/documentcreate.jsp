<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="../generalErrorPage.jsp" %>
<%@ page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="js/bower_components/bootstrap/dist/css/bootstrap.min.css">
	<link rel="stylesheet" 
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<script
	src="js/bower_components/jquery/dist/jquery.min.js"></script>
<script
	src="js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<style>
	 <%@include file='../proj.css'%>
	 <%@include file='../datepicker.css'%>
</style>

<script src="js/FillClick.js"></script> 
<script src="js/bootstrap-datepicker.js"></script> 
</head>

<body>
    <fmt:setLocale value=""/>
    
<form action="Controller" method="post" id="myForm">
	<input class="hidden" id="command" name="command" type="text" value="">
	<input class="hidden" id="docid" name="docid" type="text"	value="${document.docId}">
	
<!-- 	mcagents, custagents, mgragents -->
	<input class="hidden" id="docselector" type="text" name="docselector" value="">
	
<input class="hidden" id="numroute" type="text" name="numroute" value="${numroute}">
<input class="hidden" id="alpharoute" type="text" name="alpharoute" value="${alpharoute}">
	<div class="container-fluid bg-grey">
		<c:if test="${message != null}">
			<div class="alert alert-${message.type}" role="alert"><fmt:message key="${message.text}" bundle="${bndmessages}"/></div>
		</c:if>

		<h2>
			<span class="glyphicon glyphicon-file"></span>
			<fmt:message key="documentcreate.header" bundle="${bndmmenu}" />
		</h2>
<!-- 			<i class="fa fa-truck" style="font-size:36px;"></i> -->
<!-- ORDER HEADER-->
<div class="text-center">
	<div class="form-inline">
  		<fmt:formatDate var="docDateFormatted" pattern="dd.MM.yyyy"  value="${document.docDate}"/>
		<div class="form-group">
			<input class=" form-control input-lg doctitle " id="docname" type="text" 
						value="<fmt:message key="label.doctitle" bundle="${bndmmenu}" />" name="docname">
			<label class="head control-label" for="reg1">#</label>
			<input class=" form-control input-lg docno" id="reg1" type="text" 
						value="${document.docNo}" name="docno">
						
			<label class="control-label  head marginleft" for="reg1">
					<fmt:message key="label.docdate" bundle="${bndmmenu}" /></label>						
			<input class=" form-control input-lg docno datepicker1" id="reg1" type="text" 
							value="${docDateFormatted}" name="docdate">
		</div>
	</div>
</div>
<br>
<div class="panel panel-default">
<div class="panel-body">
<!-- mycompany -->
	  <div class="col-sm-6">
		<div class="form-group ">
			<label for="mc" class="lb-lg"><fmt:message key="label.mycompany" bundle="${bndmmenu}" /></label>
			 <div class="form-inline">  
				<select size="1" class="form-control input-lg selectpicker docselector" id="mc" name="mc"
						onchange="set2Session('mcagents')">
					<option disabled>select item</option>
					<c:forEach var="mcag" items="${mcagents}" varStatus="cnt">
							<option value="${mcag.agId}"
							<c:if test="${mcag.agId == document.mcId}"> selected </c:if>
							>${mcag.agName}</option>
					</c:forEach>
				</select>
				<button type="button" class="btn2  btn-default btn-lg " 
		   				onclick="setDocSelector('mcagents')">...
		 	    </button>
			</div>
		 </div>
	 </div>
<!-- customer-->
	 <div class="col-sm-6">
		<div class="form-group ">
			<label for="cust" class="lb-lg"><fmt:message key="label.cust" bundle="${bndmmenu}" /></label> 
			 <div class="form-inline">  
				<select size="1" class="form-control input-lg selectpicker docselector" id="cust" name="cust"
					onchange="set2Session('custagents')">
					<option disabled>select item</option>
					<c:forEach var="custag" items="${custagents}" varStatus="cnt">
							<option value="${custag.agId}"
							<c:if test="${custag.agId == document.custId}"> selected </c:if>
							>${custag.agName}</option>
					</c:forEach>
				</select>
				<button type="button" class="btn2  btn-default btn-lg " 
		   				onclick="setDocSelector('custagents')">...
		 	    </button>
	 	    </div>
		</div>
	</div>
</div>
</div>
<!-- cargo -->
	    <div class="panel panel-warning">
	         <div class="panel-heading lb-lg"><fmt:message key="cargo" bundle="${bndmmenu}" /></div>
	          <div class="panel-body">
		          <div class="col-sm-6 col-md-5">
					<div class="form-group">
						<label for="reg1" class="lb-lg"><fmt:message key="label.cargname" bundle="${bndmmenu}" />
						</label> 
						<input class="form-control input-lg " id="reg1" type="text" 
							value="${document.docTag}" name="cargname">
					</div>
				  </div> 
				  <div class="col-sm-6 col-md-3">
					<div class="form-group ">
						<label for="reg1" class="lb-lg"><fmt:message key="label.cargweight" bundle="${bndmmenu}" />
						</label> 
						<input class="form-control input-lg" id="reg1" type="text" style=" max-width: 200px;"
							value="${document.docCargWeight}" name="cargweight">
					</div>
				  </div>
				  <div class="col-sm-6 col-md-3">
					<div class="form-group ">
						<label for="reg1" class="lb-lg"><fmt:message key="label.cargvolume" bundle="${bndmmenu}"/>
						</label> 
						<input class="form-control input-lg" id="reg1" type="text" style="max-width: 200px;"
							value="${document.docCargVolume}" name="cargvolume">
					</div>
				  </div> 
			  </div> 
		</div>
<!-- route -->
		 <div class="panel panel-danger">
	         <div class="panel-heading lb-lg"><fmt:message key="label.docroute" bundle="${bndmmenu}" /></div>
	          <div class="panel-body">
		          <div class="col-sm-6 col-md-10">
						<input class="form-control input-lg " id="selectedroute" type="text" style="width:100%"
							value="" name="selectedroute" readonly >
				  </div> 
				  <div class="col-sm-6 col-md-2">
						<button type="button" id="editdocroute" class="btn-default btn-lg btn-primary"
						onclick="editroute('ShDocMiscFilter')">
							<fmt:message key="button.edit" bundle="${bndmmenu}" />
						</button>
				  </div>
			  </div> 
		</div>
		
<!-- load -->
		 <div class="panel panel-success">
	         <div class="panel-heading lb-lg"><fmt:message key="label.loadinfo" bundle="${bndmmenu}" /></div>
	          <div class="panel-body">
		          <div class="col-sm-6 col-md-5">
					<div class="form-group">
						<label for="reg1" class="lb-lg"><span class="glyphicon glyphicon-home"></span>
						<fmt:message key="label.loadaddress" bundle="${bndmmenu}" />
						</label> 
						<input class="form-control input-lg " id="reg1" type="text"
							value="${document.docLoadAddress}" name="loadaddress">
					</div>
				  </div> 
				  <div class="col-sm-6 col-md-3">
  					<fmt:formatDate var="docLoadDateFormatted" pattern="dd.MM.yyyy" value="${document.docLoadDate}"/>
					<div class="form-group ">
						<label for="ldate" class="lb-lg"><span class="glyphicon glyphicon-time"></span>
						<fmt:message key="label.loaddate" bundle="${bndmmenu}" />
						</label> 
							<div class="input-group date" data-date="${docLoadDateFormatted}" data-date-format="dd.mm.yyyy">
  								<input class="form-control input-lg dtinput datepicker1"  id="ldate" type="text"   
  										 value="${docLoadDateFormatted}" name="loaddate">
  								<span class="add-on"><i class="fa fa-calendar fa-3x"></i></span>
							</div>
					</div>
				  </div>
			  </div> 
		</div>
		
<!-- unload -->
		 <div class="panel panel-info">
	         <div class="panel-heading lb-lg"><fmt:message key="label.unloadinfo" bundle="${bndmmenu}" /></div>
	          <div class="panel-body">
		          <div class="col-sm-6 col-md-5">
					<div class="form-group">
						<label for="reg1" class="lb-lg"><span class="glyphicon glyphicon-home"></span>
						<fmt:message key="label.unloadaddress" bundle="${bndmmenu}" />
						</label> 
						<input class="form-control input-lg " id="reg1" type="text" 
							value="${document.docUnloadAddress}" name="unloadaddress">
					</div>
				  </div>
				  <div class="col-sm-6 col-md-3">
  					<fmt:formatDate var="docUnloadDateFormatted" pattern="dd.MM.yyyy" value="${document.docUnloadDate}"/> 
					<div class="form-group ">
						<label for="udate" class="lb-lg"><span class="glyphicon glyphicon-time"></span>
						<fmt:message key="label.unloaddate" bundle="${bndmmenu}" />
						</label> 
						<div class="input-group date" data-date="${docUnloadDateFormatted}" data-date-format="dd.mm.yyyy">
							<input class="form-control input-lg dtinput datepicker1"  id="udate" type="text"   
									 value="${docUnloadDateFormatted}" name="unloaddate">
							<span class="add-on"><i class="fa fa-calendar fa-3x"></i></span>
						</div>
							
					</div>
				  </div>
			  </div> 
		</div>
<!-- manager -->			
		 <div class="panel panel-warning">
	         <div class="panel-heading lb-lg"><fmt:message key="label.manager" bundle="${bndmmenu}" /></div>
	          <div class="panel-body">
		          <div class="col-sm-6 col-md-5">
					<div class="form-group">
						<label for="mgr" class="lb-lg"><span class="glyphicon glyphicon-user"></span>
							<fmt:message key="label.mgrname" bundle="${bndmmenu}" />
						</label> 
						<div class="form-inline">  
							<select size="1" class="form-control input-lg selectpicker docselector" id="mgr" name="mgr"
								onchange="set2Session('mgragents')">
								<option disabled>select item</option>
								<c:forEach var="mgrag" items="${mgragents}" varStatus="cnt">
										<option value="${mgrag.agId}"
										<c:if test="${mgrag.agId == mgragent.agId}"> selected </c:if>
										>${mgrag.agName}</option>
								</c:forEach>
							</select>
							<button type="button" class="btn2  btn-default btn-lg " 
			   					onclick="setDocSelector('mgragents')">...
			 	    		</button>
		 	    		</div>
					</div>
				  </div> 
				  <div class="col-sm-6 col-md-3">
					<div class="form-group ">
						<label for="reg1" class="lb-lg"><span class="glyphicon glyphicon-phone"></span>
						<fmt:message key="label.mgrphone" bundle="${bndmmenu}" />
						</label> 
						<input class="form-control input-lg" id="reg1" type="text" style=" max-width: 200px;"
							value="${mgragent.agPhone}" name="mgrphone" readonly>
					</div>
				  </div>
			  </div> 
		</div>
<!-- CustContactPers-->
		 <div class="panel panel-success">
	         <div class="panel-heading lb-lg"><fmt:message key="label.custcontactpers" bundle="${bndmmenu}" /></div>
	          <div class="panel-body">
		          <div class="col-sm-6 col-md-5">
					<div class="form-group">
						<label for="reg1" class="lb-lg"><span class="glyphicon glyphicon-user"></span>
						<fmt:message key="label.name" bundle="${bndmmenu}" />
						</label> 
						<input class="form-control input-lg " id="reg1" type="text" 
							value="${document.docCustContactPersName}" name="custcontactpersname">
					</div>
				  </div> 
				  <div class="col-sm-6 col-md-3">
					<div class="form-group ">
						<label for="reg1" class="lb-lg"><span class="glyphicon glyphicon-phone"></span>
						<fmt:message key="label.phone" bundle="${bndmmenu}" />
						</label> 
						<input class="form-control input-lg" id="reg1" type="text" style=" max-width: 200px;"
							value="${document.docCustContactPersPhone}" name="custcontactpersphone">
					</div>
				  </div>
			  </div> 
		</div>
<!-- Sum		-->
		 <div class="panel panel-primary">
	         <div class="panel-heading lb-lg"><i class="fa fa-cc-visa"></i>
	         <fmt:message key="label.payment" bundle="${bndmmenu}" /></div>
	          <div class="panel-body">
		          <div class="col-sm-6 col-md-5">
					<div class="form-group">
						<label for="reg1" class="lb-lg">
						<fmt:message key="label.docsum" bundle="${bndmmenu}" />
						</label> 
						<input class="form-control input-lg " id="reg1" type="text" style = "max-width: 200px;"
							value="${document.docSum}" name="docsum">
					</div>
				  </div> 
				  <div class="col-sm-6 col-md-3">
					<div class="form-group ">
					</div>
				  </div>
			  </div> 
		</div>	
	<div class="text-center">
		<div class="btn-group">
			<button type="button" id="crtupd"  class="btn1  btn-default btn-lg btn-success" 
			onclick="setCommand3('DocumentCreate')"><fmt:message key="button.create" bundle="${bndmmenu}" />
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
	    $( ".datepicker1" ).datepicker({
	     		format: 'dd.mm.yyyy'
		});
	    $('.date').datepicker();
	 });
	
	loadRouteJSP();
	//...
function setDocSelector(listtype){
	$('#docselector').val(listtype);
	$('#command').val('ShDocAgentFilter');
	$('#myForm').submit();
}

function set2Session(listtype){
	
	$('#docselector').val(listtype);
// 	$('#agobjname').val(agobjname);
	$('#command').val('DocSetAgentOnChange');
	$('#myForm').submit();
}
	
function editroute(comm){
// 		alert(comm);
		$('#command').val(comm);
		$('#myForm').submit();
};
		$('#btnList').on('click', function() {
			var documentfilter = $('#documentfilter').val();
			var docnopattern = $('#docnopattern').val();
			if (documentfilter == '' && docnopattern == ''){
				 setCommand3('ShDocumentFilter');
			} else{
				 setCommand3('ShDocumentList');
			}
		});
</script>
</html>