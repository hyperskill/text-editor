// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++ and C#: http://www.viva64.com
package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextEditor extends JFrame {
    private JTextArea textArea;
    private JTextField fileName;
    private JButton save;
    private JButton load;
    private JPanel main;
    private JScrollPane textScroll;

    public TextEditor() {
        setupMainWindowParameters();
        createWidgets();
        setupStyle();
        setupLayout();
        setupActions();
    }

    private void setupActions() {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                File file = new File(fileName.getText());
                try {
                    PrintWriter printWriter = new PrintWriter(file);
                    printWriter.println(textArea.getText());
                    printWriter.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                byte[] encoded = null;
                try {
                    encoded = Files.readAllBytes(Paths.get(fileName.getText()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                textArea.setText(new String(encoded, StandardCharsets.UTF_8));
            }
        });
    }

    private void setupMainWindowParameters() {
        setTitle("Text Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setupLayout() {
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(fileName);
        buttons.add(Box.createRigidArea(new Dimension(12, 0)));
        buttons.add(save);
        buttons.add(Box.createRigidArea(new Dimension(12, 0)));
        buttons.add(load);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
        textPanel.add(textScroll);

        main.add(buttons);
        main.add(Box.createRigidArea(new Dimension(0, 12)));
        main.add(textPanel);

        add(main);
        pack();
    }

    private void createWidgets() {
        main = new JPanel();
        textArea = new JTextArea(20, 10);
        fileName = new JTextField(30);
        save = new JButton("Save");
        load = new JButton("Load");
        textScroll = new JScrollPane(textArea);
    }

    private void setupStyle() {
        main.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        Font font = textArea.getFont();
        float size = font.getSize() + 2.0f;
        textArea.setFont(font.deriveFont(size));

        textScroll.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        textScroll.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }
}
