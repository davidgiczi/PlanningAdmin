<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<title>Modify a Record</title>
</head>
<body style="background-color:powderblue;">

<h1 align="center" style="color:white">Modify a Record of a Plan</h1><hr><br>

<form method="POST" action="ModifyRecord">
		
		<input type="hidden" value="${record.id}" name="id"/>
		Plan Number: <input type="text" size="15" maxlength="255" value="${record.planNumber}" name="number"/><br><br>
		Plan Name:<br><textarea  name="name" cols="51" rows="5">${record.planName}</textarea>
		
		<!-- input type="text" size="100" maxlength="255" value="${record.planName}" name="name"/--><br><br>
		Name of CATV Controller: <input type="text" size="30" maxlength="255" value="${record.nameOfCATVControlPerson}" name="catv"/><br><br>
		Date of CATV Control: <input type="text" size="30" maxlength="255" value="${record.dateOfCATVControl}" name="catvdate"/><br><br>
		Name of UPC Controller: <input type="text" size="30" maxlength="255" value="${record.nameOfUPCControlPerson}" name="upc"/><br><br>
		Date of UPC Control: <input type="text" size="30" maxlength="255" value="${record.dateOfUPCControl}" name="upcdate"/><br><br>
		Comment: <br><textarea  name="comment" cols="51" rows="5">${record.comment}</textarea>
		
		<!-- input type="text" size="255" maxlength="255" value="${record.comment}" name="comment"/--><br><br>
		E-kozmu: <input type="text" size="30" maxlength="255" value="${record.ekozmu}" name="kozmu"/><br><br>
		Road Statement: <input type="text" size="30" maxlength="255" value="${record.roadStatement}" name="road"/><br><br>
		Owner Statement: <input type="text" size="30" maxlength="255" value="${record.ownerStatement}" name="owner"/><br><br>
		
		<c:if test="${ready}">
		Planning is ready: <input type="checkbox" value="true" name="ready" checked="checked"/><br><br>
		</c:if>
		
		<c:if test="${!ready}">
		Planning is ready: <input type="checkbox" value="true" name="ready"/><br><br>
		</c:if>
			
		
		<c:if test="${fromAll}">
		<input type="hidden" value="true" name="all"/>
		</c:if>
		
		<c:if test="${!fromAll}">
		<input type="hidden" value="false" name="all"/>
		</c:if>
		
		
   		<input type="submit" value="Modify" />
   	
		
</form><hr><br>

		<c:if test="${fromAll}">
		<form method="POST" action="GetAllRegistrations">
   		 <input type="submit" value="Back" />
		</form>
	</c:if>
	
	<c:if test="${!fromAll}">
		<form method="POST" action="GetPlanRegistrations">
   		 <input type="submit" value="Back" />
		</form>
	</c:if>

</body>
</html>