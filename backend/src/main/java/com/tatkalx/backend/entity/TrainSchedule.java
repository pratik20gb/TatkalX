package com.tatkalx.backend.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "train_schedules")
public class TrainSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @Column(name = "stop_order", nullable = false)
    private Integer stopOrder;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "halt_duration_minutes", nullable = false)
    private Integer haltDurationMinutes = 0;

    @Column(name = "distance_from_source_km", nullable = false)
    private Integer distanceFromSourceKm = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    protected TrainSchedule() {
    }

    public TrainSchedule(
            Train train,
            Station station,
            Integer stopOrder,
            LocalTime arrivalTime,
            LocalTime departureTime,
            Integer haltDurationMinutes,
            Integer distanceFromSourceKm
    ) {
        this.train = train;
        this.station = station;
        this.stopOrder = stopOrder;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.haltDurationMinutes = haltDurationMinutes;
        this.distanceFromSourceKm = distanceFromSourceKm;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Train getTrain() {
        return train;
    }

    public Station getStation() {
        return station;
    }

    public Integer getStopOrder() {
        return stopOrder;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public Integer getHaltDurationMinutes() {
        return haltDurationMinutes;
    }

    public Integer getDistanceFromSourceKm() {
        return distanceFromSourceKm;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}