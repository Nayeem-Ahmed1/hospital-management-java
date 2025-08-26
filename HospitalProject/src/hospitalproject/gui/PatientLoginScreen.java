package hospitalproject.gui;

import hospitalproject.Main;
import hospitalproject.models.Patient;
import hospitalproject.utils.LoggedInUser;
import hospitalproject.utils.PasswordEncryptDecrypt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PatientLoginScreen implements ActionListener {

    private JTextField textField;
    private JPasswordField passField;
    private JLabel errorLabel;

    private JFrame frame;
    private JButton buttonSubmit;
    private JButton buttonExit;
    private JButton buttonCreate;

    public PatientLoginScreen() {

        frame = new JFrame();

        ImageIcon hospitalIconImage = new ImageIcon("src/hospitalproject/images/hospital_icon.png");
        ImageIcon adminIconImage = new ImageIcon("src/hospitalproject/images/patient_emoji.png");

        frame.setSize(1400, 900);
        frame.setTitle("Patient Login");
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

        loginLabel.setText("Patient Sign In");
        loginLabel.setBounds(500, 50, 500, 200);
        loginLabel.setFont(new Font("Roboto", Font.BOLD, 50));

        frame.add(loginLabel);

        /* error message */
        errorLabel = new JLabel();

        errorLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        errorLabel.setText("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(560, 130, 400, 300);

        frame.add(errorLabel);

        /* Form */
        JPanel formPanel = new JPanel();

        formPanel.setBounds(480, 250, 500, 350);
        formPanel.setLayout(new GridBagLayout());

        GridBagConstraints gdc = new GridBagConstraints();
        gdc.insets = new Insets(20, 20, 20, 20);

        /* Username */
        JLabel userLabel = new JLabel();

        userLabel.setText("Username :");
        userLabel.setFont(new Font("Roboto", Font.PLAIN, 20));

        textField = new JTextField(15);
        textField.setPreferredSize(new Dimension(500, 40));
        textField.setFont(new Font("Roboto", Font.PLAIN, 16));

        gdc.gridx = 0;
        gdc.gridy = 0;
        gdc.anchor = GridBagConstraints.EAST;

        formPanel.add(userLabel, gdc);

        gdc.gridx = 1;
        gdc.gridy = 0;
        gdc.anchor = GridBagConstraints.WEST;

        formPanel.add(textField, gdc);

        /* Password */
        JLabel passLabel = new JLabel();

        passLabel.setText("Password :");
        passLabel.setFont(new Font("Roboto", Font.PLAIN, 20));

        passField = new JPasswordField(15);

        passField.setPreferredSize(new Dimension(500, 40));
        passField.setFont(new Font("Roboto", Font.PLAIN, 16));

        gdc.gridx = 0;
        gdc.gridy = 1;
        gdc.anchor = GridBagConstraints.EAST;

        formPanel.add(passLabel, gdc);

        gdc.gridx = 1;
        gdc.gridy = 1;
        gdc.anchor = GridBagConstraints.WEST;

        formPanel.add(passField, gdc);

        /* Buttons */
        buttonSubmit = new JButton();
        buttonExit = new JButton();
        buttonCreate = new JButton();
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new BorderLayout());

        buttonSubmit.setText("LOGIN");
        buttonSubmit.setBackground(Color.black);
        buttonSubmit.setForeground(Color.white);
        buttonSubmit.setOpaque(true);
        buttonSubmit.setBorderPainted(false);
        buttonSubmit.setMargin(new Insets(10, 10, 10, 10));
        buttonSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonSubmit.addActionListener(this);

        buttonExit.setText("EXIT");
        buttonExit.setBackground(Color.black);
        buttonExit.setForeground(Color.white);
        buttonExit.setOpaque(true);
        buttonExit.setBorderPainted(false);
        buttonExit.setMargin(new Insets(10, 10, 10, 10));
        buttonExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonExit.addActionListener(this);

        buttonPanel.add(buttonSubmit, BorderLayout.EAST);

        buttonPanel.add(buttonExit, BorderLayout.WEST);

        gdc.gridx = 0;
        gdc.gridy = 2;
        gdc.gridwidth = 2;
        gdc.anchor = GridBagConstraints.CENTER;
        gdc.fill = GridBagConstraints.HORIZONTAL;

        formPanel.add(buttonPanel, gdc);


        frame.add(formPanel);
        
                
        buttonCreate.setText("Register New Account");
        buttonCreate.setBackground(Color.black);
        buttonCreate.setForeground(Color.yellow);
        buttonCreate.setOpaque(true);
        buttonCreate.setBorderPainted(false);
        buttonCreate.setMargin(new Insets(5, 5, 5, 5));
        buttonCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonCreate.addActionListener(this);
        buttonCreate.setBounds(600, 610, 300, 30);
        
        frame.add(buttonCreate);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonExit) {
            Main.changeActiveScreen("firstWindow");
            frame.dispose();
        }
        if(e.getSource() == buttonCreate){
            Main.changeActiveScreen("registerAccountPatient");
            frame.dispose();
        }
        if (e.getSource() == buttonSubmit) {

            try {
                String username = textField.getText();
                char[] passChars = passField.getPassword();

                String password = new String(passChars);

                boolean authenticated = false;

                ArrayList<Patient> patients = new ArrayList<>();

                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/hospitalproject/data/patients.dat"))) {

                    while (true) {
                        try {

                            patients.add((Patient) ois.readObject());

                        } catch (EOFException eoferr) {
                            break;
                        }
                    }

                } catch (IOException | ClassNotFoundException eror) {
                    eror.printStackTrace();
                }
                
                for(Patient patient : patients){
                    
                    if(username.equals(patient.getUsername()) && password.equals(PasswordEncryptDecrypt.decrypt(patient.getPassword()))){
                        
                        authenticated = true;
                        LoggedInUser.setLoggedName(patient.getName());
                        LoggedInUser.setLoggedId(patient.getId());
                    }
                    
                }

                if (authenticated) {
                    Main.changeActiveScreen("patientDashboard");
                    
                    frame.dispose();
                    
                } else {

                    errorLabel.setText("Invalid Username or Password!");
                    textField.setText("");
                    passField.setText("");

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

}
