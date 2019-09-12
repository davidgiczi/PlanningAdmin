<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete a Record</title>

<style>

table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
  }
th, td {
  padding: 15px;
}

</style>


</head>
<body style="background-color:powderblue;">

<h1 align="center" style="color:white">Are you sure you want to delete the record?</h1><br>

<table style="width:100%" >
  <tr>
  	<th style="background-color:WhiteSmoke; ">Number</th>
    <th style="background-color:WhiteSmoke; ">Plan Number</th>
    <th style="background-color:WhiteSmoke; ">Plan Name</th> 
    <th style="background-color:WhiteSmoke; ">Name of CATV Controller</th>
    <th style="background-color:WhiteSmoke; ">Date of CATV Control</th>
    <th style="background-color:WhiteSmoke; ">Name of UPC Controller</th>
    <th style="background-color:WhiteSmoke; ">Date of UPC Control</th>
    <th style="background-color:WhiteSmoke; ">Comment</th>
    <th style="background-color:WhiteSmoke; ">E-kozmu</th>
    <th style="background-color:WhiteSmoke; ">Road Statement</th>
    <th style="background-color:WhiteSmoke; ">Owner Statement</th>
    <th style="background-color:WhiteSmoke; ">Planning is Ready</th>
  </tr>
<tr>
	<td>${record}</td>
    <td>${reg.planNumber}</td>
    <td>${reg.planName}</td> 
    <td>${reg.nameOfCATVControlPerson}</td>
    <td>${reg.dateOfCATVControl}</td>
    <td>${reg.nameOfUPCControlPerson}</td> 
    <td>${reg.dateOfUPCControl}</td>
    <td>${reg.comment}</td>
    
    <c:if test = "${empty reg.ekozmu}">
          <td>-</td>
      </c:if>
    <c:if test = "${not empty reg.ekozmu}">
          <td>${reg.ekozmu}</td>
      </c:if>
    
    <c:if test = "${empty reg.roadStatement}">
          <td>-</td>
      </c:if>
    <c:if test = "${not empty reg.roadStatement}">
          <td>${reg.roadStatement}</td>
      </c:if>
      
    <c:if test = "${empty reg.ownerStatement}">
          <td>-</td>
      </c:if>
    <c:if test = "${not empty reg.ownerStatement}">
          <td>${reg.ownerStatement}</td>
      </c:if>
    
     <c:if test = "${reg.isOK}">
          <td>Igen</td>
      </c:if>
    <c:if test = "${!reg.isOK}">
          <td>Nem</td>
      </c:if>
    
</tr>
</table><br><hr><br>


<c:if test="${fromAll}">

		<form method="GET" action="DeleteRecord">
   		<input type="submit" value="Yes"/>
   		<input type="hidden" value="${reg.id}" name="id" />
   		<input type="hidden" value="true" name="fromAll" />
		</form>
		 
		<form method="POST" action="GetAllRegistrations">
		<input type="submit" value="No" style="float:right" />
		</form>
</c:if>

<c:if test="${!fromAll}">
		
		<form method="GET" action="DeleteRecord">
   		<input type="submit" value="Yes"/>
   		<input type="hidden" value="${reg.id}" name="id" />
   		<input type="hidden" value="false" name="fromAll" />
		</form>

		<form method="POST" action="GetPlanRegistrations">
		<input type="submit" value="No" style="float:right" />
		</form>
</c:if>

</body>
</html>