package br.com.pandox.nursery.infrastructure.controller.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErroDTO {

    private ErrorMessageDTO error;

    public ErrorMessageDTO getError() {
        return error;
    }

    public void setError(ErrorMessageDTO error) {
        this.error = error;
    }
}
