
package hospitalproject;
import hospitalproject.gui.AdminDashboard;
import hospitalproject.gui.AdminLoginPage;
import hospitalproject.gui.DoctorDashboard;
import hospitalproject.gui.DoctorLoginScreen;
import hospitalproject.gui.DoctorSignUpPage;
import hospitalproject.gui.FirstWindow;
import hospitalproject.gui.PatientDashboard;
import hospitalproject.gui.PatientLoginScreen;
import hospitalproject.gui.PatientSignUpPage;

public class Main {
    
    public static String activeScreen = "firstWindow";


    public static void main(String[] args) {
        
        screenUI();
        
    }
    
    public static void screenUI(){
        
        switch(activeScreen){
            case "firstWindow" -> {
                
                FirstWindow call = new FirstWindow();
                
            } 
            
            case "adminLoginScreen" -> {
                
                AdminLoginPage call = new AdminLoginPage();
                
            }
            
            case "adminDashboard" -> {
                AdminDashboard call = new AdminDashboard();
            }
            
            case "patientLoginScreen" -> {
                PatientLoginScreen call = new PatientLoginScreen();
            }
            case "patientDashboard" -> {
                PatientDashboard call = new PatientDashboard();
            }
            case "registerAccountPatient" -> {
                PatientSignUpPage call = new PatientSignUpPage();
            }
            case "registerAccountDoctor" -> {
                DoctorSignUpPage call = new DoctorSignUpPage();
            }
            case "doctorLoginScreen" -> {
                
                DoctorLoginScreen call = new DoctorLoginScreen();
                
            }
            case "doctorDashboard" -> {
                
                DoctorDashboard call = new DoctorDashboard();
            }
            case "DoctorSignUpPage" -> {
                
                DoctorSignUpPage call = new DoctorSignUpPage();
            }
        }          
    }
    
    public static void changeActiveScreen(String newScreen){
        
        activeScreen = newScreen;
        System.out.println("Switching to " + activeScreen);
        screenUI();
    }
    
}
