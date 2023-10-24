package br.com.forgo.todolistforgo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue
    private Long userAccountId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "userAccount",
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            }
    )
    private Set<Role> roles = new HashSet<>();
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            }
    )
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    public UserAccount(String username, String password){
        this.username = username;
        this.password = password;
        this.isAccountNonLocked = true;
        this.isAccountNonExpired = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
    }
    public UserAccount addRoles(Set<Role> roles){
        for (Role role : roles) {
            if (role != null){
                this.roles.add(role);
                role.setUserAccount(this);
            }
        }
        return this;
    }
    public UserAccount addUser(User user){
        if (user != null){
            this.user = user;
            user.setUserAccount(this);
        }
        return this;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return isAccountNonExpired == that.isAccountNonExpired && isAccountNonLocked == that.isAccountNonLocked && isCredentialsNonExpired == that.isCredentialsNonExpired && isEnabled == that.isEnabled && Objects.equals(userAccountId, that.userAccountId) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(roles, that.roles) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccountId, username, password, roles, user, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
    }

    public UserAccount(String username, String password, Set<Role> roles, User user, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.user = user;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public UserAccount() {
    }
}
