package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.controller;

import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.exceptions.TodoNotFoundException;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.model.Todo;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.response.CommonResponse;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todos")
    public ResponseEntity<CommonResponse<Todo>> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<Todo>builder()
                        .isSuccess(true)
                        .dataBundle(createdTodo)
                        .message("Todo created successfully.")
                        .build());
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<CommonResponse<Todo>> getTodoById(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        if (todo == null) {
            throw new TodoNotFoundException("Todo with ID " + id + " not found.");
        }
        return ResponseEntity.ok(CommonResponse.<Todo>builder()
                .isSuccess(true)
                .dataBundle(todo)
                .message("Todo retrieved successfully.")
                .build());
    }

    @GetMapping("/todos")
    public ResponseEntity<CommonResponse<List<Todo>>> getAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        return ResponseEntity.ok(CommonResponse.<List<Todo>>builder()
                .isSuccess(true)
                .dataBundle(todos)
                .message("Todos retrieved successfully.")
                .build());
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<CommonResponse<Todo>> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Todo updatedTodo = todoService.updateTodo(id, todo);
        if (updatedTodo == null) {
            throw new TodoNotFoundException("Todo with ID " + id + " not found.");
        }
        return ResponseEntity.ok(CommonResponse.<Todo>builder()
                .isSuccess(true)
                .dataBundle(updatedTodo)
                .message("Todo updated successfully.")
                .build());
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<CommonResponse<Boolean>> deleteTodoById(@PathVariable Long id) {
        boolean isDeleted = todoService.deleteTodoById(id);
        if (!isDeleted) {
            throw new TodoNotFoundException("Todo with ID " + id + " not found.");
        }
        return ResponseEntity.ok(CommonResponse.<Boolean>builder()
                .isSuccess(true)
                .dataBundle(true)
                .message("Todo deleted successfully.")
                .build());
    }
    @GetMapping("/todos/pages")
    public ResponseEntity<CommonResponse<Page<Todo>>> getAllTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Todo> todos = todoService.getTodos(pageable);

        return ResponseEntity.ok(CommonResponse.<Page<Todo>>builder()
                .isSuccess(true)
                .dataBundle(todos)
                .message("Todos retrieved successfully.")
                .build());
    }
    @GetMapping("/todos/search")
    public ResponseEntity<CommonResponse<List<Todo>>> searchTodos(@RequestParam String keyword) {
        List<Todo> todos = todoService.searchTodos(keyword);
        return ResponseEntity.ok(CommonResponse.<List<Todo>>builder()
                .isSuccess(true)
                .dataBundle(todos)
                .message("Todos retrieved successfully.")
                .build());
    }
}
