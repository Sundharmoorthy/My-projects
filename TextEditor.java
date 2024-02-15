import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TextEditor implements ActionListener {
    //decalring properties of a text editor
    JFrame frame;

    //declaring menubar
    JMenuBar menuBar;

    //decalring Menu for items
    JMenu file, edit;

    //declaring jmenuitems
    JMenuItem newfile, openfile, savefile, close;
    JMenuItem cut, copy, paste, selectall;

    //TextArea
    JTextArea textarea;

    TextEditor() {
        //initialize frame
        frame = new JFrame();
        //intialise text area
        textarea = new JTextArea();
        //initliase jmenubar
        menuBar = new JMenuBar();
        //intitliase jmenu file and edit
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //assinging jmenuitems and adding it to file Jmenu
        newfile = new JMenuItem("New Window");
        openfile = new JMenuItem("Open File");
        savefile = new JMenuItem("Save File");
        close = new JMenuItem("Close");
        //before adding it to th file menu we will add action listerner
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        close.addActionListener(this);

        //adding it to file menu
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);
        file.add(close);

        //assinging Jmenu itemts for edut and adding it to edit jmenu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectall = new JMenuItem("Select All");
        //add action listernes before adding it into edit
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);

        //add it to the edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);

        // adding it to menubar
        menuBar.add(file);
        menuBar.add(edit);

        //set menubar into a frame
        frame.setJMenuBar(menuBar);
        //creating Jpanel to add scrolling
        //importing panel class
        JPanel jPanel = new JPanel();
        //setting borders

        jPanel.setBorder(new EmptyBorder(7,7,7,7));
        jPanel.setLayout(new BorderLayout(0,0));
        //jPanel.setBackground(Color.lightGray);
        jPanel.add(textarea,BorderLayout.CENTER);

        //setting scroll for the textarea
        JScrollPane scrollPane = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanel.add(scrollPane);
        //adding it to frame
        frame.add(jPanel);
        //setting frame dimensions
        frame.setBounds(100, 100, 400, 400);
        frame.setBackground(Color.blue);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            //perform cut opertaion
            textarea.cut();
        }
        if (actionEvent.getSource() == copy) {
            //perform copy operation
            textarea.copy();
        }
        if (actionEvent.getSource() == paste) {
            //perform paste operation
            textarea.paste();
        }
        if (actionEvent.getSource() == selectall) {
            //perform select all operation
            textarea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            //exit the text editor
            System.exit(0);
        }
        if (actionEvent.getSource() == openfile) {
            //creating file chooser from Jfilechooser class
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we click on open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //select the file
                File file = fileChooser.getSelectedFile();
                // and get the path of the file
                String filePath = file.getPath();

                try {
                    //get the reader that read the filepath
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    //and read that using a bufferreader read it from that file and copy and paste it in text area
                    String intermediate = "";
                    StringBuilder output = new StringBuilder();

                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output.append(intermediate).append("\n");
                    }

                    textarea.setText(output.toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if (actionEvent.getSource() == savefile) {
            JFileChooser fileChooser = new JFileChooser("C:");

            int chooseOption = fileChooser.showSaveDialog(null);
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //get the file location
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                //initialize file writer
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    //initilize bufferwriter
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    textarea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newfile){
            TextEditor newtexteditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor texteditor = new TextEditor();
    }
}