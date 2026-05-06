# Quick Start Guide - Teacher Feedback System

## ⚡ Get Running in 30 Seconds

### Option 1: Using Command Line (PowerShell/CMD)

```powershell
# Step 1: Navigate to project folder
cd c:\Users\User\Desktop\JAVA

# Step 2: Compile (only needed once)
javac *.java

# Step 3: Run application
java FeedbackUI
```

The application window will open immediately!

---

### Option 2: Using VS Code

1. **Open VS Code**
2. **Open Folder**: File → Open Folder → Select `c:\Users\User\Desktop\JAVA`
3. **Open Terminal**: Ctrl + ` (backtick)
4. **Type commands**:
   ```
   javac *.java
   java FeedbackUI
   ```
5. **Application opens automatically**

---

## 🎯 Using the Application

### Submit Feedback (3 steps)

1. **Fill the form:**
   - Teacher Name: e.g., "Dr. Sarah Johnson"
   - Subject: e.g., "Computer Science"
   - Rating: Select 1-5 from dropdown
   - Comments: Type detailed feedback

2. **Click "Submit Feedback"**
   - Green button on the form
   - Success popup will appear

3. **Form clears automatically**
   - Ready for next feedback entry

### View Summary (2 steps)

1. **Click "View Summary"**
   - Blue button on the form
   - New window opens with tabs

2. **Choose view:**
   - **Tab 1 - Average Ratings**: Shows teacher ratings sorted best→worst
   - **Tab 2 - All Feedback**: Shows all entries in table format

---

## ✅ Verification Checklist

After first run, you should see:

- [ ] Main window opens with clean design
- [ ] Form has all input fields visible
- [ ] Submit and View Summary buttons are clickable
- [ ] Input validation works (try submitting empty form)
- [ ] Success popup appears after valid submission
- [ ] feedback.txt file is created in the same folder
- [ ] View Summary shows your feedback

---

## 📁 Where's My Data?

Your feedback is saved in: **`feedback.txt`**

Location: Same folder as the Java files
Format: Simple text file (CSV format)

You can open it with any text editor to see the data!

---

## 🔑 Key Features at a Glance

| Feature | How to Use |
|---------|-----------|
| **Submit Feedback** | Fill form + Click "Submit Feedback" button |
| **View Average Rating** | Click "View Summary" → First tab |
| **See All Feedback** | Click "View Summary" → Second tab |
| **Clear Form** | Automatic after submission |
| **Exit Application** | Click X button on window |

---

## ❓ Troubleshooting

### "Command not recognized" error
- **Solution**: Make sure Java is installed. Test with: `java -version`

### "Class not found" error
- **Solution**: Run `javac *.java` first to compile
- **Location**: Must be in c:\Users\User\Desktop\JAVA folder

### Window won't open
- **Solution**: Check terminal for error messages
- **Try**: Close terminal and try again

### Can't save feedback
- **Solution**: Check folder permissions
- **Try**: Run as Administrator

---

## 🎓 Project Structure

```
Your Project Folder:
├── Feedback.java              ← Data model
├── FeedbackManager.java       ← Data storage & logic
├── FeedbackUI.java           ← Main application
├── feedback.txt              ← Your feedback data (auto-created)
├── README.md                 ← Full documentation
├── PROJECT_SUMMARY.md        ← Detailed project info
└── QUICKSTART.md            ← This file
```

---

## 💡 Tips & Tricks

**Tip 1: Longer Comments**
- The comment field auto-wraps text
- Paste long feedback directly

**Tip 2: Reusing Teacher Names**
- Just type same name again
- App automatically groups ratings

**Tip 3: Checking Average Ratings**
- View Summary sorts teachers by rating
- Best teachers show first

**Tip 4: Backing Up Data**
- Copy feedback.txt to safe location
- It's a plain text file (easy to backup)

**Tip 5: Import Data**
- You can manually edit feedback.txt
- Keep CSV format: `name,subject,rating,comment`

---

## ⌨️ Keyboard Shortcuts

| Shortcut | Action |
|----------|--------|
| `Tab` | Move to next field |
| `Shift+Tab` | Move to previous field |
| `Enter` (in button) | Click button |
| `Ctrl+C` (in terminal) | Stop application |

---

## 🎨 First-Time UI Preview

```
╔════════════════════════════════════════╗
║     Teacher Feedback System            ║
╠════════════════════════════════════════╣
║                                        ║
║  Teacher Name [________________]       ║
║  Subject      [________________]       ║
║  Rating       [1▼]                    ║
║                                        ║
║  Comments                              ║
║  [________________________________]    ║
║  [________________________________]    ║
║  [________________________________]    ║
║                                        ║
║  [Submit Feedback] [View Summary]      ║
║                                        ║
╚════════════════════════════════════════╝
```

---

## 📞 Common Questions

**Q: Do I need internet?**
A: No. Application works completely offline.

**Q: Where is my data stored?**
A: In feedback.txt in the same folder. Simple text file.

**Q: Can I delete feedback?**
A: Yes, edit feedback.txt directly or delete the file and start fresh.

**Q: Can multiple people use this?**
A: Yes! They all save to the same feedback.txt file.

**Q: Will data be lost if I close the app?**
A: No. Data is saved to file immediately when you submit.

**Q: Can I modify the code?**
A: Absolutely! Source code is well-documented and easy to customize.

---

## 🚀 Next Steps

1. ✅ **Run it**: Start the application
2. ✅ **Test it**: Submit some feedback
3. ✅ **Review it**: View the summary
4. ✅ **Explore it**: Check the feedback.txt file
5. ✅ **Customize it**: Modify colors/size if desired

---

## 📚 Learn More

For detailed technical information, see:
- **README.md** - Full documentation
- **PROJECT_SUMMARY.md** - Architecture details

For code review, open:
- **Feedback.java** - Data model
- **FeedbackManager.java** - Business logic  
- **FeedbackUI.java** - User interface

---

## ✨ You're All Set!

The application is ready to use. No additional setup needed.

**Happy feedback submission! 🎉**

---

*Created: April 29, 2026*
*Version: 1.0*
