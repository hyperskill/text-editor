// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++ and C#: http://www.viva64.com
package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    private JPanel buttons;
    private JScrollPane textScroll;
    private AbstractAction saveAction;
    private AbstractAction loadAction;
    private AbstractAction exitAction;
    private JMenuBar menuBar;
    private JMenuItem loadMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem exitMenuItem;

    public TextEditor() {
        createWidgets();
        createActions();
        setupLayout();
        setupActions();
        setupMenu();
        setupStyle();
        setupMainWindowParameters();
    }

    private void setupMenu() {
        JMenu file = new JMenu("File");

        file.add(loadMenuItem);
        file.add(saveMenuItem);

        file.addSeparator();

        file.add(exitMenuItem);

        menuBar.add(file);
    }

    private void createActions() {
        saveAction = new AbstractAction() {
            {
                putValue(NAME, "Save");
            }
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
        };

        loadAction = new AbstractAction() {
            {
                putValue(NAME, "Load");
            }
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
        };

        exitAction = new AbstractAction() {
            {
                putValue(NAME, "Exit");
            }
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose();
            }
        };
    }

    private void setupActions() {
        save.addActionListener(saveAction);
        load.addActionListener(loadAction);

        saveMenuItem.setAction(saveAction);
        loadMenuItem.setAction(loadAction);
        exitMenuItem.setAction(exitAction);
    }

    private void setupMainWindowParameters() {
        setTitle("Text Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        setSize(400,400);
        setVisible(true);
    }

    private void setupLayout() {
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

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
    }

    private void createWidgets() {
        main = new JPanel();
        buttons = new JPanel();
        textArea = new JTextArea(10, 10);
        fileName = new JTextField(30);
        save = new JButton("Save");
        load = new JButton("Load");
        textScroll = new JScrollPane(textArea);
        menuBar = new JMenuBar();
        loadMenuItem = new JMenuItem();
        saveMenuItem = new JMenuItem();
        exitMenuItem = new JMenuItem();
    }

    private void setupStyle() {
        main.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        Font font = textArea.getFont();
        float size = font.getSize() + 2.0f;
        textArea.setFont(font.deriveFont(size));

        Dimension maxSize = fileName.getPreferredSize();
        fileName.setMaximumSize(maxSize);

        buttons.add(Box.createGlue());
        buttons.setMaximumSize(new Dimension(buttons.getMaximumSize().width,
                buttons.getPreferredSize().height));
    }
}
