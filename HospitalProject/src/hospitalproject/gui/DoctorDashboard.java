
package hospitalproject.gui;

import hospitalproject.utils.ChangeDoctorCenterScreen;
import hospitalproject.utils.ChangePatientCenterScreen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DoctorDashboard {
    
    static JPanel centerPanel;
    
    public DoctorDashboard(){
        
        JFrame frame = new JFrame();

        ImageIcon hospitalIconImage = new ImageIcon("src/hospitalproject/images/hospital_icon.png");

        frame.setSize(1400, 900);
        frame.setTitle("Dashboard");
        frame.setIconImage(hospitalIconImage.getImage());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setVisible(true);

        /* Top Panel */
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(133, 244, 255));
        topPanel.setPreferredSize(new Dimension(1400, 100));
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 10));

        /* NavBar */
        Image scaledImage = hospitalIconImage.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(scaledImageIcon);

        JButton homeButton = AdminNavigationButton.navigationButton("HOME");
        homeButton.addActionListener(e -> {

            ChangeDoctorCenterScreen.setDoctorCenterScreen("default");
            ReflectUIChange();

        });

        JButton editButton = AdminNavigationButton.navigationButton("EDIT PROFILE");
        editButton.addActionListener(e -> {
            
            ChangeDoctorCenterScreen.setDoctorCenterScreen("editProfileScreen");
            ReflectUIChange();

        });

        topPanel.add(imageLabel);
        topPanel.add(homeButton);
        topPanel.add(editButton);
        frame.add(topPanel, BorderLayout.NORTH);

        /* Center Panel */
        centerPanel = new JPanel();
        centerPanel.setBackground(new Color(173, 216, 230));
        centerPanel.setLayout(null);

        ReflectUIChange();

        frame.add(centerPanel, BorderLayout.CENTER);
        
        frame.setVisible(true);

        
    }
    
    public static void ReflectUIChange() {
        
        if(centerPanel == null){
            centerPanel = new JPanel();
        }

        String screen = ChangeDoctorCenterScreen.activeScreen;

        switch (screen) {
            case "default" -> {

                centerPanel.removeAll();
                DoctorCenterScreen.defaultScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
            }
            
            case "editProfileScreen" -> {
                
                centerPanel.removeAll();
                DoctorCenterScreen.editProfileScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
            }


        }

    }


    
}
