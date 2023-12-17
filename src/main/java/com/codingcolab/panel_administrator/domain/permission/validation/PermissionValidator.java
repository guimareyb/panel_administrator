package com.codingcolab.panel_administrator.domain.permission.validation;

import com.codingcolab.panel_administrator.domain.permission.dto.PermissionInsertDTO;
import com.codingcolab.panel_administrator.domain.permission.dto.PermissionUpdateDTO;

public interface PermissionValidator {
    public void validate(PermissionInsertDTO permissionInsertDTO, PermissionUpdateDTO permissionUpdateDTO);
}
