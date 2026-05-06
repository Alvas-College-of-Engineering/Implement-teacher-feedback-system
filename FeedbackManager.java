import java.io.*;
import java.util.*;

/**
 * FeedbackManager Class - Manages feedback data and file operations
 * 
 * This class handles:
 * - Reading and writing feedback to file
 * - Managing feedback collection in memory
 * - Computing statistics like average ratings
 */
public class FeedbackManager {
    // File path for storing feedback
    private static final String FEEDBACK_FILE = "feedback.txt";
    
    // ArrayList to store feedback objects
    private ArrayList<Feedback> feedbackList;
    
    // Flag to track if data has been loaded
    private boolean isLoaded;

    /**
     * Constructor - Initialize the manager
     */
    public FeedbackManager() {
        this.feedbackList = new ArrayList<>();
        this.isLoaded = false;
    }

    /**
     * Add feedback to the in-memory list
     * 
     * @param feedback Feedback object to add
     */
    public void addFeedback(Feedback feedback) {
        if (feedback != null) {
            feedbackList.add(feedback);
        }
    }

    /**
     * Save a feedback object to file
     * Creates file if it doesn't exist, appends if it does
     * 
     * @param feedback Feedback object to save
     * @return true if successful, false otherwise
     */
    public boolean saveToFile(Feedback feedback) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FEEDBACK_FILE, true))) {
            // Write feedback in CSV format
            writer.write(feedback.toString());
            writer.newLine();
            writer.flush();
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Load all feedback from file into memory
     * Handles case when file doesn't exist
     * Prevents duplicate loading by checking isLoaded flag
     */
    public void loadFeedback() {
        // Prevent duplicate loading
        if (isLoaded) {
            return;
        }

        File file = new File(FEEDBACK_FILE);
        
        // If file doesn't exist, create it
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
            isLoaded = true;
            return;
        }

        // Load existing feedback from file
        try (BufferedReader reader = new BufferedReader(new FileReader(FEEDBACK_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Feedback feedback = Feedback.fromCSV(line);
                    if (feedback != null) {
                        feedbackList.add(feedback);
                    }
                }
            }
            isLoaded = true;
        } catch (FileNotFoundException e) {
            System.err.println("Feedback file not found. A new one will be created.");
            isLoaded = true;
        } catch (IOException e) {
            System.err.println("Error reading feedback file: " + e.getMessage());
            isLoaded = true;
        }
    }

    /**
     * Get all feedback entries
     * 
     * @return ArrayList of all feedback
     */
    public ArrayList<Feedback> getAllFeedback() {
        return feedbackList;
    }

    /**
     * Calculate average ratings per teacher
     * 
     * @return Map with teacher names as keys and average ratings as values
     */
    public Map<String, Double> getAverageRatings() {
        Map<String, Double> averageRatings = new HashMap<>();
        Map<String, Integer> totalRatings = new HashMap<>();
        Map<String, Integer> ratingCounts = new HashMap<>();

        // Calculate totals and counts
        for (Feedback feedback : feedbackList) {
            String teacher = feedback.getTeacherName();
            int rating = feedback.getRating();

            totalRatings.put(teacher, totalRatings.getOrDefault(teacher, 0) + rating);
            ratingCounts.put(teacher, ratingCounts.getOrDefault(teacher, 0) + 1);
        }

        // Calculate averages
        for (String teacher : totalRatings.keySet()) {
            double average = (double) totalRatings.get(teacher) / ratingCounts.get(teacher);
            // Round to 2 decimal places
            average = Math.round(average * 100.0) / 100.0;
            averageRatings.put(teacher, average);
        }

        return averageRatings;
    }

    /**
     * Clear all feedback from memory (does not affect file)
     */
    public void clearMemory() {
        feedbackList.clear();
        isLoaded = false;
    }

    /**
     * Get count of feedback entries
     * 
     * @return number of feedback entries
     */
    public int getFeedbackCount() {
        return feedbackList.size();
    }

    /**
     * Check if data has been loaded
     * 
     * @return true if loaded, false otherwise
     */
    public boolean isDataLoaded() {
        return isLoaded;
    }
}
