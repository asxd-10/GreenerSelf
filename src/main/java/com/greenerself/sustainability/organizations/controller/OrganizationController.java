package com.greenerself.sustainability.organizations.controller;

import com.greenerself.sustainability.organizations.model.OrganizationRegistrationRequest;
import com.greenerself.sustainability.organizations.service.IOrganizationService;
import com.greenerself.sustainability.util.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private IOrganizationService organizationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseVO> registerOrganization(@RequestBody OrganizationRegistrationRequest request) {
        ResponseVO resp = organizationService.registerOrganization(request);
        return ResponseEntity.status(resp.isSuccess() ? 200 : 400).body(resp);
    }
}
