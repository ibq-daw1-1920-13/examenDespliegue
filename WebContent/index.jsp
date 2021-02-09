<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap" %>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% int n = (int)request.getAttribute("numDatos"); 
			out.println(n);
		%>
<table> 
		<tr> 
          <th><b>Dato</b></th> 
          <th><b>Valor</b></th> 
        </tr>
         <% HashMap<String,String> datos=(HashMap<String,String>)request.getAttribute("datos"); %>
       	    	<%if (datos != null){	
	    		for (String i : datos.keySet()) {%> 
	    		<tr> 
	                <td><%=i%></td> 
	                <td><%=datos.get(i)%></td> 
	            </tr> 
              <%} 
              
	         }%> 
    </table>  
</body>
</html>