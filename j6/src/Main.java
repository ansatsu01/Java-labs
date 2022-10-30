public class Main {
    public static void main(String[] args) {
        Window window = new Window("1st task");
        window.setVisible(true);
        SecondWindow s = new SecondWindow("catch me");
        s.setVisible(true);
    }
}
