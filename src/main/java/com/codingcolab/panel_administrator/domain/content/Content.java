package com.codingcolab.panel_administrator.domain.content;

import com.codingcolab.panel_administrator.domain.content.dto.ContentInsertDTO;
import com.codingcolab.panel_administrator.domain.content.dto.ContentUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "contents")
@Entity(name = "Content")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private boolean flag;

    public Content(ContentInsertDTO contentInsertDTO){
        this.flag = true;
        this.name = contentInsertDTO.name();
        this.description = contentInsertDTO.description();
    }

    public void updateContent(ContentUpdateDTO contentUpdateDTO){
        if (contentUpdateDTO.name() != null){
            this.name = contentUpdateDTO.name();
        }
        if (contentUpdateDTO.description() != null){
            this.description = contentUpdateDTO.description();
        }
    }

    public void disableOrActivateContent(boolean flag){
        this.flag = flag;
    }

}
