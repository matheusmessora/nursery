package br.com.pandox.nursery.domain;

public class CommandException extends RuntimeException {

    public CommandException(String templateMessage, Object... params) {
        super(String.format(templateMessage, params));
    }

    public CommandException(String message) {
        super(message);
    }
}
