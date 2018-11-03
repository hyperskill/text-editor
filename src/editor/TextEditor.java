import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;



public class TextEditor extends JFrame {
    private JTextField input = new JTextField("", 5);
    private JButton loadbutton = new JButton("Load");
    private JButton savebutton = new JButton("Save");
    private JTextArea textArea = new JTextArea();
    private String filename = null;
    private  JMenuBar MenuBar;
    private JPanel container;




    public TextEditor(){

     super("Text Editor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          this.textArea.setLineWrap(true);
          this.textArea.setWrapStyleWord(true);
          JScrollPane scrollPane = new JScrollPane(textArea);

         input.addActionListener(e -> {
        this.filename = input.getText();
    });
        loadbutton.addActionListener(e -> {
       loadFile();

    });
        savebutton.addActionListener(e -> {
      saveFile();
    });


        conteiner();

        createMenu();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(container, BorderLayout.PAGE_START);
        panel.add(scrollPane, BorderLayout.CENTER);

        this.setMinimumSize(new Dimension(350,110));

        this.getContentPane().add(panel);

        this.setJMenuBar(MenuBar);
        this.setPreferredSize(new Dimension(350, 400));

        this.pack();



    }

    private void conteiner() {
        this.container = new JPanel();

        GridLayout gridLayout = new GridLayout(1,3,5,15);
        container.setLayout(gridLayout);

        container.add(input);

        container.add(loadbutton);
        container.add(savebutton);
        container.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    private void createMenu() {
        this.MenuBar = new JMenuBar();

        JMenu menufile = new JMenu("File");
        MenuBar.add(menufile);
        JMenuItem save = new JMenuItem("Save");
        menufile.add(save);
        JMenuItem load = new JMenuItem("Load");
        menufile.add(load);
        JMenuItem exit = new JMenuItem("Close");
        menufile.add(exit);


        save.addActionListener(e -> {
            saveFile();

        });
        load.addActionListener(e -> {
            loadFile();
        });
        exit.addActionListener(e -> {
            this.dispose();
        });

    }

    private void loadFile() {
        File file = new File(this.filename.toLowerCase());
        try {
            Scanner scanner = new Scanner(file);
            String text = "";
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                text=text+ line+ "\n";
            }
            textArea.setText(text);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    private void saveFile() {
        File file1 = new File(this.filename);
        String alltext = this.textArea.getText();
        String [] strings = alltext.split("\n");
        try {
            FileWriter writer = new FileWriter(file1);
            for(int i = 0; i < strings.length; i ++){
                writer.write(strings[i]);
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

