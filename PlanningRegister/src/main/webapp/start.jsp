<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<title>Planning Register</title>


</head>
<body style="background-color:powderblue;">

	 	<h1 align="center" style="color:white">CATV Planning Register</h1><hr>

		<form method="POST" action="Init">
   		 <input type="submit" value="Register a Plan" />
		</form>
		
		<button type="button" onclick="exit()" style="float:right">Exit</button><br>
		
		<form action="GetAllRegistrations">
   		 <input type="submit" value="Show Registrations" />
   		 <select name="year">
	
		 <option value="all">All</option>	
		<c:forEach items="${years}" var="year">
   		 <option value="${year}">${year}</option>
		</c:forEach>
		</select>
   		 
		</form><br>
		
		<form method="POST" action="GetPlanRegistrations">
		<h3 style="color:white">Add the Number of a Plan:</h3><input type="text" name="plan"/>/
		
		<select name="year">
	
			
		<c:forEach items="${years}" var="year">
   		 <option value="${year}">${year}</option>
		</c:forEach>
		</select>
		
		
		
   		 <input type="submit" value="Show the Registrations of the Plan" />
		</form><br>
		
		<form action="GetAllRegistrations" accept-charset="UTF-8">
		
		<input type="text" name="search"/>
		<input type="submit" value="Search" />
		
		</form>
		
		<script type="text/javascript">
			function exit() {
							
				if(confirm("Would you like to exit?")){

			        close();
	
				}    
				
			}
		
		</script>	
		
		
		
		
</body>
</html>