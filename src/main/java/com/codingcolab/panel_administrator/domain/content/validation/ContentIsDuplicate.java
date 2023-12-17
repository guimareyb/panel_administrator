package com.codingcolab.panel_administrator.domain.content.validation;

import com.codingcolab.panel_administrator.domain.content.ContentRepository;
import com.codingcolab.panel_administrator.domain.content.dto.ContentInsertDTO;
import com.codingcolab.panel_administrator.domain.content.dto.ContentUpdateDTO;
import jakarta.validation.ValidationException;

public class ContentIsDuplicate implements ContentValidator {

    private final ContentRepository repository;

    public ContentIsDuplicate(ContentRepository repository) {
        this.repository = repository;
    }


    /*
        (*) Validamos que, cuando se quiera realizar una inserción de un objeto de tipo "Content"
        se valide que el campo "name" sea único, es decir, que ese no exista ningún otro registro
        con el nombre.

        (**) Validamos que, cuando se quiera realizar una actualización de un objeto de tipo "Content"
        se valide que el campo "name" sea único
    */

    @Override
    public void validate(ContentInsertDTO contentInsertDTO, ContentUpdateDTO contentUpdateDTO) {
        Boolean contentDuplicateName = null;

        //(*)
        if (contentInsertDTO.name() != null) {
            contentDuplicateName = repository.findDuplicateName(contentInsertDTO.name(), null);
        }

        //(**)
        if (contentUpdateDTO.name() != null) {
            contentDuplicateName = repository.findDuplicateName(contentUpdateDTO.name(), contentUpdateDTO.id());
        }


        if (Boolean.TRUE.equals(contentDuplicateName)) {
            throw new ValidationException("Content with the same name already exists...");
        }
    }

}
