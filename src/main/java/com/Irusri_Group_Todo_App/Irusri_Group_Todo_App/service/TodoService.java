package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.service;

import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.config.JwtTokenUtil;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.exceptions.ResourceNotFoundException;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.model.Todo;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.model.User;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TodoService {
    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        logger.info("Creating a new Todo with title: {}", todo.getTitle());

        Todo createdTodo = todoRepository.save(todo);

        logger.info("Todo created successfully with id: {}", createdTodo.getId());
        return createdTodo;
    }


    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " + id));
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = getTodoById(id);
        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setCompleted(todoDetails.isCompleted());
        return todoRepository.save(todo);
    }

    public boolean deleteTodoById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            todoRepository.delete(todo.get());
            return true;
        } else {
            return false;
        }
    }
    public Page<Todo> getTodos(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }
    public List<Todo> searchTodos(String keyword) {
        return todoRepository.searchTodos(keyword);
    }

}

