package ru.lidzhiev.exchangerateservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private String url;
    private String errorMessage;
    private int status;

    public Response(String errorMessage, int status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public Response(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
