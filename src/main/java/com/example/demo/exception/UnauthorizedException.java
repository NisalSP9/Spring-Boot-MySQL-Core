package com.example.demo.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    final static Log logger = LogFactory.getLog(UnauthorizedException.class);

    public UnauthorizedException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("User don't have access for %s %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        logger.error("User don't have access for "+resourceName+" "+fieldName+ " in "+fieldValue+", HttpStatus :" + HttpStatus.UNAUTHORIZED);
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
