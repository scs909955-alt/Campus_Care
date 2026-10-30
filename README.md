# CampusCare 🎓
> A modular, robust, object-oriented Student Information and Academic Management System written in pure Java.

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Platform](https://img.shields.io/badge/Platform-Cross--Platform-blue?style=for-the-badge)](https://www.java.com/)
[![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)](LICENSE)

---

## 📌 Overview

**CampusCare** is a console-based campus portal designed for faculty to streamline student record administration, academic tracking, attendance evaluation, and report generation. Built strictly following Core Java and Object-Oriented Programming (OOP) paradigms, CampusCare provides automatic ID generation, strict input validation, in-memory data structures, and persistent flat-file storage.

---

## 🌟 Key Features

- **👨‍🏫 Faculty Portal Profile**: Displays authenticated faculty credentials, department, and teaching specialization on startup.
- **🔢 Automated Student ID Generation**: Dynamic ID allocation starting from `101`, preventing duplicate entries or manual assignment errors.
- **🛡️ Strict Data Validation & Sanitization**:
  - Name formatting: Regex sanitization (`[A-Za-z ]+`), trimming, and standard uppercase conversion.
  - Attendance integrity: Enforces $0 \le \text{Attended Classes} \le \text{Total Classes}$ and $\text{Total Classes} > 0$.
  - Marks boundary checks: Validates scores strictly in the range of $[0.0, 100.0]$.
- **🔄 Complete CRUD Operations**:
  - **Create**: Register new student profiles with attendance and subject marks.
  - **Read**: View all registered students with formatted percentage and grade metrics.
  - **Search**: Fast linear search by Student ID.
  - **Update**: Safe atomic record updates validating inputs before committing changes.
  - **Delete**: Remove students from memory and automatically synchronize the disk store.
- **📊 Dynamic Performance Reports**: High-speed, formatted report synthesis using `StringBuffer` detailing attendance percentage and 3-subject academic averages.
- **💾 Flat-File Persistence (`campuscare.txt`)**:
  - Automatic file creation on launch if not present.
  - Comma-separated value (CSV) serialization and deserialization.
  - Automatic synchronization on student add, update, and deletion.

---

## 🏗️ Architecture & OOP Principles

CampusCare is cleanly decoupled into modular packages demonstrating core Object-Oriented principles:

```
Campus_Care/
├── Main.java                 # Entry point & interactive CLI menu loop
├── person/
│   ├── Person.java           # Abstract base class modeling shared person identity
│   ├── Student.java          # Derived class modeling student academic attributes
│   └── Faculty.java          # Derived class modeling faculty instructional roles
├── management/
│   └── StudentManager.java   # Business logic, ArrayList collection, and file I/O
├── utility/
│   └── ReportGenerator.java  # High-efficiency report synthesis via StringBuffer
├── campuscare.txt            # Persistent CSV data storage
├── run.sh                    # Automated compile & execution bash script
└── .gitignore                # Git ignore rules for compiled classes & system artifacts
```

### 🧩 Object-Oriented Design in Action

| OOP Concept | Implementation Details |
| :--- | :--- |
| **Abstraction** | [`Person`](file:///person/Person.java) is an `abstract` class defining core attributes (`id`, `name`) and the abstract method `public abstract void displayDetails()`. Instances cannot be created directly. |
| **Inheritance** | [`Student`](file:///person/Student.java) and [`Faculty`](file:///person/Faculty.java) extend `Person`, invoking the parent constructor via `super(id, name)` and inheriting standard identity fields. |
| **Polymorphism** | Dynamic method dispatch is achieved through `@Override public void displayDetails()`, customized independently for students and faculty. |
| **Encapsulation** | Sensitive fields (`department`, `totalClasses`, `marks1`, etc.) are declared `private` or `protected` and modified solely through validated getters and setters. |
| **Collections Framework** | Dynamic in-memory record storage powered by `ArrayList<Student>` in `StudentManager`. |
| **Exception Handling** | Robust validation using `IllegalArgumentException`, `NumberFormatException`, and standard `IOException` try-with-resources. |

---

## 💾 Storage File Format (`campuscare.txt`)

Data is persisted on disk using comma-separated values (CSV) with 8 fields per record:

$$\text{StudentID},\text{Name},\text{Department},\text{TotalClasses},\text{AttendedClasses},\text{Marks1},\text{Marks2},\text{Marks3}$$

**Example:**
```csv
101,ALICE JOHNSON,COMPUTER SCIENCE,50,47,92.5,88.0,95.0
102,BOB SMITH,ELECTRONICS,50,38,76.0,81.5,70.0
```

---

## 🚀 Getting Started

### 📋 Prerequisites

- **Java Development Kit (JDK)**: Version 8 or newer (JDK 17, 21, or newer recommended).
- **Git**: Installed and configured.

Verify your Java installation:
```bash
java -version
javac -version
```

---

### 📥 Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/scs909955-alt/Campus_Care.git
   cd Campus_Care
   ```

2. **Compile the source files:**
   ```bash
   javac Main.java person/*.java management/*.java utility/*.java
   ```

3. **Run CampusCare:**
   ```bash
   java Main
   ```

*(Alternatively, run the included execution script:)*
```bash
chmod +x run.sh
./run.sh
```

---

## 🖥️ Usage Guide & Walkthrough

Upon launch, CampusCare initializes the faculty portal and presents the interactive control menu:

```text
================================
       CAMPUSCARE PORTAL
================================
Faculty ID: 501566
Name: Suneetha Merugula
Department: CSE
Subject: Java Programming
Designation: Assistant Professor

========== MAIN MENU ==========
1. Add Student
2. View All Students
3. Search Student
4. Delete Student
5. Update Student
6. Generate Student Report
7. Exit
Enter choice: 1
```

### 1️⃣ Adding a Student
```text
Generated Student ID: 101
Enter Student Name: John Doe
Enter Department: CSE
Enter Total Classes: 60
Enter Attended Classes: 54
Enter Subject 1 Marks: 88.5
Enter Subject 2 Marks: 92.0
Enter Subject 3 Marks: 84.0
Student records saved!
Student added successfully!
```

### 2️⃣ Generating a Student Report
```text
================================
       CAMPUSCARE REPORT
================================
Student ID: 101
Name: JOHN DOE
Department: CSE
--------------------------------
Total Classes: 60
Attended Classes: 54
Attendance: 90.00%
--------------------------------
Subject 1: 88.5
Subject 2: 92.0
Subject 3: 84.0
Average: 88.17
================================
```

---

## 🔮 Future Enhancements

- [ ] **GUI Integration**: Modern desktop frontend using JavaFX or Swing.
- [ ] **Relational Database**: Transition from flat CSV storage to SQLite / MySQL with JDBC.
- [ ] **Grade Scale & Class Ranking**: Automatic letter grade assignment (A, B, C, D, F) and top-performer leaderboards.
- [ ] **PDF Exporting**: Export dynamic performance reports directly into PDF certificates.

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

---

## 👤 Author

Developed by **Varshith** ([@scs909955-alt](https://github.com/scs909955-alt)).
