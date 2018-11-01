package editor;

import javax.swing.*;

public class TextEditor extends JFrame {
  private JTextField input = new JTextField("", 5);
    private JButton loadbutton = new JButton("Load");
    private JButton savebutton = new JButton("Save");
    private JTextArea textArea = new JTextArea();
    private String filename = null;



    public TextEditor() {

        //устанавлиаем параметры основного окна
        super("Text Editor");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //устанавливаем размеры компонентов

        this.input.setSize(250, 30);
        this.input.setLocation(20, 30);
        this.loadbutton.setBounds(260, 30, 100, 30);
        this.savebutton.setBounds(360, 30, 100, 30);

        //TextArea

        this.textArea.setLineWrap(true);
        this.textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 60, 440, 280);

        //устанавливаем слушатель на кнопки и поля

        input.addActionListener(e -> {
            this.filename = input.getText();
        });
        loadbutton.addActionListener(e -> {
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
        });
        savebutton.addActionListener(e -> {

            File file = new File(this.filename);
           String alltext = this.textArea.getText();
           String [] strings = alltext.split("\n");

            try {
                FileWriter writer = new FileWriter(file);
                for(int i = 0; i < strings.length; i ++){
                    writer.write(strings[i]);
                    writer.write("\n");
                }

                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        });

        //складываем все в контейнер
        Container container = this.getContentPane();
        container.add(savebutton);
        container.add(loadbutton);
        container.add(input);

        this.getContentPane().add(scrollPane);
        container.setLayout(new BorderLayout());

    }
}

