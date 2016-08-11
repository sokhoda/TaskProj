<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ page errorPage="../generalErrorPage.jsp" %>
 
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
<fmt:setLocale value=""/>

<form action="Controller" method="post" id="myForm">
<input class="hidden" id ="command" name="command" type="text" value="">
<input class="hidden" id ="Id" name="docId" type="text" value="" >

<div class="container-fluid bg-grey">

 	    <c:if test="${message != null}">
			<div class="alert alert-${message.type}" role="alert"><fmt:message key="${message.text}"  bundle="${bndmessages}"/></div>
		</c:if>
 	   <h2>
 	   		<fmt:message key="label.listofdocuments"  bundle="${bndmmenu}"/>
 	   </h2>
 	   
 	   <table class="table table-striped table-hover table-condensed " id="documenttab" style="width:95%;">
            <thead>
            <tr>
	            <th><h3></h3></th>
	            <th><h3></h3></th>
                <th><h3><fmt:message key="tbl.docid" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th><h3><fmt:message key="tbl.docno" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th><h3><fmt:message key="tbl.docdate" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th><h3><fmt:message key="tbl.docname" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th style="min-width: 7em"><h3><fmt:message key="tbl.docsum" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th style="min-width: 18em"><h3><fmt:message key="tbl.route" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th><h3><fmt:message key="tbl.docstatus" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th><h3><fmt:message key="tbl.mcid" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th><h3><fmt:message key="tbl.mgrid" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th><h3><fmt:message key="tbl.custid" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th style="min-width: 10em"><h3><fmt:message key="tbl.doccustcontactpersname" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th style="min-width: 10em"><h3><fmt:message key="tbl.doccustcontactpersphone" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th><h3><fmt:message key="tbl.doctag" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th style="min-width: 6em"><h3><fmt:message key="tbl.doccargweight" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th style="min-width: 6em"><h3><fmt:message key="tbl.doccargvolume" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th><h3><fmt:message key="tbl.docloaddate" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th><h3><fmt:message key="tbl.docloadaddress" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th><h3><fmt:message key="tbl.docunloaddate" bundle="${bndmmenu}"></fmt:message></h3></th>
                <th><h3><fmt:message key="tbl.docunloadaddress" bundle="${bndmmenu}"></fmt:message></h3></th>
            </tr>
            </thead>
            <tbody>
			<c:forEach var="doc" items="${documentlist}" varStatus="count">
             <tr>
                <td>
                    <button type="button" class="btn btn-primary btn-xs" id="${doc.docId}"
                            onclick="doUpdate(event,'ShDocumentUpdate')"><fmt:message key="button.update"  bundle="${bndmmenu}"/>
                    </button>
                </td>
                <td>
                    <button  type="button" class="btn btn-warning btn-xs" id="${doc.docId}"
                       onclick="showDelete(event)"><fmt:message key="button.delete"  bundle="${bndmmenu}"/>
                    </button>
                </td>
                <td>${doc.docId}</td>
                <td align="left">${doc.docNo}</td>
                <td align="left">
                   <fmt:formatDate value="${doc.docDate}" pattern="dd.MM.yyyy" /> </td>
                <td align="left" id="row${doc.docId}">${doc.docName}</td>
                <td align="left">${doc.docSum}</td>
                <td align="left">${doc.docRoute}</td>
                <td align="left">
	                <c:forEach var="docstat" items="${docstatuses}">
	                	<c:if test="${doc.docStatus == docstat.id}">
	                		<fmt:message key="${docstat.name}"  bundle="${bndmessages}"/>
	                	</c:if>
	                </c:forEach>
                </td>
                <td align="left">
	                <c:forEach var="aglist" items="${docagentlist}">
	                	<c:if test="${doc.mcId == aglist.agId}">
	                		${aglist.agName}
	                	</c:if>
	                </c:forEach>
                </td>
                <td align="left">
	                <c:forEach var="aglist" items="${docagentlist}">
	                	<c:if test="${doc.mgrId == aglist.agId}">
	                		${aglist.agName}
	                	</c:if>
	                </c:forEach>
                </td>
                <td align="left">
	                <c:forEach var="aglist" items="${docagentlist}">
	                	<c:if test="${doc.custId == aglist.agId}">
	                		${aglist.agName}
	                	</c:if>
	                </c:forEach>
                </td>
                <td align="left">${doc.docCustContactPersName}</td>
                <td align="left">${doc.docCustContactPersPhone}</td>
                <td align="left">${doc.docTag}</td>
                <td align="left">${doc.docCargWeight}</td>
                <td align="left">${doc.docCargVolume}</td>
               	<td align="left"> 
                   <fmt:formatDate value="${doc.docLoadDate}" pattern="dd.MM.yyyy" /> </td>
                <td align="left">${doc.docLoadAddress}</td>
               	<td align="left"> 
                   <fmt:formatDate value="${doc.docUnloadDate}" pattern="dd.MM.yyyy" /> </td>
                <td align="left">${doc.docUnloadAddress}</td>
            </tr>
             </c:forEach>
            </tbody>
        </table>
<div class="text-center">      
   <div class="btn-group"> 
      <button type="button" id="crtupd" class="btn1  btn-default btn-lg btn-success" 
   			onclick="setCommand3('ShDocumentCreate')">
				<fmt:message key="button.crtupd0" bundle="${bndmmenu}" />
	  </button>
      <button type="button" class="btn1   btn-default btn-lg btn-info" onclick="setCommand3('ShDocumentFilter')">
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
                    <button type="button" class="btn btn-primary modalDeleteBtn" name=""
                       onclick="doDelete('DocumentDelete')"><fmt:message key="button.delete"  bundle="${bndmmenu}"/>
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
	name = $('#documenttab #row' + event.target.id).text();
	if (!latch){
		$('#myModalLabel').text($('#myModalLabel').text() + ' \'' + name +'\'');
		latch = true;
	}
	$('#myModal').modal('show');
};
</script>
</html>