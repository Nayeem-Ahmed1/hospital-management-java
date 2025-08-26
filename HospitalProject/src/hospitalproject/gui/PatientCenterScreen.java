package hospitalproject.gui;

import hospitalproject.models.Appointment;
import hospitalproject.models.Doctor;
import hospitalproject.models.Hospital;
import hospitalproject.models.Patient;
import hospitalproject.utils.ChangePatientCenterScreen;
import hospitalproject.utils.LoggedInUser;
import hospitalproject.utils.PasswordEncryptDecrypt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PatientCenterScreen {

    public static void defaultScreen(JPanel centerPanel) {

        ImageIcon nurseIconImage = new ImageIcon("src/hospitalproject/images/nurse_welcome.png");
        Image scaledImage = nurseIconImage.getImage().getScaledInstance(700, 550, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel sideLabel = new JLabel();
        sideLabel.setIcon(scaledIcon);
        sideLabel.setBounds(650, 50, 700, 700);
        sideLabel.setBackground(new Color(173, 216, 230));
        sideLabel.setOpaque(true);

        centerPanel.add(sideLabel);

        JLabel welcomeText = new JLabel();
        welcomeText.setBounds(90, 60, 500, 200);
        String name = LoggedInUser.name;
        welcomeText.setText("Welcome , " + name);
        welcomeText.setFont(new Font("Roboto", Font.BOLD, 30));

        centerPanel.add(welcomeText);
    }

    public static void editProfileScreen(JPanel centerPanel) {

        int id = LoggedInUser.id;

        ArrayList<Patient> patients = new ArrayList<>();

        File file = new File("src/hospitalproject/data/patients.dat");

        if (file.exists()) {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/patients.dat"))) {

                while (true) {

                    try {
                        patients.add((Patient) ois.readObject());
                    } catch (EOFException eoferr) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException error) {
                error.printStackTrace();
            }
        }

        int matchedIndex = -1;

        for (Patient patient : patients) {
            if (id == patient.getId()) {
                matchedIndex = id - 1;
            }
        }

        if (matchedIndex != -1) {

            centerPanel.removeAll();

            centerPanel.setBounds(200, 200, 800, 500);

            Patient specificPatient = patients.get(matchedIndex);

            JLabel IdLabel = new JLabel();
            IdLabel.setText("ID :");
            IdLabel.setBounds(50, 50, 50, 40);
            IdLabel.setFont(new Font("Roboto", Font.PLAIN, 20));

            centerPanel.add(IdLabel);

            JTextField idField = new JTextField(15);
            idField.setBounds(100, 56, 80, 35);
            idField.setText(Integer.toString(specificPatient.getId()));
            idField.setEnabled(false);
            idField.setFont(new Font("Roboto", Font.PLAIN, 16));

            centerPanel.add(idField);

            JLabel nameLabel = new JLabel();
            nameLabel.setText("Name :");
            nameLabel.setBounds(50, 110, 100, 40);
            nameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

            centerPanel.add(nameLabel);

            JTextField nameField = new JTextField(15);
            nameField.setBounds(130, 116, 180, 35);
            nameField.setText(specificPatient.getName());
            nameField.setFont(new Font("Roboto", Font.PLAIN, 16));

            centerPanel.add(nameField);

            JLabel contactLabel = new JLabel();
            contactLabel.setText("Contact :");
            contactLabel.setBounds(50, 160, 130, 40);
            contactLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

            centerPanel.add(contactLabel);

            JTextField contactField = new JTextField(15);
            contactField.setBounds(160, 166, 150, 35);
            contactField.setText(specificPatient.getContactNumber());
            contactField.setFont(new Font("Roboto", Font.PLAIN, 16));

            centerPanel.add(contactField);

            JLabel emergencyLabel = new JLabel();
            emergencyLabel.setText("Emergency Contact :");
            emergencyLabel.setBounds(50, 215, 200, 40);
            emergencyLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

            centerPanel.add(emergencyLabel);

            JTextField emergencyField = new JTextField(15);
            emergencyField.setBounds(250, 220, 150, 35);
            emergencyField.setText(specificPatient.getEmergencyContact());
            emergencyField.setFont(new Font("Roboto", Font.PLAIN, 16));

            centerPanel.add(emergencyField);

            JLabel bloodGroupLabel = new JLabel();
            bloodGroupLabel.setText("Blood Group :");
            bloodGroupLabel.setBounds(50, 265, 130, 40);
            bloodGroupLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

            centerPanel.add(bloodGroupLabel);

            JTextField bloodGroupField = new JTextField(15);
            bloodGroupField.setBounds(180, 270, 150, 35);
            bloodGroupField.setText(specificPatient.getBloodGroup());
            bloodGroupField.setFont(new Font("Roboto", Font.PLAIN, 16));

            centerPanel.add(bloodGroupField);

            JLabel statusLabel = new JLabel();
            statusLabel.setText("Status :");
            statusLabel.setBounds(50, 320, 130, 40);
            statusLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

            centerPanel.add(statusLabel);

            JTextField statusField = new JTextField(15);
            statusField.setBounds(150, 325, 150, 35);
            statusField.setEnabled(false);
            statusField.setText(specificPatient.getStatus());
            statusField.setFont(new Font("Roboto", Font.PLAIN, 16));

            centerPanel.add(statusField);

            JLabel emailLabel = new JLabel();
            emailLabel.setText("Email :");
            emailLabel.setBounds(50, 370, 130, 40);
            emailLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

            centerPanel.add(emailLabel);

            JTextField emailField = new JTextField(15);
            emailField.setBounds(150, 375, 200, 35);
            emailField.setText(specificPatient.getEmail());
            emailField.setFont(new Font("Roboto", Font.PLAIN, 16));

            centerPanel.add(emailField);

            JLabel usernameLabel = new JLabel();
            usernameLabel.setText("Username :");
            usernameLabel.setBounds(450, 50, 150, 40);
            usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

            centerPanel.add(usernameLabel);

            JTextField usernameField = new JTextField(15);
            usernameField.setBounds(570, 53, 170, 35);
            usernameField.setText(specificPatient.getUsername());
            usernameField.setFont(new Font("Roboto", Font.PLAIN, 16));

            centerPanel.add(usernameField);

            JLabel passwordLabel = new JLabel();
            passwordLabel.setText("Password :");
            passwordLabel.setBounds(450, 100, 150, 40);
            passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

            centerPanel.add(passwordLabel);

            JTextField passwordField = new JTextField(15);
            passwordField.setBounds(570, 103, 170, 35);
            try {
                passwordField.setText(PasswordEncryptDecrypt.decrypt(specificPatient.getPassword()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            passwordField.setFont(new Font("Roboto", Font.PLAIN, 16));

            centerPanel.add(passwordField);

            JButton submitButton = new JButton();
            submitButton.setText("UPDATE");
            submitButton.setBackground(Color.YELLOW);
            submitButton.setForeground(Color.black);
            submitButton.setFont(new Font("Roboto", Font.BOLD, 15));
            submitButton.setOpaque(true);
            submitButton.setBorderPainted(false);
            submitButton.setBounds(600, 380, 120, 50);
            submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    specificPatient.setName(nameField.getText());
                    specificPatient.setContactNumber(contactField.getText());
                    specificPatient.setEmergencyContact(emergencyField.getText());
                    specificPatient.setBloodGroup(bloodGroupField.getText());
                    specificPatient.setStatus(statusField.getText());
                    specificPatient.setEmail(emailField.getText());
                    specificPatient.setUsername(usernameField.getText());
                    try {
                        specificPatient.setPassword(PasswordEncryptDecrypt.encrypt(passwordField.getText()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    patients.set(specificPatient.getId() - 1, specificPatient);

                    try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/patients.dat"))) {

                        for (Patient patient : patients) {
                            ois.writeObject(patient);
                        }

                    } catch (IOException error) {
                        error.printStackTrace();
                    }

                    ChangePatientCenterScreen.setPatientCenterScreen("default");
                    AdminDashboard.ReflectUIChange();

                }

            });

            centerPanel.add(submitButton);

            centerPanel.revalidate();
            centerPanel.repaint();

        } else {

            centerPanel.removeAll();

            centerPanel.setBounds(200, 200, 800, 500);

            JLabel text = new JLabel();
            text.setText("ID not Found!");
            text.setForeground(Color.red);
            text.setFont(new Font("Roboto", Font.PLAIN, 50));
            text.setBounds(50, 70, 500, 500);

            centerPanel.add(text);

            centerPanel.revalidate();
            centerPanel.repaint();

        }

    }

    public static void bookAppointments(JPanel centerPanel) {

        ArrayList<Hospital> preHospitals = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/hospitals.dat"))) {
            while (true) {
                try {
                    preHospitals.add((Hospital) ois.readObject());
                } catch (EOFException eof) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        int id = LoggedInUser.id;

        ArrayList<Patient> patients = new ArrayList<>();
        File file = new File("src/hospitalproject/data/patients.dat");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/patients.dat"))) {
                while (true) {
                    try {
                        patients.add((Patient) ois.readObject());
                    } catch (EOFException eoferr) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException error) {
                error.printStackTrace();
            }
        }

        int matchedIndex = -1;
        for (Patient patient : patients) {
            if (id == patient.getId()) {
                matchedIndex = id - 1;
            }
        }

        Patient specificPatient = null;
        if (matchedIndex != -1) {
            specificPatient = patients.get(matchedIndex);
        }

        ArrayList<Hospital> finalHospitals = new ArrayList<>();
        for (Hospital hospital : preHospitals) {
            if (hospital.getCity().equals(specificPatient.getDistrict())) {
                finalHospitals.add(hospital);
            }
        }

        JPanel mainDisplayPanel = new JPanel();
        mainDisplayPanel.setBounds(300, 100, 700, 600);
        mainDisplayPanel.setLayout(new BoxLayout(mainDisplayPanel, BoxLayout.Y_AXIS));

        JLabel textLabel = new JLabel("Please Select A Hospital");
        textLabel.setFont(new Font("Roboto", Font.PLAIN, 30));
        textLabel.setBounds(450, 5, 700, 140);

        centerPanel.add(textLabel);

        for (Hospital hospital : finalHospitals) {
            JButton button = new JButton(hospital.getName());
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.addActionListener(e -> {
                showDoctorDialog(hospital, mainDisplayPanel, textLabel);
            });
            mainDisplayPanel.add(button);
        }

        centerPanel.add(mainDisplayPanel);
    }

    private static void showDoctorDialog(Hospital hospital, JPanel displayPanel, JLabel label) {

        ArrayList<Doctor> preDoctors = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/doctors.dat"))) {
            while (true) {
                try {
                    preDoctors.add((Doctor) ois.readObject());
                } catch (EOFException eof) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Doctor> finalDoctors = new ArrayList<>();
        for (Doctor doctor : preDoctors) {
            if (doctor.getHospitalName().equals(hospital.getName())) {
                finalDoctors.add(doctor);
            }
        }

        label.setText("Please Select A Doctor");
        displayPanel.removeAll();

        for (Doctor doctor : finalDoctors) {
            JButton button2 = new JButton(doctor.getName());
            button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button2.addActionListener(e -> {
                showAvailableTimeSlotDialog(doctor, displayPanel, label);
                displayPanel.removeAll();
                displayPanel.revalidate();
                displayPanel.repaint();
            });

            displayPanel.add(button2);
        }

        displayPanel.revalidate();
        displayPanel.repaint();
    }

    private static void showAvailableTimeSlotDialog(Doctor doctor, JPanel panel, JLabel label) {

        ArrayList<String> availableTime = doctor.getAvailability();

        String selectedTimeSlot = (String) JOptionPane.showInputDialog(panel,
                "Please select a time slot:",
                "Select Time Slot",
                JOptionPane.QUESTION_MESSAGE,
                null,
                availableTime.toArray(),
                availableTime.get(0));

        if (selectedTimeSlot != null) {

            Appointment appointment = new Appointment(1, LoggedInUser.name, doctor.getName(), selectedTimeSlot, "appointment");

            File file = new File("src/hospitalproject/data/appointments.dat");

            if (file.exists()) {

                ArrayList<Appointment> appointmentArr = new ArrayList<>();

                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/appointments.dat"))) {
                    while (true) {

                        try {

                            appointmentArr.add((Appointment) ois.readObject());

                        } catch (EOFException eofErr) {
                            break;
                        }

                    }

                } catch (IOException | ClassNotFoundException err) {
                    err.printStackTrace();
                }

                appointmentArr.add(appointment);

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/appointments.dat"))) {

                    for (Appointment appointmentSingle : appointmentArr) {

                        oos.writeObject(appointmentSingle);

                    }

                    System.out.println("Appointment Data Written Appended !");

                } catch (IOException err) {
                    err.printStackTrace();
                }

                ChangePatientCenterScreen.setPatientCenterScreen("default");
                PatientDashboard.ReflectUIChange();

            } else {

                try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/appointments.dat"))) {

                    ois.writeObject(appointment);

                    System.out.println("Appointment Written!");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                ChangePatientCenterScreen.setPatientCenterScreen("default");
                PatientDashboard.ReflectUIChange();

            }

        }
    }

    public static void upcomingAppointments(JPanel centerPanel) {

        ArrayList<Appointment> appointmentArr = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/appointments.dat"))) {
            while (true) {

                try {

                    appointmentArr.add((Appointment) ois.readObject());

                } catch (EOFException eofErr) {
                    break;
                }

            }

        } catch (IOException | ClassNotFoundException err) {
            err.printStackTrace();
        }

        ArrayList<Appointment> newAppointments = new ArrayList<>();

        for (Appointment appointmentSingle : appointmentArr) {

            if (appointmentSingle.getPatientName().equals(LoggedInUser.name) && appointmentSingle.getStatus().equals("pending") || appointmentSingle.getStatus().equals("confirmed")) {

                newAppointments.add(appointmentSingle);
            }

        }
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(300, 10, 700, 600);
        mainPanel.setLayout(new BorderLayout());

        
        String[] columnNames = {"ID", "Patient Name", "Doctor Name", "time slot", "status", "Date"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Appointment appointment : newAppointments) {

            Object[] row = {appointment.getAppointmentId(), appointment.getPatientName(), appointment.getDoctorName(), appointment.getTimeSlot(), appointment.getStatus(), "N/A"};

            model.addRow(row);

        }

        JTable table = new JTable(model);

        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(40);
        table.getColumnModel().getColumn(5).setPreferredWidth(40);

        JScrollPane scrollPane = new JScrollPane(table);
        
        mainPanel.add(scrollPane,BorderLayout.CENTER);
        
        
        centerPanel.add(mainPanel);

    }
    
    public static void pastAppointments(JPanel centerPanel){
        
        ArrayList<Appointment> appointmentArr = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/appointments.dat"))) {
            while (true) {

                try {

                    appointmentArr.add((Appointment) ois.readObject());

                } catch (EOFException eofErr) {
                    break;
                }

            }

        } catch (IOException | ClassNotFoundException err) {
            err.printStackTrace();
        }

        ArrayList<Appointment> newAppointments = new ArrayList<>();

        for (Appointment appointmentSingle : appointmentArr) {

            if (appointmentSingle.getStatus().equals("done") && appointmentSingle.getPatientName().equals(LoggedInUser.name)) {

                newAppointments.add(appointmentSingle);
            }

        }
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(300, 10, 700, 600);
        mainPanel.setLayout(new BorderLayout());

        
        String[] columnNames = {"ID", "Patient Name", "Doctor Name", "time slot", "status", "Date"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Appointment appointment : newAppointments) {

            Object[] row = {appointment.getAppointmentId(), appointment.getPatientName(), appointment.getDoctorName(), appointment.getTimeSlot(), appointment.getStatus(), "N/A"};

            model.addRow(row);

        }

        JTable table = new JTable(model);

        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(40);
        table.getColumnModel().getColumn(5).setPreferredWidth(40);

        JScrollPane scrollPane = new JScrollPane(table);
        
        mainPanel.add(scrollPane,BorderLayout.CENTER);
        
        
        centerPanel.add(mainPanel);


        
    }
    
    public static void myProfileScreen(JPanel centerPanel){
        
        
        
    }
}
