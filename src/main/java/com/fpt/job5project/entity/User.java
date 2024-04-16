package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

import org.hibernate.annotations.Nationalized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid", nullable = false)
    long userId;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Employer employer;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Candidate candidate;

    @NotNull
    @Column(name = "username", nullable = false)
    String userName;

    @NotNull
    @Nationalized
    @Column(name = "password", nullable = false)
    String password;

    Set<String> roles;

}