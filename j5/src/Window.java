import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {
    private Series series;
    Window(String str){
        super(str);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        JTextField firstF = new JTextField("First element");
        add(firstF);
        JTextField deltaF = new JTextField("Delta");
        add(deltaF);
        JTextField numF = new JTextField("Number of elements");
        add(numF);
        JRadioButton rb1 = new JRadioButton("Linear");
        rb1.setSelected(true);
        add(rb1);
        JRadioButton rb2 = new JRadioButton("Exponential");
        add(rb2);
        ButtonGroup group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);
        JButton createButton = new JButton("Create");
        add(createButton);
        JButton writeButton = new JButton("Write");
        add(writeButton);
        writeButton.setEnabled(false);
        JTextField seriesF = new JTextField("Sum of series     ");
        add(seriesF);
        seriesF.setEditable(false);
        JTextField fileF = new JTextField("Enter file name");
        add(fileF);
        fileF.setEditable(false);
        JButton saveButton = new JButton("Save to file");
        add(saveButton);
        saveButton.setEnabled(false);
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    double first = Double.parseDouble(firstF.getText());
                    double delta = Double.parseDouble(deltaF.getText());
                    int num = Integer.parseInt(numF.getText());
                    if(num <= 0){
                        throw new NumberFormatException();
                    }
                    if(rb1.isSelected()){
                        series = new Linear(first, delta, num);
                    }else{
                        series = new Exponential(first, delta, num);
                    }
                    writeButton.setEnabled(true);
                    saveButton.setEnabled(true);
                    fileF.setEditable(true);
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(Window.this, "Incorrect input");
                }
            }
        });
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //seriesF.setEditable(true);
                seriesF.setText(series.toString());
               // seriesF.setEditable(false);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    File file = new File(fileF.getText());
                    series.saveToFile(file);
                    JOptionPane.showMessageDialog(Window.this, "Saved to file!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(Window.this, "File not found!");
                }
            }
        });

        firstF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                firstF.setText("");
            }
        });

        deltaF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deltaF.setText("");
            }
        });

        numF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                numF.setText("");
            }
        });

        fileF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fileF.setText("");
            }
        });
    }
    public static void main(String[] args) {
        Window window = new Window("Series");
        window.pack();
        window.setVisible(true);
    }
}