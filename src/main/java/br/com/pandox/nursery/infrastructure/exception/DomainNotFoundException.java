package br.com.pandox.nursery.infrastructure.exception;

import br.com.pandox.nursery.Model;

public abstract class DomainNotFoundException extends NotFoundException {

    public DomainNotFoundException(Class<? extends Model> clazz) {
        super(clazz.getSimpleName() + ".not.found");
    }
}
