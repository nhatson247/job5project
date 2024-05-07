package com.fpt.job5project.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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