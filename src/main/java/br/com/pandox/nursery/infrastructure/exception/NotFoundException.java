package br.com.pandox.nursery.infrastructure.exception;

public abstract class NotFoundException extends AbstractException {

    public NotFoundException(String code) {
        super(code);
    }
}
