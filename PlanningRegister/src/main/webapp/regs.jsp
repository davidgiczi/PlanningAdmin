<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>


<title>Records</title>

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
<body style="background-color:#ffc87a;">
	
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
    <th style="background-color:WhiteSmoke; ">E-mail</th>
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
    	
    
    <td style="background-color:#87e187; ">${loop.count}</td>
    <td style="background-color:#87e187; ">${reg.planNumber}</td>
    <td style="background-color:#87e187; ">${reg.planName}</td> 
    <td style="background-color:#87e187; ">${reg.nameOfCATVControlPerson}</td>
    <td style="background-color:#87e187; ">${reg.dateOfCATVControl}</td>
    <td style="background-color:#87e187; ">${reg.nameOfUPCControlPerson}</td> 
    <td style="background-color:#87e187; ">${reg.dateOfUPCControl}</td>
    <td style="background-color:#87e187; ">${reg.comment}</td>
    
    <c:if test = "${empty reg.ekozmu}">
          <td style="background-color:#87e187; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.ekozmu}">
          <td style="background-color:#87e187; ">${reg.ekozmu}</td>
      </c:if>
    
    <c:if test = "${empty reg.roadStatement}">
          <td style="background-color:#87e187; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.roadStatement}">
          <td style="background-color:#87e187; ">${reg.roadStatement}</td>
      </c:if>
      
    <c:if test = "${empty reg.ownerStatement}">
          <td style="background-color:#87e187; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.ownerStatement}">
          <td style="background-color:#87e187; ">${reg.ownerStatement}</td>
      </c:if>
    
    </c:if>
    
     <c:if test = "${reg.isOK}">
          <td style="background-color:#87e187; ">Igen</td>
          <td style="background-color:#87e187; "><form
							action="ShowContent" accept-charset="UTF-8">
							<input type="submit" value="Tallózás..." style="cursor: pointer">
						</form></td>
      </c:if>
    <c:if test = "${!reg.isOK}">
          <td style="background-color:WhiteSmoke; ">Nem</td>
          <td style="background-color:WhiteSmoke; "><form
							action="ShowContent" accept-charset="UTF-8">
							<input type="submit" value="Tallózás..." style="cursor: pointer">
						</form></td>
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
   				
    <td style="background-color:#87e187; ">${loop.count}</td>
    <td style="background-color:#87e187; ">${reg.planNumber}</td>
    <td style="background-color:#87e187; ">${reg.planName}</td> 
    <td style="background-color:#87e187; ">${reg.nameOfCATVControlPerson}</td>
    <td style="background-color:#87e187; ">${reg.dateOfCATVControl}</td>
    <td style="background-color:#87e187; ">${reg.nameOfUPCControlPerson}</td> 
    <td style="background-color:#87e187; ">${reg.dateOfUPCControl}</td>
    <td style="background-color:#87e187; ">${reg.comment}</td>
    
    <c:if test = "${empty reg.ekozmu}">
          <td style="background-color:#87e187; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.ekozmu}">
          <td style="background-color:#87e187; ">${reg.ekozmu}</td>
      </c:if>
    
    <c:if test = "${empty reg.roadStatement}">
          <td style="background-color:#87e187; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.roadStatement}">
          <td style="background-color:#87e187; ">${reg.roadStatement}</td>
      </c:if>
      
    <c:if test = "${empty reg.ownerStatement}">
          <td style="background-color:#87e187; ">-</td>
      </c:if>
    <c:if test = "${not empty reg.ownerStatement}">
          <td style="background-color:#87e187; ">${reg.ownerStatement}</td>
      </c:if>
    
    		</c:if>
    
     <c:if test = "${reg.isOK}">
          <td style="background-color:#87e187; ">Igen</td>
          <td style="background-color:#87e187; "><form
							action="ShowContent" accept-charset="UTF-8">
							<input type="submit" value="Tallózás..." style="cursor: pointer">
						</form></td>
      </c:if>
    <c:if test = "${!reg.isOK}">
          <td>Nem</td>
          <td><form action="ShowContent" accept-charset="UTF-8">
							<input type="submit" value="Tallózás..." style="cursor: pointer">
						</form></td>
      </c:if>
    
  
      	</tr>
  </c:if>
   
    </c:forEach>
	</table><br>

	
	<c:if test = "${fromAll}">
	
	<form method="POST" action="GetAllRegistrations">
		<h3 style="color:white">Add the Number of a Record:</h3><input type="text" name="modify"/>
   		<input type="submit" value="Modify a Record" style="cursor: pointer"/>
		</form>
		<form method="GET" action="GetAllRegistrations">
		<input type="text" name="del"/>
   		<input type="submit" value="Delete a Record" style="cursor: pointer"/>
		</form><br>
	 </c:if>
	 
	  
	 <c:if test = "${!fromAll}">
	
		<form method="POST" action="GetPlanRegistrations">
		<h3 style="color:white">Add the Number of a Record:</h3><input type="text" name="modify"/>
   		<input type="submit" value="Modify a Record" style="cursor: pointer"/>
		</form>
		<form method="GET" action="GetPlanRegistrations">
		<input type="text" name="del"/>
   		<input type="submit" value="Delete a Record" style="cursor: pointer"/>
   		</form>
		<form method="POST" action="GetPlanRegistrations">
		<input type="hidden" value="closing" name="close"/>
   		<input type="submit" style="float:right" value="Close the Plan" style="cursor: pointer"/>
		</form>
	
	 </c:if><br><hr><br>
	 
	 <form  action="GetAllRegistrations">
	 	 <input type="hidden" value="back" name="back"/>
   		 <input type="submit" value="Back" style="cursor: pointer"/>
	</form>			
		
		
</body>

</html>