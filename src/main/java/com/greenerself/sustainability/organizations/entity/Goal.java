package com.greenerself.sustainability.organizations.entity;

import com.greenerself.sustainability.metrics.entity.SustainabilityMetric;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "goal_type", nullable = false)
    private String goalType;

    @Column(name = "target_value", nullable = false)
    private Double targetValue;

    @Column(name = "target_date", nullable = false)
    private LocalDateTime targetDate;

    @Column(name = "is_achieved", nullable = false)
    private Boolean isAchieved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sustainability_metric_id", nullable = false)
    private SustainabilityMetric sustainabilityMetric;
}
