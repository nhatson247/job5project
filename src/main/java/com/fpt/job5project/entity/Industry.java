package com.fpt.job5project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "industries")
public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "industryid", nullable = false)
    private long industryId;

    @Nationalized
    @Column(name = "industryname", nullable = false)
    private String industryName;

    // @OneToMany(mappedBy = "industry")
    // private Set<Job> jobs = new LinkedHashSet<>();
    // @ManyToMany
    // private Set<Job> jobs = new LinkedHashSet<>();

}