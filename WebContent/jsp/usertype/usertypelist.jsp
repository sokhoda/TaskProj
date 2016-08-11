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
<input class="hidden" id ="Id" name="utypeId" type="text" value="" >

<div class="container-fluid bg-grey">

 	    <c:if test="${message != null}">
			<div class="alert alert-${message.type}" role="alert"><fmt:message key="${message.text}"  bundle="${bndmessages}"/></div>
		</c:if>
 	   <h2><fmt:message key="label.listofutypes"  bundle="${bndmmenu}"/>
 	   </h2>
 	   
 	   <table class="table table-striped table-hover table-condensed " id="utypetab" style="width:95%;">
            <thead>
            <tr>
                <th><h3><fmt:message key="tbl.utypeid" bundle="${bndmmenu}"/></h3></th>
                <th><h3><fmt:message key="tbl.utypename" bundle="${bndmmenu}"/></h3></th>
            </tr>
            </thead>
            <tbody>
			<c:forEach var="utype" items="${utypelist}" varStatus="count">
             <tr>
                <td class="shrink">${utype.uTypeId}</td>
                <td align="left" id="row${utype.uTypeId}">${utype.uTypeName}</td>
                <td>
                    <button type="button" class="btn btn-primary btn-xs" id="${utype.uTypeId}"
                            onclick="doUpdate(event,'ShUserTypeUpdate')"><fmt:message key="button.update"  bundle="${bndmmenu}"/>
                    </button>
                </td>
                <td>
                    <button  type="button" class="btn btn-warning btn-xs" id="${utype.uTypeId}"
                       onclick="showDelete(event,'UserTypeDelete')"><fmt:message key="button.delete"  bundle="${bndmmenu}"/>
                    </button>
                </td>
            </tr>
             </c:forEach>
            </tbody>
        </table>
        
  <div class="text-center">      
   <div class="btn-group"> 
      <button type="button" id="crtupd" class="btn1  btn-default btn-lg btn-success" 
   			onclick="setCommand3('ShUserTypeCreate')">
				<fmt:message key="button.crtupd0" bundle="${bndmmenu}" />
	  </button>
      <button type="button" class="btn1   btn-default btn-lg btn-info" onclick="setCommand3('ShUserTypeFilter')">
	  	<fmt:message key="button.showfilter"  bundle="${bndmmenu}"/>
	  </button>
	  <button type="button" class="btn1   btn-default btn-lg btn-warning" onclick="setCommand3('Back2MainMenu')">
	  	<fmt:message key="button.mainmenu"  bundle="${bndmmenu}"/>
	  </button>
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
                    <button type="button" class="btn btn-primary modalDeleteBtn" name="" onclick="doDelete('UserTypeDelete')"><fmt:message key="button.delete"  bundle="${bndmmenu}"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</body>

<script type="text/javascript">
var name, latch = false;
function doDelete(comm) {
	$('#command').val(comm);
	$('#Id').val($('.modalDeleteBtn').attr('id'));
 	$('#myForm').submit();
};

function showDelete(event) {
	$('.modalDeleteBtn').attr('id', event.target.id);
	name = $('#utypetab #row' + event.target.id).text();
	if (!latch){
		$('#myModalLabel').text($('#myModalLabel').text() + ' \'' + name +'\'');
		latch = true;
	}
	$('#myModal').modal('show');
};
</script>
</html>