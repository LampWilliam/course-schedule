package com.courseschedule.common.exception;

import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
public abstract class AbstractCourseArrangeException extends RuntimeException implements Serializable {

    protected String message;
    protected int errorCode;

    private static final long serialVersionUID = 1L;

    protected AbstractCourseArrangeException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
}
