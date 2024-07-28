package com.greenerself.sustainability.organizations.service;

import com.greenerself.sustainability.organizations.model.OrganizationRegistrationRequest;
import com.greenerself.sustainability.util.vo.ResponseVO;

public interface IOrganizationService {
    ResponseVO registerOrganization(OrganizationRegistrationRequest request);
}
