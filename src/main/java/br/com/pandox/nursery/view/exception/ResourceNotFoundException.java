package br.com.pandox.nursery.view.exception;

public class ResourceNotFoundException extends NotFoundException {


    public ResourceNotFoundException(String code) {
        super(code + ".not.found");
    }
}
