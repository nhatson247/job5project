package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cvs")
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cvid", nullable = false)
    private long cvId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidateid", nullable = false)
    private Candidate candidate;

    @NotNull
    @Column(name = "cvfile", nullable = false)
    private String cvFile;

    @Column(name = "ismain")
    private boolean isMain;

}