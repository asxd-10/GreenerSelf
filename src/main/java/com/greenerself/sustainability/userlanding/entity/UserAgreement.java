package com.greenerself.sustainability.userlanding.entity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_agreements")
public class UserAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "agreement_id", nullable = false)
    private Agreement agreement;

    @Column(nullable = false)
    private boolean accepted;

    @Column(name = "accepted_at", nullable = false, updatable = false)
    private Timestamp acceptedAt;
}
