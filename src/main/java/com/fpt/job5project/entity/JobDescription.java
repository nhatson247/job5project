package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "jobdescriptions")
public class JobDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "descriptionid", nullable = false)
    private long descriptionId;

    @ManyToOne(targetEntity = Job.class)
    @JoinColumn(name = "jobid", insertable = false, updatable = false)
    private Job job;

    @Column(name = "jobid")
    private long jobId;

    @Nationalized
    @Column(name = "description", nullable = false)
    private String description;

}