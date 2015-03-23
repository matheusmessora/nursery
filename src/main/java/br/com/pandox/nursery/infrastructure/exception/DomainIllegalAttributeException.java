package br.com.pandox.nursery.infrastructure.exception;

import org.springframework.util.Assert;

public class DomainIllegalAttributeException extends AbstractException {


    public DomainIllegalAttributeException(String model, String attribute) {
        super(model + "." + attribute + ".illegal");
        Assert.notNull(model);
        Assert.notNull(attribute);
    }
}
