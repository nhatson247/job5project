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
@Table(name = "ranks")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rankid", nullable = false)
    private long rankId;

    @NotNull
    @Nationalized
    @Column(name = "rankname", nullable = false)
    private String rankName;

    @NotNull
    @Nationalized
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "rank")
    private Set<Employer> employers = new LinkedHashSet<>();

}