package com.fpt.job5project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employerreviews")
public class EmployerReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewid", nullable = false)
    private long reviewId;

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

    @Column(name = "score", nullable = false)
    private double score;

    @Column(name = "reviewdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date reviewDate;

}