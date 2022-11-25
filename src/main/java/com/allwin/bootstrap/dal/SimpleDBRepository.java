package com.allwin.bootstrap.dal;

import com.allwin.bootstrap.dal.dto.SimpleDBRequest;

public interface SimpleDBRepository {

    void persist(SimpleDBRequest request);
}
