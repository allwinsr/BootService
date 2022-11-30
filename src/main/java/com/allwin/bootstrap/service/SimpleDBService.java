package com.allwin.bootstrap.service;

import com.allwin.bootstrap.dal.dto.SimpleDBRequest;
import com.allwin.bootstrap.service.dto.FindRequest;
import com.allwin.bootstrap.service.dto.SaveRequest;

public interface SimpleDBService {

    SimpleDBRequest persist(SaveRequest saveRequest);

    SimpleDBRequest findById(FindRequest findRequest);

    SimpleDBRequest update(Long id, String name);

    SimpleDBRequest delete(Long id);

}
