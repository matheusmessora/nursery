package br.com.pandox.nursery.infrastructure.controller.rest;

import br.com.pandox.nursery.DataTransferObject;

public class ErrorMessageDTO implements DataTransferObject {

    public String message;
    public String code;
    public String field;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
