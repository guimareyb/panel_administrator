package com.codingcolab.panel_administrator.domain.content.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ContentUpdateDTO(
        @NotNull
        Long id,

        @Size(min = 3, max = 100)
        String name,

        @Size(min = 3, max = 200)
        String description
) {}
