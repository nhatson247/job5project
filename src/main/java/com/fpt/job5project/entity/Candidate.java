package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidateid", nullable = false)
    private long candidateId;

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

    // @OneToOne(mappedBy = "candidate")
    // private User user;

}