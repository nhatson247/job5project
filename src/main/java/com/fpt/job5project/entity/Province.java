package com.fpt.job5project.entity;

import jakarta.persistence.*;
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
@Table(name = "provinces")
public class Province {
    @Id
    @Nationalized
    @Column(name = "provincename", nullable = false)
    private String provinceName;

    @OneToMany(mappedBy = "province")
    private List<Candidate> candidates = new ArrayList<>();

    @OneToMany(mappedBy = "province")
    private List<Employer> employers = new ArrayList<>();

    @OneToMany(mappedBy = "province")
    private List<Job> jobs = new ArrayList<>();

}