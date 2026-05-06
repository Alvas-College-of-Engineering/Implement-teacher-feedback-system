import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * FeedbackUI Class - Main GUI application for Teacher Feedback System
 * 
 * This class creates the main window with input fields for feedback submission
 * and displays feedback summaries with average ratings.
 * 
 * Features:
 * - Submit teacher feedback with validation
 * - View feedback summary with average ratings
 * - Display all feedback in a table
 */
public class FeedbackUI extends JFrame {
    // GUI Components
    private JTextField teacherNameField;
    private JTextField subjectField;
    private JComboBox<Integer> ratingCombo;
    private JTextArea commentArea;
    private JButton submitButton;
    private JButton viewSummaryButton;
    private JLabel statusLabel;

    // Manager for feedback operations
    private FeedbackManager feedbackManager;

    /**
     * Constructor - Initialize the GUI and FeedbackManager
     */
    public FeedbackUI() {
        feedbackManager = new FeedbackManager();
        
        // Set up the main frame
        setTitle("Teacher Feedback System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Initialize and add components
        initializeComponents();
        loadFeedbackData();
    }

    /**
     * Initialize all GUI components
     */
    private void initializeComponents() {
        // Create main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(245, 245, 245));
        JLabel titleLabel = new JLabel("Teacher Feedback Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 15));
        inputPanel.setBackground(new Color(245, 245, 245));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Teacher Name Field
        JLabel teacherLabel = new JLabel("Teacher Name *");
        teacherLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        teacherNameField = new JTextField();
        teacherNameField.setFont(new Font("Arial", Font.PLAIN, 12));
        inputPanel.add(teacherLabel);
        inputPanel.add(teacherNameField);

        // Subject Field
        JLabel subjectLabel = new JLabel("Subject *");
        subjectLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subjectField = new JTextField();
        subjectField.setFont(new Font("Arial", Font.PLAIN, 12));
        inputPanel.add(subjectLabel);
        inputPanel.add(subjectField);

        // Rating Dropdown
        JLabel ratingLabel = new JLabel("Rating (1-5) *");
        ratingLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        Integer[] ratings = {1, 2, 3, 4, 5};
        ratingCombo = new JComboBox<>(ratings);
        ratingCombo.setFont(new Font("Arial", Font.PLAIN, 12));
        ratingCombo.setSelectedIndex(2); // Default to 3
        inputPanel.add(ratingLabel);
        inputPanel.add(ratingCombo);

        // Placeholder label for alignment
        JLabel spacerLabel = new JLabel("");
        inputPanel.add(spacerLabel);
        inputPanel.add(new JLabel(""));

        mainPanel.add(inputPanel);

        // Comments Panel
        JLabel commentsLabel = new JLabel("Comments *");
        commentsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        mainPanel.add(commentsLabel);

        commentArea = new JTextArea(6, 35);
        commentArea.setFont(new Font("Arial", Font.PLAIN, 11));
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(commentArea);
        mainPanel.add(scrollPane);

        // Status Label
        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        statusLabel.setForeground(new Color(100, 100, 100));
        mainPanel.add(statusLabel);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));

        submitButton = new JButton("Submit Feedback");
        submitButton.setFont(new Font("Arial", Font.BOLD, 12));
        submitButton.setPreferredSize(new Dimension(150, 40));
        submitButton.setBackground(new Color(52, 168, 83));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorderPainted(false);
        submitButton.setFocusPainted(false);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(this::submitFeedback);

        viewSummaryButton = new JButton("View Summary");
        viewSummaryButton.setFont(new Font("Arial", Font.BOLD, 12));
        viewSummaryButton.setPreferredSize(new Dimension(150, 40));
        viewSummaryButton.setBackground(new Color(33, 150, 243));
        viewSummaryButton.setForeground(Color.WHITE);
        viewSummaryButton.setBorderPainted(false);
        viewSummaryButton.setFocusPainted(false);
        viewSummaryButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewSummaryButton.addActionListener(this::viewSummary);

        buttonPanel.add(submitButton);
        buttonPanel.add(viewSummaryButton);

        mainPanel.add(buttonPanel);

        // Add main panel to frame
        add(new JScrollPane(mainPanel));
        setVisible(true);
    }

    /**
     * Load feedback data from file on startup
     */
    private void loadFeedbackData() {
        feedbackManager.loadFeedback();
        if (feedbackManager.getFeedbackCount() > 0) {
            statusLabel.setText("Loaded " + feedbackManager.getFeedbackCount() + " existing feedback entries.");
        } else {
            statusLabel.setText("No previous feedback found. Ready to submit new feedback.");
        }
    }

