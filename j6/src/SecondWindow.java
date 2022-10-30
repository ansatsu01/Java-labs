import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class SecondWindow extends JFrame {
    SecondWindow(String str){
        super(str);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);

        setLayout(new FlowLayout());
        JLabel label = new JLabel("Does the amount of money you get from the university satisfy you?");
        label.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        label.setVerticalAlignment(JLabel.NORTH);
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label);
        JButton yesButton = new JButton("Yes");
        add(yesButton);
        JButton noButton = new JButton("No!");
        add(noButton);
        yesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(SecondWindow.this,"You're a liar!", "What a pity", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        noButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Random random = new Random();
                noButton.setLocation(random.nextInt(getWidth() - noButton.getWidth()),
                        label.getHeight() + random.nextInt(getHeight() - (label.getHeight() + noButton.getHeight())));
            }
        });
    }
}
