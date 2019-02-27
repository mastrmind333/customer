package com.customer.model;


import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.customer.config.Operation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public class AbstractAuditingEntity implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 1L;
    
    public AbstractAuditingEntity() {
	}

    private static final String DEFAULT_SYSTEM_USER = "system";
    
    private static final String CREATE = "CREATE";

    @Basic
    @CreatedDate
    @JsonIgnore
    @Column(name = "CREATED_DATETIME", nullable = false)
    private Timestamp createdDatetime = Timestamp.from(Instant.now());

    @Basic
    @CreatedBy
    @JsonIgnore
    @Column(name = "CREATED_BY", nullable = false, updatable = false)
    private String createdBy = DEFAULT_SYSTEM_USER;

    @Basic
    @LastModifiedDate
    @JsonIgnore
    @Column(name = "UPDATED_DATETIME")
    private Timestamp updatedDatetime = Timestamp.from(Instant.now());

    @Basic
    @LastModifiedBy
    @JsonIgnore
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "VERSION")
    @Version
    @NotNull
    private Integer version;
    

    public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}



	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public Timestamp getUpdatedDatetime() {
		return updatedDatetime;
	}



	public void setUpdatedDatetime(Timestamp updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}



	public String getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}



	public Integer getVersion() {
		return version;
	}



	public void setVersion(Integer version) {
		this.version = version;
	}



	public void setAuditFields(Operation operation, String user, AbstractAuditingEntity source) {
        if (Operation.CREATE == operation) {
            this.setCreatedBy(user);
            this.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
        } else {
            this.setUpdatedBy(user);
            this.setUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
            this.setCreatedBy(source.getCreatedBy());
            this.setCreatedDatetime(source.getCreatedDatetime());
            this.setVersion(source.getVersion() + 1);
        }
    }
	
	@Override
	public String toString() {
		return updatedBy + updatedDatetime;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(updatedBy,updatedDatetime);
	}
}
