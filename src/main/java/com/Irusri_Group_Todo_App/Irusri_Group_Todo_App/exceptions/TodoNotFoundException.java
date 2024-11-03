package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.exceptions;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(String message) {
        super(message);
    }
}