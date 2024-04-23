package com.fpt.job5project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
    @OneToOne(fetch = FetchType.LAZY, optional = false)
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
    private String backGround;

    @ManyToOne(targetEntity = Province.class)
    @JoinColumn(name = "provincename", insertable = false, updatable = false)
    private Province province;

    @Column(name = "provincename")
    @Nationalized
    private String provinceName;

    @Nationalized
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "reviewscore", nullable = false)
    private double reviewScore;

    @JsonIgnore
    @OneToMany(mappedBy = "employer")
    private List<EmployerReview> employerReviews = new ArrayList<>();

    @OneToMany(mappedBy = "employer")
    private List<Job> jobs = new ArrayList<>();

}