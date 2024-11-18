import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordCounter extends JFrame {
    private JTextArea textArea;
    private JLabel resultLabel;
    private JButton countButton;

    public WordCounter() {
        // Set up the frame
        setTitle("Word Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Start in fullscreen
        getContentPane().setBackground(new Color(230, 230, 250)); // Lavender background

        // Create and set up the text area
        textArea = createTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER); // Add scroll pane

        // Create and set up the result label
        resultLabel = createResultLabel();
        add(resultLabel, BorderLayout.NORTH);

        // Create and set up the count button
        countButton = createCountButton();
        add(countButton, BorderLayout.SOUTH);

        // Add a DocumentListener to the text area to show "Typing..." while typing
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                resultLabel.setText("Typing...");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                resultLabel.setText("Typing...");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                resultLabel.setText("Typing...");
            }
        });

        // Set initial message
        resultLabel.setText("Word Count: 0");
    }

    // Method to count words
    private int countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    // Method to update the word count and refresh the label
    private void updateWordCount() {
        String text = textArea.getText();
        int wordCount = countWords(text);
        resultLabel.setText("Word Count: " + wordCount);
    }

    // Method to create and configure the text area
    private JTextArea createTextArea() {
        JTextArea area = new JTextArea();
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBackground(Color.WHITE); // White background
        area.setForeground(Color.BLACK); // Black text
        area.setFont(new Font("Arial", Font.PLAIN, 18));
        area.setMargin(new Insets(10, 10, 10, 10)); // Add some padding
        return area;
    }

    // Method to create and configure the result label
    private JLabel createResultLabel() {
        JLabel label = new JLabel("Word Count: 0", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(new Color(0, 128, 0)); // Forest green text
        label.setPreferredSize(new Dimension(getWidth(), 50)); // Make it span the width of the frame
        return label;
    }

    // Method to create and configure the count button
    private JButton createCountButton() {
        JButton button = new JButton("Count Words");
        button.setBackground(new Color(70, 130, 180)); // Steel blue button
        button.setForeground(Color.WHITE); // White text
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setFocusPainted(false);
        button.setBorderPainted(false); // No border for a cleaner look
        button.setPreferredSize(new Dimension(200, 50)); // Fixed button size
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWordCount(); // Update word count when button is clicked
            }
        });
        return button;
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordCounter wordCounter = new WordCounter();
            wordCounter.setVisible(true);
        });
    }
}
