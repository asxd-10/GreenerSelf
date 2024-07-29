package com.greenerself.sustainability.userlanding.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "agreements")
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // e.g., 'Terms of Service', 'Privacy Policy'

    @Column(nullable = false)
    private String version; // e.g., '1.0', '2.0'

    @Column(name = "content_url", nullable = false)
    private String contentUrl; // URL or file path to the agreement content

    @Column(name ="effective_date",nullable = false)
    private Date effectiveDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
}
