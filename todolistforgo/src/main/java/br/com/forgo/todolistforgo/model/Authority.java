package br.com.forgo.todolistforgo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue
    @Column(name = "authority_id")
    private Long authorityId;
    @Column(name = "authority_name")
    private String authorityName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId")
    @JsonIgnore
    private Role role;
    public Authority(String authorityName) {
        this.authorityName = authorityName;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "Authority{" +
                "authorityId=" + authorityId +
                ", authorityName='" + authorityName + '\'' +
                ", role=" + role +
                '}';
    }

    public Authority(String authorityName, Role role) {
        this.authorityName = authorityName;
        this.role = role;
    }

    public Authority() {
    }
}
