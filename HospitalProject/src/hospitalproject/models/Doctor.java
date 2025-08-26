package hospitalproject.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Doctor extends User implements Serializable {
    
    private String hospitalName;
    private String email;
    private ArrayList<Appointment> appointments;
    private Date createdAt;    
    private ArrayList<String> availability;
    private String username;
    private String password;
    private ArrayList<String> departments;

    public Doctor(int id, String name, String email, String contact, String gender, String district, String hospitalName,String username,String password,ArrayList<String> availability,ArrayList<String> departments) {
        super(id, name, email, contact, gender, district);
        this.hospitalName = hospitalName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.appointments = new ArrayList<>();
        this.availability = new ArrayList<>();
        this.departments = new ArrayList<>();
        this.availability = availability;
        this.departments = departments;
        this.createdAt = new Date();
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }


    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }
    
        public ArrayList<String> getDepartments() {
        return departments;
    }


    public void setDepartments(ArrayList<String> departments) {
        this.departments = departments;
    }


    public Date getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public ArrayList<String> getAvailability() {
        return availability;
    }


    public void setAvailability(ArrayList<String> availability) {
        this.availability = availability;
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
