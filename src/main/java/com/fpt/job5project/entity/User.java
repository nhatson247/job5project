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

    // @MapsId
    // @OneToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name = "userid", nullable = false)
    // Candidate candidate;

    // @MapsId
    // @OneToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name = "employerid", nullable = false)
    // Employer employer;

    @NotNull
    @Column(name = "username", nullable = false)
    String userName;

    @NotNull
    @Nationalized
    @Column(name = "password", nullable = false)
    String password;

    @ManyToMany
    Set<Role> roles;

}