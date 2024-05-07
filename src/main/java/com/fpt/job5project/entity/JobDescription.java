package com.fpt.job5project.entity;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "jobdescriptions")
public class JobDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "descriptionid", nullable = false)
    private long descriptionId;

    @ManyToOne(targetEntity = Job.class)
    @JoinColumn(name = "jobid", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Job job;

    @Column(name = "jobid", nullable = false)
    private long jobId;

    @Nationalized
    @Column(name = "description")
    private String description;

}