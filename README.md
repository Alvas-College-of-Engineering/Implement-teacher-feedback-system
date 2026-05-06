# Teacher Feedback System

A professional, feature-rich Java application for managing teacher feedback with a clean Swing GUI, proper OOP design, and persistent file storage.

## 📋 Project Overview

This is a **final-year/portfolio-quality project** demonstrating:
- Object-oriented programming principles
- Swing GUI design and layout management
- File I/O with buffered readers/writers
- Data persistence and management
- Exception handling
- Clean code practices

## 🎯 Features

### Core Functionality
✅ **Feedback Submission Form**
- Input fields: Teacher Name, Subject, Rating (1-5 dropdown), Comments (multi-line)
- Input validation with error messages
- Success confirmation popup
- Auto-clear fields after submission

✅ **Persistent Storage**
- Stores feedback in `feedback.txt` file (CSV format)
- Automatic file creation if it doesn't exist
- Handles corrupted or missing files gracefully

✅ **Summary & Analytics**
- View average ratings per teacher
- Sorted by rating (highest first)
- Star rating visualization (★★★★★)
- Detailed statistics

✅ **Data Viewing**
- Table view of all feedback entries
- Teacher, Subject, Rating, Comments display
- Sortable and scrollable

## 📁 Project Structure

```
JAVA/
├── Feedback.java           # Model class - represents a feedback entry
├── FeedbackManager.java    # Manager class - handles file I/O & data logic
├── FeedbackUI.java         # GUI class - main application window
├── feedback.txt            # Auto-generated - stores feedback data (CSV format)
└── README.md              # This file
```

## 🏗️ OOP Architecture

### 1. **Feedback Class**
```java
Attributes:
  - teacherName (String) - private
  - subject (String) - private
  - rating (int) - private, range 1-5
  - comment (String) - private

Methods:
  - Constructor(String, String, int, String)
  - getTeacherName(), getSubject(), getRating(), getComment()
  - toString() - CSV format for storage
  - fromCSV(String) - Parse CSV back to object
```

### 2. **FeedbackManager Class**
```java
Attributes:
  - feedbackList: ArrayList<Feedback>
  - isLoaded: boolean - prevents duplicate loading

Methods:
  - addFeedback(Feedback)
  - saveToFile(Feedback): boolean
  - loadFeedback(): void
  - getAverageRatings(): Map<String, Double>
  - getAllFeedback(): ArrayList<Feedback>
  - getFeedbackCount(): int
  - clearMemory(): void
```

### 3. **FeedbackUI Class (Main)**
```java
GUI Components:
  - JFrame: Main window (500x600, centered)
  - JTextField: Teacher name, Subject
  - JComboBox: Rating selector (1-5)
  - JTextArea + JScrollPane: Comments
  - JButton: Submit, View Summary
  - JTabbedPane: Summary tabs (Ratings & All Feedback)
  - JTable: All feedback entries with columns

Methods:
  - initializeComponents()
  - submitFeedback(ActionEvent)
  - viewSummary(ActionEvent)
  - validateInput(): boolean
  - clearFields()
  - createAverageRatingsPanel()
  - createFeedbackTablePanel()
  - main(String[])
```

## 🚀 How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher installed
- VS Code with Java extension (or any Java IDE)

### Compilation
```bash
# Open terminal in the JAVA folder
cd c:\Users\User\Desktop\JAVA

# Compile all Java files
javac Feedback.java FeedbackManager.java FeedbackUI.java
```

### Execution
```bash
# Run the application
java FeedbackUI
```

The application window will open and you're ready to submit feedback!

## 📖 Usage Guide

### Submit Feedback
1. **Fill all required fields** (marked with *)
   - Teacher Name
   - Subject
   - Rating (1-5 dropdown)
   - Comments

2. **Click "Submit Feedback"**
   - Validation runs automatically
   - Feedback saved to file
   - Success popup appears
   - Fields auto-clear

3. **Your feedback is persistent** - it's stored in `feedback.txt`

### View Summary
1. **Click "View Summary"** button
   - Opens summary window with tabs

