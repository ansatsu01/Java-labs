import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private MyQueue<String> queue  = new MyQueue<>();
    private JList<String> jList;
    private JLabel status;
    JButton pushButton = new JButton("Push");
    JButton popButton = new JButton("Pop");
    JButton pushAllButton = new JButton("Push all");

    MainWindow(){
        super("MyQueue realisation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        jList = new JList<>(queue.getModel());
        status = new JLabel();
        add(jList);
        add(pushButton);
        add(popButton);
        add(pushAllButton);
        add(status);
        setStatus();


        pushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = JOptionPane.showInputDialog(null, "Enter the element");
                queue.push(str);
                jList.setModel(queue.getModel());
                setStatus();

            }
        });

        popButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                queue.pop();
                jList.setModel(queue.getModel());
                setStatus();
                }catch (IndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pushAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] items = JOptionPane.showInputDialog(null, "enter elements").split(" ");
                MyQueue<String> toAdd = new MyQueue<>();
                for (String item : items){
                    toAdd.push(item);
                }
                queue.pushAll(toAdd);
                jList.setModel(queue.getModel());
                setStatus();
            }
        });
    }

private void setStatus(){
        if(queue.isEmpty()){
            status.setText("Queue is empty");
        }else {
            status.setText("First element: " + queue.front() + '\n' + " Last element: " + queue.back());
        }
}

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.pack();
        window.setVisible(true);
    }
}
