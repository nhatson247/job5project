package com.fpt.job5project.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "ranks")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rankid", nullable = false)
    private long rankId;

    @Nationalized
    @Column(name = "rankname")
    private String rankName;

    @Nationalized
    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "displaytime")
    private int displayTime;

    @Column(name = "reuptimes")
    private int reupTimes;

    @Column(name = "numapplications")
    private int numApplications;

    @Column(name = "limitpost")
    private int limitPost;

    @Column(name = "photo")
    private String photo;

    @JsonIgnore
    @OneToMany(mappedBy = "rank")
    private List<Employer> employers = new ArrayList<>();

}