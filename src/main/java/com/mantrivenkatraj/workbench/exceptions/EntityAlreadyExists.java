package com.mantrivenkatraj.workbench.exceptions;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExists extends ApplicationException {

    public EntityAlreadyExists(String entity) {
        super(
                "Account with this " + entity + " already exists",
                HttpStatus.CONFLICT.value()
        );
    }
}
