package com.allwin.bootstrap.aspect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdempotentRequest {

    private String idempotenceKey;

}
