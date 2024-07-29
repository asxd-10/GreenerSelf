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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "agreement_type", nullable = false)
    private String agreementType;

    @Column(name = "agreement_version", nullable = false)
    private String agreementVersion;

    @Column(name = "accepted_at", nullable = false)
    private Timestamp acceptedAt;
}
