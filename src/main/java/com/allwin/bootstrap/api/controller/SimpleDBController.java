package com.allwin.bootstrap.api.controller;

import com.allwin.bootstrap.dal.dto.SimpleDBRequest;
import com.allwin.bootstrap.service.SimpleDBService;
import com.allwin.bootstrap.service.dto.FindRequest;
import com.allwin.bootstrap.service.dto.SaveRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/db")
@Slf4j
public class SimpleDBController extends BaseController {

    @Autowired
    private SimpleDBService simpleDBService;

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@RequestBody SaveRequest saveRequest) {
        log.info("Saving new object!!! {}", saveRequest);
        SimpleDBRequest response = (SimpleDBRequest) simpleDBService.persist(saveRequest);
        return okCreatedResponseEntity(response);
    }

    @GetMapping("/fetch")
    public ResponseEntity<Map<String, Object>> fetch(@RequestBody FindRequest request) {
        log.info("Fetch the request : {}", request);
        return okCreatedResponseEntity(simpleDBService.findById(request));
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> update(@RequestParam Long id, @RequestParam String name) {
        log.info("Fetch the id : {} and name : {}", id, name);
        return okCreatedResponseEntity(simpleDBService.update(id, name));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> delete(@RequestParam Long id) {
        log.info("Deleting the id : {}", id);
        return okCreatedResponseEntity(simpleDBService.delete(id));
    }
}
