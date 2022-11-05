import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {
    JPanel panel;
    JRadioButton red, blue, yellow;
    JButton saveButton, openButton;
    DrawPanel drawPanel;
    Frame(String str){
        super(str);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new JPanel();
        drawPanel = new DrawPanel();
        addButtonsAndPanels();
        chooseColor();
        JScrollPane scrollPane = new JScrollPane(drawPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
        scrollPane.createVerticalScrollBar();
        scrollPane.createHorizontalScrollBar();
        scrollPane.setPreferredSize(new Dimension(500,600));
        drawPanel.setPreferredSize(new Dimension(drawPanel.image.getWidth(), drawPanel.image.getHeight()));
        add(scrollPane);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                try{
                    ImageIO.write(drawPanel.image, "png", fileChooser.getSelectedFile());
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }}
        });

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                try{
                    drawPanel.image = ImageIO.read(fileChooser.getSelectedFile());
                    setPreferredSize(new Dimension(drawPanel.image.getWidth(), drawPanel.image.getHeight()));
                    drawPanel.graphics = drawPanel.image.createGraphics();
                    drawPanel.setColor(drawPanel.color);
                    repaint();

                }catch (IOException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void addButtonsAndPanels(){
        ButtonGroup colors = new ButtonGroup();
        yellow = new JRadioButton("Yellow");
        red = new JRadioButton("Red");
        blue = new JRadioButton("Blue");
        saveButton = new JButton("Save");
        openButton = new JButton("Open");
        colors.add(yellow);
        colors.add(red);
        colors.add(blue);
        yellow.setSelected(true);

        panel.add(yellow);
        panel.add(red);
        panel.add(blue);
        panel.add(saveButton);
        panel.add(openButton);

        add(panel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
    }

    private void chooseColor(){

        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.setColor(Color.blue);
            }
        });

        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.setColor(Color.red);
            }
        });
        yellow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.setColor(Color.yellow);
            }
        });

    }

    public static void main(String[] args) {
        Frame fr = new Frame("Painter");
        fr.pack();
        fr.setSize(new Dimension(700,800));
        fr.setVisible(true);
    }
}
