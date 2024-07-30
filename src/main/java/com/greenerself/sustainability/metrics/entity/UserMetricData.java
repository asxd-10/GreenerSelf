package com.greenerself.sustainability.metrics.entity;

import com.greenerself.sustainability.userlanding.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_metric_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserMetricData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "metric_id", nullable = false)
    private Metric metric;

    @Column(name = "metric_value", nullable = true)
    private Double metricValue;

    @Column(name = "metric_category", nullable = true)
    private String metricCategory;

    @Column(name = "measured_at", nullable = false)
    private Timestamp measuredAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
}
