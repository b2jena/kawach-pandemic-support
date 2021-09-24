package com.stackroute.usermanagementservice.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class NullValueException extends Exception {
    private String message;
}
