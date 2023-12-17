package com.codingcolab.panel_administrator.domain.content;

import com.codingcolab.panel_administrator.domain.content.dto.ContentInsertDTO;
import com.codingcolab.panel_administrator.domain.content.dto.ContentListDTO;
import com.codingcolab.panel_administrator.domain.content.dto.ContentResponseDTO;
import com.codingcolab.panel_administrator.domain.content.dto.ContentUpdateDTO;
import com.codingcolab.panel_administrator.domain.content.validation.ContentValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    final private ContentRepository contentRepository;
    final private List<ContentValidator> validators;


    public ContentService(ContentRepository contentRepository, List<ContentValidator> validators) {
        this.contentRepository = contentRepository;
        this.validators = validators;
    }


    public ContentResponseDTO getContent(Long id){
        Content content = contentRepository.getReferenceById(id);
        return new ContentResponseDTO(content);
    }

    public Page<ContentListDTO> findByFlag(Boolean flagFilter, Pageable pagination) {
        return contentRepository.findByFlag(flagFilter, pagination).map(ContentListDTO::new);
    }

    public ContentResponseDTO insetContent(ContentInsertDTO contentInsertDTO){
        validators.forEach(v -> v.validate(contentInsertDTO,null));
        Content content = contentRepository.save(new Content(contentInsertDTO));
        return new ContentResponseDTO(content);
    }

    public ContentResponseDTO updateContent(ContentUpdateDTO contentUpdateDTO){
        validators.forEach(v -> v.validate(null,contentUpdateDTO));
        Content content = contentRepository.getReferenceById(contentUpdateDTO.id());
        content.updateContent(contentUpdateDTO);
        return new ContentResponseDTO(content);
    }
}
