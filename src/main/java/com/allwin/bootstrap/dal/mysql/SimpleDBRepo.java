package com.allwin.bootstrap.dal.mysql;

import com.allwin.bootstrap.dal.dto.SimpleDBRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SimpleDBRepo extends JpaRepository<SimpleDBRequest, Long> {

    @Modifying
    @Query("update SimpleDBRequest r set r.name = ?2, r.version = r.version +1 where r.id= ?1")
    void updateRequestById(Long id, String name);
}
