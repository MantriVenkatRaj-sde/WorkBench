package com.mantrivenkatraj.workbench.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FieldUpdateRequest {
    @NotNull
    private String field;  // e.g., "bio", "primaryInterest"
    @NotNull
    private String value;  // The new value
}
