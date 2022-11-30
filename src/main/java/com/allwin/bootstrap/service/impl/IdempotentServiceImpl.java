package com.allwin.bootstrap.service.impl;

import com.allwin.bootstrap.dal.dto.IdempotentRequestDto;
import com.allwin.bootstrap.dal.mysql.IdempotentRepo;
import com.allwin.bootstrap.service.IdempotentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Component("idempotentService")
public class IdempotentServiceImpl implements IdempotentService {

    @Autowired
    private IdempotentRepo idempotentRepo;

    @Override
    public void save(IdempotentRequestDto requestDto) {
        idempotentRepo.save(requestDto);
        log.info("Successfully persisted the request!!!");
    }

    @Override
    public Optional<IdempotentRequestDto> fetch(Long id) {
        Optional<IdempotentRequestDto> response = idempotentRepo.findById(id);
        log.info("Successfully fetched the response {} !!!", response);
        return response;
    }

    @Override
    public IdempotentRequestDto fetch(String idempotenceKey) {
        IdempotentRequestDto response = idempotentRepo.findByIdempotenceKey(idempotenceKey);
        log.info("Successfully fetched the response {} !!!", response);
        return response;
    }
}
