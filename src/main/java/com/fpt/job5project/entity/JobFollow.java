package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "jobfollows")
public class JobFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobfollowid", nullable = false)
    private long jobFollowId;

    @ManyToOne(targetEntity = Employer.class)
    @JoinColumn(name = "employerid", insertable = false, updatable = false)
    private Employer employer;

    @Column(name = "employerid")
    private long employerId;

    @ManyToOne(targetEntity = Candidate.class)
    @JoinColumn(name = "candidateid", insertable = false, updatable = false)
    private Candidate candidate;

    @Column(name = "candidateid")
    private long candidateId;

    @Column(name = "followdate", nullable = false)
    private Date followDate;
}
