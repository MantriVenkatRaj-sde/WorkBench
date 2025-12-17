package com.mantrivenkatraj.workbench.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ApplicationException {

    public EntityNotFoundException(String entity) {
        super(
                entity + " not found / doesn't exist",
                HttpStatus.CONFLICT.value()
        );
    }
}
