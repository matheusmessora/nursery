package br.com.pandox.nursery.infrastructure.controller.rest;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.view.exception.DomainIllegalAttributeException;
import br.com.pandox.nursery.view.exception.DomainMandatoryAttributeException;
import br.com.pandox.nursery.view.exception.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@ControllerAdvice
public class AdviceRestController {

    private static Logger LOG = LogManager.getLogger();

    @Autowired
    public ReloadableResourceBundleMessageSource messageSource;


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErroDTO notFoundHandler(NotFoundException ex, HttpServletResponse response) {
        LOG.debug(ex.getCode());

        ErroDTO errorDTO = new ErroDTO();
        ErrorMessageDTO messageDTO = new ErrorMessageDTO();
        messageDTO.code = ex.getCode();
        messageDTO.message = messageSource.getMessage(ex.getCode(), null, Locale.getDefault());

        errorDTO.setError(messageDTO);
        return errorDTO;
    }

    @ExceptionHandler(DomainIllegalAttributeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDTO illegalArgumentExceptionHandler(DomainIllegalAttributeException ex, HttpServletResponse response) {
        LOG.debug(ex.getMessage());

        ErroDTO errorDTO = new ErroDTO();
        ErrorMessageDTO messageDTO = new ErrorMessageDTO();
        messageDTO.code = ex.getCode();
        messageDTO.message = messageSource.getMessage(ex.getCode(), null, Locale.getDefault());

        errorDTO.setError(messageDTO);
        return errorDTO;
    }

    @ExceptionHandler(DomainMandatoryAttributeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDTO mandatoryAttributeHandler(DomainMandatoryAttributeException ex, HttpServletResponse response) {
        LOG.debug(ex.getMessage());

        ErroDTO errorDTO = new ErroDTO();
        ErrorMessageDTO messageDTO = new ErrorMessageDTO();
        messageDTO.code = ex.getCode();
        messageDTO.message = messageSource.getMessage("nursery.attribute.mandatory", new Object[]{ex.getCode()}, Locale.getDefault());

        errorDTO.setError(messageDTO);
        return errorDTO;
    }

    @ExceptionHandler(CommandException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDTO illegalStateExceptionHandle(CommandException ex, HttpServletResponse response) {
        LOG.debug(ex.getMessage());

        ErroDTO errorDTO = new ErroDTO();
        ErrorMessageDTO messageDTO = new ErrorMessageDTO();
        messageDTO.setMessage(ex.getMessage());

        errorDTO.setError(messageDTO);
        return errorDTO;
    }
}
