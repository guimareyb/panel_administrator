package com.codingcolab.panel_administrator.domain.permission.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PermissionUpdateDTO(
        @NotNull
        Long id,

        @Size(min = 3, max = 100)
        String name,

        Long contentId
) {}
