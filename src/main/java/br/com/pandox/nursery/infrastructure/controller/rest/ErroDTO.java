package br.com.pandox.nursery.infrastructure.controller.rest;

import br.com.pandox.nursery.DataTransferObject;

public class ErroDTO implements DataTransferObject {

    private ErrorMessageDTO error;

    public ErrorMessageDTO getError() {
        return error;
    }

    public void setError(ErrorMessageDTO error) {
        this.error = error;
    }
}
