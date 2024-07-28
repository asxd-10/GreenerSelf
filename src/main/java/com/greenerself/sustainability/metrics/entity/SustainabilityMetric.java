package com.greenerself.sustainability.metrics.entity;

import com.greenerself.sustainability.userlanding.entity.User;
import com.greenerself.sustainability.organizations.entity.Organization;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "sustainability_metrics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SustainabilityMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(name = "metric_name", nullable = false, length = 50)
    private String metricName;

    @Column(name = "metric_type", nullable = false, length = 20)
    private String metricType;

    @Column(name = "metric_value")
    private Double metricValue;

    @Column(name = "metric_category", length = 50)
    private String metricCategory;

    @Column(name = "measured_at", nullable = false)
    private Timestamp measuredAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
