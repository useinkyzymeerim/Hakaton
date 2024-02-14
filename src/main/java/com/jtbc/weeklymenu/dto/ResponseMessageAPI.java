package com.jtbc.weeklymenu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jtbc.weeklymenu.enums.ResultCodeAPI;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseMessageAPI<T> {
    T result;
    String resultCode;
    String details;
    String message;
    int code;

    public ResponseMessageAPI(T result, ResultCodeAPI resultCode, String details, String message, int code) {
        this.result = result;
        this.resultCode = resultCode.getDescription();
        this.details = details;
        this.message = message;
        this.code = code;
    }
}