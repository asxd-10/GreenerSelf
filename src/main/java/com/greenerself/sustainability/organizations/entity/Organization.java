package com.greenerself.sustainability.organizations.entity;

import com.greenerself.sustainability.metrics.entity.Metric;
import com.greenerself.sustainability.userlanding.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "organizations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(length = 100)
    private String industry;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(length = 255)
    private String size;

    @Column(name = "contact_email", length = 255)
    private String contactEmail;

    @Column(name = "contact_number", length = 20)
    private String contactNumber;

    @Column(columnDefinition = "text")
    private String address;

    @Column(length = 255)
    private String city;

    @Column(length = 255)
    private String country;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;
}
