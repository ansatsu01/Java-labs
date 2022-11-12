import javax.swing.*;
import java.awt.*;

public class ThirdWindow extends JPanel {
    ThirdWindow(){
        ImageIcon[] icons = new ImageIcon[5];
        icons[0] = new ImageIcon("D:\\Temporary\\img1.png");
        icons[1] = new ImageIcon("D:\\Temporary\\img2.png");
        icons[2] = new ImageIcon("D:\\Temporary\\img3.png");
        icons[3] = new ImageIcon("D:\\Temporary\\img4.png");
        JLabel label = new JLabel("Choose the best region to live:");
        String[] towns = new String[]{"Minsk", "Brest", "Homyel", "Mogilev", "Vitebsk", "Hrodna"};
       //JRadioButton[] buttons = new JRadioButton[6];
        ButtonGroup group = new ButtonGroup();
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(label);

        for(int i = 0; i < towns.length; i++){
            JRadioButton button = new JRadioButton(towns[i]);
            group.add(button);
            panel.add(button);
            button.setIcon(icons[1]);
            button.setSelectedIcon(icons[0]);
            button.setDisabledSelectedIcon(icons[1]);
            button.setPressedIcon(icons[2]);
            button.setRolloverIcon(icons[3]);
        }
        add(panel, BorderLayout.CENTER);
    }
}
