package com.greenerself.sustainability.util.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseVO {
    private Object data;
    private String actionError;
    private String actionMessage;
    private boolean isSuccess;
}
