package com.greenerself.sustainability.organizations.service;

import com.greenerself.sustainability.organizations.model.OrganizationRegistrationRequest;
import com.greenerself.sustainability.organizations.repo.OrganizationRepository;
import com.greenerself.sustainability.userlanding.entity.User;
import com.greenerself.sustainability.userlanding.repo.UserRepository;
import com.greenerself.sustainability.util.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.greenerself.sustainability.organizations.entity.Organization;

import java.sql.Timestamp;

@Service
public class OrganizationService implements IOrganizationService{

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseVO registerOrganization(OrganizationRegistrationRequest request) {
        // Create Organization entity
        ResponseVO resp = new ResponseVO();
        try {
            Organization organization = new Organization();
            organization.setName(request.getOrganizationName());
            organization.setIndustry(request.getIndustry());
            organization.setSize(request.getSize());
            organization.setContactEmail(request.getContactEmail());
            organization.setContactNumber(request.getContactNumber());
            organization.setAddress(request.getAddress());
            organization.setCity(request.getCity());
            organization.setCountry(request.getCountry());
            organization.setPostalCode(request.getPostalCode());
            organization.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            organization.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            organizationRepository.save(organization);

            // Create User entity for admin
            User admin = new User();
            admin.setUsername(request.getAdminEmail());
            admin.setPassword(passwordEncoder.encode(request.getAdminPassword()));
            admin.setEmail(request.getAdminEmail());
            admin.setRole(request.getAdminRole());
            admin.setContactNumber(request.getContactNumber());
            admin.setActiveStatus('Y');
            admin.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            admin.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            userRepository.save(admin);
            resp.setData(organization);
            resp.addActionMessage("ORG_REG_SUCCESS", "Organization registration successful");

        }
        catch (Exception e)
        {
            resp.addActionError("ORG_REG_FAILURE", "Organization registration failed: " + e.getMessage());
            resp.setSuccess(false);
        }
        return resp;
    }
}
