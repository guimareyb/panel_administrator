package com.codingcolab.panel_administrator.domain.content.validation;

import com.codingcolab.panel_administrator.domain.content.ContentRepository;
import com.codingcolab.panel_administrator.domain.content.dto.ContentInsertDTO;
import com.codingcolab.panel_administrator.domain.content.dto.ContentUpdateDTO;
import jakarta.validation.ValidationException;

public class ContentIsActive implements ContentValidator{

    private final ContentRepository repository;

    public ContentIsActive(ContentRepository repository) {
        this.repository = repository;
    }

    /*
        Validamos que cuando se quiera realizar una actualizacion de un objeto tipo Content
        se verifique que este se encuentre activo, caso contratio no se permite la actualizacion
    */
    @Override
    public void validate(ContentInsertDTO contentInsertDTO, ContentUpdateDTO contentUpdateDTO) {
        if (contentUpdateDTO == null || contentUpdateDTO.id() == null){
            return;
        }

        Boolean contentActive = repository.findActiveById(contentUpdateDTO.id());

        if (!contentActive){
            throw new ValidationException("You cannot update data for content that does not exist or is inactive...");
        }
    }
}
