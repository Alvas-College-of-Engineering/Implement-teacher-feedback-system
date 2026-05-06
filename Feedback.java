/**
 * Feedback Class - Model class representing a teacher feedback entry
 * 
 * This class encapsulates the data for a single feedback submission.
 * It follows OOP principles with private attributes and getter methods only.
 */
public class Feedback {
    // Private attributes for encapsulation
    private String teacherName;
    private String subject;
    private int rating;
    private String comment;

    /**
     * Constructor with parameters
     * 
     * @param teacherName Name of the teacher
     * @param subject     Subject taught by the teacher
     * @param rating      Rating from 1 to 5
     * @param comment     Detailed feedback comments
     */
    public Feedback(String teacherName, String subject, int rating, String comment) {
        this.teacherName = teacherName;
        this.subject = subject;
        this.rating = rating;
        this.comment = comment;
    }

    // Getter methods - read-only access to attributes
    /**
     * Get the teacher's name
     * @return teacher name
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * Get the subject taught
     * @return subject name
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get the rating given
     * @return rating (1-5)
     */
    public int getRating() {
        return rating;
    }

    /**
     * Get the feedback comment
     * @return comment text
     */
    public String getComment() {
        return comment;
    }

    /**
     * Override toString() method for CSV format storage
     * Format: TeacherName,Subject,Rating,Comment
     * 
     * @return formatted string in CSV format
     */
    @Override
    public String toString() {
        // Escape commas in comment by replacing with semicolon for CSV format
        String safeComment = comment.replace(",", ";");
        return teacherName + "," + subject + "," + rating + "," + safeComment;
    }

    /**
     * Parse a CSV line back into a Feedback object
     * 
     * @param csvLine CSV formatted string
     * @return Feedback object
     */
    public static Feedback fromCSV(String csvLine) {
        String[] parts = csvLine.split(",", 4);
        if (parts.length >= 4) {
            String name = parts[0].trim();
            String subject = parts[1].trim();
            int rating = Integer.parseInt(parts[2].trim());
            String comment = parts[3].trim().replace(";", ",");
            return new Feedback(name, subject, rating, comment);
        }
        return null;
    }
}
