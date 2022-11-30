package com.allwin.bootstrap.dal.dto;

import com.allwin.bootstrap.dal.utils.ObjectConverter;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "idempotent_requests")
@EntityListeners(AuditingEntityListener.class)
public class IdempotentRequestDto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idempotent_key", updatable = false)
    private String idempotentKey;
    @Column(name = "request")
    private String request;
    @Column(name = "response", updatable = false)
    @Convert(converter = ObjectConverter.class)
    private Object response;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    public IdempotentRequestDto() {
    }

    public IdempotentRequestDto(String idempotentKey, String request, String response) {
        this.idempotentKey = idempotentKey;
        this.request = request;
        this.response = response;
    }
}
