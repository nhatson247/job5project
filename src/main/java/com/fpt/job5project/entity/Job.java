package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @NotNull
    @Nationalized
    @Column(name = "jobname", nullable = false)
    private String jobName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employerid", nullable = false)
    private Employer employer;

    @NotNull
    @Column(name = "postdate", nullable = false)
    private Date postDate;

    @Column(name = "expirationdate")
    private Date expirationDate;

    @Column(name = "acceptdate")
    private Date acceptDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "typeid", nullable = false)
    private JobType jobType;

    @NotNull
    @Nationalized
    @Column(name = "jobposition", nullable = false)
    private String jobPosition;

    @NotNull
    @Column(name = "numposition", nullable = false)
    private int numPosition;

    @Column(name = "minsalary")
    private long minSalary;

    @Column(name = "maxsalary")
    private long maxSalary;

    @Column(name = "expirence")
    private int expirence;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location", nullable = false)
    private Province province;

    // @NotNull
    // @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name = "industryid", nullable = false)
    // private Industry industry;
    @ManyToMany
    private Set<Industry> industries = new LinkedHashSet<>();

    @Column(name = "isexpired")
    private boolean isExpired;

    @Column(name = "isremoved")
    private boolean isRemoved;

    @OneToMany(mappedBy = "job")
    private Set<Application> applications = new LinkedHashSet<>();

    @OneToMany(mappedBy = "job")
    private Set<JobBenefit> jobBenefits = new LinkedHashSet<>();

    @OneToMany(mappedBy = "job")
    private Set<JobDescription> jobDescriptions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "job")
    private Set<JobKey> jobKeys = new LinkedHashSet<>();

    @OneToMany(mappedBy = "job")
    private Set<JobReport> jobReports = new LinkedHashSet<>();

    @OneToMany(mappedBy = "job")
    private Set<JobRequirement> jobRequirements = new LinkedHashSet<>();

}