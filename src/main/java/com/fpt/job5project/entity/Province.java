package com.fpt.job5project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "provinces")
public class Province {
    @Id
    @Nationalized
    @Column(name = "provincename", nullable = false)
    private String provinceName;

    @OneToMany(mappedBy = "province")
    private Set<Candidate> candidates = new LinkedHashSet<>();

    @OneToMany(mappedBy = "province")
    private Set<Employer> employers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "province")
    private Set<Job> jobs = new LinkedHashSet<>();

}