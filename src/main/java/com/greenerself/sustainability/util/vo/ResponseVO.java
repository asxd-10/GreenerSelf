package com.greenerself.sustainability.util.vo;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseVO {
    private Object data;
    private List<Message> actionMessages;
    private List<Error> actionErrors;
    private boolean isSuccess = true;

    // Convenience method to add a single message
    public void addActionMessage(String code, String message) {
        if (actionMessages == null) {
            actionMessages = new ArrayList<>();
        }
        actionMessages.add(new Message(code, message));
    }

    // Convenience method to add a single error
    public void addActionError(String code, String message) {
        if (actionErrors == null) {
            actionErrors = new ArrayList<>();
        }
        actionErrors.add(new Error(code, message));
        this.isSuccess = false;
    }
}

