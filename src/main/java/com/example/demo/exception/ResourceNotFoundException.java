package com.example.demo.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    final static Log logger = LogFactory.getLog(ResourceNotFoundException.class);

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("Could not find %s with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        logger.error("Could not find "+resourceName+" with "+fieldName+ " in "+fieldValue+", HttpStatus :" + HttpStatus.NOT_FOUND);
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
