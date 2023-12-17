package com.codingcolab.panel_administrator.domain.content.dto;

import com.codingcolab.panel_administrator.domain.content.Content;

public record ContentResponseDTO(
        Long id,
        String name,
        String description
) {
    public ContentResponseDTO(Content content){
        this(
                content.getId(),
                content.getName(),
                content.getDescription()
        );
    }
}
