
package hospitalproject;
import hospitalproject.gui.AdminLoginPage;
import hospitalproject.gui.FirstWindow;

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
        }          
    }
    
    public static void changeActiveScreen(String newScreen){
        
        activeScreen = newScreen;
        System.out.println("Switching to " + activeScreen);
        screenUI();
    }
    
}
