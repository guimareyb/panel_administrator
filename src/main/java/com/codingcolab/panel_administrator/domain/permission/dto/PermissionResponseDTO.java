package com.codingcolab.panel_administrator.domain.permission.dto;

import com.codingcolab.panel_administrator.domain.content.Content;
import com.codingcolab.panel_administrator.domain.permission.Permission;

public record PermissionResponseDTO(Long id, String name, Long contentId, String contentName) {
    public PermissionResponseDTO(Permission permission){
        this(
                permission.getId(),
                permission.getName(),
                permission.getContent().getId(),
                permission.getContent().getName()
        );
    }
}
