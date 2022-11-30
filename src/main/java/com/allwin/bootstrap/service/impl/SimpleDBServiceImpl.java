package com.allwin.bootstrap.service.impl;

import com.allwin.bootstrap.aspect.annotation.Idempotent;
import com.allwin.bootstrap.dal.dto.SimpleDBRequest;
import com.allwin.bootstrap.dal.mysql.SimpleDBRepo;
import com.allwin.bootstrap.service.SimpleDBService;
import com.allwin.bootstrap.service.dto.FindRequest;
import com.allwin.bootstrap.service.dto.SaveRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Component("simpleDBService")
public class SimpleDBServiceImpl implements SimpleDBService {

    @Autowired
    private SimpleDBRepo simpleDBRepo;

    @Override
    @Idempotent
    public SimpleDBRequest persist(SaveRequest saveRequest) {
        log.info("Persisting the message : {}", saveRequest);
        SimpleDBRequest request = new SimpleDBRequest(saveRequest.getName(), saveRequest.getIdempotenceKey());
        return simpleDBRepo.saveAndFlush(request);
    }

    @Override
    public SimpleDBRequest findById(FindRequest findRequest) {
        log.info("Fetching the id {} with getIdempotenceKey : {}", findRequest.getId(),
                findRequest.getIdempotenceKey());
        return simpleDBRepo.findById(findRequest.getId()).orElse(null);
    }

    @Override
    public SimpleDBRequest update(Long id, String name) {
        log.info("Updating the name : {} for id: {}", name, id);
        simpleDBRepo.updateRequestById(id, name);
        return simpleDBRepo.findById(id).orElse(null);
    }

    @Override
    public SimpleDBRequest delete(Long id) {
        log.info("Updating the name : {} for id: {}", id);
        SimpleDBRequest request = simpleDBRepo.findById(id).orElse(null);
        simpleDBRepo.deleteById(id);
        return request;
    }
}
