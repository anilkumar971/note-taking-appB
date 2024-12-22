package com.example.userapi.Util;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {

    public ResponseEntity<Object> successResponse(Object data, String message) {
        return ResponseEntity.ok(new ApiResponse(true, message, data));
    }

    public ResponseEntity<Object> errorResponse(String message) {
        return ResponseEntity.badRequest().body(new ApiResponse(false, message, null));
    }

    static class ApiResponse {
        private boolean success;
        private String message;
        private Object data;

        public ApiResponse(boolean success, String message, Object data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public Object getData() {
            return data;
        }
    }
}

