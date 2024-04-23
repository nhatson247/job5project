package com.fpt.job5project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.*;

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

    @Nationalized
    @Column(name = "fullname")
    private String fullName;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "yearexpirence")
    private int yearExpirence;

    @Nationalized
    @Column(name = "bio")
    private String bio;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "photo")
    private String photo;

    @ManyToOne(targetEntity = Province.class)
    @JoinColumn(name = "provincename", insertable = false, updatable = false)
    private Province province;

    @Column(name = "provincename")
    @Nationalized
    private String provinceName;

    @Nationalized
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "candidate")
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "candidate")
    private List<CV> cvs = new ArrayList<>();

    @OneToMany(mappedBy = "candidate")
    private List<EmployerReview> employerReviews = new ArrayList<>();

    @OneToMany(mappedBy = "candidate")
    private List<JobReport> jobReports = new ArrayList<>();
}