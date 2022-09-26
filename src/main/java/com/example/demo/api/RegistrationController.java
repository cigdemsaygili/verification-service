package com.example.demo.api;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.business.abstracts.RegistrationService;
import com.example.demo.entities.User;

@RestController
public class RegistrationController extends JFrame{
	
	public void UImethods() {
		JFrame frame = new JFrame("Email Verification Service");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Email");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);

        
        JPanel panel2 = new JPanel(); // the panel is not visible in output
        JLabel label2 = new JLabel("Enter Password");
        JTextField tf2 = new JTextField(10); // accepts upto 10 characters
        JButton send2 = new JButton("Verify");
        
        JPanel panel3 = new JPanel();
        JLabel label3 = new JLabel();
        
        send2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//verificate(new User(1,tf.getText(),tf.getText()));
				label3.setText(verificate(new User(1,tf.getText(),tf2.getText())));
			}
		});
        
        panel2.add(label2); // Components Added using Flow Layout
        panel2.add(tf2);
        panel2.add(send2);
        
        panel3.add(label3);
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.PAGE_START, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel2);
        frame.getContentPane().add(BorderLayout.SOUTH, panel3);
        
        frame.setVisible(true);
	}
	
	private RegistrationService service;
	
	User user1 = new User(1,"cigdemsaygili@hotmail.com","12345");
	User user2 = new User(2,"aynursaygili1967@gmail.com","12345");
	User user3 = new User(3,"alicemalsaygili@hotmail.com","12345");
	User user4 = new User(4,"yilmazsaygili@gmail.com","12345");
	User user5 = new User(5,"eylemsaygili@gmail.com","12345");

	@Autowired
	public RegistrationController(RegistrationService service) {
		super();
		this.service=service;
		UImethods();
	}
	
	@GetMapping("/api/listOfUsers")
	public List<User> getUsers(){
		return service.listOfUsers();
	}
	
	
	@GetMapping("/api/verification")
	public String verificate(User user) {
		return this.service.verification(user);
	}
	
	@PostMapping("/api/posts")
	public void registrateUser() {
		 this.service.registrateUser(new User(6,"abcdefgh","abcdef"));
	}
	
}
