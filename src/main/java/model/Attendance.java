package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Attendance {
    //idm,status,notes manytomany join table between student and training session
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Attendance_ENUM status;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "training_session_id", nullable = false)
    private TrainingSession trainingSession;


    public Attendance() {
    }

    public Attendance(Attendance_ENUM status, String notes) {
        this.status = status;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Attendance_ENUM getStatus() {
        return status;
    }

    public void setStatus(Attendance_ENUM status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public TrainingSession getTrainingSession() {
        return trainingSession;
    }

    public void setTrainingSession(TrainingSession trainingSession) {
        this.trainingSession = trainingSession;
    }


    public void updateStatus(Attendance_ENUM status) {
        this.status = status;
    }
}