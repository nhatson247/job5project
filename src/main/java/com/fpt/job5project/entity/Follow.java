package com.fpt.job5project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "follows")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "followid", nullable = false)
    private long followId;

    @ManyToOne(targetEntity = Employer.class)
    @JoinColumn(name = "employerid", insertable = false, updatable = false)
    private Employer employer;

    @Column(name = "employerid", nullable = false)
    private long employerId;

    @ManyToOne(targetEntity = Candidate.class)
    @JoinColumn(name = "candidateid", insertable = false, updatable = false)
    private Candidate candidate;

    @Column(name = "candidateid", nullable = false)
    private long candidateId;

    @Column(name = "followdate")
    private Date followDate;
}
