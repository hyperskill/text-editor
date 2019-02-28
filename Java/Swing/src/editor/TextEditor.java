package editor;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextEditor extends JFrame {
    private JTextArea textArea;
    public TextEditor() {
        setupMainWindowParameters();
        createWidgets();
        setupStyle();
        setupLayout();
    }

    private void setupMainWindowParameters() {
        setTitle("The first stage");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(375, 375);
        setVisible(true);
    }

    private void setupLayout() {
        add(textArea);
    }

    private void createWidgets() {
        textArea = new JTextArea();
    }

    private void setupStyle() {
        /***************************TextArea**************************************/
        Border border = BorderFactory.
                createMatteBorder(32, 32,32,32,
                        new Color(237,237,237));
        textArea.setBorder(border);
        Font font = textArea.getFont();
        float size = font.getSize() + 2.0f;
        textArea.setFont( font.deriveFont(size) );
        /***************************TextArea**************************************/
    }
}