package model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Aikido_ENUM rank;
    private LocalDate joinDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Transient
    private int membershipDuration;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<Attendance> attendances;


    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ProgressReport> progressReports;

    public Student() {}

    public Student(String name, String email, Aikido_ENUM rank, LocalDate joinDate) {
        this.name = name;
        this.email = email;
        this.rank = rank;
        this.joinDate = joinDate;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @PostLoad
    protected void calculateMembershipDuration() {
        this.membershipDuration = LocalDate.now().getYear() - joinDate.getYear();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Aikido_ENUM getRank() { return rank; }
    public LocalDate getJoinDate() { return joinDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public int getMembershipDuration() { return membershipDuration; }


    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setRank(Aikido_ENUM rank) { this.rank = rank; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public void setMembershipDuration(int membershipDuration) { this.membershipDuration = membershipDuration; }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<ProgressReport> getProgressReports() {
        return progressReports;
    }

    public void setProgressReports(List<ProgressReport> progressReports) {
        this.progressReports = progressReports;
    }

    public void addAttendance(Attendance attendance) {
        this.attendances.add(attendance);
        attendance.setStudent(this);
    }



}