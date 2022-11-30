package com.allwin.bootstrap.dal.mysql;

import com.allwin.bootstrap.dal.dto.IdempotentRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IdempotentRepo extends JpaRepository<IdempotentRequestDto, Long> {

    public static final String FIND_REQUESTS = "select * from idempotent_requests id where id.idempotent_key = ?1 ";

    @Query(value = FIND_REQUESTS, nativeQuery = true)
    IdempotentRequestDto findByIdempotenceKey(String idempotenceKey);
}
