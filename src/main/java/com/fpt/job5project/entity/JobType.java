package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "jobtypes")
public class JobType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typeid", nullable = false)
    private int typeid;

    @NotNull
    @Nationalized
    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(mappedBy = "jobType")
    private Set<Job> jobs = new LinkedHashSet<>();

}