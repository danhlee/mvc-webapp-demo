<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Use JSTL tags instead, don't need Array or packages -->
<!-- %@ page import="java.util.*, com.web.jdbc.*" %  -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>View Students</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<!--  
	// get students from request object (sent by servlet)
	@SuppressWarnings("unchecked")
	List<Student> theStudents = List.class.cast(request.getAttribute("student_list"));
-->

<body>
	<div id="wrapper">
		<div id="header">
			<center><h2>Northeastern Illinois University</h2></center>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			<!-- Add Student Button -->
			<input type="button" value="Add Student" 
				onclick="window.location.href='add-student-form.jsp'; return false;"
				class="add-student-button"
			/>
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<tbody>
					<!-- 
						% for (Student tempStudent: theStudents) { %
							<tr>
								<td>%= tempStudent.getFirstName() %</td>
								<td>%= tempStudent.getLastName() %</td>
								<td>%= tempStudent.getEmail() %</td>
							</tr>
						% } %
					 -->
					 <c:forEach var="tempStudent" items="${student_list}">
					 	
					 	<!-- set up update link for each student -->
					 	<c:url var="tempLink" value="Controller_StudentServlet">
					 		<c:param name="command" value="LOAD" />
					 		<c:param name="studentId" value="${tempStudent.id}" />
					 	</c:url>
					 	
					 	<!-- set up delete link for each student -->
					 	<c:url var="deleteLink" value="Controller_StudentServlet">
					 		<c:param name="command" value="DELETE" />
					 		<c:param name="studentId" value="${tempStudent.id}" />
					 	</c:url>
					 	
					 	
					 	<tr>
					 		<td> ${tempStudent.firstName} </td>
					 		<td> ${tempStudent.lastName} </td>
					 		<td> ${tempStudent.email} </td>
					 		<td> 
					 			<a href="${tempLink}">Update</a>
					 			 | 
					 			<a href="${deleteLink}"
					 			onclick="if ( !(confirm('Are you sure you want to delete this student?')) ) return false">Delete</a>
				 			</td>
					 	</tr>
					 </c:forEach>
					 
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>