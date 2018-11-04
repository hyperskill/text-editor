package editor;

import javax.swing.*;

//A simple JTextArea component

public class TextEditor extends JFrame {
    //constructor - initializing a JTextArea component 
    public TextEditor() {
        JTextArea area = new JTextArea(); //constructor of the JTextArea component.
        JScrollPane spane = new JScrollPane(area); //make the text scrollable

        area.setLineWrap(true); //makes the lines wrapped if they are too long to fit the text area's width.
        area.setWrapStyleWord(true); //lines will be wrapped at word boundaries—white spaces.

        //createLayout(spane);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
        setTitle("JTextEditor");
    }
    
    
}