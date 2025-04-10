package com.example.universityCanteenManagement.domain;

import com.example.universityCanteenManagement.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password", unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String email;
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public void registerUser(){}
    public void login(){};
    public void updateProfile(){};

}


