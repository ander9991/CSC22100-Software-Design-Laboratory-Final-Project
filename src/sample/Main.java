package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage ps) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        final Canvas canvas = new Canvas(1200, 800);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        ps.setTitle("Steven Granaturov Assignment 4");

        PieChart pc = new PieChart();
        pc.draw(graphicsContext);
        graphicsContext.fillText("CSC22100 Grade Distribution", 2 * canvas.getWidth() / 5 , canvas.getHeight() / 30);

        root.getChildren().add(canvas);
        ps.setScene(scene);
        ps.show();
    }

    public static void main(String[] args) {
        Database.connectToDatabase();
        Database.clearPreviousTables();
        Database.createTables();
        try {
            Database.parseData();
        } catch (Exception e){
            e.printStackTrace();
        }

        Database.insertIntoStudents(23756046, "Steven", "Granaturov",
                "sg@gmail.com", 'M');
        Database.insertIntoStudents(23756047, "John", "Smith",
                "js@gmail.com", 'U');
        Database.insertIntoStudents(23756048, "Jane", "Doe",
                "jd@gmail.com", 'U');
        Database.insertIntoStudents(23756049, "Hesham", "Auda",
                "ha@gmail.com", 'M');
        Database.insertIntoStudents(23756050, "William", "Skeith",
                "ws@gmail.com", 'M');
        Database.insertIntoStudents(23756051, "George", "Wolberg",
                "gw@gmail.com", 'M');
        Database.insertIntoStudents(23756052, "Nihal", "Vatandas",
                "nv@gmail.com", 'F');
        Database.insertIntoStudents(23756053, "Zheng", "Peng",
                "zp@gmail.com", 'M');
        Database.insertIntoStudents(23756054, "Elahe", "Vihdani",
                "ev@gmail.com", 'F');
        Database.insertIntoStudents(23756055, "Adam", "Bubrow",
                "ab@gmail.com", 'M');
        Database.insertIntoStudents(23756056, "John", "Adamski",
                "ja@gmail.com", 'M');
        Database.insertIntoStudents(23756057, "Akira", "Kawaguchi",
                "ak@gmail.com", 'M');
        Database.insertIntoStudents(23756058, "Crystal", "Sawyer",
                "cs@gmail.com", 'F');
        Database.insertIntoStudents(23756059, "Willie", "Shie",
                "ws@gmail.com", 'M');
        Database.insertIntoStudents(23756060, "David", "Lakhter",
                "dl@gmail.com", 'M');
        Database.insertIntoStudents(23756061, "Amad", "Saleem",
                "as@gmail.com", 'M');
        Database.insertIntoStudents(23756062, "Victor", "Abramov",
                "va@gmail.com", 'M');
        Database.insertIntoStudents(23756063, "Mariam", "Khan",
                "mk@gmail.com", 'F');
        Database.insertIntoStudents(23756064, "Cindy", "Lu",
                "cl@gmail.com", 'F');
        Database.insertIntoStudents(23756065, "Rachel", "Yu",
                "ry@gmail.com", 'F');
        Database.insertIntoStudents(23756066, "Lal", "Saitram",
                "ls@gmail.com", 'M');
        Database.insertIntoStudents(23756067, "Nelson", "Chow",
                "nc@gmail.com", 'M');
        Database.insertIntoStudents(23756068, "Erica", "Gallanter",
                "eg@gmail.com", 'F');
        Database.insertIntoStudents(23756069, "Michael", "Pollack",
                "mp@gmail.com", 'M');
        Database.insertIntoStudents(23756070, "Sarah", "Segal",
                "ss@gmail.com", 'F');


        Database.insertIntoClasses("CSC 22100", 23756046, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756047, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756048, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756049, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756050, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756051, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756052, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756053, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756054, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756056, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756057, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756058, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756059, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756060, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756061, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756062, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756063, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756064, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756065, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756066, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756067, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756068, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756069, "32131", 2021,
                "Spring", null);
        Database.insertIntoClasses("CSC 22100", 23756070, "32131", 2021,
                "Spring", null);


        Database.updateStudentGrade("A", 23756046);
        Database.updateStudentGrade("A", 23756047);
        Database.updateStudentGrade("A", 23756048);
        Database.updateStudentGrade("A", 23756049);
        Database.updateStudentGrade("A", 23756050);
        Database.updateStudentGrade("B", 23756051);
        Database.updateStudentGrade("B", 23756052);
        Database.updateStudentGrade("B", 23756053);
        Database.updateStudentGrade("B", 23756054);
        Database.updateStudentGrade("C", 23756055);
        Database.updateStudentGrade("C", 23756056);
        Database.updateStudentGrade("C", 23756057);
        Database.updateStudentGrade("C", 23756058);
        Database.updateStudentGrade("D", 23756059);
        Database.updateStudentGrade("D", 23756060);
        Database.updateStudentGrade("F", 23756061);
        Database.updateStudentGrade("F", 23756062);
        Database.updateStudentGrade("F", 23756063);
        Database.updateStudentGrade("W", 23756064);
        Database.updateStudentGrade("W", 23756065);
        Database.updateStudentGrade("W", 23756066);
        Database.updateStudentGrade("W", 23756067);
        Database.updateStudentGrade("W", 23756068);
        Database.updateStudentGrade("W", 23756069);
        Database.updateStudentGrade("W", 23756070);

        Database.showValues();
        Database.tableEnrolledInCSC22100();

        launch(args);
        Database.closeDatabase();
    }
}