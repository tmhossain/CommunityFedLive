package com.tztech.comfed.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table (name="CF_REFERENCE_CODE")
public class CfReferenceCode {
	
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	private long id;
	
	@Column (name="STATUS")
	private String status;	
	
	@Column (name="INSERTED_DT")
	private Date insertedAt;
	
	@Column (name="UPDATED_DT")
	private Date updatedAt;	

	@Column (name="REF_CATEGORY")
	private String refCategory;
	
	@Column (name="REF_CODE")
	private String refCode;
	
	@Column (name="REF_CODE_DESCRIPTION")
	private String refCodeDescription;
	
	@Column (name="XREF_CODE")
	private String xrefCode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getInsertedAt() {
		return insertedAt;
	}

	public void setInsertedAt(Date insertedAt) {
		this.insertedAt = insertedAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getRefCategory() {
		return refCategory;
	}

	public void setRefCategory(String refCategory) {
		this.refCategory = refCategory;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getRefCodeDescription() {
		return refCodeDescription;
	}

	public void setRefCodeDescription(String refCodeDescription) {
		this.refCodeDescription = refCodeDescription;
	}

	public String getXrefCode() {
		return xrefCode;
	}

	public void setXrefCode(String xrefCode) {
		this.xrefCode = xrefCode;
	}

	@Override
	public String toString() {
		return "CfReferenceCode [id=" + id + ", status=" + status
				+ ", insertedAt=" + insertedAt + ", updatedAt=" + updatedAt
				+ ", refCategory=" + refCategory + ", refCode=" + refCode
				+ ", refCodeDescription=" + refCodeDescription + ", xrefCode="
				+ xrefCode + "]";
	}
	
	
}
