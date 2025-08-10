
package hospitalproject.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


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
        
        loginLabel.setText("Sign in Form");
        loginLabel.setBounds(550, 100, 500, 200);
        loginLabel.setFont(new Font("Roboto", Font.BOLD, 50));
        
        frame.add(loginLabel);
        
        /* Form */
        
        

        frame.setVisible(true);
        
    }
    
}
