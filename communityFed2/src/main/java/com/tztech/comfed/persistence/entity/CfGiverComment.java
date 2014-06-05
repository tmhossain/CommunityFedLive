package com.tztech.comfed.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table (name="CF_GIVER_COMMENT")
public class CfGiverComment {
	
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
	
	@Column (name="REVIEW_ID")
	private long reviewId;
	
	@Column (name="GIVER_ID")
	private long giverId;
	
	@Column (name="GIVER_COMMENTS")
	private String giverComments;

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

	public long getReviewId() {
		return reviewId;
	}

	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}

	public long getGiverId() {
		return giverId;
	}

	public void setGiverId(long giverId) {
		this.giverId = giverId;
	}

	public String getGiverComments() {
		return giverComments;
	}

	public void setGiverComments(String giverComments) {
		this.giverComments = giverComments;
	}

	@Override
	public String toString() {
		return "CfGiverComment [id=" + id + ", status=" + status
				+ ", insertedAt=" + insertedAt + ", updatedAt=" + updatedAt
				+ ", reviewId=" + reviewId + ", giverId=" + giverId
				+ ", giverComments=" + giverComments + "]";
	}	  
	
	
}
