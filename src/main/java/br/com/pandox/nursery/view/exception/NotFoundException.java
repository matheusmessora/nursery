package br.com.pandox.nursery.view.exception;

public abstract class NotFoundException extends AbstractException {

    public NotFoundException(String code) {
        super(code);
    }
}
