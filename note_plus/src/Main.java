import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

public class Main extends JFrame {

    JTextArea mainArea;
    JMenuBar mainBar;
    JMenu fileMenu, editMenu, formatMenu, viewMenu;
    JMenuItem newFile, openFile, saveFile, saveAsFile, exitFile;
    JMenuItem cutFile, copyFile, pasteFile, fontColor;
    JMenuItem about;
    JCheckBoxMenuItem wordWrap;
    String fileName;
    JFileChooser chooser;
    String fileContent;
    UndoManager undo;
    UndoAction undoAction;
    RedoAction redoAction;

    private String programName = " Notes + ";

    public String getProgramName() {
        return programName;
    }

    public Main() {
        intitComponentes();

//        Or we can use the new feature of java that is lambda
//        openFile.addActionListener(e->open());
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open();
            }
        });
        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFile();
            }
        });
        exitFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        copyFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainArea.copy();
            }
        });
        cutFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainArea.cut();
            }
        });
        pasteFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainArea.paste();
            }
        });


        mainArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undo.addEdit(e.getEdit());
                undoAction.update();
                redoAction.update();
            }
        });

        wordWrap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wordWrap.isSelected()) {
                    mainArea.setLineWrap(true);
                    mainArea.setWrapStyleWord(true);
                } else {
                    mainArea.setLineWrap(false);
                    mainArea.setWrapStyleWord(false);
                }
            }
        });

        fontColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color colorChooser = JColorChooser.showDialog(rootPane, "Choose Font Color ", Color.black);
                mainArea.setForeground(colorChooser);
            }
        });


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
                dispose();
                System.exit(0);
            }
        });

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane, "Group Members \n\t1.Kirubel Solomon                NSR/6880/07\n" +
                        "2.Kiya Tolcha                         NSR/3408/06\n" +
                        "3.Nathnael Tesfaye             NSR/3870/06\n" +
                        "4.Rabra Hierpa                     NSR/4570/07");
            }
        });
        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        saveAsFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAs();
            }
        });
    }


    private void intitComponentes() {
        chooser = new JFileChooser(".");
        mainArea = new JTextArea();
        undo = new UndoManager();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        getContentPane().add(mainArea);
        getContentPane().add(new JScrollPane(mainArea), BorderLayout.CENTER);
        setTitle("Untitled " + getProgramName());
        setLocation(400, 100);
        setSize(700, 500);
        //Menu bar initialization
        mainBar = new JMenuBar();
        //Menu initialization
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        viewMenu = new JMenu("View");
        formatMenu = new JMenu("Format");
        //File Menu
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save ");
        saveAsFile = new JMenuItem("Save As ");
        exitFile = new JMenuItem("Exit");
        //Edit Menu
        cutFile = new JMenuItem("Cut");
        copyFile = new JMenuItem("Copy");
        pasteFile = new JMenuItem("Paste");
        // Format Menu
        wordWrap = new JCheckBoxMenuItem("Word Wrap");
        fontColor = new JMenuItem("Font Color");
        //View Menu
        about = new JMenuItem("About ");
        viewMenu.add(about);
        //adding shortcuts to the menu items
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        saveAsFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));
        cutFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        copyFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        pasteFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        // Add menu items in the file menu
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAsFile);
        fileMenu.addSeparator();
        fileMenu.add(exitFile);
        //Add menu items to the edit menu
        editMenu.add(undoAction);
        editMenu.add(redoAction);
        editMenu.addSeparator();
        editMenu.add(cutFile);
        editMenu.add(copyFile);
        editMenu.add(pasteFile);
        // Add menu items to the format menu
        formatMenu.add(wordWrap);
        formatMenu.addSeparator();
        formatMenu.add(fontColor);
        // Add fileMenu to the main menu bar
        mainBar.add(fileMenu);
        mainBar.add(editMenu);
        mainBar.add(formatMenu);
        mainBar.add(viewMenu);

        // activate the main menu bar
        setJMenuBar(mainBar);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void save() {
        PrintWriter fileOut = null;
        try {
            if (fileName == null) {
                saveAs();
            } else {
                fileOut = new PrintWriter(new FileWriter(fileName));
                String file = mainArea.getText();
                StringTokenizer stringTokenizer = new StringTokenizer(file, System.getProperty("line.separator"));
                while (stringTokenizer.hasMoreElements()) {
                    fileOut.println(stringTokenizer.nextToken());
                }
                JOptionPane.showMessageDialog(rootPane, "File Saved!");
                fileContent = mainArea.getText();
            }

        } catch (IOException ext) {
            JOptionPane.showMessageDialog(rootPane, "Error occurred while saving the file");
        } finally {
            if (fileOut != null)
                fileOut.close();
        }
    }

    private void saveAs() {
        PrintWriter fileOut = null;
        int returnValue;
        try {
            returnValue = chooser.showSaveDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                fileOut = new PrintWriter(new FileWriter(chooser.getSelectedFile()));
                String file = mainArea.getText();
                StringTokenizer stringTokenizer = new StringTokenizer(file, System.getProperty("line.separator"));
                while (stringTokenizer.hasMoreElements()) {
                    fileOut.println(stringTokenizer.nextToken());
                }
                JOptionPane.showMessageDialog(rootPane, "File Saved!");
                fileName = chooser.getSelectedFile().getName();
                fileContent = mainArea.getText();
                setTitle(fileName = chooser.getSelectedFile().getName());
            } else if (returnValue == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(rootPane, "No file Saved!");
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        } finally {
            if (fileOut != null)
                fileOut.close();
        }
    }

    private void open() {
        try {
            int returnValue = chooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                mainArea.setText(null);
                Reader fileReader = new FileReader(chooser.getSelectedFile());
                char[] bufferReader = new char[1_00_000_000];
                int newChar;
                while ((newChar = fileReader.read(bufferReader, 0, bufferReader.length)) != -1) {
                    mainArea.append(new String(bufferReader, 0, newChar));
                }
            }
            fileName = chooser.getSelectedFile().getName();
            setTitle(fileName = chooser.getSelectedFile().getName());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "No file was opened");
        }
    }

    private void newFile() {
        if (!mainArea.getText().equals("") && !mainArea.getText().equals(fileContent)) {
            if (fileName == null) {
                int userAction = JOptionPane.showConfirmDialog(rootPane, "Do you want to save your changes ?");
                if (userAction == 0) {
                    saveAs();
                    clearMainArea();
                } else if (userAction == 2) {
                    System.out.print("");
                } else {
                    clearMainArea();
                }
            } else {
                int userAction = JOptionPane.showConfirmDialog(rootPane, "Do you want to save your changes ?");
                if (userAction == 0) {
                    save();
                    clearMainArea();
                } else if (userAction == 2) {
                    System.out.print("");
                } else {
                    clearMainArea();
                }
            }
        } else {
            clearMainArea();
        }
    }

    private void clearMainArea() {
        mainArea.setText("");
        setTitle("Untitled -" + getProgramName());
        fileName = null;
        fileContent = null;
    }

    private void exit() {
        if (!mainArea.getText().equals("") && !mainArea.getText().equals(fileContent)) {
            if (fileName == null) {
                int userAction = JOptionPane.showConfirmDialog(rootPane, "Do you want to save your changes ?");
                if (userAction == 0) {
                    saveAs();
                    dispose();
                    System.exit(0);
                } else if (userAction == 2) {
                    System.out.print("");
                } else {
                    dispose();
                    System.exit(0);
                }
            } else {
                int userAction = JOptionPane.showConfirmDialog(rootPane, "Do you want to save your changes ?");
                if (userAction == 0) {
                    save();
                    dispose();
                    System.exit(0);
                } else if (userAction == 2) {
                    System.out.print("");
                } else {
                    dispose();
                    System.exit(0);
                }
            }
        } else {
            dispose();
            System.exit(0);
        }
    }

    class UndoAction extends AbstractAction {

        public UndoAction() {
            super("Undo");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                undo.undo();
            } catch (CannotUndoException exp) {
                JOptionPane.showMessageDialog(rootPane, "No more undo can be done!");
            }
            update();
            redoAction.update();
        }

        protected void update() {
            if (undo.canUndo()) {
                setEnabled(true);
                putValue(Action.NAME, "Undo");
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Undo");
            }
        }
    }

    class RedoAction extends AbstractAction {

        public RedoAction() {
            super("Redo");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                undo.redo();
            } catch (CannotUndoException exp) {
                JOptionPane.showMessageDialog(rootPane, "No more redo can be done!");
            }
            update();
            redoAction.update();
        }

        protected void update() {
            if (undo.canRedo()) {
                setEnabled(true);
                putValue(Action.NAME, "Redo");
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
