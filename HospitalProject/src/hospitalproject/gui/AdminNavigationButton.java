
package hospitalproject.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;


public class AdminNavigationButton {
    
    public static JButton navigationButton(String text){
        
        JButton btn = new JButton();
        
        btn.setText(text);
        btn.setPreferredSize(new Dimension(170,75));
        btn.setBackground(new Color(0,206,209));
        btn.setFont(new Font("Roboto",Font.BOLD,15));
        btn.setOpaque(true);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(113,235,178));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(0,206,209));
            }
        });  

        
        return btn;
    }
    
}
