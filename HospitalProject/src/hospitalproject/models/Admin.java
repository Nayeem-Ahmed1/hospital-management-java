
package hospitalproject.models;

import java.io.Serializable;

public class Admin extends User implements Serializable{
       
    private String username;
    private String password;
    private String role;
    
    
    public Admin(int id,String name,String email,String contact,String gender,String district,String userName,String password,String role){
        
        super(id,name,email,contact,gender,district);
        
        this.username = userName;
        this.password = password;
        this.role = role;        
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
