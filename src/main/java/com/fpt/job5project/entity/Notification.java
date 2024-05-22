package com.fpt.job5project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificationid", nullable = false)
    private long notificationId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private User user;

    @Column(name = "userid", nullable = false)
    private long userId;

    @Column(name = "message")
    @Nationalized
    private String message;

    @Column(name = "postdate")
    private Date postDate;

    @Column(name = "seen")
    private boolean seen;

//    @JsonIgnore
//    @OneToMany(mappedBy = "user")
//    private List<Notification> notifications = new ArrayList<>();
}
