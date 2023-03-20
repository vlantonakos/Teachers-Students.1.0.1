import javax.swing.*;
import java.sql.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;


public class TeacherGUI extends JFrame{
	private JPanel panel;
	private JList<String> list;
	private DefaultListModel<String> model;
	private JButton addbtn;
	private JButton delbtn;
	private JButton infobtn;
	private JLabel info;
	private JLabel addinfo;
	ArrayList<Student> students = new ArrayList();
	
	public TeacherGUI() {

		panel = new JPanel();
		
		list = new JList<String>();
		model = new DefaultListModel<String>();
		
		addbtn = new JButton("ADD STUDENT");
		delbtn = new JButton("DEL STUDENT");
		infobtn = new JButton("STUDENT INFO");
		info = new JLabel("");
		addinfo = new JLabel("");

		
		try {
	         // Load the MySQL JDBC driver
	         Class.forName("com.mysql.jdbc.Driver");

	         // Connect to the database
	         String url = "jdbc:mysql://localhost:3306/new_schema";
	         String user = "user";
	         String password = "user";
	         Connection con = DriverManager.getConnection(url, user, password);

	         // Create a statement
	         Statement stmt = con.createStatement();

	         // Execute a query to retrieve the data from the table
	         ResultSet rs = stmt.executeQuery("SELECT * FROM students");
	         
	      // Create a new ArrayList to hold the updated list of students
	         ArrayList<Student> updatedStudents = new ArrayList<>();

	         //Adding the Records of the table to the Array List
	         while (rs.next()) {
	        	    String id = rs.getString("ID");
	        	    String name = rs.getString("Name");
	        	    String lastName = rs.getString("Last Name");
	        	    int grade = rs.getInt("Grade");
	        	    Student student = new Student(id, name, lastName, grade);
	        	    students.add(student);
	        	}
	         
	         for(Student s : students) {
	        	 model.addElement(s.getId());
	         }
	         
	         // Close the connection and release resources
	         rs.close();
	         stmt.close();
	         con.close();
	      } catch (Exception e) {
	         System.out.println(e);
	      }
		
		
		list.setModel(model);
		
		
		panel.add(list);
		panel.add(addbtn);
		panel.add(delbtn);
		panel.add(infobtn);
		panel.add(info);
		panel.add(addinfo);
		
		this.setContentPane(panel);
		
		ButtonListener listener = new ButtonListener();
		addbtn.addActionListener(listener);
		delbtn.addActionListener(listener);
		infobtn.addActionListener(listener);
		
		this.setVisible(true);
		
		this.setTitle("Teacher");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String selectedId = list.getSelectedValue();
			Student selectedStudent = null;
			
			for(Student s : students)
			{
				if (s.getId().equals(selectedId))
				{
					selectedStudent = s;
					break;
				}
			}
			
			if (selectedStudent!=null)
			{
				if (e.getSource().equals(infobtn))
				{
					info.setText("Name: "+ selectedStudent.getName() + "                    Last Name: " + selectedStudent.getLast_name() 
					+ "                    Grade: " + selectedStudent.getGrade());
					
				} else if (e.getSource().equals(delbtn)){
					try {
				         // Load the MySQL JDBC driver
				         Class.forName("com.mysql.jdbc.Driver");

				         // Connect to the database
				         String url = "jdbc:mysql://localhost:3306/new_schema";
				         String user = "user";
				         String password = "user";
				         Connection con = DriverManager.getConnection(url, user, password);

				      // Create a prepared statement with a parameterized query to delete the selected student
				         PreparedStatement pstmt = con.prepareStatement("DELETE FROM students WHERE ID = ?");

				      // Set the parameter value to the selected student ID
				         pstmt.setString(1, selectedStudent.getId());
				         
				      // Execute the prepared statement to delete the selected student
				         pstmt.executeUpdate();
				         
				      // Remove the selected student from the list of students
				         students.remove(selectedStudent);

				         // Update the JList with the updated list of student IDs
				         DefaultListModel<String> model = new DefaultListModel<>();
				         for (Student student : students) {
				             model.addElement(student.getId());
				         }
				         list.setModel(model);
				         
				      // Close the prepared statement and release resources
				         pstmt.close();
				         con.close();
				      } catch (Exception h) {
				         System.out.println(h);
				      }
				} 
			}
				if (e.getSource().equals(addbtn)){
					
					 // Prompt the user for input
					   String id = JOptionPane.showInputDialog("Enter student ID:");
					   String name = JOptionPane.showInputDialog("Enter student name:");
					   String last_name = JOptionPane.showInputDialog("Enter student last name:");
					   int grade = Integer.parseInt(JOptionPane.showInputDialog("Enter student grade:"));
					   
					try {
				         // Load the MySQL JDBC driver
				         Class.forName("com.mysql.jdbc.Driver");

				         // Connect to the database
				         String url = "jdbc:mysql://localhost:3306/new_schema";
				         String user = "user";
				         String password = "user";
				         Connection con = DriverManager.getConnection(url, user, password);

				         // Create a prepared statement with placeholders for the user input
				         PreparedStatement pstmt = con.prepareStatement("INSERT INTO students VALUES (?, ?, ?, ?)");

				      // Set the values of the placeholders using the user input
				         pstmt.setString(1, id);
				         pstmt.setString(2, name);
				         pstmt.setString(3, last_name);
				         pstmt.setInt(4, grade);
				         
				      // Execute the prepared statement to insert the data into the table
				         pstmt.executeUpdate();

				         // Close the statement and connection objects
				         pstmt.close();
				         con.close();
				         
				      // Add the new student to the students list
				            Student student = new Student(id,name,last_name,grade);
				            students.add(student);

				            // Update the model of the JList
				            DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
				            model.addElement(student.getId());
				            
				      } catch (Exception h) {
				         System.out.println(h);
				      }
			
				}
			
				}
				
			}
	}
