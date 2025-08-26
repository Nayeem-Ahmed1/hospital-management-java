
package hospitalproject.gui;

import com.toedter.calendar.JDateChooser;
import hospitalproject.models.Patient;
import hospitalproject.utils.ChangePatientCenterScreen;
import hospitalproject.utils.PasswordEncryptDecrypt;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PatientSignUpPage {
    JFrame frame;
    
    public PatientSignUpPage(){
        
        
        frame = new JFrame();

        ImageIcon hospitalIconImage = new ImageIcon("src/hospitalproject/images/hospital_icon.png");
        ImageIcon adminIconImage = new ImageIcon("src/hospitalproject/images/patient_emoji.png");

        frame.setSize(1400, 900);
        frame.setTitle("Patient Registration");
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

        loginLabel.setText("Patient Sign Up");
        loginLabel.setBounds(500, 20, 500, 200);
        loginLabel.setFont(new Font("Roboto", Font.BOLD, 50));

        frame.add(loginLabel);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(400, 200, 750, 650);
        mainPanel.setLayout(null);

        JPanel addPatientPanel = new JPanel();
        addPatientPanel.setBounds(20, 20, 750, 600);
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

                ChangePatientCenterScreen.setPatientCenterScreen("default");
                PatientDashboard.ReflectUIChange();
                frame.dispose();

            } else {

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/hospitalproject/data/patients.dat"))) {

                    oos.writeObject(patient);
                    System.out.println("Patient Data Written !");

                } catch (IOException err) {
                    err.printStackTrace();
                }

                ChangePatientCenterScreen.setPatientCenterScreen("default");
                PatientDashboard.ReflectUIChange();
                frame.dispose();

            }

        });

        addPatientPanel.add(submitButton);

        mainPanel.add(addPatientPanel);

        
        frame.add(mainPanel);
        
        frame.setVisible(true);
        
    }
}