    /**
     * Validate input fields
     * 
     * @return true if all required fields are filled, false otherwise
     */
    private boolean validateInput() {
        if (teacherNameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter Teacher Name", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (subjectField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter Subject", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (commentArea.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter your Comments", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Handle submit feedback button click
     */
    private void submitFeedback(ActionEvent e) {
        // Validate input
        if (!validateInput()) {
            return;
        }

        try {
            // Create Feedback object
            String teacherName = teacherNameField.getText().trim();
            String subject = subjectField.getText().trim();
            int rating = (Integer) ratingCombo.getSelectedItem();
            String comment = commentArea.getText().trim();

            Feedback feedback = new Feedback(teacherName, subject, rating, comment);

            // Save to file
            if (feedbackManager.saveToFile(feedback)) {
                // Add to in-memory list
                feedbackManager.addFeedback(feedback);

                // Show success message
                JOptionPane.showMessageDialog(this,
                    "Feedback submitted successfully!\n\n" +
                    "Teacher: " + teacherName + "\n" +
                    "Subject: " + subject + "\n" +
                    "Rating: " + rating + "/5",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

                // Clear fields
                clearFields();
                statusLabel.setText("Feedback saved successfully!");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Error saving feedback. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "An error occurred: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    /**
     * Clear all input fields
     */
    private void clearFields() {
        teacherNameField.setText("");
        subjectField.setText("");
        ratingCombo.setSelectedIndex(2); // Reset to 3
        commentArea.setText("");
        teacherNameField.requestFocus();
    }

    /**
     * Handle view summary button click
     */
    private void viewSummary(ActionEvent e) {
        try {
            // Reload feedback to ensure latest data
            feedbackManager.clearMemory();
            feedbackManager.loadFeedback();

            ArrayList<Feedback> allFeedback = feedbackManager.getAllFeedback();

            if (allFeedback.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "No feedback has been submitted yet.",
                    "Summary",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Get average ratings
            Map<String, Double> averageRatings = feedbackManager.getAverageRatings();

            // Create summary window
            JFrame summaryFrame = new JFrame("Feedback Summary");
            summaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            summaryFrame.setSize(700, 600);
            summaryFrame.setLocationRelativeTo(this);

            JPanel summaryPanel = new JPanel(new BorderLayout(10, 10));
            summaryPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            summaryPanel.setBackground(new Color(245, 245, 245));

            // Title
            JLabel titleLabel = new JLabel("Feedback Summary & Statistics");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
            summaryPanel.add(titleLabel, BorderLayout.NORTH);

            // Tabbed pane for different views
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.setBackground(new Color(245, 245, 245));

            // Tab 1: Average Ratings
            JPanel ratingsPanel = createAverageRatingsPanel(averageRatings);
            tabbedPane.addTab("Average Ratings", ratingsPanel);

            // Tab 2: All Feedback Table
            JPanel tablePanel = createFeedbackTablePanel(allFeedback);
            tabbedPane.addTab("All Feedback", tablePanel);

            summaryPanel.add(tabbedPane, BorderLayout.CENTER);

            // Summary statistics
            JPanel statsPanel = new JPanel();
            statsPanel.setBackground(new Color(245, 245, 245));
            statsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel statsLabel = new JLabel("Total Feedback Entries: " + allFeedback.size() + 
                                          " | Teachers: " + averageRatings.size());
            statsLabel.setFont(new Font("Arial", Font.PLAIN, 11));
            statsPanel.add(statsLabel);
            summaryPanel.add(statsPanel, BorderLayout.SOUTH);

            summaryFrame.add(summaryPanel);
            summaryFrame.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error loading feedback: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    /**
     * Create panel displaying average ratings per teacher
     * 
     * @param averageRatings Map of teacher names and ratings
     * @return JPanel with formatted summary
     */
    private JPanel createAverageRatingsPanel(Map<String, Double> averageRatings) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextArea summaryText = new JTextArea();
        summaryText.setFont(new Font("Courier New", Font.PLAIN, 12));
        summaryText.setEditable(false);
        summaryText.setBackground(Color.WHITE);
        summaryText.setForeground(new Color(33, 33, 33));

        // Build summary using StringBuilder
        StringBuilder summary = new StringBuilder();
        summary.append("AVERAGE RATINGS BY TEACHER\n");
        summary.append("=".repeat(50)).append("\n\n");

        // Sort by average rating (descending)
        averageRatings.entrySet().stream()
            .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
            .forEach(entry -> {
                String teacher = entry.getKey();
                double rating = entry.getValue();
                String stars = getStarRating(rating);
                summary.append(String.format("%-25s >> %.1f * %s\n", teacher, rating, stars));
            });

        summary.append("\n").append("=".repeat(50));
        summary.append("\nScale: 1 (Poor) - 5 (Excellent)");

        summaryText.setText(summary.toString());

        JScrollPane scrollPane = new JScrollPane(summaryText);
        panel.add(scrollPane);

        return panel;
    }

    /**
     * Create panel displaying all feedback entries in table format
     * 
     * @param allFeedback List of all feedback entries
     * @return JPanel with table
     */
    private JPanel createFeedbackTablePanel(ArrayList<Feedback> allFeedback) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Column names
        String[] columnNames = {"Teacher", "Subject", "Rating", "Comments"};

        // Data for table
        Object[][] data = new Object[allFeedback.size()][4];
        for (int i = 0; i < allFeedback.size(); i++) {
            Feedback fb = allFeedback.get(i);
            data[i][0] = fb.getTeacherName();
            data[i][1] = fb.getSubject();
            data[i][2] = fb.getRating() + "/5";
            // Truncate long comments
            String comment = fb.getComment();
            data[i][3] = comment.length() > 50 ? comment.substring(0, 47) + "..." : comment;
        }

        // Create table with custom model (read-only)
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 11));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(33, 150, 243));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(33, 150, 243));
        table.setSelectionForeground(Color.WHITE);

        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(280);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Convert numeric rating to star representation
     * 
     * @param rating Numeric rating (1-5)
     * @return String representation with stars
     */
    private String getStarRating(double rating) {
        if (rating >= 4.5) return "[★★★★★]";
        if (rating >= 3.5) return "[★★★★☆]";
        if (rating >= 2.5) return "[★★★☆☆]";
        if (rating >= 1.5) return "[★★☆☆☆]";
        return "[★☆☆☆☆]";
    }

    /**
     * Main method - Entry point of the application
     */
    public static void main(String[] args) {
        // Run GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new FeedbackUI();
        });
    }
}
