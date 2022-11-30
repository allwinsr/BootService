package com.allwin.bootstrap.service;

import com.allwin.bootstrap.dal.dto.IdempotentRequestDto;

import java.util.Optional;

public interface IdempotentService {

    void save(IdempotentRequestDto requestDto);

    Optional<IdempotentRequestDto> fetch(Long id);

    IdempotentRequestDto fetch(String idempotenceKey);
}
