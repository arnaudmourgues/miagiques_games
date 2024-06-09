package com.example.jo.entities;

import com.example.jo.entities.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Inheritance
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public abstract class User implements UserDetails {
    //to check https://en.wikibooks.org/wiki/Java_Persistence/Inheritance#Single_Table_Inheritance
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String email, String encryptedPassword, UserRole role) {
        this.email = email;
        this.password = encryptedPassword;
        this.role = role;
    }

    public User(String login, String encryptedPassword, UserRole userRole, String nom, String prenom) {
        this.email = login;
        this.password = encryptedPassword;
        this.role = userRole;
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (role) {
            case ORGANISATEUR -> List.of(new SimpleGrantedAuthority("ROLE_ORGANISATEUR"));
            case CONTROLEUR -> List.of(new SimpleGrantedAuthority("ROLE_CONTROLEUR"));
            case SPECTATEUR -> List.of(new SimpleGrantedAuthority("ROLE_SPECTATEUR"));
            case PARTICIPANT -> List.of(new SimpleGrantedAuthority("ROLE_PARTICIPANT"));
        };
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String toString() {
        return "User(id=" + this.getId() + ", nom=" + this.getNom() + ", prenom=" + this.getPrenom() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", role=" + this.getRole() + ")";
    }
}
