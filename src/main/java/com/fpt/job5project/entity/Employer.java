package com.fpt.job5project.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "employers")
public class Employer {
    @Id
    @Column(name = "employerid", nullable = false)
    private long employerId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "employerid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Nationalized
    @Column(name = "employername")
    private String employerName;

    @Nationalized
    @Column(name = "description")
    private String description;

    @ManyToOne(targetEntity = Rank.class)
    @JoinColumn(name = "rankid", insertable = false, updatable = false)
    private Rank rank;

    @Column(name = "rankid", nullable = false)
    private long rankId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "photo")
    private String photo;

    @Column(name = "background")
    private String background;

    @ManyToOne(targetEntity = Province.class)
    @JoinColumn(name = "provincename", insertable = false, updatable = false)
    private Province province;

    @Column(name = "provincename")
    @Nationalized
    private String provinceName;

    @Nationalized
    @Column(name = "address")
    private String address;

    @Column(name = "reviewscore")
    private double reviewScore;

    @JsonIgnore
    @OneToMany(mappedBy = "employer")
    private List<EmployerReview> employerReviews = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "employer")
    private List<Job> jobs = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "employer")
    private List<Follow> follows = new ArrayList<>();

    @Column(name = "approved")
    private boolean approved;

}