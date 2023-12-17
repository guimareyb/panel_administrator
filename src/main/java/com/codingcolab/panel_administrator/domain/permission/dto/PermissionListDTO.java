package com.codingcolab.panel_administrator.domain.permission.dto;

import com.codingcolab.panel_administrator.domain.content.Content;
import com.codingcolab.panel_administrator.domain.permission.Permission;

public record PermissionListDTO(Long id, String name, Long contentId, String contentName, boolean flag) {
    public PermissionListDTO(Permission permission){
        this(
                permission.getId(),
                permission.getName(),
                permission.getContent().getId(),
                permission.getContent().getName(),
                permission.isFlag()
        );
    }
}
