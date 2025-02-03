package com.rpd.ms_books_catalogue.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDTO {
    private String message;
    private boolean success;

    public ResponseDTO(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

}
