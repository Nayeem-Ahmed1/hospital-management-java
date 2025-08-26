package hospitalproject.gui;

import hospitalproject.utils.ChangeAdminCenterScreen;
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

public class AdminDashboard {

    static JPanel centerPanel;

    public AdminDashboard() {

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

            ChangeAdminCenterScreen.setAdminCenterScreen("default");
            ReflectUIChange();

        });

        JButton hospitalButton = AdminNavigationButton.navigationButton("HOSPITALS");
        hospitalButton.addActionListener(e -> {

            String[] choices = {"Add Hospital", "Delete Hospital", "Display Hospitals", "Update Info", "Display by Id"};

            int selection = JOptionPane.showOptionDialog(null, "Please Select Your Action", "options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, scaledImageIcon, choices, choices[0]);

            switch (selection) {
                case 0 -> {
                    ChangeAdminCenterScreen.setAdminCenterScreen("addHospitalScreen");
                    ReflectUIChange();
                }
                case 1 -> {
                    ChangeAdminCenterScreen.setAdminCenterScreen("deleteHospitalScreen");
                    ReflectUIChange();
                }
                case 2 -> {
                    ChangeAdminCenterScreen.setAdminCenterScreen("displayAllHospitalScreen");
                    ReflectUIChange();
                }
                case 3 -> {

                    ChangeAdminCenterScreen.setAdminCenterScreen("updateHospitalScreen");
                    ReflectUIChange();

                }
                case 4 -> {

                    ChangeAdminCenterScreen.setAdminCenterScreen("displayByIdHospitalScreen");
                    ReflectUIChange();

                }
            }
        });

        JButton patientButton = AdminNavigationButton.navigationButton("PATIENTS");
        patientButton.addActionListener(e -> {

            String[] choices = {"Add Patient", "Delete Patient", "Display Patients", "Update Info", "Display by Id"};

            int selection = JOptionPane.showOptionDialog(null, "Please Select Your Action", "options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, scaledImageIcon, choices, choices[0]);

            switch (selection) {
                case 0 -> {
                    ChangeAdminCenterScreen.setAdminCenterScreen("addPatientScreen");
                    ReflectUIChange();
                }
                case 1 -> {
                    ChangeAdminCenterScreen.setAdminCenterScreen("deletePatientScreen");
                    ReflectUIChange();
                }
                case 2 -> {
                    ChangeAdminCenterScreen.setAdminCenterScreen("displayAllPatientScreen");
                    ReflectUIChange();
                }
                case 3 -> {
                    ChangeAdminCenterScreen.setAdminCenterScreen("updatePatientScreen");
                    ReflectUIChange();
                }
            }

        });

        JButton doctorButton = AdminNavigationButton.navigationButton("DOCTORS");
        doctorButton.addActionListener(e -> {

            String[] choices = {"Add Doctor", "Delete Doctor", "Display Doctors", "Update Info", "Display by Id"};

            int selection = JOptionPane.showOptionDialog(null, "Please Select Your Action", "options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, scaledImageIcon, choices, choices[0]);

            switch (selection) {
                case 0 -> {
                    ChangeAdminCenterScreen.setAdminCenterScreen("addDoctorScreen");
                    ReflectUIChange();
                }
                case 1 -> {
                    ChangeAdminCenterScreen.setAdminCenterScreen("deleteDoctorScreen");
                    ReflectUIChange();
                }
                case 2 -> {
                    ChangeAdminCenterScreen.setAdminCenterScreen("displayAllDoctorScreen");
                    ReflectUIChange();
                }
                case 3 -> {
                    ChangeAdminCenterScreen.setAdminCenterScreen("updateDoctorScreen");
                    ReflectUIChange();
                }
            }

        });

        JButton staffButton = AdminNavigationButton.navigationButton("STAFFS");

        JButton appointmentsButton = AdminNavigationButton.navigationButton("APPOINTMENTS");
        appointmentsButton.addActionListener(e -> {

            String[] choices = {"Approve Appointment", "View All Appointments", "Check In", "View Appointment by Patient"};

            int selection = JOptionPane.showOptionDialog(null, "Please Select Your Action", "options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, scaledImageIcon, choices, choices[0]);

            switch (selection) {
                case 0 -> {
                    ChangeAdminCenterScreen.setAdminCenterScreen("approveAppointment");
                    ReflectUIChange();
                }
                case 1 -> {
                    ChangeAdminCenterScreen.setAdminCenterScreen("viewAppointments");
                    ReflectUIChange();
                }
                case 2 -> {
                    ChangeAdminCenterScreen.setAdminCenterScreen("checkInAppointments");
                    ReflectUIChange();
                }
            }

        });

        topPanel.add(imageLabel);
        topPanel.add(homeButton);
        topPanel.add(hospitalButton);
        topPanel.add(patientButton);
        topPanel.add(doctorButton);
        topPanel.add(staffButton);
        topPanel.add(appointmentsButton);
        frame.add(topPanel, BorderLayout.NORTH);

        /* Left Panel */
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(230, 255, 253));
        leftPanel.setPreferredSize(new Dimension(150, 800));
        leftPanel.setLayout(new GridLayout(4, 1, 5, 130));

        JButton addAdminButton = AdminNavigationButton.navigationButton("ADD ADMIN");
        addAdminButton.addActionListener(e -> {
            
            ChangeAdminCenterScreen.setAdminCenterScreen("addAdminScreen");
            ReflectUIChange();
            
        });

        JButton roomButton = AdminNavigationButton.navigationButton("ROOMS");

        JButton ambulanceButton = AdminNavigationButton.navigationButton("AMBULANCE");

        JButton contactButton = AdminNavigationButton.navigationButton("CONTACT");

        leftPanel.add(addAdminButton);
        leftPanel.add(roomButton);
        leftPanel.add(ambulanceButton);
        leftPanel.add(contactButton);
        frame.add(leftPanel, BorderLayout.WEST);

        /* Center Panel */
        centerPanel = new JPanel();
        centerPanel.setBackground(new Color(173, 216, 230));
        centerPanel.setLayout(null);

        ReflectUIChange();

        frame.add(centerPanel, BorderLayout.CENTER);

    }

    public static void ReflectUIChange() {

        String screen = ChangeAdminCenterScreen.activeScreen;

        switch (screen) {
            case "default" -> {

                centerPanel.removeAll();
                AdminCenterScreen.defaultScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
            }

            case "addHospitalScreen" -> {

                centerPanel.removeAll();

                AdminCenterScreen.addHospitalScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();

            }

            case "deleteHospitalScreen" -> {

                centerPanel.removeAll();

                AdminCenterScreen.deleteHospitalScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
            }

            case "displayAllHospitalScreen" -> {

                centerPanel.removeAll();

                AdminCenterScreen.displayAllHospitalScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();

            }

            case "updateHospitalScreen" -> {

                centerPanel.removeAll();

                AdminCenterScreen.updateHospitalScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
            }
            case "displayByIdHospitalScreen" -> {

                centerPanel.removeAll();

                AdminCenterScreen.displayByIdHospital(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();

            }

            case "addPatientScreen" -> {

                centerPanel.removeAll();

                AdminCenterScreen.addPatientScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
            }

            case "deletePatientScreen" -> {

                centerPanel.removeAll();

                AdminCenterScreen.deletePatientScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
            }

            case "displayAllPatientScreen" -> {

                centerPanel.removeAll();

                AdminCenterScreen.displayAllPatientScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
            }

            case "updatePatientScreen" -> {

                centerPanel.removeAll();

                AdminCenterScreen.updatePatientScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();

            }

            case "addDoctorScreen" -> {

                centerPanel.removeAll();

                AdminCenterScreen.addDoctorScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();

            }

            case "deleteDoctorScreen" -> {

                centerPanel.removeAll();

                AdminCenterScreen.deleteDoctorScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
            }

            case "displayAllDoctorScreen" -> {

                centerPanel.removeAll();

                AdminCenterScreen.displayAllDoctorScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
            }

            case "updateDoctorScreen" -> {

                centerPanel.removeAll();

                AdminCenterScreen.updateDoctorScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
            }

            case "approveAppointment" -> {

                centerPanel.removeAll();

                AdminCenterScreen.approveAppointment(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();
            }

            case "viewAppointments" -> {

                centerPanel.removeAll();

                AdminCenterScreen.viewAppointment(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();

            }

            case "checkInAppointments" -> {

                centerPanel.removeAll();

                AdminCenterScreen.checkInAppointment(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();

            }
            
            case "addAdminScreen" -> {
                
                
                centerPanel.removeAll();

                AdminCenterScreen.addAdminScreen(centerPanel);

                centerPanel.revalidate();
                centerPanel.repaint();

            }
        }

    }

}
