package notepad;
import javax.swing.*;
import java.awt.*;

public class Notepad extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;

    public Notepad() {
        // Set up frame
        setTitle("Notepad");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create text area
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        // Create file menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        
        // Add file menu items
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);
        JMenuItem saveAsItem = new JMenuItem("Save As");
        fileMenu.add(saveAsItem);
        
        // Set up menu item actions
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile(false));
        saveAsItem.addActionListener(e -> saveFile(true));
        
        setLocationRelativeTo(null);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File file = fileChooser.getSelectedFile();
                java.nio.file.Files.readAllLines(file.toPath()).forEach(line -> textArea.append(line + "\n"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error opening file.");
            }
        }
    }

    private void saveFile(boolean saveAs) {
        JFileChooser fileChooser = new JFileChooser();
        int option = saveAs ? fileChooser.showSaveDialog(this) : JFileChooser.APPROVE_OPTION;
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File file = fileChooser.getSelectedFile();
                java.nio.file.Files.write(file.toPath(), textArea.getText().getBytes());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving file.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Notepad notepad = new Notepad();
            notepad.setVisible(true);
        });
    }
}
