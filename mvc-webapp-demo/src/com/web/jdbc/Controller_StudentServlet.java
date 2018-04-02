package com.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Controller_StudentServlet
 */
@WebServlet("/Controller_StudentServlet")
public class Controller_StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDao;
	
	// Resource annotation "initializes" dataSource reference
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	// called by tomcat server when servlet is first loaded/init
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create studentDao and pass in connection pool / dataSource
		try {
			studentDao = new StudentDAO(dataSource);
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO list students in MVC fashion
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if command is missing, default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			case "LIST":
				listStudents(request, response);
				break;
			case "ADD":
				addStudent(request, response);
				break;
			case "LOAD":
				loadStudent(request, response);
				break;
			case "UPDATE":
				updateStudent(request, response);
				break;
			case "DELETE":
				deleteStudent(request, response);
				break;
			default:
				listStudents(request, response);
			}
			
			
			
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
		
	}


	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studentId = request.getParameter("studentId");
		
		studentDao.deleteStudent(studentId);
		
		listStudents(request, response);
		
	}


	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student info from form data
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// create a new student object
		Student theStudent = new Student(id, firstName, lastName, email);
		
		
		// perform update on database
		studentDao.updateStudent(theStudent);
		
		// send them back to view page
		listStudents(request, response);
		
	}


	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student id from form data
		String theStudentId = request.getParameter("studentId");
		
		// get student from database (DAO)
		Student theStudent = studentDao.loadStudent(theStudentId);
		
		// place student in the request attribute
		request.setAttribute("loaded_student", theStudent);
		
		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
		
	}


	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// create a new student object
		Student newStudent = new Student(firstName, lastName, email);
		
		// add student to database
		studentDao.addStudent(newStudent);
		
		// send back to main page (the student list)
		listStudents(request, response);
	}


	private void listStudents(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		// TODO get students from dao
		List<Student> students = studentDao.getStudents();
		
		
		// TODO add students to the request
		request.setAttribute("student_list", students);
		
		
		// TODO send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view_students.jsp");
		dispatcher.forward(request, response);
	
	}

}
