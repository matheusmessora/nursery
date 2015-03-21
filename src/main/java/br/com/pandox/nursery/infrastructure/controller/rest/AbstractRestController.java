package br.com.pandox.nursery.infrastructure.controller.rest;

import br.com.pandox.nursery.infrastructure.exception.NotFoundException;
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
public class AbstractRestController {

    private static Logger LOG = LogManager.getLogger();

    @Autowired
    public ReloadableResourceBundleMessageSource messageSource;


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErroDTO badRequestHandler(NotFoundException ex, HttpServletResponse response) {
        LOG.debug(ex.getCode());

        ErroDTO errorDTO = new ErroDTO();
        ErrorMessageDTO messageDTO = new ErrorMessageDTO();
        messageDTO.code = ex.getCode();
        messageDTO.message = messageSource.getMessage(ex.getCode(), null, Locale.getDefault());

        errorDTO.setError(messageDTO);
        return errorDTO;
    }
}
