package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @Column(name = "candidateid", nullable = false)
    private long candidateId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidateid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @NotNull
    @Nationalized
    @Column(name = "fullname", nullable = false)
    private String fullName;

    @NotNull
    @Column(name = "birthdate", nullable = false)
    private Date birthDate;

    @Column(name = "yearexpirence")
    private int yearExpirence;

    @Nationalized
    @Column(name = "bio")
    private String bio;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "photo")
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provincename")
    private Province province;

    @Nationalized
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "candidate")
    private Set<Application> applications = new LinkedHashSet<>();

    @OneToMany(mappedBy = "candidate")
    private Set<CV> cvs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "candidate")
    private Set<EmployerReview> employerReviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "candidate")
    private Set<JobReport> jobReports = new LinkedHashSet<>();
}