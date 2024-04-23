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
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicationid", nullable = false)
    private long applicationId;

    @ManyToOne(targetEntity = Job.class)
    @JoinColumn(name = "jobid", insertable = false, updatable = false)
    private Job job;

    @Column(name = "jobid", nullable = false)
    private long jobId;

    @ManyToOne(targetEntity = Candidate.class)
    @JoinColumn(name = "candidateid", insertable = false, updatable = false)
    private Candidate candidate;

    @Column(name = "candidateid", nullable = false)
    private long candidateId;

    @Column(name = "applicationdate", nullable = false)
    private Date applicationDate;

    @ManyToOne(targetEntity = ApplicationStatus.class)
    @JoinColumn(name = "status", insertable = false, updatable = false)
    private ApplicationStatus applicationStatus;

    @Column(name = "status", nullable = false)
    private long status;

    @Column(name = "cv")
    private String cv;

}