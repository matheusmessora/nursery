package br.com.pandox.nursery.infrastructure.exception;

import org.springframework.util.Assert;

public abstract class AbstractException extends RuntimeException{

    private String code;

    public AbstractException(String code) {
        Assert.hasText(code, "Code must not be null");
        this.code = code.toLowerCase();
    }

    public String getCode() {
        return code;
    }
}
