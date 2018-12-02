package com.springboot.jpa.postgres.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdTs", "updatedTs"},
        allowGetters = true
)
public abstract class TraceModel implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_ts", nullable = false, updatable = false)
    @CreatedDate
    private Date createdTs;    

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_ts", nullable = false)
    @LastModifiedDate
    private Date updatedTs;
	
	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public Date getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}

    
}