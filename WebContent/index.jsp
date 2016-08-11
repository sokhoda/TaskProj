

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
          <link rel="stylesheet" href="js/bower_components/bootstrap/dist/css/bootstrap.min.css">
		  <script src="js/bower_components/jquery/dist/jquery.min.js"></script>
		  <script src="js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        
    </head>
    <body  onload="document.main.submit()">
        <form action="Controller" method="post" name = "main" id="main" > 
			<input class="hidden" type="text"  name="command" value="Init">
        </form>
    </body>
</html>
