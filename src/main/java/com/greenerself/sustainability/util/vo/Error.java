package com.greenerself.sustainability.util.vo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Error {
    private String code;
    private String errorMessage;
}
