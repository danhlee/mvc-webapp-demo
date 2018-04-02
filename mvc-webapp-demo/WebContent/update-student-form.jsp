<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Student Page</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/add-student-style.css">
</head>
<body>
<div id="wrapper">
		<div id="header">
			<center><h2>Northeastern Illinois University</h2></center>
		</div>
	</div>
	
	<div id="container">
		<h3>Add Student</h3>
		
		<form action="Controller_StudentServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />
			
			<input type="hidden" name="studentId" value="${loaded_student.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>First name: </label></td>
						<td><input type="text" name="firstName" value="${loaded_student.firstName}"/></td>
					</tr>
					
					<tr>
						<td><label>Last name: </label></td>
						<td><input type="text" name="lastName" value="${loaded_student.lastName}"/></td>
					</tr>
					
					<tr>
						<td><label>Email: </label></td>
						<td><input type="text" name="email" value="${loaded_student.email}"/></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		 </form>
		 
		 <div style="clear: both;"></div>
		 
		 <p>
		 	<a href="Controller_StudentServlet">Back to List</a>
		 </p>
	</div>
	
</body>
</html>