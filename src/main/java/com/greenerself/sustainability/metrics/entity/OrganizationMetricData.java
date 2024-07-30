package com.greenerself.sustainability.metrics.entity;

import com.greenerself.sustainability.organizations.entity.Organization;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "organization_metric_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrganizationMetricData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

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
