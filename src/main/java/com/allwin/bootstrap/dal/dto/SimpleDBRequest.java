package com.allwin.bootstrap.dal.dto;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

//@Data
@Entity
@Data
@Table(name = "requests")
@EntityListeners(AuditingEntityListener.class)
public class SimpleDBRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;
    private String name;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    public SimpleDBRequest(String name) {
        this.name = name;
    }

    public SimpleDBRequest() {

    }

    @Override
    public String toString() {
        return "SimpleDBRequest{" + "id=" + id + ", version=" + version + ", name='" + name + '\'' + ", createdAt=" +
               createdAt + ", updatedAt=" + updatedAt + '}';
    }
}
