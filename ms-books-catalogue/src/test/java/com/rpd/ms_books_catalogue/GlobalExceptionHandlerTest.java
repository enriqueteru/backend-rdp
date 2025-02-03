package com.rpd.ms_books_catalogue.exception;

import com.rpd.ms_books_catalogue.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    @Test
    void shouldReturnNotFoundResponseForRuntimeException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        RuntimeException exception = new RuntimeException("Test error");

        ResponseEntity<ResponseDTO> response = handler.handleRuntimeException(exception);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Test error", response.getBody().getMessage());
        assertFalse(response.getBody().isSuccess());
    }
}
