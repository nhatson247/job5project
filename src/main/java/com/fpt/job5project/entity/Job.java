package com.fpt.job5project.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @Builder.Default
    @ManyToMany
    private Set<Industry> industries = new LinkedHashSet<>();

    @Column(name = "isexpired")
    private boolean isExpired;

    @Column(name = "isremoved")
    private boolean isRemoved;

    @Builder.Default
    @OneToMany(mappedBy = "job")
    private List<Application> applications = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "job")
    private List<JobBenefit> jobBenefits = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "job")
    private List<JobDescription> jobDescriptions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "job")
    private List<JobKey> jobKeys = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "job")
    private List<JobReport> jobReports = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "job")
    private List<JobRequirement> jobRequirements = new ArrayList<>();

}