package com.tztech.comfed.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table (name="CF_OFFER")
public class CfOffer {

	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	private long id;
	
	@Column (name="USER_ID")
	private long userId;
	
	@Column (name="OFFER_CATEGORY_ID")
	private String offerCategoryId;
	
	@Column (name="ITEM_ID")
	private String itemId;
	
	@Column (name="QUANTITY")
	private int quantity;
	
	@Column (name="PRICE_PER_POUND")
	private double pricePerPound;	
	
	@Column (name="FREE")
	private String free;
	
	@Column (name="OFFER_TITLE")
	private String offerTitle;
	
	@Column (name="OFFER_DATE")
	private Date offerDate;	
	
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getOfferCategoryId() {
		return offerCategoryId;
	}

	public void setOfferCategoryId(String offerCategoryId) {
		this.offerCategoryId = offerCategoryId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPricePerPound() {
		return pricePerPound;
	}

	public void setPricePerPound(double pricePerPound) {
		this.pricePerPound = pricePerPound;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getOfferTitle() {
		return offerTitle;
	}

	public void setOfferTitle(String offerTitle) {
		this.offerTitle = offerTitle;
	}

	public Date getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
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
		return "CfOffer [id=" + id + ", userId=" + userId
				+ ", offerCategoryId=" + offerCategoryId + ", itemId=" + itemId
				+ ", quantity=" + quantity + ", pricePerPound=" + pricePerPound
				+ ", free=" + free + ", offerTitle=" + offerTitle
				+ ", offerDate=" + offerDate + ", status=" + status
				+ ", insertedAt=" + insertedAt + ", updatedAt=" + updatedAt
				+ "]";
	}
	
}
