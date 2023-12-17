package com.codingcolab.panel_administrator.domain.content;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    /*
        Si flagFilter es null, se ignorará ese filtro y se devolverán todos los elementos.
        Si flagFilter es true o false, se buscarán elementos con el valor correspondiente en el campo flag.
     */
    @Query("SELECT c FROM Content c WHERE (:flagFilter IS NULL OR c.flag = :flagFilter)")
    Page<Content> findByFlag(Boolean flagFilter, Pageable pagination);

    @Query("""
            SELECT c.flag FROM Content c
            WHERE c.id =:contentId
            """)
    Boolean findActiveById(Long contentId);

    @Query("""
            SELECT EXISTS(
                SELECT c FROM Content c
                WHERE c.name =:name
                AND (:contentId IS NULL OR c.id != :contentId)
            ) AS duplicate_name
            """)
    Boolean findDuplicateName(String name, Long contentId);
}
