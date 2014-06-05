package com.tztech.comfed.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table (name="CF_PICTURE")
public class CfPicture {

	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	private long id;
	
	@Column (name="OFFER_ID")
	private long offerId;
	
	@Column (name="PICTURE_ID")
	private String pictureId;	
	
	@Column (name="STATUS")
	private String status;	
	
	@Column (name="INSERTED_DT")
	private Date insertedAt;
	
	@Column (name="UPDATED_DT")
	private Date updatedAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
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

	@Override
	public String toString() {
		return "CfPicture [id=" + id + ", offerId=" + offerId + ", pictureId="
				+ pictureId + ", status=" + status + ", insertedAt="
				+ insertedAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
