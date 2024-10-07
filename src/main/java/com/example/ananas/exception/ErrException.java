package com.example.ananas.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrException {
    USER_EXISTED(101,"User existed"),
    USER_NOT_EXISTED(102,"User not existed"),
    INVALID_KEY(105,"Invalid key"),
    EMAIL_NOT_EXISTED(106,"Email not existed"),
    EMAIL_EXISTED(107,"Email existed"),
    NOT_FILE(108,"Not file"),
    DIRECTORY_CREATION_FAILED(109,"DIRECTORY_CREATION_FAILED"),
    FILE_STORAGE_FAILED(110, "File storage failed");

    private int code;
    private String message;
}
