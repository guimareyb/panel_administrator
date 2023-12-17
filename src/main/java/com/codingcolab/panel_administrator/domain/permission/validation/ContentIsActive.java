package com.codingcolab.panel_administrator.domain.permission.validation;

import com.codingcolab.panel_administrator.domain.content.ContentRepository;
import com.codingcolab.panel_administrator.domain.permission.dto.PermissionInsertDTO;
import com.codingcolab.panel_administrator.domain.permission.dto.PermissionUpdateDTO;
import jakarta.validation.ValidationException;

public class ContentIsActive  implements PermissionValidator{

    private final ContentRepository repository;

    public ContentIsActive(ContentRepository contentRepository) {
        this.repository = contentRepository;
    }

    @Override
    public void validate(PermissionInsertDTO permissionInsertDTO, PermissionUpdateDTO permissionUpdateDTO){

        Boolean contentActive = null;

        if (permissionInsertDTO.contentId() != null){
            contentActive = repository.findActiveById(permissionInsertDTO.contentId());
        }

        if (permissionUpdateDTO.contentId() != null){
            contentActive = repository.findActiveById(permissionUpdateDTO.contentId());
        }

        if (Boolean.FALSE.equals(contentActive)){
            throw new ValidationException("You cannot enter the id of a content that does not exist or is inactive...");
        }
    }
}
