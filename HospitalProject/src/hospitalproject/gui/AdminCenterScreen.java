package hospitalproject.gui;

import com.toedter.calendar.JDateChooser;
import hospitalproject.models.Admin;
import hospitalproject.models.Appointment;
import hospitalproject.models.Doctor;
import hospitalproject.models.Hospital;
import hospitalproject.models.Patient;
import hospitalproject.utils.ChangeAdminCenterScreen;
import hospitalproject.utils.LoggedInUser;
import hospitalproject.utils.PasswordEncryptDecrypt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdminCenterScreen {

    static JPanel formPanel;
    static GridBagConstraints gdc;

    public static void defaultScreen(JPanel centerPanel) {

        JLabel helloText = new JLabel();
        helloText.setText("Welcome, " + LoggedInUser.name);
        helloText.setFont(new Font("Roboto", Font.BOLD, 20));
        helloText.setBounds(100, 70, 800, 50);

        centerPanel.add(helloText);

        /* Total Hospital Panel */
        File hospitalFile = new File("src/hospitalproject/data/hospitals.dat");

        String hospitalCountValue;

        ArrayList<Hospital> hospitals = new ArrayList<>();

        if (hospitalFile.exists()) {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/hospitals.dat"))) {
                while (true) {
                    try {

                        hospitals.add((Hospital) ois.readObject());

                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            hospitalCountValue = Integer.toString(hospitals.size());

        } else {
            hospitalCountValue = "0";
        }

        centerPanel.add(createPanel("src/hospitalproject/images/mini_hospital_icon.png", "Total Hospital Count", hospitalCountValue, new Color(29, 57, 128), 90, 180));

        /* Total Doctor Panel */
        File doctorFile = new File("src/hospitalproject/data/doctors.dat");

        String doctorCountValue;

        ArrayList<Doctor> doctors = new ArrayList<>();

        if (doctorFile.exists()) {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/doctors.dat"))) {
                while (true) {
                    try {

                        doctors.add((Doctor) ois.readObject());

                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            doctorCountValue = Integer.toString(doctors.size());

        } else {
            doctorCountValue = "0";
        }

        centerPanel.add(createPanel("src/hospitalproject/images/doctor_emoji.png", "Total Doctor Count", doctorCountValue, new Color(29, 57, 128), 700, 180));

        /* Total Patient Panel */
        File patientFile = new File("src/hospitalproject/data/patients.dat");

        String patientCountValue;

        ArrayList<Patient> patients = new ArrayList<>();

        if (patientFile.exists()) {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/patients.dat"))) {
                while (true) {
                    try {

                        patients.add((Patient) ois.readObject());

                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            patientCountValue = Integer.toString(patients.size());

        } else {
            patientCountValue = "0";
        }

        centerPanel.add(createPanel("src/hospitalproject/images/patient_emoji.png", "Patients Registered", patientCountValue, new Color(29, 57, 128), 90, 480));

        /* Total Appointment Panel */
        File appointFile = new File("src/hospitalproject/data/appointments.dat");

        String appointCountValue;

        ArrayList<Appointment> appointments = new ArrayList<>();

        if (appointFile.exists()) {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/appointments.dat"))) {
                while (true) {
                    try {

                        appointments.add((Appointment) ois.readObject());

                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            appointCountValue = Integer.toString(appointments.size());

        } else {
            appointCountValue = "0";
        }

        String pendingAppointment;

        ArrayList<Appointment> pendingAppointments = new ArrayList<>();

        if (appointFile.exists()) {

            for (Appointment appointment : appointments) {
                if (appointment.getStatus().equals("pending")) {
                    pendingAppointments.add(appointment);
                }
            }

            pendingAppointment = Integer.toString(pendingAppointments.size());

        } else {
            pendingAppointment = "0";
        }

        JPanel totalAppointmentPanel = new JPanel();
        totalAppointmentPanel.setBackground(new Color(29, 57, 128));
        totalAppointmentPanel.setOpaque(true);
        totalAppointmentPanel.setBounds(700, 480, 400, 200);
        totalAppointmentPanel.setLayout(null);

        ImageIcon appointmentPanelImage = new ImageIcon("src/hospitalproject/images/appointment_icon.png");
        Image scaledAppointmentPanelImage = appointmentPanelImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        ImageIcon scaledAppointmentIcon = new ImageIcon(scaledAppointmentPanelImage);

        JLabel appointmentImageLabel = new JLabel();
        appointmentImageLabel.setIcon(scaledAppointmentIcon);
        appointmentImageLabel.setBounds(164, 10, 50, 50);
        appointmentImageLabel.setOpaque(true);
        appointmentImageLabel.setBackground(new Color(29, 57, 128));

        totalAppointmentPanel.add(appointmentImageLabel);

        JLabel titleTextAppointment = new JLabel();
        titleTextAppointment.setText("Total Appointments : " + appointCountValue);
        titleTextAppointment.setFont(new Font("Roboto", Font.BOLD, 23));
        titleTextAppointment.setForeground(Color.white);
        titleTextAppointment.setBounds(80, 60, 400, 50);

        totalAppointmentPanel.add(titleTextAppointment);

        JLabel pendingAppointmentText = new JLabel();
        pendingAppointmentText.setText("Pending : " + pendingAppointment);
        pendingAppointmentText.setFont(new Font("Roboto", Font.BOLD, 23));
        pendingAppointmentText.setForeground(Color.yellow);
        pendingAppointmentText.setBounds(135, 110, 190, 50);

        totalAppointmentPanel.add(pendingAppointmentText);

        centerPanel.add(totalAppointmentPanel);

    }

    private static JPanel createPanel(String imagePath, String titleText, String countValue, Color backgroundColor, int x, int y) {

        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setBounds(x, y, 400, 200);
        panel.setLayout(null);

        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
        iconLabel.setBounds(160, 10, 50, 50);
        iconLabel.setOpaque(true);
        iconLabel.setBackground(backgroundColor);
        panel.add(iconLabel);

        JLabel title = new JLabel(titleText);
        title.setForeground(Color.white);
        title.setFont(new Font("Roboto", Font.BOLD, 23));
        title.setBounds(80, 60, 400, 50);
        panel.add(title);

        JLabel count = new JLabel(countValue);
        count.setForeground(Color.white);
        count.setFont(new Font("Roboto", Font.BOLD, 23));
        count.setBounds(180, 110, 40, 50);
        panel.add(count);

        return panel;
    }

    public static void addHospitalScreen(JPanel centerPanel) {

        JLabel titleText = new JLabel();
        titleText.setText("Add New Hospital");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);

        formPanel = new JPanel();
        formPanel.setBounds(350, 70, 500, 650);
        formPanel.setLayout(new GridBagLayout());

        gdc = new GridBagConstraints();
        gdc.insets = new Insets(5, 10, 5, 10);

        JLabel idLabel = new JLabel();
        idLabel.setText("ID :");
        idLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        gdc.gridx = 0;
        gdc.gridy = 0;
        gdc.anchor = GridBagConstraints.WEST;

        formPanel.add(idLabel, gdc);

        String idValue;

        ArrayList<Hospital> hospitals = new ArrayList<>();

        File file = new File("src/hospitalproject/data/hospitals.dat");

        if (file.exists()) {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/hospitals.dat"))) {

                while (true) {

                    try {
                        hospitals.add((Hospital) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (hospitals.isEmpty()) {
            idValue = "1";
        } else {
            idValue = Integer.toString(hospitals.get(hospitals.size() - 1).getId() + 1);
        }

        JTextField idField = new JTextField(15);
        idField.setPreferredSize(new Dimension(400, 30));
        idField.setFont(new Font("Roboto", Font.PLAIN, 14));
        idField.setEnabled(false);
        idField.setText(idValue);
        idField.setForeground(Color.BLACK);

        gdc.gridx = 1;
        gdc.gridy = 0;
        gdc.anchor = GridBagConstraints.EAST;

        formPanel.add(idField, gdc);

        JLabel nameLabel = new JLabel();
        JTextField nameField = new JTextField(15);

        textInput(nameLabel, nameField, "Name :", "Smith Hospital Ltd.", 0, 1, 1, 1);

        JLabel registrationNumLabel = new JLabel();
        JTextField regField = new JTextField(15);

        textInput(registrationNumLabel, regField, "Reg Num :", "8329A8BD", 0, 2, 1, 2);

        JLabel addressLabel = new JLabel();
        JTextField addressField = new JTextField(15);

        textInput(addressLabel, addressField, "Address : ", "00/0 station road", 0, 3, 1, 3);

        JLabel cityLabel = new JLabel();
        JTextField cityField = new JTextField(15);

        textInput(cityLabel, cityField, "City :", "Dhaka", 0, 4, 1, 4);

        JLabel postalLabel = new JLabel();
        JTextField postalField = new JTextField(15);

        textInput(postalLabel, postalField, "Postal Code :", "0000", 0, 5, 1, 5);

        JLabel phoneLabel = new JLabel();
        JTextField phoneField = new JTextField(15);

        textInput(phoneLabel, phoneField, "Phone No. ", "01700000000", 0, 6, 1, 6);

        JLabel emailLabel = new JLabel();
        JTextField emailField = new JTextField(15);

        textInput(emailLabel, emailField, "Email ", "0000@gmail.com", 0, 7, 1, 7);

        JLabel websiteLabel = new JLabel();
        JTextField websiteField = new JTextField(15);

        textInput(websiteLabel, websiteField, "Website ", "www.0000.com", 0, 8, 1, 8);

        JLabel estaLabel = new JLabel();
        estaLabel.setText("Established Date");
        estaLabel.setFont(new Font("Roboto", Font.PLAIN, 16));

        gdc.gridx = 0;
        gdc.gridy = 9;
        gdc.anchor = GridBagConstraints.WEST;

        formPanel.add(estaLabel, gdc);

        JDateChooser dateEsta = new JDateChooser();
        dateEsta.setDateFormatString("dd/MM/yyyy");

        gdc.gridx = 1;
        gdc.gridy = 9;
        gdc.anchor = GridBagConstraints.EAST;

        formPanel.add(dateEsta, gdc);

        JLabel bedsLabel = new JLabel();
        JTextField bedsField = new JTextField(15);

        textInput(bedsLabel, bedsField, "Total Beds : ", "00", 0, 10, 1, 10);

        JLabel doctorLabel = new JLabel();
        JTextField doctorField = new JTextField(15);

        textInput(doctorLabel, doctorField, "Total Doctors : ", "00", 0, 11, 1, 11);

        JLabel staffLabel = new JLabel();
        JTextField staffField = new JTextField(15);

        textInput(staffLabel, staffField, "Total Staffs :", "00", 0, 12, 1, 12);

        JLabel departmentsLabel = new JLabel();
        JTextField departmentsField = new JTextField(15);

        textInput(departmentsLabel, departmentsField, "Departments :", "Cardiology,Surgery,Radiology", 0, 13, 1, 13);

        JButton addButton = new JButton();
        addButton.setText("ADD");
        addButton.setForeground(Color.white);
        addButton.setBackground(Color.black);
        addButton.setMargin(new Insets(5, 10, 5, 10));
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String idString = idField.getText();
                int id = Integer.parseInt(idString);

                String name = nameField.getText();
                String reg = regField.getText();
                String address = addressField.getText();
                String city = cityField.getText();
                String postalCode = postalField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();
                String website = websiteField.getText();
                Date selectedDate = dateEsta.getDate();

                String bedsString = bedsField.getText();
                int beds = Integer.parseInt(bedsString);

                String doctorString = doctorField.getText();
                int doctors = Integer.parseInt(doctorString);

                String staffString = staffField.getText();
                int staffs = Integer.parseInt(staffString);

                String departmentsText = departmentsField.getText();
                String[] arrDepartment = departmentsText.split(",");

                ArrayList<String> departments = new ArrayList<>();

                for (String s : arrDepartment) {

                    departments.add(s);
                }

                Hospital hospitalObj = new Hospital(id, name, reg, address, city, postalCode, phone, email, website, selectedDate, beds, doctors, staffs);

                hospitalObj.setDepartments(departments);

                File file = new File("src/hospitalproject/data/hospitals.dat");

                if (file.exists()) {

                    ArrayList<Hospital> hospitals = new ArrayList<>();

                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/hospitals.dat"))) {
                        while (true) {

                            try {

                                hospitals.add((Hospital) ois.readObject());

                            } catch (EOFException eofErr) {
                                break;
                            }

                        }

                    } catch (IOException | ClassNotFoundException err) {
                        err.printStackTrace();
                    }

                    hospitals.add(hospitalObj);

                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/hospitals.dat"))) {

                        for (Hospital hospital : hospitals) {

                            oos.writeObject(hospital);

                        }

                        System.out.println("Hospital Data Written Appended !");

                    } catch (IOException err) {
                        err.printStackTrace();
                    }

                    ChangeAdminCenterScreen.setAdminCenterScreen("default");
                    AdminDashboard.ReflectUIChange();

                } else {

                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/hospitals.dat"))) {

                        oos.writeObject(hospitalObj);
                        System.out.println("Hospital Data Written !");

                    } catch (IOException err) {
                        err.printStackTrace();
                    }

                    ChangeAdminCenterScreen.setAdminCenterScreen("default");
                    AdminDashboard.ReflectUIChange();

                }
            }
        });

        gdc.gridx = 1;
        gdc.gridy = 14;
        gdc.anchor = GridBagConstraints.EAST;

        formPanel.add(addButton, gdc);

        centerPanel.add(formPanel);
    }

    private static void textInput(JLabel label, JTextField field, String text, String placeholder, int a, int b, int x, int y) {

        label.setText(text);
        label.setFont(new Font("Roboto", Font.PLAIN, 16));

        gdc.gridx = a;
        gdc.gridy = b;
        gdc.anchor = GridBagConstraints.WEST;

        formPanel.add(label, gdc);

        field.setPreferredSize(new Dimension(400, 30));
        field.setFont(new Font("Roboto", Font.PLAIN, 14));
        field.setText(placeholder);
        field.setForeground(Color.gray);
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });

        gdc.gridx = x;
        gdc.gridy = y;
        gdc.anchor = GridBagConstraints.EAST;

        formPanel.add(field, gdc);
    }

    public static void deleteHospitalScreen(JPanel centerPanel) {

        JLabel titleText = new JLabel();
        titleText.setText("Delete Hospital");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);

        JPanel searchIdPanel = new JPanel();
        searchIdPanel.setBounds(200, 250, 800, 350);
        searchIdPanel.setLayout(null);

        JLabel deleteTextLabel = new JLabel();
        deleteTextLabel.setText("Enter ID of the Hospital :");
        deleteTextLabel.setBounds(270, 60, 300, 50);
        deleteTextLabel.setFont(new Font("Roboto", Font.PLAIN, 20));

        searchIdPanel.add(deleteTextLabel);

        JTextField deleteField = new JTextField(15);
        deleteField.setBounds(280, 130, 200, 50);
        deleteField.setFont(new Font("Roboto", Font.BOLD, 17));

        searchIdPanel.add(deleteField);

        JButton deleteButton = new JButton();
        deleteButton.setText("DELETE");
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.setBackground(Color.red);
        deleteButton.setFont(new Font("Roboto", Font.BOLD, 16));
        deleteButton.setBounds(310, 220, 140, 30);
        deleteButton.setForeground(Color.white);
        deleteButton.setOpaque(true);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteButton.setBackground(new Color(255, 100, 100));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteButton.setBackground(Color.red);
            }
        });
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(null, "Are You sure you want to Delete?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {

                    ArrayList<Hospital> hospitals = new ArrayList<>();

                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/hospitals.dat"))) {
                        while (true) {

                            try {

                                hospitals.add((Hospital) ois.readObject());

                            } catch (EOFException eofErr) {
                                break;
                            }

                        }

                    } catch (IOException | ClassNotFoundException err) {
                        err.printStackTrace();
                    }

                    int data = Integer.parseInt(deleteField.getText());
                    int matchedIndex = -1;

                    for (int i = 0; i < hospitals.size(); i++) {

                        if (hospitals.get(i).getId() == data) {
                            matchedIndex = i;
                            break;
                        }
                    }

                    if (matchedIndex != -1) {

                        hospitals.remove(matchedIndex);

                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/hospitals.dat"))) {

                            for (Hospital hospital : hospitals) {
                                oos.writeObject(hospital);
                            }

                            System.out.println("Deleted A Hospital and Updated file!");

                        } catch (IOException error) {
                            error.printStackTrace();
                        }

                        ChangeAdminCenterScreen.setAdminCenterScreen("default");
                        AdminDashboard.ReflectUIChange();

                    } else {

                        JOptionPane.showMessageDialog(null, "You have Entered a wrong ID", "error", JOptionPane.ERROR_MESSAGE);

                    }

                } else {

                }
            }

        });

        searchIdPanel.add(deleteButton);

        centerPanel.add(searchIdPanel);

    }

    public static void displayAllHospitalScreen(JPanel centerPanel) {

        JPanel displayPanel = new JPanel();

        JLabel titleText = new JLabel();
        titleText.setText("All Hospitals");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);

        JTextField citySearchField = new JTextField(15);
        citySearchField.setPreferredSize(new Dimension(100, 30));
        citySearchField.setBounds(410, 80, 160, 40);
        citySearchField.setText("#city search");
        citySearchField.setForeground(Color.GRAY);

        citySearchField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (citySearchField.getText().equals("#city search")) {
                    citySearchField.setText("");
                    citySearchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (citySearchField.getText().isEmpty()) {
                    citySearchField.setText("#city search");
                    citySearchField.setForeground(Color.GRAY);
                }
            }
        });

        centerPanel.add(citySearchField);

        JButton searchButton = new JButton();
        searchButton.setText("SEARCH");
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);
        searchButton.setBackground(Color.black);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setForeground(Color.white);
        searchButton.setBounds(610, 85, 100, 30);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                searchedDisplayHospital(displayPanel, citySearchField);

            }

        });

        centerPanel.add(searchButton);

        displayPanel.setBounds(140, 200, 980, 400);
        displayPanel.setLayout(new BorderLayout());

        allDisplayHospital(displayPanel);

        centerPanel.add(displayPanel);

    }

    private static void allDisplayHospital(JPanel panel) {

        ArrayList<Hospital> hospitals = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/hospitals.dat"))) {

            while (true) {
                try {

                    hospitals.add((Hospital) ois.readObject());

                } catch (EOFException eoferr) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        String[] columnNames = {"ID", "Name", "Reg Num", "Address", "City", "PostalCode", "Phone", "Email", "Est Date"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Hospital hospital : hospitals) {

            Object[] row = {hospital.getId(), hospital.getName(), hospital.getRegistrationNumber(), hospital.getAddress(), hospital.getCity(), hospital.getPostalCode(), hospital.getPhoneNumber(), hospital.getEmail(), sdf.format(hospital.getEstablishedDate())};

            model.addRow(row);

        }

        JTable table = new JTable(model);

        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(20);
        table.getColumnModel().getColumn(5).setPreferredWidth(20);
        table.getColumnModel().getColumn(6).setPreferredWidth(45);

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

    }

    private static void searchedDisplayHospital(JPanel panel, JTextField textField) {

        panel.removeAll();

        ArrayList<Hospital> hospitals = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/hospitals.dat"))) {

            while (true) {
                try {

                    hospitals.add((Hospital) ois.readObject());

                } catch (EOFException eoferr) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Hospital> newHospitals = new ArrayList<>();

        for (Hospital hospital : hospitals) {

            if (textField.getText().toLowerCase().equals(hospital.getCity().toLowerCase())) {
                newHospitals.add(hospital);
            }

        }

        if (newHospitals.isEmpty()) {

            JLabel text = new JLabel();
            text.setText(" No Search Results Found ! ");
            text.setFont(new Font("Roboto", Font.PLAIN, 50));
            text.setForeground(Color.ORANGE);

            panel.add(text, BorderLayout.CENTER);

        } else {

            String[] columnNames = {"ID", "Name", "Reg Num", "Address", "City", "PostalCode", "Phone", "Email", "Est Date"};

            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Hospital hospital : newHospitals) {

                Object[] row = {hospital.getId(), hospital.getName(), hospital.getRegistrationNumber(), hospital.getAddress(), hospital.getCity(), hospital.getPostalCode(), hospital.getPhoneNumber(), hospital.getEmail(), sdf.format(hospital.getEstablishedDate())};

                model.addRow(row);

            }

            JTable table = new JTable(model);

            table.getColumnModel().getColumn(0).setPreferredWidth(5);
            table.getColumnModel().getColumn(2).setPreferredWidth(20);
            table.getColumnModel().getColumn(4).setPreferredWidth(20);
            table.getColumnModel().getColumn(5).setPreferredWidth(20);
            table.getColumnModel().getColumn(6).setPreferredWidth(45);

            JScrollPane scrollPane = new JScrollPane(table);

            panel.add(scrollPane, BorderLayout.CENTER);

        }

        panel.revalidate();
        panel.repaint();

    }

    public static void updateHospitalScreen(JPanel centerPanel) {

        JLabel titleText = new JLabel();
        titleText.setText("Update Hospital Info");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(300, 300, 600, 300);
        mainPanel.setLayout(null);

        JLabel textLabel = new JLabel();
        textLabel.setText("Enter ID of the Hospital :");
        textLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        textLabel.setBounds(180, 50, 250, 30);

        mainPanel.add(textLabel);

        JTextField idSearchField = new JTextField(15);
        idSearchField.setBounds(230, 100, 120, 40);
        idSearchField.setText("#ID");
        idSearchField.setForeground(Color.gray);
        idSearchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (idSearchField.getText().equals("#ID")) {
                    idSearchField.setText("");
                    idSearchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (idSearchField.getText().isEmpty()) {
                    idSearchField.setText("#ID");
                    idSearchField.setForeground(Color.GRAY);
                }
            }
        });

        mainPanel.add(idSearchField);

        JButton idSearchButton = new JButton();
        idSearchButton.setText("PROCEED");
        idSearchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        idSearchButton.setOpaque(true);
        idSearchButton.setBorderPainted(false);
        idSearchButton.setBackground(Color.black);
        idSearchButton.setForeground(Color.white);
        idSearchButton.setBounds(236, 170, 110, 30);
        idSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(idSearchField.getText());

                ArrayList<Hospital> hospitals = new ArrayList<>();

                File file = new File("src/hospitalproject/data/hospitals.dat");

                if (file.exists()) {

                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/hospitals.dat"))) {

                        while (true) {

                            try {
                                hospitals.add((Hospital) ois.readObject());
                            } catch (EOFException eoferr) {
                                break;
                            }
                        }
                    } catch (IOException | ClassNotFoundException error) {
                        error.printStackTrace();
                    }
                }

                int matchedIndex = -1;

                for (Hospital hospital : hospitals) {
                    if (id == hospital.getId()) {
                        matchedIndex = id - 1;
                    }
                }

                if (matchedIndex != -1) {

                    mainPanel.removeAll();

                    mainPanel.setBounds(200, 200, 800, 500);

                    Hospital specificHospital = hospitals.get(matchedIndex);

                    JLabel IdLabel = new JLabel();
                    IdLabel.setText("ID :");
                    IdLabel.setBounds(50, 50, 50, 40);
                    IdLabel.setFont(new Font("Roboto", Font.PLAIN, 20));

                    mainPanel.add(IdLabel);

                    JTextField idField = new JTextField(15);
                    idField.setBounds(100, 56, 80, 35);
                    idField.setText(Integer.toString(specificHospital.getId()));
                    idField.setEnabled(false);
                    idField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(idField);

                    JLabel nameLabel = new JLabel();
                    nameLabel.setText("Name :");
                    nameLabel.setBounds(50, 110, 100, 40);
                    nameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(nameLabel);

                    JTextField nameField = new JTextField(15);
                    nameField.setBounds(130, 116, 180, 35);
                    nameField.setText(specificHospital.getName());
                    nameField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(nameField);

                    JLabel regNumLabel = new JLabel();
                    regNumLabel.setText("Reg Number :");
                    regNumLabel.setBounds(50, 160, 130, 40);
                    regNumLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(regNumLabel);

                    JTextField regNumField = new JTextField(15);
                    regNumField.setBounds(180, 166, 150, 35);
                    regNumField.setText(specificHospital.getRegistrationNumber());
                    regNumField.setEnabled(false);
                    regNumField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(regNumField);

                    JLabel addressLabel = new JLabel();
                    addressLabel.setText("Address :");
                    addressLabel.setBounds(50, 215, 130, 40);
                    addressLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(addressLabel);

                    JTextField addressField = new JTextField(15);
                    addressField.setBounds(145, 220, 170, 35);
                    addressField.setText(specificHospital.getAddress());
                    addressField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(addressField);

                    JLabel cityLabel = new JLabel();
                    cityLabel.setText("City :");
                    cityLabel.setBounds(50, 265, 130, 40);
                    cityLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(cityLabel);

                    JTextField cityField = new JTextField(15);
                    cityField.setBounds(120, 270, 170, 35);
                    cityField.setText(specificHospital.getCity());
                    cityField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(cityField);

                    JLabel postalLabel = new JLabel();
                    postalLabel.setText("Postal Code :");
                    postalLabel.setBounds(50, 320, 150, 40);
                    postalLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(postalLabel);

                    JTextField postalField = new JTextField(15);
                    postalField.setBounds(170, 325, 150, 35);
                    postalField.setText(specificHospital.getPostalCode());
                    postalField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(postalField);

                    JLabel phoneLabel = new JLabel();
                    phoneLabel.setText("Phone :");
                    phoneLabel.setBounds(50, 370, 150, 40);
                    phoneLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(phoneLabel);

                    JTextField phoneField = new JTextField(15);
                    phoneField.setBounds(140, 375, 150, 35);
                    phoneField.setText(specificHospital.getPhoneNumber());
                    phoneField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(phoneField);

                    JLabel emailLabel = new JLabel();
                    emailLabel.setText("Email :");
                    emailLabel.setBounds(450, 50, 150, 40);
                    emailLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(emailLabel);

                    JTextField emailField = new JTextField(15);
                    emailField.setBounds(520, 53, 170, 35);
                    emailField.setText(specificHospital.getEmail());
                    emailField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(emailField);

                    JLabel websiteLabel = new JLabel();
                    websiteLabel.setText("Website :");
                    websiteLabel.setBounds(450, 100, 150, 40);
                    websiteLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(websiteLabel);

                    JTextField websiteField = new JTextField(15);
                    websiteField.setBounds(540, 103, 170, 35);
                    websiteField.setText(specificHospital.getWebsiteName());
                    websiteField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(websiteField);

                    JLabel totalBedsLabel = new JLabel();
                    totalBedsLabel.setText("Total Beds :");
                    totalBedsLabel.setBounds(450, 150, 150, 40);
                    totalBedsLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(totalBedsLabel);

                    JTextField totalBedsField = new JTextField(15);
                    totalBedsField.setBounds(570, 153, 130, 35);
                    totalBedsField.setText(Integer.toString(specificHospital.getTotalBeds()));
                    totalBedsField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(totalBedsField);

                    JLabel totalDoctorsLabel = new JLabel();
                    totalDoctorsLabel.setText("Total Doctors :");
                    totalDoctorsLabel.setBounds(450, 200, 150, 40);
                    totalDoctorsLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(totalDoctorsLabel);

                    JTextField totalDoctorsField = new JTextField(15);
                    totalDoctorsField.setBounds(600, 203, 125, 35);
                    totalDoctorsField.setText(Integer.toString(specificHospital.getTotalDoctors()));
                    totalDoctorsField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(totalDoctorsField);

                    JLabel totalStaffsLabel = new JLabel();
                    totalStaffsLabel.setText("Total Staffs :");
                    totalStaffsLabel.setBounds(450, 250, 150, 40);
                    totalStaffsLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(totalStaffsLabel);

                    JTextField totalStaffsField = new JTextField(15);
                    totalStaffsField.setBounds(580, 253, 125, 35);
                    totalStaffsField.setText(Integer.toString(specificHospital.getTotalDoctors()));
                    totalStaffsField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(totalStaffsField);

                    JLabel statusLabel = new JLabel();
                    statusLabel.setText("Status :");
                    statusLabel.setBounds(460, 300, 150, 40);
                    statusLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(statusLabel);

                    String[] options = {"active", "inactive"};

                    JComboBox<String> comboBox = new JComboBox<>(options);
                    comboBox.setBounds(550, 305, 100, 35);

                    final String[] selected = new String[1];

                    comboBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            selected[0] = (String) comboBox.getSelectedItem();

                        }

                    });

                    mainPanel.add(comboBox);

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

                            specificHospital.setName(nameField.getText());
                            specificHospital.setRegistrationNumber(regNumField.getText());
                            specificHospital.setAddress(addressField.getText());
                            specificHospital.setCity(cityField.getText());
                            specificHospital.setPostalCode(postalField.getText());
                            specificHospital.setPhoneNumber(phoneField.getText());
                            specificHospital.setEmail(emailField.getText());
                            specificHospital.setWebsiteName(websiteField.getText());
                            specificHospital.setTotalBeds(Integer.parseInt(totalBedsField.getText()));
                            specificHospital.setTotalDoctors(Integer.parseInt(totalDoctorsField.getText()));
                            specificHospital.setTotalStaffs(Integer.parseInt(totalStaffsField.getText()));
                            specificHospital.setStatus(selected[0] == null ? "active" : "inactive");

                            hospitals.set(specificHospital.getId() - 1, specificHospital);

                            try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/hospitals.dat"))) {

                                for (Hospital hospital : hospitals) {

                                    ois.writeObject(hospital);

                                }

                            } catch (IOException eror) {

                            }

                            ChangeAdminCenterScreen.setAdminCenterScreen("default");
                            AdminDashboard.ReflectUIChange();

                        }

                    });

                    mainPanel.add(submitButton);

                    mainPanel.revalidate();
                    mainPanel.repaint();

                } else {

                    mainPanel.removeAll();

                    mainPanel.setBounds(200, 200, 800, 500);

                    JLabel text = new JLabel();
                    text.setText("ID not Found!");
                    text.setForeground(Color.red);
                    text.setFont(new Font("Roboto", Font.PLAIN, 50));
                    text.setBounds(50, 70, 500, 500);

                    mainPanel.add(text);

                    mainPanel.revalidate();
                    mainPanel.repaint();

                }

            }

        });

        mainPanel.add(idSearchButton);

        centerPanel.add(mainPanel);

    }

    public static void displayByIdHospital(JPanel centerPanel) {

        JLabel titleText = new JLabel();
        titleText.setText("Display Hospital By ID");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(300, 300, 600, 300);
        mainPanel.setLayout(null);

        JLabel textLabel = new JLabel();
        textLabel.setText("Enter ID of the Hospital :");
        textLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        textLabel.setBounds(180, 50, 250, 30);

        mainPanel.add(textLabel);

        JTextField idSearchField = new JTextField(15);
        idSearchField.setBounds(230, 100, 120, 40);
        idSearchField.setText("#ID");
        idSearchField.setForeground(Color.gray);
        idSearchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (idSearchField.getText().equals("#ID")) {
                    idSearchField.setText("");
                    idSearchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (idSearchField.getText().isEmpty()) {
                    idSearchField.setText("#ID");
                    idSearchField.setForeground(Color.GRAY);
                }
            }
        });

        mainPanel.add(idSearchField);

        JButton idSearchButton = new JButton();
        idSearchButton.setText("PROCEED");
        idSearchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        idSearchButton.setOpaque(true);
        idSearchButton.setBorderPainted(false);
        idSearchButton.setBackground(Color.black);
        idSearchButton.setForeground(Color.white);
        idSearchButton.setBounds(236, 170, 110, 30);
        idSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(idSearchField.getText());

                ArrayList<Hospital> hospitals = new ArrayList<>();

                File file = new File("src/hospitalproject/data/hospitals.dat");

                if (file.exists()) {

                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/hospitals.dat"))) {

                        while (true) {

                            try {
                                hospitals.add((Hospital) ois.readObject());
                            } catch (EOFException eoferr) {
                                break;
                            }
                        }
                    } catch (IOException | ClassNotFoundException error) {
                        error.printStackTrace();
                    }
                }

                int matchedIndex = -1;

                for (Hospital hospital : hospitals) {
                    if (id == hospital.getId()) {
                        matchedIndex = id - 1;
                    }
                }

                if (matchedIndex != -1) {

                    mainPanel.removeAll();

                    mainPanel.setBounds(200, 200, 800, 500);

                    Hospital specificHospital = hospitals.get(matchedIndex);

                    JLabel IdLabel = new JLabel();
                    IdLabel.setText("ID :");
                    IdLabel.setBounds(50, 50, 50, 40);
                    IdLabel.setFont(new Font("Roboto", Font.PLAIN, 20));

                    mainPanel.add(IdLabel);

                    JTextField idField = new JTextField(15);
                    idField.setBounds(100, 56, 80, 35);
                    idField.setText(Integer.toString(specificHospital.getId()));
                    idField.setEnabled(false);
                    idField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(idField);

                    JLabel nameLabel = new JLabel();
                    nameLabel.setText("Name :");
                    nameLabel.setBounds(50, 110, 100, 40);
                    nameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(nameLabel);

                    JTextField nameField = new JTextField(15);
                    nameField.setBounds(130, 116, 180, 35);
                    nameField.setText(specificHospital.getName());
                    nameField.setEditable(false);
                    nameField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(nameField);

                    JLabel regNumLabel = new JLabel();
                    regNumLabel.setText("Reg Number :");
                    regNumLabel.setBounds(50, 160, 130, 40);
                    regNumLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(regNumLabel);

                    JTextField regNumField = new JTextField(15);
                    regNumField.setBounds(180, 166, 150, 35);
                    regNumField.setText(specificHospital.getRegistrationNumber());
                    regNumField.setEnabled(false);
                    regNumField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(regNumField);

                    JLabel addressLabel = new JLabel();
                    addressLabel.setText("Address :");
                    addressLabel.setBounds(50, 215, 130, 40);
                    addressLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(addressLabel);

                    JTextField addressField = new JTextField(15);
                    addressField.setBounds(145, 220, 170, 35);
                    addressField.setText(specificHospital.getAddress());
                    addressField.setEditable(false);
                    addressField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(addressField);

                    JLabel cityLabel = new JLabel();
                    cityLabel.setText("City :");
                    cityLabel.setBounds(50, 265, 130, 40);
                    cityLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(cityLabel);

                    JTextField cityField = new JTextField(15);
                    cityField.setBounds(120, 270, 170, 35);
                    cityField.setText(specificHospital.getCity());
                    cityField.setEnabled(false);
                    cityField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(cityField);

                    JLabel postalLabel = new JLabel();
                    postalLabel.setText("Postal Code :");
                    postalLabel.setBounds(50, 320, 150, 40);
                    postalLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(postalLabel);

                    JTextField postalField = new JTextField(15);
                    postalField.setBounds(170, 325, 150, 35);
                    postalField.setText(specificHospital.getPostalCode());
                    postalField.setEnabled(false);
                    postalField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(postalField);

                    JLabel phoneLabel = new JLabel();
                    phoneLabel.setText("Phone :");
                    phoneLabel.setBounds(50, 370, 150, 40);
                    phoneLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(phoneLabel);

                    JTextField phoneField = new JTextField(15);
                    phoneField.setBounds(140, 375, 150, 35);
                    phoneField.setText(specificHospital.getPhoneNumber());
                    phoneField.setEnabled(false);
                    phoneField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(phoneField);

                    JLabel emailLabel = new JLabel();
                    emailLabel.setText("Email :");
                    emailLabel.setBounds(450, 50, 150, 40);
                    emailLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(emailLabel);

                    JTextField emailField = new JTextField(15);
                    emailField.setBounds(520, 53, 170, 35);
                    emailField.setText(specificHospital.getEmail());
                    emailField.setEditable(false);
                    emailField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(emailField);

                    JLabel websiteLabel = new JLabel();
                    websiteLabel.setText("Website :");
                    websiteLabel.setBounds(450, 100, 150, 40);
                    websiteLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(websiteLabel);

                    JTextField websiteField = new JTextField(15);
                    websiteField.setBounds(540, 103, 170, 35);
                    websiteField.setText(specificHospital.getWebsiteName());
                    websiteField.setEditable(false);
                    websiteField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(websiteField);

                    JLabel totalBedsLabel = new JLabel();
                    totalBedsLabel.setText("Total Beds :");
                    totalBedsLabel.setBounds(450, 150, 150, 40);
                    totalBedsLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(totalBedsLabel);

                    JTextField totalBedsField = new JTextField(15);
                    totalBedsField.setBounds(570, 153, 130, 35);
                    totalBedsField.setText(Integer.toString(specificHospital.getTotalBeds()));
                    totalBedsField.setEnabled(false);
                    totalBedsField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(totalBedsField);

                    JLabel totalDoctorsLabel = new JLabel();
                    totalDoctorsLabel.setText("Total Doctors :");
                    totalDoctorsLabel.setBounds(450, 200, 150, 40);
                    totalDoctorsLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(totalDoctorsLabel);

                    JTextField totalDoctorsField = new JTextField(15);
                    totalDoctorsField.setBounds(600, 203, 125, 35);
                    totalDoctorsField.setText(Integer.toString(specificHospital.getTotalDoctors()));
                    totalDoctorsField.setEnabled(false);
                    totalDoctorsField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(totalDoctorsField);

                    JLabel totalStaffsLabel = new JLabel();
                    totalStaffsLabel.setText("Total Staffs :");
                    totalStaffsLabel.setBounds(450, 250, 150, 40);
                    totalStaffsLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(totalStaffsLabel);

                    JTextField totalStaffsField = new JTextField(15);
                    totalStaffsField.setBounds(580, 253, 125, 35);
                    totalStaffsField.setText(Integer.toString(specificHospital.getTotalDoctors()));
                    totalStaffsField.setEnabled(false);
                    totalStaffsField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(totalStaffsField);

                    JLabel statusLabel = new JLabel();
                    statusLabel.setText("Status :");
                    statusLabel.setBounds(460, 300, 150, 40);
                    statusLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(statusLabel);

                    JLabel statusField = new JLabel();
                    statusField.setText(specificHospital.getStatus());
                    statusField.setForeground(Color.white);
                    statusField.setFont(new Font("Roboto", Font.BOLD, 20));
                    if (specificHospital.getStatus() != null && specificHospital.getStatus().equals("active")) {
                        statusField.setBackground(Color.GREEN);
                    } else {
                        statusField.setBackground(Color.red);
                    }

                    statusField.setBounds(570, 305, 70, 30);
                    statusField.setOpaque(true);

                    mainPanel.add(statusField);

                    JLabel estaLabel = new JLabel();
                    estaLabel.setText("Established Date :");
                    estaLabel.setBounds(460, 350, 200, 40);
                    estaLabel.setFont(new Font("Roboto", Font.PLAIN, 15));

                    mainPanel.add(estaLabel);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    JTextField dateField = new JTextField(10);
                    dateField.setBounds(610, 352, 130, 30);
                    dateField.setText(sdf.format(specificHospital.getEstablishedDate()));
                    dateField.setEnabled(false);
                    dateField.setFont(new Font("Roboto", Font.PLAIN, 15));

                    mainPanel.add(dateField);

                    JLabel departmentsLabel = new JLabel();
                    departmentsLabel.setText("Departments :");
                    departmentsLabel.setBounds(460, 400, 150, 40);
                    departmentsLabel.setFont(new Font("Roboto", Font.PLAIN, 17));

                    mainPanel.add(departmentsLabel);

                    JTextField deparmentsField = new JTextField(15);
                    deparmentsField.setBounds(580, 400, 180, 35);

                    System.out.println(specificHospital.getDepartments());

                    if (specificHospital.getDepartments() != null && !specificHospital.getDepartments().isEmpty()) {
                        String result = String.join(",", specificHospital.getDepartments());
                        deparmentsField.setText(result);
                    } else {
                        deparmentsField.setText("");
                    }

                    deparmentsField.setEditable(false);
                    deparmentsField.setFont(new Font("Roboto", Font.PLAIN, 14));

                    mainPanel.add(deparmentsField);

                    mainPanel.revalidate();
                    mainPanel.repaint();

                } else {

                    mainPanel.removeAll();

                    mainPanel.setBounds(200, 200, 800, 500);

                    JLabel text = new JLabel();
                    text.setText("ID not Found!");
                    text.setForeground(Color.red);
                    text.setFont(new Font("Roboto", Font.PLAIN, 50));
                    text.setBounds(50, 70, 500, 500);

                    mainPanel.add(text);

                    mainPanel.revalidate();
                    mainPanel.repaint();

                }
            }
        });

        mainPanel.add(idSearchButton);
        centerPanel.add(mainPanel);
    }

    public static void addPatientScreen(JPanel centerPanel) {

        JLabel titleText = new JLabel();
        titleText.setText("Add New Patient");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);

        JPanel addPatientPanel = new JPanel();
        addPatientPanel.setBounds(350, 100, 750, 600);
        addPatientPanel.setLayout(null);

        JLabel idLabel = new JLabel();
        idLabel.setText("ID :");
        idLabel.setBounds(50, 40, 70, 20);
        idLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addPatientPanel.add(idLabel);

        String idValue;

        ArrayList<Patient> patients = new ArrayList<>();

        File file = new File("src/hospitalproject/data/patients.dat");

        if (file.exists()) {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/patients.dat"))) {

                while (true) {

                    try {
                        patients.add((Patient) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (patients.isEmpty()) {
            idValue = "1";
        } else {
            idValue = Integer.toString(patients.get(patients.size() - 1).getId() + 1);
        }

        JTextField idField = new JTextField(15);
        idField.setText(idValue);
        idField.setEnabled(false);
        idField.setForeground(Color.gray);
        idField.setBounds(95, 38, 100, 30);

        addPatientPanel.add(idField);

        JLabel nameLabel = new JLabel();
        nameLabel.setText("Name :");
        nameLabel.setBounds(50, 90, 70, 20);
        nameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addPatientPanel.add(nameLabel);

        JTextField nameField = new JTextField(15);
        nameField.setBounds(120, 86, 180, 30);

        addPatientPanel.add(nameField);

        JLabel emailLabel = new JLabel();
        emailLabel.setText("Email :");
        emailLabel.setBounds(50, 140, 70, 20);
        emailLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addPatientPanel.add(emailLabel);

        JTextField emailField = new JTextField(15);
        emailField.setBounds(120, 136, 180, 30);

        addPatientPanel.add(emailField);

        JLabel contactNumLabel = new JLabel();
        contactNumLabel.setText("Contact Num :");
        contactNumLabel.setBounds(50, 190, 150, 20);
        contactNumLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addPatientPanel.add(contactNumLabel);

        JTextField contactNumField = new JTextField(15);
        contactNumField.setBounds(185, 186, 180, 30);

        addPatientPanel.add(contactNumField);

        JLabel genderLabel = new JLabel();
        genderLabel.setText("Gender :");
        genderLabel.setBounds(50, 240, 150, 20);
        genderLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addPatientPanel.add(genderLabel);

        JTextField genderField = new JTextField(15);
        genderField.setBounds(140, 236, 140, 30);

        addPatientPanel.add(genderField);

        JLabel districtLabel = new JLabel();
        districtLabel.setText("District :");
        districtLabel.setBounds(50, 290, 150, 20);
        districtLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addPatientPanel.add(districtLabel);

        JTextField districtField = new JTextField(15);
        districtField.setBounds(140, 286, 140, 30);

        addPatientPanel.add(districtField);

        JLabel dobLabel = new JLabel();
        dobLabel.setText("DOB :");
        dobLabel.setBounds(50, 340, 150, 20);
        dobLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addPatientPanel.add(dobLabel);

        JDateChooser dobField = new JDateChooser();
        dobField.setDateFormatString("dd/MM/yyyy");
        dobField.setBounds(120, 340, 150, 20);

        addPatientPanel.add(dobField);

        JLabel addressLabel = new JLabel();
        addressLabel.setText("Address :");
        addressLabel.setBounds(50, 390, 150, 20);
        addressLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addPatientPanel.add(addressLabel);

        JTextField addressField = new JTextField(15);
        addressField.setBounds(140, 386, 170, 30);

        addPatientPanel.add(addressField);

        JLabel emerContactLabel = new JLabel();
        emerContactLabel.setText("Emergency Contact :");
        emerContactLabel.setBounds(50, 440, 200, 20);
        emerContactLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addPatientPanel.add(emerContactLabel);

        JTextField emerContactField = new JTextField(15);
        emerContactField.setBounds(240, 436, 140, 30);

        addPatientPanel.add(emerContactField);

        JLabel bloodGroupLabel = new JLabel();
        bloodGroupLabel.setText("Blood Group :");
        bloodGroupLabel.setBounds(50, 490, 200, 20);
        bloodGroupLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addPatientPanel.add(bloodGroupLabel);

        JTextField bloodGroupField = new JTextField(15);
        bloodGroupField.setBounds(180, 486, 100, 30);

        addPatientPanel.add(bloodGroupField);

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username :");
        usernameLabel.setBounds(400, 88, 200, 20);
        usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addPatientPanel.add(usernameLabel);

        JTextField usernameField = new JTextField(15);
        usernameField.setBounds(510, 85, 200, 30);

        addPatientPanel.add(usernameField);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password :");
        passwordLabel.setBounds(400, 138, 200, 20);
        passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addPatientPanel.add(passwordLabel);

        JTextField passwordField = new JTextField(15);
        passwordField.setBounds(510, 135, 200, 30);

        addPatientPanel.add(passwordField);

        JButton submitButton = new JButton();
        submitButton.setText("CREATE");
        submitButton.setBackground(Color.blue);
        submitButton.setForeground(Color.white);
        submitButton.setFont(new Font("Roboto", Font.BOLD, 15));
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.setBounds(500, 500, 130, 30);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(e -> {

            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String email = emailField.getText();
            String contactNum = contactNumField.getText();
            String gender = genderField.getText();
            String district = districtField.getText();
            Date dob = dobField.getDate();
            String address = addressField.getText();
            String emerContact = emerContactField.getText();
            String bloodGroup = bloodGroupField.getText();
            String username = usernameField.getText();
            String password = "";
            try {
                password = PasswordEncryptDecrypt.encrypt(passwordField.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Patient patient = new Patient(id, name, email, contactNum, gender, district, dob, address, emerContact, bloodGroup, username, password);

            if (file.exists()) {

                ArrayList<Patient> patientsArr = new ArrayList<>();

                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/patients.dat"))) {
                    while (true) {

                        try {

                            patientsArr.add((Patient) ois.readObject());

                        } catch (EOFException eofErr) {
                            break;
                        }

                    }

                } catch (IOException | ClassNotFoundException err) {
                    err.printStackTrace();
                }

                patientsArr.add(patient);

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/patients.dat"))) {

                    for (Patient patientSingle : patientsArr) {

                        oos.writeObject(patientSingle);

                    }

                    System.out.println("Patient Data Written Appended !");

                } catch (IOException err) {
                    err.printStackTrace();
                }

                ChangeAdminCenterScreen.setAdminCenterScreen("default");
                AdminDashboard.ReflectUIChange();

            } else {

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/patients.dat"))) {

                    oos.writeObject(patient);
                    System.out.println("Patient Data Written !");

                } catch (IOException err) {
                    err.printStackTrace();
                }

                ChangeAdminCenterScreen.setAdminCenterScreen("default");
                AdminDashboard.ReflectUIChange();

            }

        });

        addPatientPanel.add(submitButton);

        centerPanel.add(addPatientPanel);

    }

    public static void deletePatientScreen(JPanel centerPanel) {

        JLabel titleText = new JLabel();
        titleText.setText("Delete Patient");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);

        JPanel searchIdPanel = new JPanel();
        searchIdPanel.setBounds(200, 250, 800, 350);
        searchIdPanel.setLayout(null);

        JLabel deleteTextLabel = new JLabel();
        deleteTextLabel.setText("Enter ID of the Patient :");
        deleteTextLabel.setBounds(270, 60, 300, 50);
        deleteTextLabel.setFont(new Font("Roboto", Font.PLAIN, 20));

        searchIdPanel.add(deleteTextLabel);

        JTextField deleteField = new JTextField(15);
        deleteField.setBounds(280, 130, 200, 50);
        deleteField.setFont(new Font("Roboto", Font.BOLD, 17));

        searchIdPanel.add(deleteField);

        JButton deleteButton = new JButton();
        deleteButton.setText("DELETE");
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.setBackground(Color.red);
        deleteButton.setFont(new Font("Roboto", Font.BOLD, 16));
        deleteButton.setBounds(310, 220, 140, 30);
        deleteButton.setForeground(Color.white);
        deleteButton.setOpaque(true);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteButton.setBackground(new Color(255, 100, 100));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteButton.setBackground(Color.red);
            }
        });
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(null, "Are You sure you want to Delete?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {

                    ArrayList<Patient> patients = new ArrayList<>();

                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/patients.dat"))) {
                        while (true) {

                            try {

                                patients.add((Patient) ois.readObject());

                            } catch (EOFException eofErr) {
                                break;
                            }

                        }

                    } catch (IOException | ClassNotFoundException err) {
                        err.printStackTrace();
                    }

                    int data = Integer.parseInt(deleteField.getText());
                    int matchedIndex = -1;

                    for (int i = 0; i < patients.size(); i++) {

                        if (patients.get(i).getId() == data) {
                            matchedIndex = i;
                            break;
                        }
                    }

                    if (matchedIndex != -1) {

                        patients.remove(matchedIndex);

                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/patients.dat"))) {

                            for (Patient patient : patients) {
                                oos.writeObject(patient);
                            }

                            System.out.println("Deleted A Patient and Updated file!");

                        } catch (IOException error) {
                            error.printStackTrace();
                        }

                        ChangeAdminCenterScreen.setAdminCenterScreen("default");
                        AdminDashboard.ReflectUIChange();

                    } else {

                        JOptionPane.showMessageDialog(null, "You have Entered a wrong ID", "error", JOptionPane.ERROR_MESSAGE);

                    }

                } else {

                }
            }

        });

        searchIdPanel.add(deleteButton);

        centerPanel.add(searchIdPanel);

    }

    public static void displayAllPatientScreen(JPanel centerPanel) {

        JPanel displayPanel = new JPanel();

        JLabel titleText = new JLabel();
        titleText.setText("All Patients!");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);

        JTextField citySearchField = new JTextField(15);
        citySearchField.setPreferredSize(new Dimension(100, 30));
        citySearchField.setBounds(410, 80, 160, 40);
        citySearchField.setText("#city search");
        citySearchField.setForeground(Color.GRAY);

        citySearchField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (citySearchField.getText().equals("#city search")) {
                    citySearchField.setText("");
                    citySearchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (citySearchField.getText().isEmpty()) {
                    citySearchField.setText("#city search");
                    citySearchField.setForeground(Color.GRAY);
                }
            }
        });

        centerPanel.add(citySearchField);

        JButton searchCityButton = new JButton();
        searchCityButton.setText("SEARCH");
        searchCityButton.setOpaque(true);
        searchCityButton.setBorderPainted(false);
        searchCityButton.setBackground(Color.black);
        searchCityButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchCityButton.setForeground(Color.white);
        searchCityButton.setBounds(610, 85, 100, 30);
        searchCityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                searchedCityDisplayPatient(displayPanel, citySearchField);
                citySearchField.setText("#city search");

            }

        });

        centerPanel.add(searchCityButton);

        JTextField bloodGroupField = new JTextField(15);
        bloodGroupField.setPreferredSize(new Dimension(100, 30));
        bloodGroupField.setBounds(780, 80, 160, 40);
        bloodGroupField.setText("#Blood search");
        bloodGroupField.setForeground(Color.GRAY);

        bloodGroupField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (bloodGroupField.getText().equals("#Blood search")) {
                    bloodGroupField.setText("");
                    bloodGroupField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (bloodGroupField.getText().isEmpty()) {
                    bloodGroupField.setText("#Blood search");
                    bloodGroupField.setForeground(Color.GRAY);
                }
            }
        });

        centerPanel.add(bloodGroupField);

        JButton searchBloodButton = new JButton();
        searchBloodButton.setText("SEARCH");
        searchBloodButton.setOpaque(true);
        searchBloodButton.setBorderPainted(false);
        searchBloodButton.setBackground(Color.black);
        searchBloodButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchBloodButton.setForeground(Color.white);
        searchBloodButton.setBounds(950, 85, 100, 30);
        searchBloodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                searchedBloodDisplayPatient(displayPanel, bloodGroupField);
                bloodGroupField.setText("#Blood search");

            }

        });

        centerPanel.add(searchBloodButton);

        displayPanel.setBounds(140, 200, 980, 400);
        displayPanel.setLayout(new BorderLayout());

        allDisplayPatients(displayPanel);

        centerPanel.add(displayPanel);

    }

    private static void allDisplayPatients(JPanel panel) {

        ArrayList<Patient> patients = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/patients.dat"))) {

            while (true) {
                try {

                    patients.add((Patient) ois.readObject());

                } catch (EOFException eoferr) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        String[] columnNames = {"ID", "Name", "Email", "Contact", "Gender", "District", "Blood Group", "DOB"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Patient patient : patients) {

            Object[] row = {patient.getId(), patient.getName(), patient.getEmail(), patient.getContactNumber(), patient.getGender(), patient.getDistrict(), patient.getBloodGroup(), sdf.format(patient.getDob())};

            model.addRow(row);

        }

        JTable table = new JTable(model);

        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(2).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(20);
        table.getColumnModel().getColumn(5).setPreferredWidth(20);
        table.getColumnModel().getColumn(6).setPreferredWidth(20);

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

    }

    private static void searchedCityDisplayPatient(JPanel panel, JTextField textField) {

        panel.removeAll();

        ArrayList<Patient> patients = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/patients.dat"))) {

            while (true) {
                try {

                    patients.add((Patient) ois.readObject());

                } catch (EOFException eoferr) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Patient> newPatients = new ArrayList<>();

        for (Patient patient : patients) {

            if (textField.getText().toLowerCase().equals(patient.getDistrict().toLowerCase())) {
                newPatients.add(patient);
            }

        }

        if (newPatients.isEmpty()) {

            JLabel text = new JLabel();
            text.setText(" No Search Results Found ! ");
            text.setFont(new Font("Roboto", Font.PLAIN, 50));
            text.setForeground(Color.ORANGE);

            panel.add(text, BorderLayout.CENTER);

        } else {

            String[] columnNames = {"ID", "Name", "Email", "Contact", "Gender", "District", "Blood Group", "DOB"};

            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Patient patient : newPatients) {

                Object[] row = {patient.getId(), patient.getName(), patient.getEmail(), patient.getContactNumber(), patient.getGender(), patient.getDistrict(), patient.getBloodGroup(), sdf.format(patient.getDob())};

                model.addRow(row);

            }

            JTable table = new JTable(model);

            table.getColumnModel().getColumn(0).setPreferredWidth(5);
            table.getColumnModel().getColumn(2).setPreferredWidth(20);
            table.getColumnModel().getColumn(4).setPreferredWidth(20);
            table.getColumnModel().getColumn(5).setPreferredWidth(20);
            table.getColumnModel().getColumn(6).setPreferredWidth(20);

            JScrollPane scrollPane = new JScrollPane(table);

            panel.add(scrollPane, BorderLayout.CENTER);

        }

        panel.revalidate();
        panel.repaint();

    }

    private static void searchedBloodDisplayPatient(JPanel panel, JTextField textField) {
        panel.removeAll();

        ArrayList<Patient> patients = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/patients.dat"))) {

            while (true) {
                try {

                    patients.add((Patient) ois.readObject());

                } catch (EOFException eoferr) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Patient> newPatients = new ArrayList<>();

        for (Patient patient : patients) {

            if (textField.getText().toLowerCase().equals(patient.getBloodGroup().toLowerCase())) {
                newPatients.add(patient);
            }

        }

        if (newPatients.isEmpty()) {

            JLabel text = new JLabel();
            text.setText(" No Search Results Found ! ");
            text.setFont(new Font("Roboto", Font.PLAIN, 50));
            text.setForeground(Color.ORANGE);

            panel.add(text, BorderLayout.CENTER);

        } else {

            String[] columnNames = {"ID", "Name", "Email", "Contact", "Gender", "District", "Blood Group", "DOB"};

            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Patient patient : newPatients) {

                Object[] row = {patient.getId(), patient.getName(), patient.getEmail(), patient.getContactNumber(), patient.getGender(), patient.getDistrict(), patient.getBloodGroup(), sdf.format(patient.getDob())};

                model.addRow(row);

            }

            JTable table = new JTable(model);

            table.getColumnModel().getColumn(0).setPreferredWidth(5);
            table.getColumnModel().getColumn(2).setPreferredWidth(20);
            table.getColumnModel().getColumn(4).setPreferredWidth(20);
            table.getColumnModel().getColumn(5).setPreferredWidth(20);
            table.getColumnModel().getColumn(6).setPreferredWidth(20);

            JScrollPane scrollPane = new JScrollPane(table);

            panel.add(scrollPane, BorderLayout.CENTER);

        }

        panel.revalidate();
        panel.repaint();

    }

    public static void updatePatientScreen(JPanel centerPanel) {

        JLabel titleText = new JLabel();
        titleText.setText("Update Patient Info");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(300, 300, 600, 300);
        mainPanel.setLayout(null);

        JLabel textLabel = new JLabel();
        textLabel.setText("Enter ID of the Patient :");
        textLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        textLabel.setBounds(180, 50, 250, 30);

        mainPanel.add(textLabel);

        JTextField idSearchField = new JTextField(15);
        idSearchField.setBounds(230, 100, 120, 40);
        idSearchField.setText("#ID");
        idSearchField.setForeground(Color.gray);
        idSearchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (idSearchField.getText().equals("#ID")) {
                    idSearchField.setText("");
                    idSearchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (idSearchField.getText().isEmpty()) {
                    idSearchField.setText("#ID");
                    idSearchField.setForeground(Color.GRAY);
                }
            }
        });

        mainPanel.add(idSearchField);

        JButton idSearchButton = new JButton();
        idSearchButton.setText("PROCEED");
        idSearchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        idSearchButton.setOpaque(true);
        idSearchButton.setBorderPainted(false);
        idSearchButton.setBackground(Color.black);
        idSearchButton.setForeground(Color.white);
        idSearchButton.setBounds(236, 170, 110, 30);
        idSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(idSearchField.getText());

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

                    mainPanel.removeAll();

                    mainPanel.setBounds(200, 200, 800, 500);

                    Patient specificPatient = patients.get(matchedIndex);

                    JLabel IdLabel = new JLabel();
                    IdLabel.setText("ID :");
                    IdLabel.setBounds(50, 50, 50, 40);
                    IdLabel.setFont(new Font("Roboto", Font.PLAIN, 20));

                    mainPanel.add(IdLabel);

                    JTextField idField = new JTextField(15);
                    idField.setBounds(100, 56, 80, 35);
                    idField.setText(Integer.toString(specificPatient.getId()));
                    idField.setEnabled(false);
                    idField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(idField);

                    JLabel nameLabel = new JLabel();
                    nameLabel.setText("Name :");
                    nameLabel.setBounds(50, 110, 100, 40);
                    nameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(nameLabel);

                    JTextField nameField = new JTextField(15);
                    nameField.setBounds(130, 116, 180, 35);
                    nameField.setText(specificPatient.getName());
                    nameField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(nameField);

                    JLabel contactLabel = new JLabel();
                    contactLabel.setText("Contact :");
                    contactLabel.setBounds(50, 160, 130, 40);
                    contactLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(contactLabel);

                    JTextField contactField = new JTextField(15);
                    contactField.setBounds(160, 166, 150, 35);
                    contactField.setText(specificPatient.getContactNumber());
                    contactField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(contactField);

                    JLabel emergencyLabel = new JLabel();
                    emergencyLabel.setText("Emergency Contact :");
                    emergencyLabel.setBounds(50, 215, 200, 40);
                    emergencyLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(emergencyLabel);

                    JTextField emergencyField = new JTextField(15);
                    emergencyField.setBounds(250, 220, 150, 35);
                    emergencyField.setText(specificPatient.getEmergencyContact());
                    emergencyField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(emergencyField);

                    JLabel bloodGroupLabel = new JLabel();
                    bloodGroupLabel.setText("Blood Group :");
                    bloodGroupLabel.setBounds(50, 265, 130, 40);
                    bloodGroupLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(bloodGroupLabel);

                    JTextField bloodGroupField = new JTextField(15);
                    bloodGroupField.setBounds(180, 270, 150, 35);
                    bloodGroupField.setText(specificPatient.getBloodGroup());
                    bloodGroupField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(bloodGroupField);

                    JLabel statusLabel = new JLabel();
                    statusLabel.setText("Status :");
                    statusLabel.setBounds(50, 320, 130, 40);
                    statusLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(statusLabel);

                    JTextField statusField = new JTextField(15);
                    statusField.setBounds(150, 325, 150, 35);
                    statusField.setEnabled(false);
                    statusField.setText(specificPatient.getStatus());
                    statusField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(statusField);

                    JLabel emailLabel = new JLabel();
                    emailLabel.setText("Email :");
                    emailLabel.setBounds(50, 370, 130, 40);
                    emailLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(emailLabel);

                    JTextField emailField = new JTextField(15);
                    emailField.setBounds(150, 375, 200, 35);
                    emailField.setText(specificPatient.getEmail());
                    emailField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(emailField);

                    JLabel usernameLabel = new JLabel();
                    usernameLabel.setText("Username :");
                    usernameLabel.setBounds(450, 50, 150, 40);
                    usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(usernameLabel);

                    JTextField usernameField = new JTextField(15);
                    usernameField.setBounds(570, 53, 170, 35);
                    usernameField.setText(specificPatient.getUsername());
                    usernameField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(usernameField);

                    JLabel passwordLabel = new JLabel();
                    passwordLabel.setText("Password :");
                    passwordLabel.setBounds(450, 100, 150, 40);
                    passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(passwordLabel);

                    JTextField passwordField = new JTextField(15);
                    passwordField.setBounds(570, 103, 170, 35);
                    try {
                        passwordField.setText(PasswordEncryptDecrypt.decrypt(specificPatient.getPassword()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    passwordField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(passwordField);

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

                            ChangeAdminCenterScreen.setAdminCenterScreen("default");
                            AdminDashboard.ReflectUIChange();

                        }

                    });

                    mainPanel.add(submitButton);

                    mainPanel.revalidate();
                    mainPanel.repaint();

                } else {

                    mainPanel.removeAll();

                    mainPanel.setBounds(200, 200, 800, 500);

                    JLabel text = new JLabel();
                    text.setText("ID not Found!");
                    text.setForeground(Color.red);
                    text.setFont(new Font("Roboto", Font.PLAIN, 50));
                    text.setBounds(50, 70, 500, 500);

                    mainPanel.add(text);

                    mainPanel.revalidate();
                    mainPanel.repaint();

                }

            }

        });

        mainPanel.add(idSearchButton);

        centerPanel.add(mainPanel);

    }

    public static void addDoctorScreen(JPanel centerPanel) {

        JLabel titleText = new JLabel();
        titleText.setText("Add New Doctor");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);

        JPanel addDoctorPanel = new JPanel();
        addDoctorPanel.setBounds(350, 100, 750, 600);
        addDoctorPanel.setLayout(null);

        JLabel idLabel = new JLabel();
        idLabel.setText("ID :");
        idLabel.setBounds(50, 40, 70, 20);
        idLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addDoctorPanel.add(idLabel);

        String idValue;

        ArrayList<Doctor> doctors = new ArrayList<>();

        File file = new File("src/hospitalproject/data/doctors.dat");

        if (file.exists()) {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/doctors.dat"))) {

                while (true) {

                    try {
                        doctors.add((Doctor) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (doctors.isEmpty()) {
            idValue = "1";
        } else {
            idValue = Integer.toString(doctors.get(doctors.size() - 1).getId() + 1);
        }

        JTextField idField = new JTextField(15);
        idField.setText(idValue);
        idField.setEnabled(false);
        idField.setForeground(Color.gray);
        idField.setBounds(95, 38, 100, 30);

        addDoctorPanel.add(idField);

        JLabel nameLabel = new JLabel();
        nameLabel.setText("Name :");
        nameLabel.setBounds(50, 90, 70, 20);
        nameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addDoctorPanel.add(nameLabel);

        JTextField nameField = new JTextField(15);
        nameField.setBounds(120, 86, 180, 30);

        addDoctorPanel.add(nameField);

        JLabel emailLabel = new JLabel();
        emailLabel.setText("Email :");
        emailLabel.setBounds(50, 140, 70, 20);
        emailLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addDoctorPanel.add(emailLabel);

        JTextField emailField = new JTextField(15);
        emailField.setBounds(120, 136, 180, 30);

        addDoctorPanel.add(emailField);

        JLabel contactNumLabel = new JLabel();
        contactNumLabel.setText("Contact Num :");
        contactNumLabel.setBounds(50, 190, 150, 20);
        contactNumLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addDoctorPanel.add(contactNumLabel);

        JTextField contactNumField = new JTextField(15);
        contactNumField.setBounds(185, 186, 180, 30);

        addDoctorPanel.add(contactNumField);

        JLabel genderLabel = new JLabel();
        genderLabel.setText("Gender :");
        genderLabel.setBounds(50, 240, 150, 20);
        genderLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addDoctorPanel.add(genderLabel);

        JTextField genderField = new JTextField(15);
        genderField.setBounds(140, 236, 140, 30);

        addDoctorPanel.add(genderField);

        JLabel districtLabel = new JLabel();
        districtLabel.setText("District :");
        districtLabel.setBounds(50, 290, 150, 20);
        districtLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addDoctorPanel.add(districtLabel);

        JTextField districtField = new JTextField(15);
        districtField.setBounds(140, 286, 140, 30);

        addDoctorPanel.add(districtField);

        JLabel hospitalLabel = new JLabel("Hospital:");
        hospitalLabel.setBounds(50, 340, 100, 30);
        hospitalLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addDoctorPanel.add(hospitalLabel);

        ArrayList<Hospital> hospitals = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/hospitals.dat"))) {
            while (true) {

                try {

                    hospitals.add((Hospital) ois.readObject());

                } catch (EOFException eofErr) {
                    break;
                }

            }

        } catch (IOException | ClassNotFoundException err) {
            err.printStackTrace();
        }

        String[] options = new String[hospitals.size()];

        int i = 0;
        for (Hospital hospital : hospitals) {

            options[i] = hospital.getName();

            i++;

        }
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(150, 340, 100, 35);

        final String[] selected = new String[1];

        selected[0] = hospitals.get(0).getName();

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                selected[0] = (String) comboBox.getSelectedItem();

            }

        });

        addDoctorPanel.add(comboBox);

        JLabel availabilityLabel = new JLabel();
        availabilityLabel.setText("Availability (e.g. Monday: 9 AM - 12 PM):");
        availabilityLabel.setBounds(50, 390, 350, 20);
        availabilityLabel.setFont(new Font("Roboto", Font.PLAIN, 14));

        addDoctorPanel.add(availabilityLabel);

        JTextField availabilityField = new JTextField(15);
        availabilityField.setBounds(340, 388, 155, 30);

        addDoctorPanel.add(availabilityField);

        JLabel departmentsLabel = new JLabel();
        departmentsLabel.setText("Departments :");
        departmentsLabel.setBounds(50, 440, 350, 20);
        departmentsLabel.setFont(new Font("Roboto", Font.PLAIN, 14));

        addDoctorPanel.add(departmentsLabel);

        JTextField departmentsField = new JTextField(15);
        departmentsField.setBounds(170, 438, 180, 30);
        departmentsField.setText("Radiology,Cardiology");
        departmentsField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (departmentsField.getText().equals("Radiology,Cardiology")) {
                    departmentsField.setText("");
                    departmentsField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (departmentsField.getText().isEmpty()) {
                    departmentsField.setText("");
                    departmentsField.setForeground(Color.GRAY);
                }
            }
        });

        addDoctorPanel.add(departmentsField);

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username :");
        usernameLabel.setBounds(400, 88, 200, 20);
        usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addDoctorPanel.add(usernameLabel);

        JTextField usernameField = new JTextField(15);
        usernameField.setBounds(510, 85, 200, 30);

        addDoctorPanel.add(usernameField);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password :");
        passwordLabel.setBounds(400, 138, 200, 20);
        passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addDoctorPanel.add(passwordLabel);

        JTextField passwordField = new JTextField(15);
        passwordField.setBounds(510, 135, 200, 30);

        addDoctorPanel.add(passwordField);

        JButton submitButton = new JButton();
        submitButton.setText("CREATE");
        submitButton.setBackground(Color.blue);
        submitButton.setForeground(Color.white);
        submitButton.setFont(new Font("Roboto", Font.BOLD, 15));
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.setBounds(500, 500, 130, 30);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(e -> {

            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String email = emailField.getText();
            String contactNum = contactNumField.getText();
            String gender = genderField.getText();
            String district = districtField.getText();
            String hospitalName = selected[0];

            ArrayList<String> availability = new ArrayList<>();

            availability.add(availabilityField.getText());

            String departmentsText = departmentsField.getText();
            String[] arrDepartment = departmentsText.split(",");

            ArrayList<String> departments = new ArrayList<>();

            for (String s : arrDepartment) {

                departments.add(s);
            }

            String username = usernameField.getText();
            String password = "";
            try {
                password = PasswordEncryptDecrypt.encrypt(passwordField.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Doctor doctor = new Doctor(id, name, email, contactNum, gender, district, hospitalName, username, password, availability, departments);

            if (file.exists()) {

                ArrayList<Doctor> doctorsArr = new ArrayList<>();

                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/doctors.dat"))) {
                    while (true) {

                        try {

                            doctorsArr.add((Doctor) ois.readObject());

                        } catch (EOFException eofErr) {
                            break;
                        }

                    }

                } catch (IOException | ClassNotFoundException err) {
                    err.printStackTrace();
                }

                doctorsArr.add(doctor);

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/doctors.dat"))) {

                    for (Doctor doctorSingle : doctorsArr) {

                        oos.writeObject(doctorSingle);

                    }

                    System.out.println("Doctor Data Written Appended !");

                } catch (IOException err) {
                    err.printStackTrace();
                }

                ChangeAdminCenterScreen.setAdminCenterScreen("default");
                AdminDashboard.ReflectUIChange();

            } else {

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/doctors.dat"))) {

                    oos.writeObject(doctor);
                    System.out.println("Doctor Data Written !");

                } catch (IOException err) {
                    err.printStackTrace();
                }

                ChangeAdminCenterScreen.setAdminCenterScreen("default");
                AdminDashboard.ReflectUIChange();

            }

        });

        addDoctorPanel.add(submitButton);

        centerPanel.add(addDoctorPanel);

    }

    public static void deleteDoctorScreen(JPanel centerPanel) {

        JLabel titleText = new JLabel();
        titleText.setText("Delete Doctor");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);

        JPanel searchIdPanel = new JPanel();
        searchIdPanel.setBounds(200, 250, 800, 350);
        searchIdPanel.setLayout(null);

        JLabel deleteTextLabel = new JLabel();
        deleteTextLabel.setText("Enter ID of the Doctor :");
        deleteTextLabel.setBounds(270, 60, 300, 50);
        deleteTextLabel.setFont(new Font("Roboto", Font.PLAIN, 20));

        searchIdPanel.add(deleteTextLabel);

        JTextField deleteField = new JTextField(15);
        deleteField.setBounds(280, 130, 200, 50);
        deleteField.setFont(new Font("Roboto", Font.BOLD, 17));

        searchIdPanel.add(deleteField);

        JButton deleteButton = new JButton();
        deleteButton.setText("DELETE");
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.setBackground(Color.red);
        deleteButton.setFont(new Font("Roboto", Font.BOLD, 16));
        deleteButton.setBounds(310, 220, 140, 30);
        deleteButton.setForeground(Color.white);
        deleteButton.setOpaque(true);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteButton.setBackground(new Color(255, 100, 100));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteButton.setBackground(Color.red);
            }
        });
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(null, "Are You sure you want to Delete?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {

                    ArrayList<Doctor> doctors = new ArrayList<>();

                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/doctors.dat"))) {
                        while (true) {

                            try {

                                doctors.add((Doctor) ois.readObject());

                            } catch (EOFException eofErr) {
                                break;
                            }

                        }

                    } catch (IOException | ClassNotFoundException err) {
                        err.printStackTrace();
                    }

                    int data = Integer.parseInt(deleteField.getText());
                    int matchedIndex = -1;

                    for (int i = 0; i < doctors.size(); i++) {

                        if (doctors.get(i).getId() == data) {
                            matchedIndex = i;
                            break;
                        }
                    }

                    if (matchedIndex != -1) {

                        doctors.remove(matchedIndex);

                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/doctors.dat"))) {

                            for (Doctor doctor : doctors) {
                                oos.writeObject(doctor);
                            }

                            System.out.println("Deleted A Doctor and Updated file!");

                        } catch (IOException error) {
                            error.printStackTrace();
                        }

                        ChangeAdminCenterScreen.setAdminCenterScreen("default");
                        AdminDashboard.ReflectUIChange();

                    } else {

                        JOptionPane.showMessageDialog(null, "You have Entered a wrong ID", "error", JOptionPane.ERROR_MESSAGE);

                    }

                } else {

                }
            }

        });

        searchIdPanel.add(deleteButton);

        centerPanel.add(searchIdPanel);

    }

    public static void displayAllDoctorScreen(JPanel centerPanel) {

        JPanel displayPanel = new JPanel();

        JLabel titleText = new JLabel();
        titleText.setText("All Doctors!");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);

        JTextField hospitalSearchField = new JTextField(15);
        hospitalSearchField.setPreferredSize(new Dimension(100, 30));
        hospitalSearchField.setBounds(410, 80, 160, 40);
        hospitalSearchField.setText("#hospital search");
        hospitalSearchField.setForeground(Color.GRAY);

        hospitalSearchField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (hospitalSearchField.getText().equals("#hospital search")) {
                    hospitalSearchField.setText("");
                    hospitalSearchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (hospitalSearchField.getText().isEmpty()) {
                    hospitalSearchField.setText("#hospital search");
                    hospitalSearchField.setForeground(Color.GRAY);
                }
            }
        });

        centerPanel.add(hospitalSearchField);

        JButton searchHospitalButton = new JButton();
        searchHospitalButton.setText("SEARCH");
        searchHospitalButton.setOpaque(true);
        searchHospitalButton.setBorderPainted(false);
        searchHospitalButton.setBackground(Color.black);
        searchHospitalButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchHospitalButton.setForeground(Color.white);
        searchHospitalButton.setBounds(610, 85, 100, 30);
        searchHospitalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                searchedHopitalDisplayDoctor(displayPanel, hospitalSearchField);
                hospitalSearchField.setText("#hospital search");

            }

        });

        centerPanel.add(searchHospitalButton);

        displayPanel.setBounds(140, 200, 980, 400);
        displayPanel.setLayout(new BorderLayout());

        allDisplayDoctors(displayPanel);

        centerPanel.add(displayPanel);

    }

    private static void allDisplayDoctors(JPanel panel) {

        ArrayList<Doctor> doctors = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/doctors.dat"))) {

            while (true) {
                try {

                    doctors.add((Doctor) ois.readObject());

                } catch (EOFException eoferr) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        String[] columnNames = {"ID", "Name", "Email", "Contact", "Gender", "District", "Hospital Name", "Availability"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Doctor doctor : doctors) {

            Object[] row = {doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getContactNumber(), doctor.getGender(), doctor.getDistrict(), doctor.getHospitalName(), doctor.getAvailability()};

            model.addRow(row);

        }

        JTable table = new JTable(model);

        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(10);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        table.getColumnModel().getColumn(5).setPreferredWidth(10);
        table.getColumnModel().getColumn(6).setPreferredWidth(20);
        table.getColumnModel().getColumn(7).setPreferredWidth(80);

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);
    }

    private static void searchedHopitalDisplayDoctor(JPanel panel, JTextField textField) {

        panel.removeAll();

        ArrayList<Doctor> doctors = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/doctors.dat"))) {

            while (true) {
                try {

                    doctors.add((Doctor) ois.readObject());

                } catch (EOFException eoferr) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Doctor> newDoctors = new ArrayList<>();

        for (Doctor doctor : doctors) {

            if (textField.getText().toLowerCase().equals(doctor.getHospitalName().toLowerCase())) {
                newDoctors.add(doctor);
            }

        }

        if (newDoctors.isEmpty()) {

            JLabel text = new JLabel();
            text.setText(" No Search Results Found ! ");
            text.setFont(new Font("Roboto", Font.PLAIN, 50));
            text.setForeground(Color.ORANGE);

            panel.add(text, BorderLayout.CENTER);

        } else {

            String[] columnNames = {"ID", "Name", "Email", "Contact", "Gender", "District", "Hospital Name", "Availability"};

            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Doctor doctor : newDoctors) {

                Object[] row = {doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getContactNumber(), doctor.getGender(), doctor.getDistrict(), doctor.getHospitalName(), doctor.getAvailability()};

                model.addRow(row);

            }

            JTable table = new JTable(model);

            table.getColumnModel().getColumn(0).setPreferredWidth(2);
            table.getColumnModel().getColumn(2).setPreferredWidth(20);
            table.getColumnModel().getColumn(3).setPreferredWidth(10);
            table.getColumnModel().getColumn(4).setPreferredWidth(10);
            table.getColumnModel().getColumn(5).setPreferredWidth(10);
            table.getColumnModel().getColumn(6).setPreferredWidth(20);
            table.getColumnModel().getColumn(7).setPreferredWidth(80);

            JScrollPane scrollPane = new JScrollPane(table);

            panel.add(scrollPane, BorderLayout.CENTER);

        }

        panel.revalidate();
        panel.repaint();

    }

    public static void updateDoctorScreen(JPanel centerPanel) {

        JLabel titleText = new JLabel();
        titleText.setText("Update Doctor Info");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(300, 300, 600, 300);
        mainPanel.setLayout(null);

        JLabel textLabel = new JLabel();
        textLabel.setText("Enter ID of the Doctor :");
        textLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        textLabel.setBounds(180, 50, 250, 30);

        mainPanel.add(textLabel);

        JTextField idSearchField = new JTextField(15);
        idSearchField.setBounds(230, 100, 120, 40);
        idSearchField.setText("#ID");
        idSearchField.setForeground(Color.gray);
        idSearchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (idSearchField.getText().equals("#ID")) {
                    idSearchField.setText("");
                    idSearchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (idSearchField.getText().isEmpty()) {
                    idSearchField.setText("#ID");
                    idSearchField.setForeground(Color.GRAY);
                }
            }
        });

        mainPanel.add(idSearchField);

        JButton idSearchButton = new JButton();
        idSearchButton.setText("PROCEED");
        idSearchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        idSearchButton.setOpaque(true);
        idSearchButton.setBorderPainted(false);
        idSearchButton.setBackground(Color.black);
        idSearchButton.setForeground(Color.white);
        idSearchButton.setBounds(236, 170, 110, 30);
        idSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(idSearchField.getText());

                ArrayList<Doctor> doctors = new ArrayList<>();

                File file = new File("src/hospitalproject/data/doctors.dat");

                if (file.exists()) {

                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/doctors.dat"))) {

                        while (true) {

                            try {
                                doctors.add((Doctor) ois.readObject());
                            } catch (EOFException eoferr) {
                                break;
                            }
                        }
                    } catch (IOException | ClassNotFoundException error) {
                        error.printStackTrace();
                    }
                }

                int matchedIndex = -1;

                for (Doctor doctor : doctors) {
                    if (id == doctor.getId()) {
                        matchedIndex = id - 1;
                    }
                }

                if (matchedIndex != -1) {

                    mainPanel.removeAll();

                    mainPanel.setBounds(200, 200, 800, 500);

                    Doctor specificDoctor = doctors.get(matchedIndex);

                    JLabel IdLabel = new JLabel();
                    IdLabel.setText("ID :");
                    IdLabel.setBounds(50, 50, 50, 40);
                    IdLabel.setFont(new Font("Roboto", Font.PLAIN, 20));

                    mainPanel.add(IdLabel);

                    JTextField idField = new JTextField(15);
                    idField.setBounds(100, 56, 80, 35);
                    idField.setText(Integer.toString(specificDoctor.getId()));
                    idField.setEnabled(false);
                    idField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(idField);

                    JLabel nameLabel = new JLabel();
                    nameLabel.setText("Name :");
                    nameLabel.setBounds(50, 110, 100, 40);
                    nameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(nameLabel);

                    JTextField nameField = new JTextField(15);
                    nameField.setBounds(130, 116, 180, 35);
                    nameField.setText(specificDoctor.getName());
                    nameField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(nameField);

                    JLabel contactLabel = new JLabel();
                    contactLabel.setText("Contact :");
                    contactLabel.setBounds(50, 160, 130, 40);
                    contactLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(contactLabel);

                    JTextField contactField = new JTextField(15);
                    contactField.setBounds(160, 166, 150, 35);
                    contactField.setText(specificDoctor.getContactNumber());
                    contactField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(contactField);

                    JLabel emailLabel = new JLabel();
                    emailLabel.setText("Email :");
                    emailLabel.setBounds(50, 370, 130, 40);
                    emailLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(emailLabel);

                    JTextField emailField = new JTextField(15);
                    emailField.setBounds(150, 375, 200, 35);
                    emailField.setText(specificDoctor.getEmail());
                    emailField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(emailField);

                    JLabel usernameLabel = new JLabel();
                    usernameLabel.setText("Username :");
                    usernameLabel.setBounds(450, 50, 150, 40);
                    usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(usernameLabel);

                    JTextField usernameField = new JTextField(15);
                    usernameField.setBounds(570, 53, 170, 35);
                    usernameField.setText(specificDoctor.getUsername());
                    usernameField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(usernameField);

                    JLabel passwordLabel = new JLabel();
                    passwordLabel.setText("Password :");
                    passwordLabel.setBounds(450, 100, 150, 40);
                    passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

                    mainPanel.add(passwordLabel);

                    JTextField passwordField = new JTextField(15);
                    passwordField.setBounds(570, 103, 170, 35);
                    try {
                        passwordField.setText(PasswordEncryptDecrypt.decrypt(specificDoctor.getPassword()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    passwordField.setFont(new Font("Roboto", Font.PLAIN, 16));

                    mainPanel.add(passwordField);

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

                            specificDoctor.setName(nameField.getText());
                            specificDoctor.setContactNumber(contactField.getText());
                            specificDoctor.setEmail(emailField.getText());
                            specificDoctor.setUsername(usernameField.getText());
                            try {
                                specificDoctor.setPassword(PasswordEncryptDecrypt.encrypt(passwordField.getText()));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                            doctors.set(specificDoctor.getId() - 1, specificDoctor);

                            try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/doctors.dat"))) {

                                for (Doctor doctor : doctors) {
                                    ois.writeObject(doctor);
                                }

                            } catch (IOException error) {
                                error.printStackTrace();
                            }

                            ChangeAdminCenterScreen.setAdminCenterScreen("default");
                            AdminDashboard.ReflectUIChange();

                        }

                    });

                    mainPanel.add(submitButton);

                    mainPanel.revalidate();
                    mainPanel.repaint();

                } else {

                    mainPanel.removeAll();

                    mainPanel.setBounds(200, 200, 800, 500);

                    JLabel text = new JLabel();
                    text.setText("ID not Found!");
                    text.setForeground(Color.red);
                    text.setFont(new Font("Roboto", Font.PLAIN, 50));
                    text.setBounds(50, 70, 500, 500);

                    mainPanel.add(text);

                    mainPanel.revalidate();
                    mainPanel.repaint();

                }

            }

        });

        mainPanel.add(idSearchButton);

        centerPanel.add(mainPanel);
    }

    public static void approveAppointment(JPanel centerPanel) {

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

        JPanel approPanel = new JPanel();

        approPanel.setLayout(new BoxLayout(approPanel, BoxLayout.Y_AXIS));
        approPanel.setBounds(180, 30, 600, 600);

        ArrayList<Appointment> pendingAppointments = new ArrayList<>();

        for (Appointment appointment : appointmentArr) {

            if (appointment.getStatus().equals("pending")) {
                pendingAppointments.add(appointment);
            }
        }

        if (pendingAppointments.isEmpty()) {
            approPanel.add(new JLabel("No pending appointments."));
        } else {

            for (Appointment appointment : pendingAppointments) {

                JButton button = new JButton("Appointment ID: " + appointment.getAppointmentId() + " | Patient: " + appointment.getPatientName());

                button.setCursor(new Cursor(Cursor.HAND_CURSOR));

                button.addActionListener(e -> {

                    appointment.setStatus("confirmed");

                    for (int i = 0; i < appointmentArr.size(); i++) {
                        if (appointmentArr.get(i).getAppointmentId() == appointment.getAppointmentId()) {
                            appointmentArr.set(i, appointment);
                            break;
                        }
                    }

                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/appointments.dat"))) {
                        for (Appointment appointmento : appointmentArr) {
                            oos.writeObject(appointmento);
                        }
                    } catch (IOException err) {
                        err.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(centerPanel, "Appointment Approved!");

                    ChangeAdminCenterScreen.setAdminCenterScreen("default");
                    AdminDashboard.ReflectUIChange();
                });
                approPanel.add(button);
            }
        }

        centerPanel.removeAll();
        centerPanel.add(approPanel);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public static void viewAppointment(JPanel centerPanel) {

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

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(300, 10, 700, 600);
        mainPanel.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Patient Name", "Doctor Name", "time slot", "status", "Date"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Appointment appointment : appointmentArr) {

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

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        centerPanel.add(mainPanel);

    }

    public static void checkInAppointment(JPanel centerPanel) {

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

        JPanel approPanel = new JPanel();

        approPanel.setLayout(new BoxLayout(approPanel, BoxLayout.Y_AXIS));
        approPanel.setBounds(180, 30, 600, 600);

        ArrayList<Appointment> confirmedAppointments = new ArrayList<>();

        for (Appointment appointment : appointmentArr) {

            if (appointment.getStatus().equals("confirmed")) {
                confirmedAppointments.add(appointment);
            }
        }

        if (confirmedAppointments.isEmpty()) {
            approPanel.add(new JLabel("No pending appointments."));
        } else {

            for (Appointment appointment : confirmedAppointments) {

                JButton button = new JButton("Appointment ID: " + appointment.getAppointmentId() + " | Patient: " + appointment.getPatientName());

                button.setCursor(new Cursor(Cursor.HAND_CURSOR));

                button.addActionListener(e -> {

                    appointment.setStatus("done");

                    for (int i = 0; i < appointmentArr.size(); i++) {
                        if (appointmentArr.get(i).getAppointmentId() == appointment.getAppointmentId()) {
                            appointmentArr.set(i, appointment);
                            break;
                        }
                    }

                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/appointments.dat"))) {
                        for (Appointment appointmento : appointmentArr) {
                            oos.writeObject(appointmento);
                        }
                    } catch (IOException err) {
                        err.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(centerPanel, "Appointment Done!");

                    ChangeAdminCenterScreen.setAdminCenterScreen("default");
                    AdminDashboard.ReflectUIChange();
                });
                approPanel.add(button);
            }
        }

        centerPanel.removeAll();
        centerPanel.add(approPanel);
        centerPanel.revalidate();
        centerPanel.repaint();

    }
    
    public static void addAdminScreen(JPanel centerPanel){
        
        JLabel titleText = new JLabel();
        titleText.setText("Add New Admin");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);
        
        JPanel addAdminPanel = new JPanel();
        addAdminPanel.setBounds(350, 100, 750, 600);
        addAdminPanel.setLayout(null);

        JLabel idLabel = new JLabel();
        idLabel.setText("ID :");
        idLabel.setBounds(50, 40, 70, 20);
        idLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addAdminPanel.add(idLabel);

        String idValue;

        ArrayList<Admin> admins = new ArrayList<>();

        File file = new File("src/hospitalproject/data/admins.dat");

        if (file.exists()) {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/admins.dat"))) {

                while (true) {

                    try {
                        admins.add((Admin) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (admins.isEmpty()) {
            idValue = "1";
        } else {
            idValue = Integer.toString(admins.get(admins.size() - 1).getId() + 1);
        }

        JTextField idField = new JTextField(15);
        idField.setText(idValue);
        idField.setEnabled(false);
        idField.setForeground(Color.gray);
        idField.setBounds(95, 38, 100, 30);

        addAdminPanel.add(idField);

        JLabel nameLabel = new JLabel();
        nameLabel.setText("Name :");
        nameLabel.setBounds(50, 90, 70, 20);
        nameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addAdminPanel.add(nameLabel);

        JTextField nameField = new JTextField(15);
        nameField.setBounds(120, 86, 180, 30);

        addAdminPanel.add(nameField);

        JLabel emailLabel = new JLabel();
        emailLabel.setText("Email :");
        emailLabel.setBounds(50, 140, 70, 20);
        emailLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addAdminPanel.add(emailLabel);

        JTextField emailField = new JTextField(15);
        emailField.setBounds(120, 136, 180, 30);

        addAdminPanel.add(emailField);

        JLabel contactNumLabel = new JLabel();
        contactNumLabel.setText("Contact Num :");
        contactNumLabel.setBounds(50, 190, 150, 20);
        contactNumLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addAdminPanel.add(contactNumLabel);

        JTextField contactNumField = new JTextField(15);
        contactNumField.setBounds(185, 186, 180, 30);

        addAdminPanel.add(contactNumField);

        JLabel genderLabel = new JLabel();
        genderLabel.setText("Gender :");
        genderLabel.setBounds(50, 240, 150, 20);
        genderLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addAdminPanel.add(genderLabel);

        JTextField genderField = new JTextField(15);
        genderField.setBounds(140, 236, 140, 30);

        addAdminPanel.add(genderField);

        JLabel districtLabel = new JLabel();
        districtLabel.setText("District :");
        districtLabel.setBounds(50, 290, 150, 20);
        districtLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addAdminPanel.add(districtLabel);

        JTextField districtField = new JTextField(15);
        districtField.setBounds(140, 286, 140, 30);

        addAdminPanel.add(districtField);

        JLabel roleLabel = new JLabel();
        roleLabel.setText("Role :");
        roleLabel.setBounds(50, 390, 150, 20);
        roleLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addAdminPanel.add(roleLabel);

        JTextField roleField = new JTextField(15);
        roleField.setBounds(140, 386, 170, 30);

        addAdminPanel.add(roleField);

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username :");
        usernameLabel.setBounds(400, 88, 200, 20);
        usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addAdminPanel.add(usernameLabel);

        JTextField usernameField = new JTextField(15);
        usernameField.setBounds(510, 85, 200, 30);

        addAdminPanel.add(usernameField);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password :");
        passwordLabel.setBounds(400, 138, 200, 20);
        passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        addAdminPanel.add(passwordLabel);

        JTextField passwordField = new JTextField(15);
        passwordField.setBounds(510, 135, 200, 30);

        addAdminPanel.add(passwordField);

        JButton submitButton = new JButton();
        submitButton.setText("CREATE");
        submitButton.setBackground(Color.blue);
        submitButton.setForeground(Color.white);
        submitButton.setFont(new Font("Roboto", Font.BOLD, 15));
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.setBounds(500, 500, 130, 30);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(e -> {

            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String email = emailField.getText();
            String contactNum = contactNumField.getText();
            String gender = genderField.getText();
            String district = districtField.getText();
            String role = roleField.getText();
            String username = usernameField.getText();
            String password = "";
            try {
                password = PasswordEncryptDecrypt.encrypt(passwordField.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Admin admin = new Admin(id, name, email, contactNum, gender, district, username, password,role);

            if (file.exists()) {

                ArrayList<Admin> adminsArr = new ArrayList<>();

                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/admins.dat"))) {
                    while (true) {

                        try {

                            adminsArr.add((Admin) ois.readObject());

                        } catch (EOFException eofErr) {
                            break;
                        }

                    }

                } catch (IOException | ClassNotFoundException err) {
                    err.printStackTrace();
                }

                adminsArr.add(admin);

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/admins.dat"))) {

                    for (Admin adminSingle : adminsArr) {

                        oos.writeObject(adminSingle);

                    }

                    System.out.println("Admin Data Written Appended !");

                } catch (IOException err) {
                    err.printStackTrace();
                }

                ChangeAdminCenterScreen.setAdminCenterScreen("default");
                AdminDashboard.ReflectUIChange();

            } else {

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/admins.dat"))) {

                    oos.writeObject(admin);
                    System.out.println("Admin Data Written !");

                } catch (IOException err) {
                    err.printStackTrace();
                }

                ChangeAdminCenterScreen.setAdminCenterScreen("default");
                AdminDashboard.ReflectUIChange();

            }

        });

        addAdminPanel.add(submitButton);

        centerPanel.add(addAdminPanel);
        
        
    }
}
