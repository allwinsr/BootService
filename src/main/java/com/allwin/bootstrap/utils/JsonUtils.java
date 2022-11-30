package com.allwin.bootstrap.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.io.IOException;

/*
 * author : allwin.s
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new ParameterNamesModule()).registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());

    public static <T> T getObject(String obj, Class<T> returnType) throws IOException {
        return mapper.readValue(obj, returnType);
    }

    public static String getJsonString(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

}
