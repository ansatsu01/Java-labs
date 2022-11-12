import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindow extends JPanel {
    DefaultListModel<String> model2;
    DefaultListModel<String> model1;
    JList<String> list1;
    JList<String> list2;
    FirstWindow(){

        String[] womenNames = new String[]{"Anna", "Angelina", "Yana", "Victoria", "Valeria"};
        String[] menNames = new String[]{"Aleksander", "Anton", "Yuri", "Victor", "Valera", "Toha"};
        JPanel panel = new JPanel(new BorderLayout());
        setLayout(new BorderLayout());
        JButton left = new JButton("<<");
        JButton right = new JButton(">>");
        panel.add(left, BorderLayout.SOUTH);
        panel.add(right, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        model1 = new DefaultListModel<>();
        list1 = new JList<>(model1);
        for (String name : womenNames){
            model1.addElement(name);
        }
        model2 = new DefaultListModel<>();
        list2 = new JList<>(model2);
        for (String name : menNames){
            model2.addElement(name);
        }
        add(list1, BorderLayout.WEST);
        add(list2, BorderLayout.EAST);
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(model1, model2, list2);
            }
        });
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(model2, model1, list1);
            }
        });
    }
    public void move(DefaultListModel<String> m1, DefaultListModel<String> m2, JList<String> list){
        int[] indices = list.getSelectedIndices();
        for(int i = 0; i < indices.length; i++){
            m1.addElement(m2.getElementAt(indices[i]));
        }
        for(int i = indices.length - 1; i >= 0; i--){
            m2.remove(indices[i]);
        }

    }
}
