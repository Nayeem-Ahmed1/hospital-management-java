
package hospitalproject.models;

public abstract class User {
    
    private int id;
    private String name;
    private String email;
    private String contactNumber;
    private String gender;
    private String district;
    
    protected User(){
        this.id = 0;
        this.name = "default";
        this.email = "default";
        this.contactNumber = "default";
        this.gender = "default";
        this.district = "default";
    };
    
    protected User(int id,String name,String email,String contact,String gender,String district){
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contact;
        this.gender = gender;
        this.district = district;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
    
}
