package com.gr1.spring.exception.error;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Data

public class ValidationError extends  SubError{
    public ValidationError(String object, String field, String message) {
        super(object, field, message);
    }
    public ValidationError(String object, String message) {
        super();
        this.object = object;
        this.message = message;
    }
}
