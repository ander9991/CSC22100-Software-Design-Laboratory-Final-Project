package sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private static Connection connection;
    private static Map<String, String[]> courses = new HashMap<>();
    private static Map<String, String[]> classes = new HashMap<>();

    public static void connectToDatabase() {
        connection = null;
        try {
            // Establish connection to the database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CSC221 Database",
                    "postgres", "0837");
            if (connection != null) { // check the connection
                System.out.println("Successfully connected to the Database!");
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void parseData() throws IOException {
        List<String> content = Files.readAllLines(Paths.get("C:\\Users\\steve\\Desktop\\Courses.txt"));
        for(int i = 1; i < content.size(); i++){
            String[] s = content.get(i).split("\t");

            String[] course1 = new String[] {s[2], s[6]};
            courses.put(s[0], course1);

            String[] class_array = new String[]{s[1], s[4], s[3]};
            classes.put(s[0], class_array);
        }
        addCourseThroughTextFile();
    }

    public static void addCourseThroughTextFile(){
        try {
            String sqlCommand = "INSERT INTO Courses " + "(courseID, courseTitle, department) VALUES (?, ?, ?)";
            PreparedStatement c = connection.prepareStatement(sqlCommand);
            for (Map.Entry<String, String[]> entry : courses.entrySet()){
                c.setString(1, entry.getKey());
                String[] array = entry.getValue();
                c.setString(2, array[0]);
                c.setString(3, array[1]);
                c.addBatch();
            }
            c.executeBatch();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Create the tables
    public static void createTables() {
        try {
            if (connection != null) {
                PreparedStatement stmt1 = connection.prepareStatement("CREATE TABLE Students " +
                        " (studentID INT NOT NULL, " +
                        " firstName VARCHAR(255), " +
                        " lastName VARCHAR(255), " +
                        " email VARCHAR(255), " +
                        " gender CHAR(1), " +
                        " PRIMARY KEY (studentID)," +
                        " CHECK (gender = 'U' OR gender = 'F' OR gender = 'M'))");;
                stmt1.execute();

                PreparedStatement stmt2 = connection.prepareStatement("CREATE TABLE Courses " +
                        " (courseID CHAR(10) NOT NULL, " +
                        " courseTitle VARCHAR(255), " +
                        " department CHAR(20), " +
                        " PRIMARY KEY (courseID))");
                stmt2.execute();

                PreparedStatement stmt3 = connection.prepareStatement("CREATE TABLE Classes " +
                        " (courseID CHAR(10) NOT NULL, " +
                        " studentID INT NOT NULL, " +
                        " section VARCHAR(255) NOT NULL, " +
                        " year INT, " +
                        " semester VARCHAR(6), " +
                        " grade VARCHAR(10), " +
                        " PRIMARY KEY (courseID, studentID, section)," +
                        " CHECK (grade = NULL or grade = 'A' OR grade = 'B' OR grade = 'C' OR grade = 'D' OR grade = 'F' OR grade = 'W'))");
                stmt3.execute();

            }
        }  catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // If tables exist, they will be cleared
    public static void clearPreviousTables() {
        try {
            if (connection != null) {
                PreparedStatement stmt1 = connection.prepareStatement("DROP TABLE Students");
                stmt1.execute();

                PreparedStatement stmt2 = connection.prepareStatement("DROP TABLE Courses");
                stmt2.execute();

                PreparedStatement stmt3 = connection.prepareStatement("DROP TABLE Classes");
                stmt3.execute();

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Insert a student into the student table
    public static void insertIntoStudents(int studentID, String firstName, String lastName, String email, char gender) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Students(studentID, firstName, lastName, email, gender)"
                    + "VALUES('"+studentID+"', '"+firstName+"', '"+lastName+"','"+email+"', '"+gender+"')");
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Manually insert a course into the course table
    public static void insertIntoCourses(String courseID, String courseTitle, String department) {
        try {
            PreparedStatement stmt = connection.prepareStatement
                    ("INSERT INTO Courses (courseID, courseTitle, department) VALUES('" +
                                    courseID + "', '" + courseTitle + "', '" + department + "')",
                            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Insert a class into the class table
    public static void insertIntoClasses(String courseID, int studentID,String section,int year,String semester,String grade) {
        try {
            PreparedStatement stmt = connection.prepareStatement
                    ("INSERT INTO Classes(courseID, studentID, section, year, semester, grade) VALUES('" +
                            courseID + "', " + studentID + ", '" + section + "', " + year + ", '" + semester + "', '" +
                            grade + "')", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Returns a table with the information of students and the class that are enrolled into CSC221
    public static void tableEnrolledInCSC22100() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT grade, COUNT(studentID) FROM Classes " +
                    "WHERE courseID = 'CSC 22100' AND year = 2021 AND semester = 'Spring' GROUP BY grade");
            ResultSet rSet = stmt.executeQuery();
            Database.showResults("Classes", rSet);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    // Returns a HashMap with the # of students enrolled for each letter grade
    public static HashMap<Character, Integer> studentsAndGrades() {
        HashMap<Character, Integer> hm = new HashMap<>(6);
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT grade FROM Classes");
            ResultSet rSet = stmt.executeQuery();
            String allGrades = "";
            while (rSet.next()) {
                String row = rSet.getString("grade");
                if (row != null) {
                    allGrades += row + "";
                }
            }
            for (int i = 0; i < allGrades.length(); i++) {
                char currGrade = allGrades.charAt(i);
                if (!hm.containsKey(currGrade)) {
                    hm.put(currGrade, 1);
                } else {
                    int oldFreq = hm.get(currGrade);
                    hm.replace(currGrade, ++oldFreq);
                }
            }
        }  catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
        return hm;
    }

    // Shows the elements in the tables
    public static void showValues() {
        try {
            PreparedStatement stmt1 = connection.prepareStatement("SELECT * FROM Students");
            ResultSet rset1 = stmt1.executeQuery();
            Database.showResults("Students", rset1);

            PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM Courses");
            ResultSet rset2 = stmt2.executeQuery();
            Database.showResults("Courses", rset2);

            PreparedStatement stmt3 = connection.prepareStatement("SELECT * FROM Classes");
            ResultSet rset3 = stmt3.executeQuery();
            Database.showResults("Classes", rset3);
        }  catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Shows the results of a specific resultset
    public static void showResults(String tableName, ResultSet rSet) {
        try {
            ResultSetMetaData rsmd = rSet.getMetaData();
            int numColumns = rsmd.getColumnCount();
            String resultString = null;
            if (numColumns > 0) {
                resultString = "\nTable: " + tableName + "\n" +
                        "============================================================\n";
                for (int colNum = 1; colNum <= numColumns; colNum++)
                    resultString += rsmd.getColumnLabel(colNum) + " ";
            }
            System.out.println(resultString);
            System.out.println(
                    "============================================================");
            while (rSet.next()) {
                resultString = "";
                for (int colNum = 1; colNum <= numColumns; colNum++) {
                    String column = rSet.getString(colNum);
                    if (column != null)
                        resultString += column + " ";
                }
                System.out.println(resultString + '\n' +
                        "------------------------------------------------------------");
            }
        }  catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void updateStudentGrade(String grade, int studentID){
        try {
            String updateGrades = "UPDATE Classes SET GRADE = ? WHERE studentID = ?";
            PreparedStatement statement = connection.prepareStatement(updateGrades);
            statement.setString(1, grade + "");
            statement.setInt(2, studentID);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void closeDatabase() {
        try {
            if (connection != null) {
                connection.close();
            }
        }  catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}