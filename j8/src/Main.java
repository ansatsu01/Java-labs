import javax.swing.*;

public class Main extends JFrame {
    JTabbedPane pane = new JTabbedPane();
    Main(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pane.add(new FirstWindow(), "first task");
        pane.add(new SecondWindow(), "second task");
        pane.add(new ThirdWindow(), "third task");
        add(pane);
    }

    public static void main(String[] args) {
        Main window = new Main();
        window.pack();
        window.setVisible(true);
    }
}