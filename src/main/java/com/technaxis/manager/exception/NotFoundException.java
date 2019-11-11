package com.technaxis.manager.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class NotFoundException extends RuntimeException {

    private String message;
}
