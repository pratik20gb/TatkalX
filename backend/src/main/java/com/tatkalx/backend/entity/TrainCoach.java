package com.tatkalx.backend.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "train_coaches")
public class TrainCoach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @Column(name = "coach_number", nullable = false, length = 10)
    private String coachNumber;


    @Column(name = "coach_type", nullable = false, length = 10)
    private CoachType coachType;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    protected TrainCoach() {
    }

    public TrainCoach(Train train, String coachNumber, CoachType coachType, Integer totalSeats) {
        this.train = train;
        this.coachNumber = coachNumber;
        this.coachType = coachType;
        this.totalSeats = totalSeats;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Train getTrain() {
        return train;
    }

    public String getCoachNumber() {
        return coachNumber;
    }

    public CoachType getCoachType() {
        return coachType;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}