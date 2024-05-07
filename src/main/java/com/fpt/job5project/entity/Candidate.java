package com.fpt.job5project.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "candidateid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Nationalized
    @Column(name = "fullname")
    private String fullName;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "yearexperience")
    private int yearExperience;

    @Nationalized
    @Column(name = "currentjob")
    private String currentJob;

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

    @OneToMany(mappedBy = "candidate")
    private List<JobInterest> jobInterests = new ArrayList<>();

    @OneToMany(mappedBy = "candidate")
    private List<Follow> follows = new ArrayList<>();

    @OneToMany(mappedBy = "candidate")
    private List<TimeLine> timeLines = new ArrayList<>();
}