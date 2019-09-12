<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Input Data</title>
    
    </head>

    <body style="background-color:powderblue;">
    	
    	<h1 align="center" style="color:white">Register a Record of a Plan</h1><hr><br>
    
        <form method="POST" action="InputData">
        	Number of the plan: <input type="text" size="5" maxlength="255"name="number" /><br><br>
            Name of the plan:<br> <textarea  name="name" cols="51" rows="5"></textarea>
            
            <!-- input type="text" size="255" maxlength="255" name="name" /--><br><br>
            CATV controller: <select name="catv" style="width: 150px">
	
		 <option value=""></option>		
		<c:forEach items="${catvnames}" var="name">
   		 <option value="${name}">${name}</option>
		</c:forEach>
		</select><br><br>
            UPC controller: <select name="upc" style="width: 150px">
	
		 <option value=""></option>		
		<c:forEach items="${upcnames}" var="name">
   		 <option value="${name}">${name}</option>
		</c:forEach>
		</select><br><br>
 			Comment:<br> <textarea  name="comment" cols="51" rows="5" ></textarea>
 			
 			<!-- input type="text" size="255" maxlength="255" name="comment" /--><br><br>
 			E-kozmu:	 <input type="checkbox" value="true" name="kozmu" /><br><br>
 			Road Statement:	 <input type="checkbox" value="true" name="road" /><br><br>
 			Owner Statement: <input type="checkbox" value="true" name="owner" /><br><br>
           
            Planning is ready: <input type="checkbox" value="true" name="ready" /><hr><br>
         
          <input type="submit" value="Send" style="float:right" />
   
        </form>
	
		<form  method="POST" action="GetAllRegistrations">
		<input type="hidden" value="back" name="back"/>
   		 <input type="submit" value="Back" />
		</form>


     </body>
 </html>
