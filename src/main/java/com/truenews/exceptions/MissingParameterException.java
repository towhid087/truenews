package com.truenews.exceptions;

public class MissingParameterException extends RuntimeException {
    public MissingParameterException(String parameterName) {
        super("Missing required query parameter: " + parameterName);
    }
}
