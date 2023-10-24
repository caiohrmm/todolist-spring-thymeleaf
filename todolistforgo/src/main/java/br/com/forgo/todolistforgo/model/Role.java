package br.com.forgo.todolistforgo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "role",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH
            }
    )
    private Set<Authority> authorities = new HashSet<>();
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "userAccountId")
    @JsonIgnore
    private UserAccount userAccount;
    public Role(String roleName) {
        this.roleName = roleName;
    }
    public Role addAuthorities(Set<Authority> authorities){
        for (Authority authority : authorities) {
            if (authority != null){
                this.authorities.add(authority);
                authority.setRole(this);
            }
        }
        return this;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> grantedAuthorities = this.authorities
                .stream()
                .map(authority ->
                        new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toSet());

        grantedAuthorities.add(
                new SimpleGrantedAuthority("ROLE_"+this.roleName));
        return grantedAuthorities;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", authorities=" + authorities +
                ", userAccount=" + userAccount +
                '}';
    }

    public Role(String roleName, Set<Authority> authorities, UserAccount userAccount) {
        this.roleName = roleName;
        this.authorities = authorities;
        this.userAccount = userAccount;
    }

    public Role() {
    }
}