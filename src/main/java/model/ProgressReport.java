package model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ProgressReport {
    //id,reportDate,achievements,areasForImprovement. Onetomany between student and progress report
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDateTime reportDate;
    private String achievements;
    private String areasForImprovement;
    @Version
    private int version;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public ProgressReport() {
    }


    public ProgressReport(String achievements, String areasForImprovement) {
        this.reportDate = LocalDateTime.now();
        this.achievements = achievements;
        this.areasForImprovement = areasForImprovement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }



    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getAreasForImprovement() {
        return areasForImprovement;
    }

    public void setAreasForImprovement(String areasForImprovement) {
        this.areasForImprovement = areasForImprovement;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }





}
