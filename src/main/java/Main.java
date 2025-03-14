
import dao.ProgressReportDAO;
import dao.StudentDAO;
import dao.InstructorDAO;
import dao.TrainingSessionDAO;
import model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Initialize DAO classes
        StudentDAO studentDAO = new StudentDAO();
        InstructorDAO instructorDAO = new InstructorDAO();
        TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO();
        ProgressReportDAO progressReportDAO = new ProgressReportDAO();
        // Add sample students
        Student student1 = new Student("John Doe", "john@example.com", Aikido_ENUM.BROWN, LocalDate.now());
        Student student2 = new Student("Jane Smith", "jane@example.com", Aikido_ENUM.BLUE, LocalDate.now());

        studentDAO.save(student1);
        studentDAO.save(student2);

        // Add an instructor
        Instructor instructor = new Instructor("Sensei Aki", "Aikido Throws", 10);
        instructorDAO.save(instructor);




        // create a training session
        Student student = studentDAO.findById(1L);
        Student student3 = studentDAO.findById(2L);
        Instructor instructor1 = instructorDAO.findById(1L);
        TrainingSession trainingSession = new TrainingSession(LocalDate.now(), "Dojo 1", 2);
        trainingSession.setInstructor(instructor1);
        trainingSession.addStudent(student, Attendance_ENUM.PRESENT, "Good");
        trainingSession.addStudent(student3, Attendance_ENUM.ABSENT, "Sick");
        trainingSessionDAO.save(trainingSession);



        // Add students to the training session

        // Fetch and print students
        List<Student> students = studentDAO.findAll();
        System.out.println("Students:");
        students.forEach(s -> {
            System.out.println(s.getName() + " - " + s.getRank());
            System.out.println("Created At: " + s.getCreatedAt());
            System.out.println("Membership Duration: " + s.getMembershipDuration() + " years");
        });

        // Fetch and print instructors
        List<Instructor> instructors = instructorDAO.findAll();
        System.out.println("Instructors:");
        instructors.forEach(i -> System.out.println(i.getName() + " - " + i.getSpecialization()));

        // Fetch training sessions by student name
        List<TrainingSession> trainingSessions = trainingSessionDAO.findTrainingSessionsByStudent(1L);
        System.out.println("Training Sessions for John Doe:");
        trainingSessions.forEach(ts -> System.out.println(ts.getDate() + " - " + ts.getLocation()));

        // Fetch students by rank
        List<Student> studentsByRank = studentDAO.findStudentByRank(Aikido_ENUM.BROWN);

        System.out.println("Students with Brown Belt:");
        studentsByRank.forEach(s -> System.out.println(s.getName() + " - " + s.getRank()));

        // create progress report
        ProgressReport progressReport = new ProgressReport("100kg benchpress", "Good Progress");
        progressReport.setStudent(student);
        progressReportDAO.save(progressReport);
        progressReport.setAchievements("200kg benchpress");
        progressReportDAO.update(progressReport);
        //i should remake some of the code to test the concurrent modification step,but it would be messy :'D but i am pretty sure it should work like that. atleast the versioning works.


    }
}