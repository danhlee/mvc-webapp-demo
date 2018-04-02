package com.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDAO {
	private DataSource dataSource;
	
	public StudentDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// method that lists students
	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		
		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultSet = null;
		
		try {
			// TODO get a connection
			myConnection = dataSource.getConnection();
			
			// TODO create sql statement object
			String sql = "SELECT * FROM student ORDER BY last_name";
			myStatement = myConnection.createStatement();
			
			// TODO execute query
			myResultSet = myStatement.executeQuery(sql);
			
			// TODO process result set
			while (myResultSet.next()) {
				
				// retrieve data from result set row
				int id = myResultSet.getInt("id");
				String firstName = myResultSet.getString("first_name");
				String lastName = myResultSet.getString("last_name");
				String email = myResultSet.getString("email");
				
				// create new student objects
				Student tempStudent = new Student(id, firstName, lastName, email);
				
				// add it to the list of students
				students.add(tempStudent);
			
			}
			
			return students;
		}
		finally {
			// TODO close JDBC objects
			close(myConnection, myStatement, myResultSet);
		}		
		
	}



	public void addStudent(Student newStudent) throws Exception {
		Connection myConnection = null;
		PreparedStatement myStatement = null;
		
		try {
			// get db connection
			myConnection = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO student "
					+ "(first_name, last_name, email)"
					+ "VALUES (?, ?, ?)";
			
			myStatement = myConnection.prepareStatement(sql);
			
			// set param values for student
			myStatement.setString(1, newStudent.getFirstName());
			myStatement.setString(2, newStudent.getLastName());
			myStatement.setString(3, newStudent.getEmail());
			
			// execute sql insert
			myStatement.execute();
			
		}
		finally {
			// clean up JDBC objects
			close(myConnection, myStatement, null);
		}	
		
	}
	
	private void close(Connection myConnection, Statement myStatement, ResultSet myResultSet) {
		try {
			
			if (myConnection != null) {
				myConnection.close();
			}
			
			if (myStatement != null) {
				myStatement.close();
			}
			
			if (myResultSet != null) {
				myResultSet.close();
			} 
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Student loadStudent(String theStudentId) throws Exception {
		
		Student theStudent = null;
		
		Connection myConnection = null;
		PreparedStatement myStatement = null;
		ResultSet myResultSet = null;
		int studentId;
		
		try {
			// convert student id to int
			studentId = Integer.parseInt(theStudentId);
			
			// get connection to DB
			myConnection = dataSource.getConnection();
			
			
			// create sql to get selected student
			String sql = "SELECT * FROM student WHERE id=?";
			
			// created prepared statement
			myStatement = myConnection.prepareStatement(sql);
			
			// set params
			myStatement.setInt(1, studentId);
			
			// execute statement
			myResultSet = myStatement.executeQuery();
			
			
			// retrieve data from result set row
			if (myResultSet.next()) {
				String firstName = myResultSet.getString("first_name");
				String lastName = myResultSet.getString("last_Name");
				String email = myResultSet.getString("email");
				
				//use studentId to create student object
				theStudent = new Student(studentId, firstName, lastName, email);
			}
			else {
				throw new Exception("Could not find student id: " + studentId);
			}
			return theStudent;
		}
		finally {
			close(myConnection, myStatement, myResultSet);
		}
		
	}

	public void updateStudent(Student theStudent) throws Exception {
		
		Connection myConnection = null;
		PreparedStatement myStatement = null;

		try {
			myConnection = dataSource.getConnection();
			String sql = "UPDATE student SET first_name=?, last_name=?, email=? WHERE id=?";
			myStatement = myConnection.prepareStatement(sql);
			myStatement.setString(1, theStudent.getFirstName());
			myStatement.setString(2, theStudent.getLastName());
			myStatement.setString(3, theStudent.getEmail());
			myStatement.setInt(4, theStudent.getId());
			myStatement.execute();
			
		}
		finally {
			close(myConnection, myStatement, null);
		}
	}

	public void deleteStudent(String studentId) throws Exception {
		int id = Integer.parseInt(studentId);
		
		Connection myConnection = null;
		PreparedStatement myStatement = null;
		
		try {
			myConnection = dataSource.getConnection();
			String sql = "DELETE FROM student WHERE id=?";
			myStatement = myConnection.prepareStatement(sql);
			myStatement.setInt(1, id);
			myStatement.execute();
			
		}
		finally {
			close(myConnection, myStatement, null);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
