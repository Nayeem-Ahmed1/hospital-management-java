
package hospitalproject.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class AdminLoginPage {
    
    public AdminLoginPage(){
        
        JFrame frame = new JFrame();
        
        ImageIcon hospitalIconImage = new ImageIcon("/Users/nayeemahmed/Documents/University-Assignment/HospitalProject/src/hospitalproject/images/hospital_Icon.png");

        
        frame.setSize(1400,900);
        frame.setTitle("Admin Login");
        frame.setIconImage(hospitalIconImage.getImage());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        
        
        /* Label */
        
        JLabel loginLabel = new JLabel();
        
        loginLabel.setText("Admin Sign In");
        loginLabel.setBounds(500, 50, 500, 200);
        loginLabel.setFont(new Font("Roboto", Font.BOLD, 50));
        
        frame.add(loginLabel);
        
        /* Form */
        
        
        JPanel formPanel = new JPanel();
        
        formPanel.setBounds(420, 250, 500, 350);
        formPanel.setLayout(new GridLayout(3,2,10,10));
        
        JLabel userPanel = new JLabel();
        
        userPanel.setText("Username :");
        userPanel.setFont(new Font("Roboto",Font.PLAIN,20));
        JTextField textField = new JTextField();
        
        JLabel passPanel = new JLabel();
        
        passPanel.setText("Password :");
        passPanel.setFont(new Font("Roboto",Font.PLAIN,20));
        JPasswordField passField = new JPasswordField();
        
        formPanel.add(userPanel);
        formPanel.add(textField);
        formPanel.add(passPanel);
        formPanel.add(passField);
        frame.add(formPanel);
        

        frame.setVisible(true);
        
    }
    
}
