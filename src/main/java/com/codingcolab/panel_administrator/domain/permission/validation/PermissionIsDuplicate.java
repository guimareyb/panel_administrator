package com.codingcolab.panel_administrator.domain.permission.validation;

import com.codingcolab.panel_administrator.domain.permission.PermissionRepository;
import com.codingcolab.panel_administrator.domain.permission.dto.PermissionInsertDTO;
import com.codingcolab.panel_administrator.domain.permission.dto.PermissionUpdateDTO;
import jakarta.validation.ValidationException;

public class PermissionIsDuplicate implements PermissionValidator{

    private final PermissionRepository repository;

    public PermissionIsDuplicate(PermissionRepository repository) {
        this.repository = repository;
    }

    /*
        (*) Validamos que, cuando se quiera realizar una inserción de un objeto de tipo "Permission"
        se valide que el campo "name" sea único, es decir, que ese no exista ningún otro registro
        con el nombre.

        (**) Validamos que, cuando se quiera realizar una actualización de un objeto de tipo "Permission"
        se valide que el campo "name" sea único
    */
    @Override
    public void validate(PermissionInsertDTO permissionInsertDTO, PermissionUpdateDTO permissionUpdateDTO) {
        Boolean permissionDuplicateName = null;

        //(*)
        if (permissionInsertDTO.name() != null) {
            permissionDuplicateName = repository.findDuplicateName(permissionInsertDTO.name(), null);
        }

        //(**)
        if (permissionUpdateDTO.name() != null) {
            permissionDuplicateName = repository.findDuplicateName(permissionUpdateDTO.name(), permissionUpdateDTO.id());
        }


        if (Boolean.TRUE.equals(permissionDuplicateName)) {
            throw new ValidationException("Permission with the same name already exists...");
        }
    }
}
