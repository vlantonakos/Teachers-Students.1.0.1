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


public class StudentGUI extends JFrame{
	private JPanel panel;
	private JList<String> list;
	private DefaultListModel<String> model;
	private JButton addbtn;
	private JButton delbtn;
	private JButton infobtn;
	private JLabel info;
	private JLabel addinfo;
	ArrayList<Course> courses = new ArrayList();
	
	public StudentGUI() {

		panel = new JPanel();
		
		list = new JList<String>();
		model = new DefaultListModel<String>();
		
		addbtn = new JButton("ADD COURSE");
		delbtn = new JButton("DEL COURSE");
		infobtn = new JButton("COURSE INFO");
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
	         ResultSet rs = stmt.executeQuery("SELECT * FROM courses");

	         //Adding the Records of the table to the Array List
	         while (rs.next()) {
	        	    String name = rs.getString("Name");
	        	    String info = rs.getString("Info");

	        	    Course course = new Course(name, info);
	        	    courses.add(course);
	        	}
	         
	         for(Course s : courses) {
	        	 model.addElement(s.getName());
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
		
		this.setTitle("Student");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String selectedId = list.getSelectedValue();
			Course selectedCourse = null;
			
			for(Course c : courses)
			{
				if (c.getName().equals(selectedId))
				{
					selectedCourse = c;
					break;
				}
			}
			
			if (selectedCourse!=null)
			{
				if (e.getSource().equals(infobtn))
				{
					info.setText("Info: "+ selectedCourse.getInfo());
					
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
				         PreparedStatement pstmt = con.prepareStatement("DELETE FROM courses WHERE Name = ?");

				         pstmt.setString(1, selectedCourse.getName());
				         
				         pstmt.executeUpdate();

				         courses.remove(selectedCourse);

				         DefaultListModel<String> model = new DefaultListModel<>();
				         for (Course course : courses) {
				             model.addElement(course.getName());
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
					   String name = JOptionPane.showInputDialog("Enter course Name:");
					   String info = JOptionPane.showInputDialog("Enter course Info:");
					   
					try {
				         // Load the MySQL JDBC driver
				         Class.forName("com.mysql.jdbc.Driver");

				         // Connect to the database
				         String url = "jdbc:mysql://localhost:3306/new_schema";
				         String user = "user";
				         String password = "user";
				         Connection con = DriverManager.getConnection(url, user, password);

				         // Create a prepared statement with placeholders for the user input
				         PreparedStatement pstmt = con.prepareStatement("INSERT INTO courses VALUES (?, ?)");

				      // Set the values of the placeholders using the user input
				         pstmt.setString(1, name);
				         pstmt.setString(2, info);
				         
				      // Execute the prepared statement to insert the data into the table
				         pstmt.executeUpdate();

				         // Close the statement and connection objects
				         pstmt.close();
				         con.close();
				         
				         Course course = new Course(name,info);
				         courses.add(course);

				         // Update the model of the JList
				         DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
				         model.addElement(course.getName());
				            
				      } catch (Exception h) {
				         System.out.println(h);
				      }
				}
			}		
		}	
}
