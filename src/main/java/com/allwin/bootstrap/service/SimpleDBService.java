package com.allwin.bootstrap.service;

import com.allwin.bootstrap.dal.dto.SimpleDBRequest;

public interface SimpleDBService {

    SimpleDBRequest persist(String name);

    SimpleDBRequest findById(Long id);

    SimpleDBRequest update(Long id, String name);

    SimpleDBRequest delete(Long id);

}
