import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.*;

public class LoginTeacher extends JFrame{
	private JPanel panel;
	private JLabel labelname;
	private JTextField textname;
	private JLabel labelpass;
	private JTextField textpass;
	private JButton btn;
	private JTextField t;
	
	
	public LoginTeacher() {
		
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		labelname = new JLabel("Your name: ");
		textname = new JTextField("insert name");
		labelpass = new JLabel("Your password: ");
		textpass = new JTextField("insert password");
		btn = new JButton("Login");
		t = new JTextField(26);
	    t.setVisible(false);  // hide the text field initially
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, 100, 165); // Add some padding
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(labelname, constraints);
		constraints.insets = new Insets(0, 0, 100, 0); // Add some padding
		panel.add(textname, constraints);
		textname.setPreferredSize(new Dimension(100, 20)); // set preferred size of textname
		constraints.insets = new Insets(0, 0, 50, 190); // Add some padding
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(labelpass, constraints);
		constraints.insets = new Insets(0, 0, 50, 0); // Add some padding
		panel.add(textpass, constraints);
		textpass.setPreferredSize(new Dimension(100, 20)); // set preferred size of textpass
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
		this.setTitle("Teacher Login");
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			 if(e.getSource().equals(btn)) {
				 String name = textname.getText();
				 String pass = textpass.getText();
				 if(name.equals("admin") && pass.equals("admin")) {
					 dispose();
					 TeacherGUI teachergui = new TeacherGUI();
					 
				 } else {
					 String msg = "Error...Please enter the correct name and password";
			            t.setText(msg);
			            t.setVisible(true); // make the textfield visible
			            panel.revalidate();
		                panel.repaint();
				 }
			 }
		}	
	}
}
