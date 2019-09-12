<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Register a Record</title>

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

	<c:forEach items="${regs}" var="reg" varStatus="loop">
    
    <c:if test="${loop.count%2==0}">
    			
    			<c:if test="${!reg.isOK}">
    			
    <tr>
    
    <td style="background-color:WhiteSmoke; ">${loop.count}</td>
    <td style="background-color:WhiteSmoke; ">${reg.planNumber}</td>
    <td style="background-color:WhiteSmoke; ">${reg.planName}</td> 
    <td style="background-color:WhiteSmoke; ">${reg.nameOfCATVControlPerson}</td>
    <td style="background-color:WhiteSmoke; ">${reg.dateOfCATVControl}</td>
    <td style="background-color:WhiteSmoke; ">${reg.nameOfUPCControlPerson}</td> 
    <td style="background-color:WhiteSmoke; ">${reg.dateOfUPCControl}</td>
    <td style="background-color:WhiteSmoke; ">${reg.comment}</td>
    
    <c:if test = "${empty reg.ekozmu}">
          <td style="background-color:WhiteSmoke; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.ekozmu}">
          <td style="background-color:WhiteSmoke; ">${reg.ekozmu}</td>
      </c:if>
    
    <c:if test = "${empty reg.roadStatement}">
          <td style="background-color:WhiteSmoke; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.roadStatement}">
          <td style="background-color:WhiteSmoke; ">${reg.roadStatement}</td>
      </c:if>
      
    <c:if test = "${empty reg.ownerStatement}">
          <td style="background-color:WhiteSmoke; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.ownerStatement}">
          <td style="background-color:WhiteSmoke; ">${reg.ownerStatement}</td>
      </c:if>
    
    </c:if>
    
    
    <c:if test="${reg.isOK}">
    	
    
    <td style="background-color:#ffef96; ">${loop.count}</td>
    <td style="background-color:#ffef96; ">${reg.planNumber}</td>
    <td style="background-color:#ffef96; ">${reg.planName}</td> 
    <td style="background-color:#ffef96; ">${reg.nameOfCATVControlPerson}</td>
    <td style="background-color:#ffef96; ">${reg.dateOfCATVControl}</td>
    <td style="background-color:#ffef96; ">${reg.nameOfUPCControlPerson}</td> 
    <td style="background-color:#ffef96; ">${reg.dateOfUPCControl}</td>
    <td style="background-color:#ffef96; ">${reg.comment}</td>
    
    <c:if test = "${empty reg.ekozmu}">
          <td style="background-color:#ffef96; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.ekozmu}">
          <td style="background-color:#ffef96; ">${reg.ekozmu}</td>
      </c:if>
    
    <c:if test = "${empty reg.roadStatement}">
          <td style="background-color:#ffef96; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.roadStatement}">
          <td style="background-color:#ffef96; ">${reg.roadStatement}</td>
      </c:if>
      
    <c:if test = "${empty reg.ownerStatement}">
          <td style="background-color:#ffef96; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.ownerStatement}">
          <td style="background-color:#ffef96; ">${reg.ownerStatement}</td>
      </c:if>
    
    </c:if>
    
     <c:if test = "${reg.isOK}">
          <td style="background-color:#ffef96; ">Igen</td>
      </c:if>
    <c:if test = "${!reg.isOK}">
          <td style="background-color:WhiteSmoke; ">Nem</td>
      </c:if>
    
   
   </c:if> 
   
     
    <c:if test="${loop.count%2==1}">
      	<tr>
   				
   				<c:if test="${!reg.isOK}">
   				
    <td>${loop.count}</td>
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
    
    		</c:if>
    
    
    <c:if test="${reg.isOK}">
   				
    <td style="background-color:#ffef96; ">${loop.count}</td>
    <td style="background-color:#ffef96; ">${reg.planNumber}</td>
    <td style="background-color:#ffef96; ">${reg.planName}</td> 
    <td style="background-color:#ffef96; ">${reg.nameOfCATVControlPerson}</td>
    <td style="background-color:#ffef96; ">${reg.dateOfCATVControl}</td>
    <td style="background-color:#ffef96; ">${reg.nameOfUPCControlPerson}</td> 
    <td style="background-color:#ffef96; ">${reg.dateOfUPCControl}</td>
    <td style="background-color:#ffef96; ">${reg.comment}</td>
    
    <c:if test = "${empty reg.ekozmu}">
          <td style="background-color:#ffef96; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.ekozmu}">
          <td style="background-color:#ffef96; ">${reg.ekozmu}</td>
      </c:if>
    
    <c:if test = "${empty reg.roadStatement}">
          <td style="background-color:#ffef96; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.roadStatement}">
          <td style="background-color:#ffef96; ">${reg.roadStatement}</td>
      </c:if>
      
    <c:if test = "${empty reg.ownerStatement}">
          <td style="background-color:#ffef96; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.ownerStatement}">
          <td style="background-color:#ffef96; ">${reg.ownerStatement}</td>
      </c:if>
    
    		</c:if>
    
     <c:if test = "${reg.isOK}">
          <td style="background-color:#ffef96; ">Igen</td>
      </c:if>
    <c:if test = "${!reg.isOK}">
          <td>Nem</td>
      </c:if>
    
      	</tr>
  </c:if>
    
    </c:forEach>
	</table><br>

	
	<c:if test = "${fromAll}">
	
	<form method="POST" action="GetAllRegistrations">
		<h3 style="color:white">Add the Number of a Record:</h3><input type="text" name="modify"/>
   		<input type="submit" value="Modify a Record" />
		</form>
		<form method="GET" action="GetAllRegistrations">
		<input type="text" name="del"/>
   		<input type="submit" value="Delete a Record" />
		</form><br>
	 </c:if>
	 
	  
	 <c:if test = "${!fromAll}">
	
		<form method="POST" action="GetPlanRegistrations">
		<h3 style="color:white">Add the Number of a Record:</h3><input type="text" name="modify"/>
   		<input type="submit" value="Modify a Record" />
		</form>
		<form method="GET" action="GetPlanRegistrations">
		<input type="text" name="del"/>
   		<input type="submit" value="Delete a Record" />
   		</form>
		<form method="POST" action="GetPlanRegistrations">
		<input type="hidden" value="closing" name="close"/>
   		<input type="submit" style="float:right" value="Close the Plan" />
		</form>
	
	 </c:if><br><hr><br>
	 
	 <form  action="GetAllRegistrations">
	 	 <input type="hidden" value="back" name="back"/>
   		 <input type="submit" value="Back" />
	</form>		
		
</body>

</html>