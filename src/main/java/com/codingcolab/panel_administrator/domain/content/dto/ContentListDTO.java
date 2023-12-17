package com.codingcolab.panel_administrator.domain.content.dto;

import com.codingcolab.panel_administrator.domain.content.Content;

public record ContentListDTO(Long id, String name, String description, boolean flag) {
    public ContentListDTO(Content content){
        this(
                content.getId(),
                content.getName(),
                content.getDescription(),
                content.isFlag()
        );
    }
}
