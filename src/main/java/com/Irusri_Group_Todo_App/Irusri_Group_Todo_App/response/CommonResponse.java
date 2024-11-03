package com.Irusri_Group_Todo_App.Irusri_Group_Todo_App.response;

public class CommonResponse<T> {
    private boolean isSuccess;
    private T dataBundle;
    private String message;

    public CommonResponse() {
    }

    public CommonResponse(boolean isSuccess, T dataBundle, String message) {
        this.isSuccess = isSuccess;
        this.dataBundle = dataBundle;
        this.message = message;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public T getDataBundle() {
        return dataBundle;
    }

    public void setDataBundle(T dataBundle) {
        this.dataBundle = dataBundle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Builder pattern for convenience
    public static <T> CommonResponse<T> builder() {
        return new CommonResponse<>();
    }

    public CommonResponse<T> isSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
        return this;
    }

    public CommonResponse<T> dataBundle(T dataBundle) {
        this.dataBundle = dataBundle;
        return this;
    }

    public CommonResponse<T> message(String message) {
        this.message = message;
        return this;
    }

    public CommonResponse<T> build() {
        return this;
    }
}
