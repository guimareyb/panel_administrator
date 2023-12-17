package com.codingcolab.panel_administrator.domain.content.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContentInsertDTO (
        @NotBlank
        @Size(min = 3, max = 100)
        String name,


        @Size(min = 3, max = 200)
        String description
){}
