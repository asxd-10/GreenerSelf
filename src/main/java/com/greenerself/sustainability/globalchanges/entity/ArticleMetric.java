package com.greenerself.sustainability.globalchanges.entity;

import com.greenerself.sustainability.metrics.entity.Metric;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "article_metrics")
public class ArticleMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_metrics_id_seq")
    @SequenceGenerator(name = "article_metrics_id_seq", sequenceName = "article_metrics_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "metric_id", nullable = false)
    private Metric metric;
}
