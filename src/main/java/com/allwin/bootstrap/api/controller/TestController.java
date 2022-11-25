package com.allwin.bootstrap.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/test")
@Slf4j
public class TestController extends BaseController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> getHealth() {
        log.info("Running fine!!!");
        return okCreatedResponseEntity("success");
    }

}

