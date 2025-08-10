
package hospitalproject.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;


public class FirstWindowButton {
    
    public static void styledButton(JButton button,String text,Color backGroundColor,Color hoverColor){
        
        button.setText(text);
        button.setFont(new Font("Roboto",Font.BOLD,20));
        button.setBackground(backGroundColor);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backGroundColor);
            }
        });       
    }    
}
