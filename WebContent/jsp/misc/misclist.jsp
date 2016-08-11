<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="../generalErrorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
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
<input class="hidden" id ="Id" name="mscId" type="text" value="" >
<input class="hidden" id="numroute" type="text" name="numroute" value="${numroute}">
<input class="hidden" id="alpharoute" type="text" name="alpharoute" value="${alpharoute}">

<div class="container-fluid bg-grey">
 	    <c:if test="${message != null}">
			<div class="alert alert-${message.type}" role="alert"><fmt:message key="${message.text}" bundle="${bndmessages}"/></div>
		</c:if>
	   <c:if test="${mode == selectmode}">
	      <div class="form-group">
	         <label for= "selectedroute" class="lb-lg"><fmt:message key="label.selectedroute" bundle="${bndmmenu}"/></label>
         	 <div class="form-inline"> 
	         	<input class="form-control input-lg" id="selectedroute" type="text" value=""
	         	 								name="selectedroute"	style="width:65%" readonly>
			  <div class="btn-group"> 
		        	<button type="button" class="btn2  btn-default btn-lg btn-danger" 
		   				onclick="deleteLast()"><fmt:message key="button.cancellast" bundle="${bndmmenu}" />
			 		</button>
			 		<button type="button" class="btn2  btn-default btn-lg btn-success" 
		   				onclick="setCommand3('DocInitNumAlphaRoute')"><fmt:message key="button.acceptroute" bundle="${bndmmenu}" />
			 		</button>
	 		   </div> 
 		 	 </div>						
	     </div>
       </c:if>
       
 	   <h2><fmt:message key="label.listofcities" bundle="${bndmmenu}"/>
 	   </h2>
 	   
 	   <table class="table table-striped table-hover table-condensed " id="misctab" style="width:95%;">
            <thead>
            <tr>
                <th><h3><fmt:message key="tbl.mscid" bundle="${bndmmenu}"/></h3></th>
                <th><h3><fmt:message key="tbl.mscregname" bundle="${bndmmenu}"/></h3></th>
                <th><h3><fmt:message key="tbl.mscname" bundle="${bndmmenu}"/></h3></th>
                <th><h3><fmt:message key="tbl.msctag" bundle="${bndmmenu}"/></h3></th>
            </tr>
            </thead>
            <tbody>
			<c:forEach var="msc" items="${misclist}" varStatus="count">
             <tr>
                <td align="left">${msc.mscId}</td>
                <td align="left">
	                <c:forEach var="reg" items="${regions}">
	                	<c:if test="${reg.regId == msc.regId}">
	                		${reg.regName}
	                	</c:if>
	                </c:forEach>
                </td>
                <td align="left" id="row${msc.mscId}">${msc.mscName}</td>
                <td align="left">${msc.mscTag}</td>
                <c:if test="${mode != selectmode}">
                <td>
                    <button type="button" class="btn btn-primary btn-xs" id="${msc.mscId}"
                            onclick="doUpdate(event,'ShMiscUpdate')"><fmt:message key="button.update"  bundle="${bndmmenu}"/>
                    </button>
                </td>
                <td>
                    <button  type="button" class="btn btn-warning btn-xs" id="${msc.mscId}"
                       onclick="showDelete(event)"><fmt:message key="button.delete"  bundle="${bndmmenu}"/>
                    </button>
                </td>
               </c:if>
               <c:if test="${mode == selectmode}">
               <td>
	                <button type="button" class="btn  btn-default btn-xs btn-success" id="${msc.mscId}" 
	                onclick="add2route(event)"><fmt:message key="button.select"  bundle="${bndmmenu}"/>
		  			</button>
	  			</td>
	  			</c:if>
            </tr>
             </c:forEach>
            </tbody>
        </table>
<div class="text-center">      
   <div class="btn-group"> 
   	<c:if test="${mode != selectmode}">
      <button type="button" id="crtupd" class="btn1  btn-default btn-lg btn-success" 
   			onclick="setCommand3('ShMiscCreate')">
				<fmt:message key="button.crtupd0" bundle="${bndmmenu}" />
	  </button>
	 </c:if>
      <button type="button" class="btn1   btn-default btn-lg btn-info" onclick="setCommand3('ShMiscFilter')">
	  	<fmt:message key="button.showfilter"  bundle="${bndmmenu}"/>
	  </button>
	 <c:if test="${mode != selectmode}">
	  <button type="button" class="btn1   btn-default btn-lg btn-warning" 
	    onclick="setCommand3('Back2MainMenu')">	<fmt:message key="button.mainmenu"  bundle="${bndmmenu}"/>
	  </button>
	 </c:if>
   </div>
 </div>
        <footer style="margin-top:10px; text-align: center">&copy; <fmt:message key="allRights"  bundle="${bndmmenu}"/></footer>
  
</div>
 </form>
 
 <!-- Modal -->
      <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="myModalLabel"> <fmt:message key="DELETE_ELEMENT" bundle="${bndmessages}"/> </h3>
                </div>
                <div class="modal-body">
                    <fmt:message key="CONFIRM_DELETION"  bundle="${bndmessages}"/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal" ><fmt:message key="button.cancel"  bundle="${bndmmenu}"/> 
                     </button>
                    <button type="button" class="btn btn-primary modalDeleteBtn" name=""
                       onclick="doDelete('MiscDelete')"><fmt:message key="button.delete"  bundle="${bndmmenu}"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
 
 
 
</body>
<script type="text/javascript">
	loadRouteJSP();

var name, latch = false;
function doDelete(comm) {
	$('#command').val(comm);
	$('#Id').val($('.modalDeleteBtn').attr('id'));
 	$('#myForm').submit();
};

function showDelete(event) {
	$('.modalDeleteBtn').attr('id', event.target.id);
	name = $('#misctab #row' + event.target.id).text();
	if (!latch){
		$('#myModalLabel').text($('#myModalLabel').text() + ' \'' + name +'\'');
		latch = true;
	}
	$('#myModal').modal('show');
};


</script>
</html>