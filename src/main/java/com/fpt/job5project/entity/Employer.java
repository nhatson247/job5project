package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employers")
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employerid", nullable = false)
    private long employerId;

    @NotNull
    @Nationalized
    @Column(name = "employername", nullable = false)
    private String employerName;

    @Nationalized
    @Column(name = "description")
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rank", nullable = false)
    private Rank rank;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "photo")
    private String photo;

    @Column(name = "background")
    private String backGround;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provincename")
    private Province province;

    @Nationalized
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "reviewscore", nullable = false)
    private int reviewScore;

    @OneToMany(mappedBy = "employer")
    private Set<EmployerReview> employerReviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "employer")
    private Set<Job> jobs = new LinkedHashSet<>();

    // @OneToOne(mappedBy = "employer")
    // private User user;

}