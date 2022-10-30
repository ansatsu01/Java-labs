import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame{
    private final JLabel status;
    Window(String str){
        super(str);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,600);
        setLayout(new BorderLayout());
        status = new JLabel();
        add(status, BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        JButton button = new JButton("button");
        panel.setLayout(null);
        panel.add(button);
        button.setSize(100,30);
        button.setLocation((getWidth() - button.getWidth())/2, 10);
        add(panel, BorderLayout.CENTER);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setStatus(e.getX(), e.getY());
                button.setLocation(e.getX(), e.getY());
            }

        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseMoved(e);
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                setStatus(e.getX(), e.getY());
            }
        });

        button.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                setStatus(e.getX() + button.getX(), e.getY() + button.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if(e.isControlDown()){
                    button.setLocation(e.getX() + button.getX(), e.getY() + button.getY());
                    setStatus(e.getX() + button.getX(), e.getY() + button.getY());
                }
            }
        });
        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    String text = button.getText();
                    if(text.length() > 0) {
                        StringBuilder builder = new StringBuilder(text);
                        builder.deleteCharAt(text.length()-1);
                        button.setText(builder.toString());
                    }
                } else {
                    String text = button.getText();
                    button.setText(text + e.getKeyChar());
                }
            }
        });
    }

    void setStatus(int x, int y){
        status.setText("x: " + x + ", y: " + y);
    }

}
