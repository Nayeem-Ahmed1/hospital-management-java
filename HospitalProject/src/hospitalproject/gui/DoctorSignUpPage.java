
package hospitalproject.gui;

import hospitalproject.models.Doctor;
import hospitalproject.models.Hospital;
import hospitalproject.utils.ChangeDoctorCenterScreen;
import hospitalproject.utils.PasswordEncryptDecrypt;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DoctorSignUpPage {
    JFrame frame;
    
    public DoctorSignUpPage(){
        frame = new JFrame();

        ImageIcon hospitalIconImage = new ImageIcon("src/hospitalproject/images/hospital_icon.png");
        ImageIcon adminIconImage = new ImageIcon("src/hospitalproject/images/patient_emoji.png");

        frame.setSize(1400, 900);
        frame.setTitle("Doctor Registration");
        frame.setIconImage(hospitalIconImage.getImage());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(173, 216, 230));

        JLabel adminImageLabel = new JLabel();

        adminImageLabel.setBounds(100, 240, 400, 400);
        adminImageLabel.setIcon(adminIconImage);

        frame.add(adminImageLabel);

        /*Login Message Label */
        JLabel loginLabel = new JLabel();

        loginLabel.setText("Doctor Sign Up");
        loginLabel.setBounds(500, 20, 500, 200);
        loginLabel.setFont(new Font("Roboto", Font.BOLD, 20));

        frame.add(loginLabel);
        
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

                ChangeDoctorCenterScreen.setDoctorCenterScreen("default");
                DoctorDashboard.ReflectUIChange();
                frame.dispose();

            } else {

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/doctors.dat"))) {

                    oos.writeObject(doctor);
                    System.out.println("Doctor Data Written !");

                } catch (IOException err) {
                    err.printStackTrace();
                }

                ChangeDoctorCenterScreen.setDoctorCenterScreen("default");
                DoctorDashboard.ReflectUIChange();
                frame.dispose();

            }

        });

        addDoctorPanel.add(submitButton);

        frame.add(addDoctorPanel);
        frame.setVisible(true);
        
    }
    
}
