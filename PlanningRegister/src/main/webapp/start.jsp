<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<title>Planning Register</title>


</head>
<body style="background-color:#ffc87a;">

	 	<h1 align="center" style="color:white">CATV Planning Register</h1><hr>

		<form method="POST" action="Init">
   		 <input type="submit" value="Register a Plan" style="cursor: pointer"/>
		</form>
		
		<button type="button" onclick="exit()" style="float:right;cursor: pointer">Exit</button><br>
		
		<form action="GetAllRegistrations">
   		 <input type="submit" value="Show Registrations" style="cursor: pointer"/>
   		 <select name="year" style="cursor: pointer">
	
		 <option value="all">All</option>	
		<c:forEach items="${years}" var="year">
   		 <option value="${year}">${year}</option>
		</c:forEach>
		</select>
   		 
		</form><br>
		
		<form method="POST" action="GetPlanRegistrations">
		<h3 style="color:white">Add the Number of a Plan:</h3><input type="text" name="plan"/>/
		
		<select name="year" style="cursor: pointer">
	
			
		<c:forEach items="${years}" var="year">
   		 <option value="${year}">${year}</option>
		</c:forEach>
		</select>
		
		
		
   		 <input type="submit" value="Show the Registrations of the Plan" style="cursor: pointer"/>
		</form><br>
		
		<form action="GetAllRegistrations" accept-charset="UTF-8">
		
		<input type="text" name="search"/>
		<input type="submit" value="Search" style="cursor: pointer"/>
		
		</form><br>
		
		<form action="SaveDataToFile">
		
		<input type="submit" value="Save All Registrations to a File" style="cursor: pointer"/>
		
		</form>
		
		<br>
		
		<form action="LoadDataFromFile" method="POST">
		<p style="color:#484848">
		
		<input type="submit" value="Load Registrations from the File" style="cursor: pointer"/> C:\AllRegs\SavedRegs_
		<select name="year" style="cursor: pointer">
	
			
		<c:forEach items="${years}" var="year">
   		 <option value="${year}">${year}</option>
		</c:forEach>
		</select> -
		
		<select name="month" style="cursor: pointer">
	
			
		<c:forEach begin="1"  end="12" varStatus="i">
   		 <option value="${i.index}">${i.index}</option>
		</c:forEach>
		</select> -
		
		<select name="day" style="cursor: pointer">
		
		<c:forEach begin="1"  end="31" varStatus="i">
   		 <option value="${i.index}">${i.index}</option>
		</c:forEach>
		</select>.txt
		
		</p>
		</form><br>
	
	<a href="initGeoJobProject"><button style="cursor: pointer">Geodesy</button></a>
		
		<script type="text/javascript">
		
		var message = "${msg}";
		
		
			
		if( "yes" === message.substring(0, 3) ) {
			
			var date = new Date();
			var yyyy = date.getFullYear();
			var MM = date.getMonth() < 10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
			var dd = date.getDate() < 10 ? "0"+date.getDate() : date.getDate();
			
			
		alert(message.substring(3, message.length)+ " pcs registrations have saved: C:\\SavedRegs\\AllRegs_" + yyyy + "-" + MM + "-"  + dd + ".txt");
			
		}
		else if ( "no" === message ) {
			
			alert("Unfortunately, the registrations cannot be saved. Create folder: \'C:\\SavedRegs\'");
			
		}
		else if( "load" === message.substring(0, 4) ) {
			
			alert(message.substring(4, message.length)+" pcs registrations have loaded into the database.")
			
		}
		else if( "notfound" === message.substring(0, 8) ) {
			
			alert("Unfortunately, registrations cannot be loaded into the database, maybe \'C:\\SavedRegs\\"+message.substring(8, message.length)+"\' file doesn't exist.")
			
		}
		
			function exit() {
							
				if(confirm("Would you like to exit?")){

			        close();
	
				}    
				
			}
		
		</script>	
		
		
		
		
</body>
</html>