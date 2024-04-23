package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "applicationstatus")
public class ApplicationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status", nullable = false)
    private long status;

    @Nationalized
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "applicationStatus")
    private List<Application> applications = new ArrayList<>();

}