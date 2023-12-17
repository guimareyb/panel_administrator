package com.codingcolab.panel_administrator.domain.permission;

import com.codingcolab.panel_administrator.domain.content.Content;
import com.codingcolab.panel_administrator.domain.permission.dto.PermissionInsertDTO;
import com.codingcolab.panel_administrator.domain.permission.dto.PermissionUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "permissions")
@Entity(name = "Permission")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;
    private boolean flag;

    public Permission(PermissionInsertDTO permissionInsertDTO, Content content){
        this.flag = true;
        this.name = permissionInsertDTO.name();
        this.content = content;
    }

    public void updatePermission(PermissionUpdateDTO permissionUpdateDTO, Content content){
        if (permissionUpdateDTO.name() != null){
            this.name = permissionUpdateDTO.name();
        }
        if (permissionUpdateDTO.contentId() != null){
            this.content = content;
        }
    }

    public void disableOrActivatePermission(boolean flag){
        this.flag = flag;
    }

}
