import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SecondWindow extends JPanel {
    public static final int DIMENSION = 9;
    public static final int NUM_OF_BUTTONS = (int)Math.round(Math.sqrt(DIMENSION));
    public static final String MESSAGE = "Clicked!";
    String text;
    Color color;

    public SecondWindow(){
        setLayout(new GridLayout(NUM_OF_BUTTONS, NUM_OF_BUTTONS));
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    JButton button = (JButton)e.getSource();
                    text = button.getText();
                    button.setText(MESSAGE);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    JButton button = (JButton)e.getSource();
                    button.setText(text);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton)e.getSource();
                color = button.getBackground();
                button.setBackground(Color.orange);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton button = (JButton)e.getSource();
                button.setBackground(color);
            }
        };
        for(int i = 1; i <= DIMENSION; i++){
            JButton button = new JButton(i + "");
            add(button);
            button.addMouseListener(listener);
        }


    }
}
