package com.tosan;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * This class is meant to be extended by all REST resource "controllers".
 * It contains exception mapping and other common REST API functionality
 */
//@ControllerAdvice?
public abstract class AbstractRestHandler implements ApplicationEventPublisherAware {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected ApplicationEventPublisher eventPublisher;

    protected static final String  DEFAULT_PAGE_SIZE = "100";
    protected static final String DEFAULT_PAGE_NUM = "0";

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    /*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataFormatException.class)
    public
    @ResponseBody
    RestErrorInfo handleDataStoreException(DataFormatException ex, WebRequest request, HttpServletResponse response) {
        log.info("Converting Data Store exception to RestResponse : " + ex.getMessage());

        return new RestErrorInfo(ex, "You messed up.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public
    @ResponseBody
    RestErrorInfo handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, HttpServletResponse response) {
        log.info("ResourceNotFoundException handler:" + ex.getMessage());

        return new RestErrorInfo(ex, "Sorry I couldn't find it.");
    }

    //todo: replace with exception mapping
    public static <T> T checkResourceFound(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException("resource not found");
        }
        return resource;
    }*/

}
