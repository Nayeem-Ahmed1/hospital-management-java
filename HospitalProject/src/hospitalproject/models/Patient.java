package hospitalproject.models;

import java.util.ArrayList;
import java.util.Date;

public class Patient extends User {

    private Date dob;
    private String address;
    private String emergencyContact;
    private String bloodGroup;
    private String status;
    private String username;
    private String password;
    private ArrayList<String> medicalHistories;
    private ArrayList<String> appointments;
    
    public Patient(int id, String name, String email, String contact,String gender,String district,Date dob,String address,String emerContact,String bloodGroup){
        
        this(id,name,email,contact,gender,district,dob,address,emerContact,bloodGroup,"none");
        
    }

    public Patient(int id, String name, String email, String contact, String gender,String district, Date dob, String address, String emerContact, String bloodGroup, String status) {

        super(id, name, email, contact, gender,district);

        this.dob = dob;
        this.address = address;
        this.emergencyContact = emerContact;
        this.bloodGroup = bloodGroup;
        this.status = status;
        this.medicalHistories = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public Date getDob() {
        return dob;
    }


    public void setDob(Date dob) {
        this.dob = dob;
    }

 
    public String getAddress() {
        return address;
    }

    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }


    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getMedicalHistories() {
        return medicalHistories;
    }

    public void setMedicalHistories(ArrayList<String> medicalHistories) {
        this.medicalHistories = medicalHistories;
    }

    public ArrayList<String> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<String> appointments) {
        this.appointments = appointments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

}
