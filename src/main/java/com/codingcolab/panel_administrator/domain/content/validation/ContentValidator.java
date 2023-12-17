package com.codingcolab.panel_administrator.domain.content.validation;

import com.codingcolab.panel_administrator.domain.content.dto.ContentInsertDTO;
import com.codingcolab.panel_administrator.domain.content.dto.ContentUpdateDTO;

public interface ContentValidator {
    public void validate(ContentInsertDTO contentInsertDTO, ContentUpdateDTO contentUpdateDTO);
}
