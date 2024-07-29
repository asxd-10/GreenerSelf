package com.greenerself.sustainability.userlanding.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAgreementVO {
    private Long userId;
    private AgreementType agreementType;
    private Boolean agreed;

    public enum AgreementType {
        TERMS_AND_CONDITIONS,
        PRIVACY_POLICY
    }
}

