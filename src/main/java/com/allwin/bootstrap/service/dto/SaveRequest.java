package com.allwin.bootstrap.service.dto;

import com.allwin.bootstrap.aspect.dto.IdempotentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SaveRequest extends IdempotentRequest {

    private String name;
}
