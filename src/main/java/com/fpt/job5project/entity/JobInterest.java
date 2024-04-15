package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "jobinterests")
public class JobInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobinterestid", nullable = false)
    private long jobInterestId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jobid", nullable = false)
    private Job job;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidateid", nullable = false)
    private Candidate candidate;

    @NotNull
    @Column(name = "interestdate", nullable = false)
    private Date interestDate;
}
