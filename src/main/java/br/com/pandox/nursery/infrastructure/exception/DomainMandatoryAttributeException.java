package br.com.pandox.nursery.infrastructure.exception;


public class DomainMandatoryAttributeException extends AbstractException {

	public DomainMandatoryAttributeException(String attribute) {
		super(attribute);
	}
}
