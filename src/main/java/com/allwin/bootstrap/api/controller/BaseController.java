package com.allwin.bootstrap.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BaseController {

    public static final String SUCCESS = "success";
    public static final String STATUS = "status";
    public static final String DATA = "data";
    public static final String TIMESTAMP_IN_MS = "timestamp_in_ms";

    private static final Supplier<Long> CURRENT_DATETIME_IN_MS = () -> new Date().getTime();

    public static ResponseEntity<Map<String, Object>> okCreatedResponseEntity(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, SUCCESS);
        response.put(DATA, data);
        response.put(TIMESTAMP_IN_MS, CURRENT_DATETIME_IN_MS.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
