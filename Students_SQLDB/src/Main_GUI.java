import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class Main_GUI extends JFrame{
	private JPanel panel;
	private JButton teacherBtn;
	private JButton studentBtn;
	private JLabel label;
	
	public Main_GUI() {
		
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		label = new JLabel("Welcome");
		teacherBtn = new JButton("TEACHER");
		studentBtn = new JButton("STUDENT");
		
		// Set the constraints to center the label
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, 10, 0); // Add some padding
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(label, constraints);

		// Set the constraints to center the teacher button
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 0, 2, 0); // Add some padding
		panel.add(teacherBtn, constraints);
		

		// Set the constraints to center the student button
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(0, 0, 0, 0); // Add some padding
		panel.add(studentBtn, constraints);
		
		
		this.setContentPane(panel);
		
		ButtonListener listener = new ButtonListener();
		teacherBtn.addActionListener(listener);
		studentBtn.addActionListener(listener);
		
		this.setVisible(true);
		this.setTitle("Login");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(teacherBtn)) {
					dispose();
					LoginTeacher log1 = new LoginTeacher();
				} else {
					dispose();
					LoginStudent log1 = new LoginStudent();
				}
		}	
	}


}