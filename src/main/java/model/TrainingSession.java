package model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "training_sessions")
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String location;
    private int duration;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @OneToMany(mappedBy = "trainingSession",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Attendance> attendances;
    // Constructors, Getters & Setters
    public TrainingSession() {
    }

    public TrainingSession(LocalDate date, String location, int duration) {
        this.date = date;
        this.location = location;
        this.duration = duration;
        this.attendances = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void addStudent(Student student, Attendance_ENUM status, String notes) {

        Attendance attendance = new Attendance(status, notes);
        attendance.setStudent(student);
        attendance.setTrainingSession(this);

        attendances.add(attendance);
    }
    public void removeStudent(Student student) {
        if (attendances != null) {
            attendances.removeIf(a -> a.getStudent().equals(student));
        }
    }



}