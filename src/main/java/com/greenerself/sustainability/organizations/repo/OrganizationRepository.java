package com.greenerself.sustainability.organizations.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import com.greenerself.sustainability.organizations.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
