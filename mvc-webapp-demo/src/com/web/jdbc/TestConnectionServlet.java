package com.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestConnectionServlet
 */
@WebServlet("/TestConnectionServlet")
public class TestConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// TODO 1 - Define datasource/connection pool for Resource Injection
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 2 - set up printWriter & contentType
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		// TODO 3 - get a connection to DB
		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultSet = null;
		
		try {
			myConnection = dataSource.getConnection();
			
			// TODO 4 - create SQL statement
			String sql = "SELECT * FROM STUDENT";
			myStatement = myConnection.createStatement();
			
			// TODO 5 - execute SQL query
			myResultSet = myStatement.executeQuery(sql);
			
			// TODO 6 - process the result set
			while (myResultSet.next()) {
				String email = myResultSet.getString("email");
				out.println(email);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			
	}



}
