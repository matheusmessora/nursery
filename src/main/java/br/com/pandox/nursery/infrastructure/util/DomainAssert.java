package br.com.pandox.nursery.infrastructure.util;

import br.com.pandox.nursery.view.exception.DomainMandatoryAttributeException;
import org.springframework.util.Assert;

public class DomainAssert {

    public static void hasText(String text, String attribute) {
        Assert.hasText(attribute);

        try {
            Assert.hasText(text);
        } catch (IllegalArgumentException ex) {
            throw new DomainMandatoryAttributeException(attribute);
        }
    }

    public static void notNull(Object object, String attribute) {
        Assert.hasText(attribute);

        try {
            Assert.notNull(object);
        } catch (IllegalArgumentException ex) {
            throw new DomainMandatoryAttributeException(attribute);
        }
    }
}
