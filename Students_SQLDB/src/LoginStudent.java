import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.*;

public class LoginStudent extends JFrame{
	private JPanel panel;
	private JLabel labelID;
	private JTextField textID;
	private JLabel labelpass;
	private JTextField textpass;
	private JButton btn;
	private JTextField t;
	ArrayList<Student> students = new ArrayList();
	ArrayList<String> list = new ArrayList();
	
	public LoginStudent() {
		
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		labelID = new JLabel("Your ID: ");
		textID = new JTextField("insert ID");
		labelpass = new JLabel("Your password: ");
		textpass = new JTextField("insert password");
		btn = new JButton("Login");
		t = new JTextField(20);
	    t.setVisible(false);  // hide the text field initially
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, 100, 143); // Add some padding
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(labelID, constraints);
		constraints.insets = new Insets(0, 0, 100, 0); // Add some padding
		panel.add(textID, constraints);
		textID.setPreferredSize(new Dimension(100, 20)); // set preferred size of textname
		constraints.insets = new Insets(0, 0, 50, 190); // Add some padding
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(labelpass, constraints);
		constraints.insets = new Insets(0, 0, 50, 0); // Add some padding
		panel.add(textpass, constraints);
		textpass.setPreferredSize(new Dimension(100, 20));
		constraints.insets = new Insets(0, 0, 0, 0); // Add some padding
		panel.add(btn, constraints);
		constraints.insets = new Insets(0, 0, -50, 0); // Add some padding
		panel.add(t, constraints);
		
		
		Color color=new Color(255,228,181);
		panel.setBackground(color);
		this.setContentPane(panel);
		
		ButtonListener listener = new ButtonListener();
		btn.addActionListener(listener);

		this.setVisible(true);
		this.setTitle("Student Login");
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
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
		        	 list.add(s.getId());
		         }
		         
		         // Close the connection and release resources
		         rs.close();
		         stmt.close();
		         con.close();
		      } catch (Exception h) {
		         System.out.println(h);
		      }
			 if(e.getSource().equals(btn)) {
				 String id = textID.getText();
				 String pass = textpass.getText();
					 if(list.contains(id) && pass.equals("test")) {
						 dispose();
						 System.out.println(id + " Exists");
						 StudentGUI studentgui = new StudentGUI();
					 
					 } else {
						 String msg = "Error...Please enter the correct ID";
						 t.setText(msg);
						 t.setVisible(true); // make the textfield visible
						 panel.revalidate();
						 panel.repaint();
					 }
				 
			 }
		}	
	}
}
