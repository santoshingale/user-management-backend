package com.bridgelabz.usermanagement.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Setter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Responce {
    private int status;
    private String message;
    private Object object;

    public Responce(int status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public Responce(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