2. **Average Ratings Tab**
   - Shows average rating per teacher
   - Sorted by rating (best first)
   - Star visualization for quick reference

3. **All Feedback Tab**
   - Table view of all submissions
   - Sortable columns
   - Shows truncated comments

## 📊 Data Format

Feedback is stored in `feedback.txt` in CSV format:
```
Teacher_Name,Subject,Rating,Comment_Text
John Doe,Mathematics,5,Excellent teaching methodology
Jane Smith,English,4,Good class management; could improve engagement
```

**Note:** Commas in comments are replaced with semicolons to preserve CSV integrity.

## ✨ Design Features

### UI Design
- **Color Scheme**: Clean white/light gray background with professional colors
  - Green buttons (#34A853) for positive actions
  - Blue buttons (#2196F3) for informational actions
  - Consistent Arial font throughout

- **Layout**: BoxLayout + GridLayout for clean, organized spacing
- **Responsiveness**: Proper padding, alignment, and sizing
- **Accessibility**: Good contrast, readable fonts, clear labels

### Error Handling
- ✅ File not found → Creates new file automatically
- ✅ Empty fields → Validates and shows error message
- ✅ IO exceptions → Gracefully handled with error messages
- ✅ Invalid data → Skips corrupted lines during load

### Code Quality
- ✅ Comprehensive JavaDoc comments
- ✅ Meaningful variable/method names
- ✅ DRY principle (Don't Repeat Yourself)
- ✅ Encapsulation - private attributes, public getters
- ✅ Separation of concerns - UI, Logic, Data

## 🎁 Bonus Features Included

1. **Tabbed Summary View**
   - Professional two-tab interface
   - Average ratings with visual stars
   - Complete feedback table

2. **JTable for Data Display**
   - Read-only, professional styling
   - Adjustable column widths
   - Color-coded headers and selections

3. **Star Ratings Visualization**
   - ★★★★★ (5 stars) for excellent ratings
   - Helps quickly identify best teachers
   - Scale indicator

4. **Automatic Data Reload**
   - Summary always shows latest data
   - No application restart needed

## 🔧 Customization

### Change Window Size
In `FeedbackUI.java`, line ~45:
```java
setSize(500, 600);  // Width x Height
```

### Change Button Colors
In `FeedbackUI.java`, lines ~125-130:
```java
submitButton.setBackground(new Color(52, 168, 83));  // RGB color
viewSummaryButton.setBackground(new Color(33, 150, 243));
```

### Change File Path
In `FeedbackManager.java`, line ~15:
```java
private static final String FEEDBACK_FILE = "feedback.txt";
```

### Add New Ratings
In `FeedbackUI.java`, line ~90:
```java
Integer[] ratings = {1, 2, 3, 4, 5};  // Add more numbers here
```

## 📝 Example Workflow

1. **User opens application** → Sees "Ready to submit new feedback"
2. **User fills form** → Enters teacher, subject, rating, comments
3. **User clicks Submit** → Feedback saved, success popup shown
4. **Next time app opens** → Loads previous feedback automatically
5. **User clicks View Summary** → Sees average ratings and all feedback in tables

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| Application won't compile | Check JDK is installed, compile in correct directory |
| File not found errors | Application creates `feedback.txt` automatically |
| GUI not appearing | Ensure running from correct directory with `java FeedbackUI` |
| Data not saving | Check folder permissions, ensure disk space available |

## 📚 Learning Outcomes

This project demonstrates:
1. **OOP Principles**: Encapsulation, separation of concerns, proper class design
2. **Swing GUI**: Layout managers, event handling, custom dialogs
3. **File I/O**: BufferedReader/Writer, file creation, exception handling
4. **Data Structures**: ArrayList, HashMap for efficient data management
5. **Best Practices**: Comments, validation, error handling, clean code

## 🎓 Portfolio Value

This project is suitable for:
- ✅ College/University final-year submission
- ✅ GitHub portfolio showcase
- ✅ Job interview demonstrations
- ✅ Software engineering interviews
- ✅ Code review examples

## 📄 License

Free to use for educational purposes.

## 👨‍💻 Author

Created as a professional Java learning project.

---

**Ready to use! Compile and run to get started.**
