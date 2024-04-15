package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "applicationstatus")
public class ApplicationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status", nullable = false)
    private long status;

    @NotNull
    @Nationalized
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "applicationStatus")
    private Set<Application> applications = new LinkedHashSet<>();

}