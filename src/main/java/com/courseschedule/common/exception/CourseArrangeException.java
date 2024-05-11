package com.courseschedule.common.exception;

import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
public class CourseArrangeException extends AbstractCourseArrangeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public CourseArrangeException(String message) {
        super(message, 9999);
    }
}
