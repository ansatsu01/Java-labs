import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Window extends JFrame {
    JTextArea allStudents;
    JTextArea input;
    JTextArea losers;
    JButton openButton;
    JButton showButton;
    JButton addButton;
    ArrayList<Student> students;
    Window(){
        super("Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        openButton = new JButton("Open");
        showButton = new JButton("Find losers!");
        addButton = new JButton("Add student");
        students = new ArrayList<>();
        allStudents = new JTextArea("Open the file!");
        input = new JTextArea("Enter the line");
        losers = new JTextArea("There will be losers");
        add(allStudents);
        add(openButton);
        add(addButton);
        add(input);
        add(showButton);
        add(losers);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String str = JOptionPane.showInputDialog(null, "Add student (name, id, sem, subject, grade)");
                    StringTokenizer tokenizer = new StringTokenizer(str, " ");
                    String name = tokenizer.nextToken();
                    int id = Integer.parseInt(tokenizer.nextToken());
                    int sem = Integer.parseInt(tokenizer.nextToken());
                    String subject = tokenizer.nextToken();
                    int grade = Integer.parseInt(tokenizer.nextToken());
                    Student st = new Student(name, id, sem, subject , grade);
                    boolean changed = false;
                    for(int i=0; i < students.size(); i++){
                        if(students.get(i).exists(name, id, sem, subject)){
                            students.set(i, st);
                            changed = true;
                        }
                    }
                    if(!changed){
                        students.add(st);
                    }
                    showStudents();
                }catch (NoSuchElementException ex){
                    JOptionPane.showMessageDialog(null,"Not enough info", "Error", JOptionPane.ERROR_MESSAGE);
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Incorrect input", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        openFileAndInit();
        processAndFindLosers();
        input.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                input.setText("");
            }
        });
    }

    public void openFileAndInit(){
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                        File file = fileChooser.getSelectedFile();
                        Scanner sc = new Scanner(file);
                        students.addAll(fillFromFile(file, sc));
                        showStudents();
                    }
                }catch (FileNotFoundException ex){
                    JOptionPane.showMessageDialog(null, "File's not found", "Error", JOptionPane.ERROR_MESSAGE);
                }catch (InputMismatchException ex){
                    JOptionPane.showMessageDialog(null, "Incorrect input", "Error", JOptionPane.ERROR_MESSAGE);
                }catch (NoSuchElementException ex){
                    JOptionPane.showMessageDialog(null, "Not enough elements", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    void processAndFindLosers(){
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                StringBuilder str = new StringBuilder();
                String[] line = input.getText().split(" ");
                if(line.length < 2){
                    throw new IllegalArgumentException("Not enough info was entered");
                }
                ArrayList<Student> studentsCopy = new ArrayList<>(students);
                int sem = Integer.parseInt(line[0]);
                studentsCopy = (ArrayList<Student>) studentsCopy.stream().
                        filter(a -> a.getSem()==sem).collect(Collectors.toList());
                for (int i = 1; i < line.length; i++){
                    int index = i;
                    ArrayList<Student> toRemove = (ArrayList<Student>) studentsCopy.stream().
                            filter(a -> a.getGrade() > 4 && a.getSubject().
                                    equals(line[index])).collect(Collectors.toList());
                    studentsCopy.removeAll(toRemove);
                    Set<Integer> IDs = new HashSet<>();
                    for(Student st : toRemove){
                        IDs.add(st.getId());
                    }
                    toRemove.clear();
                    for(Student st : studentsCopy){
                        if(IDs.contains(st.getId())){
                            toRemove.add(st);
                        }
                    }
                    studentsCopy.removeAll(toRemove);

                }
                studentsCopy = (ArrayList<Student>)studentsCopy.stream().sorted().collect(Collectors.toList());
                for(Student student : studentsCopy){
                    str.append(student.toString()).append("\n");
                }
                losers.setText(str.toString());
            }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Troubles in parsing", "Error", JOptionPane.ERROR_MESSAGE);
                }catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
    public ArrayList<Student> fillFromFile(File file, Scanner sc){
        ArrayList<Student> list = new ArrayList<>();
        while (sc.hasNext()){
            Student a = new Student(sc.next(), sc.nextInt(), sc.nextInt(), sc.next(), sc.nextInt());
            list.add(a);
        }
        return list;
    }


    public void showStudents(){
        StringBuilder str = new StringBuilder();
        for (Student st : students) {
            str.append(st.toString()).append("\n");
        }
        allStudents.setText(str.toString());
    }

    public static void main(String[] args) {
        Window window = new Window();
        window.pack();
        window.setVisible(true);
    }

}
