package com.codingcolab.panel_administrator.domain.permission;

import com.codingcolab.panel_administrator.domain.content.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository {

    @Query("SELECT p FROM Permission p WHERE (:flagFilter IS NULL OR p.flag = :flagFilter)")
    Page<Content> findByFlag(Boolean flagFilter, Pageable pagination);

    @Query("""
            SELECT p.flag FROM Permission p
            WHERE p.id =:permissionId
            """)
    Boolean findActiveById(Long permissionId);

    @Query("""
            SELECT EXISTS(
                SELECT p FROM Permission p
                WHERE p.name =:name
                AND (:permissionId IS NULL OR p.id != :permissionId)
            ) AS duplicate_name
            """)
    Boolean findDuplicateName(String name, Long permissionId);

}
