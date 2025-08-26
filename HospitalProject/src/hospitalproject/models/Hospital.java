
package hospitalproject.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Hospital implements Serializable{
    
    private int id;
    private String name;
    private String registrationNumber;
    private String address;
    private String city;
    private String postalCode;
    private String phoneNumber;
    private String email;
    private String websiteName;
    private Date establishedDate;
    private int totalBeds;
    private int totalDoctors;
    private int totalStaffs;
    private ArrayList<String> departments;
    private Date createdAt;
    private String status;

    public Hospital(int id, String name, String registrationNumber, String address, String city, String postalCode, String phoneNumber, String email, String websiteName, Date establishedDate, int totalBeds, int totalDoctors, int totalStaffs) {
        this.id = id;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.websiteName = websiteName;
        this.establishedDate = establishedDate;
        this.totalBeds = totalBeds;
        this.totalDoctors = totalDoctors;
        this.totalStaffs = totalStaffs;
        this.status = "active";
        
        this.departments = new ArrayList<>();
        this.createdAt = new Date();
    }

    @Override
    public String toString() {
        return "Hospital{" + "id=" + id + ", name=" + name + ", registrationNumber=" + registrationNumber + ", address=" + address + ", city=" + city + ", postalCode=" + postalCode + ", phoneNumber=" + phoneNumber + ", email=" + email + ", websiteName=" + websiteName + ", establishedDate=" + establishedDate + ", totalBeds=" + totalBeds + ", totalDoctors=" + totalDoctors + ", totalStaffs=" + totalStaffs + ", departments=" + departments + ", createdAt=" + createdAt + ", status=" + status + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public Date getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(Date establishedDate) {
        this.establishedDate = establishedDate;
    }

    public int getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(int totalBeds) {
        this.totalBeds = totalBeds;
    }

    public int getTotalDoctors() {
        return totalDoctors;
    }

    public void setTotalDoctors(int totalDoctors) {
        this.totalDoctors = totalDoctors;
    }

    public int getTotalStaffs() {
        return totalStaffs;
    }

    public void setTotalStaffs(int totalStaffs) {
        this.totalStaffs = totalStaffs;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
