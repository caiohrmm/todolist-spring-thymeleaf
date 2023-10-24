package br.com.forgo.todolistforgo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 50)
    private String username;

    @Column(name = "created_user", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdUser;

    @Column(nullable = false, length = 70)
    private String email;

    @Column(nullable = false, length = 20)
    private String phone;

    @Transient
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();


    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private UserAccount userAccount;

    public User(String username, LocalDateTime createdUser, String email, String phone, String password, List<Task> tasks, UserAccount userAccount) {
        this.username = username;
        this.createdUser = createdUser;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.tasks = tasks;
        this.userAccount = userAccount;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(LocalDateTime createdUser) {
        this.createdUser = createdUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(createdUser, user.createdUser) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(password, user.password) && Objects.equals(tasks, user.tasks) && Objects.equals(userAccount, user.userAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, createdUser, email, phone, password, tasks, userAccount);
    }
}
