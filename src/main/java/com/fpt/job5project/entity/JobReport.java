package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "jobreports")
public class JobReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reportid", nullable = false)
    private long reportId;

    @ManyToOne(targetEntity = Job.class)
    @JoinColumn(name = "jobid", insertable = false, updatable = false)
    private Job job;

    @Column(name = "jobid", nullable = false)
    private long jobId;

    @ManyToOne(targetEntity = Candidate.class)
    @JoinColumn(name = "candidateid", insertable = false, updatable = false)
    private Candidate candidate;

    @Column(name = "candidateid")
    private long candidateId;

    @Column(name = "reportdate", nullable = false)
    private Date reportDate;

    @Nationalized
    @Column(name = "description", nullable = false)
    private String description;

}