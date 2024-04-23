package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobid", nullable = false)
    private long jobId;

    @Nationalized
    @Column(name = "jobname", nullable = false)
    private String jobName;

    @ManyToOne(targetEntity = Employer.class)
    @JoinColumn(name = "employerid", insertable = false, updatable = false)
    private Employer employer;

    @Column(name = "employerid", nullable = false)
    private long employerId;

    @Column(name = "postdate", nullable = false)
    private Date postDate;

    @Column(name = "expirationdate")
    private Date expirationDate;

    @Column(name = "acceptdate")
    private Date acceptDate;

    @ManyToOne(targetEntity = JobType.class)
    @JoinColumn(name = "typeid", insertable = false, updatable = false)
    private JobType jobType;

    @Column(name = "typeid", nullable = false)
    private int typeId;

    @Nationalized
    @Column(name = "jobposition", nullable = false)
    private String jobPosition;

    @Column(name = "numposition", nullable = false)
    private int numPosition;

    @Column(name = "minsalary")
    private long minSalary;

    @Column(name = "maxsalary")
    private long maxSalary;

    @Column(name = "yearexpirence")
    private int yearExpirence;

    @ManyToOne(targetEntity = Province.class)
    @JoinColumn(name = "location", insertable = false, updatable = false)
    private Province province;

    @Column(name = "location")
    @Nationalized
    private String location;

    @ManyToMany
    private Set<Industry> industries = new LinkedHashSet<>();

    @Column(name = "isexpired")
    private boolean isExpired;

    @Column(name = "isremoved")
    private boolean isRemoved;

    @OneToMany(mappedBy = "job")
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "job")
    private List<JobBenefit> jobBenefits = new ArrayList<>();

    @OneToMany(mappedBy = "job")
    private List<JobDescription> jobDescriptions = new ArrayList<>();

    @OneToMany(mappedBy = "job")
    private List<JobKey> jobKeys = new ArrayList<>();

    @OneToMany(mappedBy = "job")
    private List<JobReport> jobReports = new ArrayList<>();

    @OneToMany(mappedBy = "job")
    private List<JobRequirement> jobRequirements = new ArrayList<>();

}