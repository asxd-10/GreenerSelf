package com.greenerself.sustainability.metrics.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "metric")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type", nullable = false)
    private String type;  // NUMERICAL or CATEGORICAL
}