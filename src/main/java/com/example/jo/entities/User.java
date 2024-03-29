package com.example.jo.entities;

import com.example.jo.entities.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Inheritance
@DiscriminatorColumn(name = "type_users", discriminatorType = DiscriminatorType.STRING)
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
    private String username;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String login, String encryptedPassword, UserRole role) {
        this.username = login;
        this.password = encryptedPassword;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ORGANISATEUR) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (this.role == UserRole.CONTROLLEUR) {
            return List.of(new SimpleGrantedAuthority("ROLE_CONTROLLEUR"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return username;
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
