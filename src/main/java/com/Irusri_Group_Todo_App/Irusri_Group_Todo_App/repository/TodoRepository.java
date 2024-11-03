package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.repository;

import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface TodoRepository extends JpaRepository<Todo, Long> {

    // Find todos by user ID
    Optional<Todo> findById(Long userId);
    Page<Todo> findAll(Pageable pageable);

    // searching todos by title or description
    @Query("SELECT t FROM Todo t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Todo> searchTodos(@Param("keyword") String keyword);

}
