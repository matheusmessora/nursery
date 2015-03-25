package br.com.pandox.nursery.view.exception;


public class DomainMandatoryAttributeException extends AbstractException {

	public DomainMandatoryAttributeException(String attribute) {
		super(attribute);
	}
}
