package com.fpt.job5project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cvs")
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cvid", nullable = false)
    private long cvId;

    @ManyToOne(targetEntity = Candidate.class)
    @JoinColumn(name = "candidateid", insertable = false, updatable = false)
    private Candidate candidate;

    @Column(name = "candidateid")
    private long candidateId;


    @NotNull
    @Column(name = "cvfile", nullable = false)
    private String cvFile;

    @Column(name = "ismain")
    private boolean isMain;

}