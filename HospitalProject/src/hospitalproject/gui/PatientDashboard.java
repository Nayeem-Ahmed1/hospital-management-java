package hospitalproject.gui;

import hospitalproject.utils.ChangeAdminCenterScreen;
import hospitalproject.utils.ChangePatientCenterScreen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PatientDashboard {

    static JPanel centerPanel;

    public PatientDashboard() {
        
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

            ChangePatientCenterScreen.setPatientCenterScreen("default");
            ReflectUIChange();

        });

        JButton editButton = AdminNavigationButton.navigationButton("EDIT PROFILE");
        editButton.addActionListener(e -> {
            
            ChangePatientCenterScreen.setPatientCenterScreen("editProfileScreen");
            ReflectUIChange();

        });

        JButton appointmentButton = AdminNavigationButton.navigationButton("APPOINTMENTS");
        appointmentButton.addActionListener(e -> {
            
                        
            String[] choices = {"Book New Appointment","Upcoming Appointments","Past Appointments"};

            int selection = JOptionPane.showOptionDialog(null, "Please Select Your Action", "options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);

            switch (selection) {
                case 0 -> {
                    ChangePatientCenterScreen.setPatientCenterScreen("bookAppointments");
                    ReflectUIChange();
                }
                case 1 -> {
                    ChangePatientCenterScreen.setPatientCenterScreen("upcomingApppointments");
                    ReflectUIChange();
                }
                case 2 -> {
                    
                    ChangePatientCenterScreen.setPatientCenterScreen("pastAppointments");
                    ReflectUIChange();
                }
            }

        });

        JButton selfButton = AdminNavigationButton.navigationButton("MY PROFILE");
        selfButton.addActionListener(e -> {

            ChangePatientCenterScreen.setPatientCenterScreen("myProfile");
            ReflectUIChange();

        });

        topPanel.add(imageLabel);
        topPanel.add(homeButton);
        topPanel.add(editButton);
        topPanel.add(appointmentButton);
        topPanel.add(selfButton);
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

        String screen = ChangePatientCenterScreen.activeScreen;

        switch (screen) {
            case "default" -> {

                centerPanel.removeAll();
                PatientCenterScreen.defaultScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
            }
            case "editProfileScreen" -> {
                
                centerPanel.removeAll();
                PatientCenterScreen.editProfileScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
                
            }
            
            case "bookAppointments" -> {
                
                   
                centerPanel.removeAll();
                PatientCenterScreen.bookAppointments(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
                
            }
            
            case "upcomingApppointments" -> {
                
                centerPanel.removeAll();
                PatientCenterScreen.upcomingAppointments(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
                
            }
            
            case "pastAppointments" -> {
                
                                
                centerPanel.removeAll();
                PatientCenterScreen.pastAppointments(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
                
            }
            
            case "myProfile" -> {
                
                                                
                centerPanel.removeAll();
                PatientCenterScreen.myProfileScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
                
            }

        }

    }

}
