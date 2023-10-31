package br.com.forgo.todolistforgo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "completed_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime completionDate;

    @Column(name = "expected_date")
    public String expectedDate;

    @Column
    public Boolean completedTaskOnTime;

    @Column
    private Boolean done;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDateTime = localDateTime.format(formatter);
        this.createdAt = LocalDateTime.parse(formattedDateTime, formatter);
    }

    public String getFormatDate(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return localDateTime.format(formatter);
    }

    public LocalDateTime getConvertedDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // faz o parsing e setar o horário para meia-noite
        return LocalDate.parse(date, formatter).atStartOfDay();
    }


    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public Boolean getCompletedTaskOnTime() {
        return completedTaskOnTime;
    }

    public void setCompletedTaskOnTime(Boolean completedTaskOnTime) {
        this.completedTaskOnTime = completedTaskOnTime;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(createdAt, task.createdAt) && Objects.equals(completionDate, task.completionDate) && Objects.equals(expectedDate, task.expectedDate) && Objects.equals(completedTaskOnTime, task.completedTaskOnTime) && Objects.equals(done, task.done) && Objects.equals(user, task.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, createdAt, completionDate, expectedDate, completedTaskOnTime, done, user);
    }
}