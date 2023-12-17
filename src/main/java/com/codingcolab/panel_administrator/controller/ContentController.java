package com.codingcolab.panel_administrator.controller;

import com.codingcolab.panel_administrator.domain.content.ContentService;
import com.codingcolab.panel_administrator.domain.content.dto.ContentInsertDTO;
import com.codingcolab.panel_administrator.domain.content.dto.ContentListDTO;
import com.codingcolab.panel_administrator.domain.content.dto.ContentResponseDTO;
import com.codingcolab.panel_administrator.domain.content.dto.ContentUpdateDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/content")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponseDTO> getContentByID(
            @PathVariable Long id){

        return ResponseEntity.ok(contentService.getContent(id));
    }

    // "localhost:8080/content?flagFilter=true" -> Lista todos los activos
    // "localhost:8080/content?flagFilter=false" -> Lista todos los inactivos
    // "localhost:8080/content" -> Lista todos, tanto activos como inactivos
    @GetMapping
    public ResponseEntity<Page<ContentListDTO>> getContentsByFlag(
            @RequestParam(value = "flagFilter", required = false) Boolean flagFilter,
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {

        return ResponseEntity.ok(contentService.findByFlag(flagFilter,pageable));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ContentResponseDTO> insertContent (
            @Valid @RequestBody ContentInsertDTO contentInsertDTO,
            UriComponentsBuilder uriComponentsBuilder) {

        ContentResponseDTO contentResponseDTO = contentService.insetContent(contentInsertDTO);
        URI url = uriComponentsBuilder.path("/content/{id}").buildAndExpand(contentResponseDTO.id()).toUri();
        return ResponseEntity.created(url).body(contentResponseDTO);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ContentResponseDTO> updateContent (
            @Valid @RequestBody ContentUpdateDTO contentUpdateDTO){

        ContentResponseDTO responseDTO = contentService.updateContent(contentUpdateDTO);
        return ResponseEntity.ok(responseDTO);
    }

}
