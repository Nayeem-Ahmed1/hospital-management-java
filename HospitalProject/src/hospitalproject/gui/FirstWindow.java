package hospitalproject.gui;

import hospitalproject.Main;
import hospitalproject.utils.FirstWindowButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstWindow implements ActionListener {

    private JFrame frame;
    private JButton buttonAdmin;
    private JButton buttonDoctor;
    private JButton buttonPatient;

    public FirstWindow() {

        frame = new JFrame();

        ImageIcon hospitalIconImage = new ImageIcon("/Users/nayeemahmed/Documents/University-Assignment/HospitalProject/src/hospitalproject/images/hospital_Icon.png");

        frame.setSize(1400, 900);
        frame.setTitle("Hospital Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setIconImage(hospitalIconImage.getImage());
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(173, 216, 230));

        /* Hospital Image Section */
        
        JLabel labelHospitalIcon = new JLabel();

        Image scaledImage = hospitalIconImage.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        labelHospitalIcon.setIcon(scaledIcon);
        labelHospitalIcon.setBounds(580, 60, 250, 250);

        frame.add(labelHospitalIcon);

        /* Middle Text */
        
        JLabel labelMiddle = new JLabel();

        labelMiddle.setText("Please Select One");
        labelMiddle.setBounds(500, 300, 500, 200);
        labelMiddle.setFont(new Font("Roboto", Font.BOLD, 50));

        frame.add(labelMiddle);

        /* Buttons */
        
        JPanel buttonPanel = new JPanel();
        
        buttonAdmin = new JButton();
        buttonDoctor = new JButton();
        buttonPatient = new JButton();

        buttonPanel.setBounds(320, 500, 800, 100);
        buttonPanel.setBackground(new Color(173, 216, 230));
        buttonPanel.setLayout(new GridLayout(1, 3, 50, 22));

        FirstWindowButton.styledButton(buttonAdmin, "ADMIN", Color.red, new Color(255, 100, 100));
        buttonAdmin.addActionListener(this);

        FirstWindowButton.styledButton(buttonDoctor, "DOCTOR", new Color(75, 0, 130), new Color(109, 90, 207));
        buttonDoctor.addActionListener(this);
        
        FirstWindowButton.styledButton(buttonPatient, "PATIENT", new Color(65, 105, 225), new Color(150, 200, 255));
        buttonPatient.addActionListener(this);
        
        buttonPanel.add(buttonAdmin);
        buttonPanel.add(buttonDoctor);
        buttonPanel.add(buttonPatient);
        frame.add(buttonPanel);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonAdmin) {

            Main.changeActiveScreen("adminLoginScreen");
            
            frame.dispose();

        }

        if (e.getSource() == buttonDoctor) {

            System.out.println("Doctor Button Clicked!");

        }

        if (e.getSource() == buttonPatient) {

            System.out.println("Patient Button Clicked!");

        }

    }

}
