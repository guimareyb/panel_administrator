package com.codingcolab.panel_administrator.domain.permission.dto;

import com.codingcolab.panel_administrator.domain.content.Content;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PermissionInsertDTO (
        @NotBlank
        @Size(min = 3, max = 100)
        String name,

        @NotNull
        Long contentId
){}
