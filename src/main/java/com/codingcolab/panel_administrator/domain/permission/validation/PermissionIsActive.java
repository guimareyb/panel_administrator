package com.codingcolab.panel_administrator.domain.permission.validation;

import com.codingcolab.panel_administrator.domain.permission.PermissionRepository;
import com.codingcolab.panel_administrator.domain.permission.dto.PermissionInsertDTO;
import com.codingcolab.panel_administrator.domain.permission.dto.PermissionUpdateDTO;
import jakarta.validation.ValidationException;

public class PermissionIsActive implements PermissionValidator{

    private final PermissionRepository repository;

    public PermissionIsActive(PermissionRepository repository) {
        this.repository = repository;
    }


    /*
        Validamos que cuando se quiera realizar una actualizacion de un objeto tipo Permission
        se verifique que este se encuentre activo, caso contratio no se permite la actualizacion
    */
    @Override
    public void validate(PermissionInsertDTO permissionInsertDTO, PermissionUpdateDTO permissionUpdateDTO) {
        if (permissionUpdateDTO == null || permissionUpdateDTO.id() == null){
            return;
        }

        Boolean permissionActive = repository.findActiveById(permissionUpdateDTO.id());

        if (!permissionActive){
            throw new ValidationException("You cannot update data for permission that does not exist or is inactive...");
        }
    }
}
