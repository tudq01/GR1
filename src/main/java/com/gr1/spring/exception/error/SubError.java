package com.gr1.spring.exception.error;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class SubError {
    protected String object;
    protected  String field;
    protected  String message;
}
