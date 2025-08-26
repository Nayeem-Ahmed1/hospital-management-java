
package hospitalproject.gui;

import hospitalproject.models.Doctor;
import hospitalproject.utils.ChangeDoctorCenterScreen;
import hospitalproject.utils.LoggedInUser;
import hospitalproject.utils.PasswordEncryptDecrypt;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DoctorCenterScreen {
    
    public static void defaultScreen(JPanel centerPanel){
        
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
    
    public static void editProfileScreen(JPanel centerPanel){
        
        JLabel titleText = new JLabel();
        titleText.setText("Update Doctor Info");
        titleText.setFont(new Font("Roboto", Font.BOLD, 20));
        titleText.setBounds(100, 70, 800, 50);

        centerPanel.add(titleText);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(300, 300, 600, 300);
        mainPanel.setLayout(null);

                int id = LoggedInUser.id;

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

                            ChangeDoctorCenterScreen.setDoctorCenterScreen("default");
                            DoctorDashboard.ReflectUIChange();

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


        centerPanel.add(mainPanel);
        
    }
    
}
