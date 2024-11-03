package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.serviceTest;


import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.exceptions.ResourceNotFoundException;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.model.Todo;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.repository.TodoRepository;
import com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    private Todo todo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        todo = new Todo();
        todo.setId(1L);
        todo.setTitle("Test Todo");
        todo.setDescription("Test Description");
        todo.setCompleted(false);
    }

    @Test
    public void testCreateTodo() {
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        Todo createdTodo = todoService.createTodo(todo);

        assertEquals("Test Todo", createdTodo.getTitle());
        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    public void testGetTodoById() {
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        Todo foundTodo = todoService.getTodoById(1L);

        assertEquals("Test Todo", foundTodo.getTitle());
        assertEquals(1L, foundTodo.getId());
    }

    @Test
    public void testGetTodoById_NotFound() {
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            todoService.getTodoById(1L);
        });

        assertEquals("Todo not found with id 1", exception.getMessage());
    }

    @Test
    public void testUpdateTodo() {
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        Todo updatedTodo = todoService.updateTodo(1L, todo);
        assertEquals("Test Todo", updatedTodo.getTitle());
        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    public void testDeleteTodo() {
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        boolean isDeleted = todoService.deleteTodoById(1L);

        assertTrue(isDeleted);
        verify(todoRepository, times(1)).delete(todo);
    }
}

