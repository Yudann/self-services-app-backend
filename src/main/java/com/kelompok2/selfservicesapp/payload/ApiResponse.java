package com.kelompok2.selfservicesapp.payload;

public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private T data;
    private Object pagination;

    public ApiResponse(int statusCode, String message, T data, Object pagination) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.pagination = pagination;
    }

    public ApiResponse(int statusCode, String message, T data) {
        this(statusCode, message, data, null);
    }

    public ApiResponse(int statusCode, String message) {
        this(statusCode, message, null, null);
    }

    // Getter & Setter
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public Object getPagination() {
        return pagination;
    }
    public void setPagination(Object pagination) {
        this.pagination = pagination;
    }
}
