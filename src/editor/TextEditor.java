package editor;

import javax.swing.*;

public class TextEditor extends JFrame {
    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
 setLayout(null);
        setTitle("Text Editor");
        createTextArea();
        setSize(new Dimension(350, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    private void createTextArea() {
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPanepane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        Container pane = getContentPane();
        GroupLayout groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);
        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.getAutoCreateGaps();

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addComponent(scrollPanepane));
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(scrollPanepane));

        pack();
    }
}
