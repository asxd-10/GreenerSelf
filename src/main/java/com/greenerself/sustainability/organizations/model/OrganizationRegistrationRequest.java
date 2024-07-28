package com.greenerself.sustainability.organizations.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrganizationRegistrationRequest {
    private String organizationName;
    private String industry;
    private String size;
    private String contactEmail;
    private String contactNumber;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String adminName;
    private String adminEmail;
    private String adminPassword;
    private String adminRole;  // e.g., "ADMIN"
}
