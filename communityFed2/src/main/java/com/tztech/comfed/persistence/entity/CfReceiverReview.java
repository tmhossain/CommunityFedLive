package com.tztech.comfed.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table (name="CF_RECEIVER_REVIEW")
public class CfReceiverReview {

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
	
	@Column (name="USER_ID")
	private long userId;
	
	@Column (name="OFFER_ID")
	private long offerId;
	
	@Column (name="OVERALL_SATISFACTION")
	private int overallSatisfaction;
	 
	@Column (name="REVIEW")
	private String review;	
	   
	@Column (name="SAFETY_RATING")
	private int safetyRating;
	 
	@Column (name="LOCATION_RATING")
	private int locationRating;
	 
	@Column (name="VALUE_FOR_MONEY_RATING")
	private int valueForMoneyRating;
	 
	@Column (name="COMMUNICATION_RATING")
	private int communicationRating;

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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public int getOverallSatisfaction() {
		return overallSatisfaction;
	}

	public void setOverallSatisfaction(int overallSatisfaction) {
		this.overallSatisfaction = overallSatisfaction;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getSafetyRating() {
		return safetyRating;
	}

	public void setSafetyRating(int safetyRating) {
		this.safetyRating = safetyRating;
	}

	public int getLocationRating() {
		return locationRating;
	}

	public void setLocationRating(int locationRating) {
		this.locationRating = locationRating;
	}

	public int getValueForMoneyRating() {
		return valueForMoneyRating;
	}

	public void setValueForMoneyRating(int valueForMoneyRating) {
		this.valueForMoneyRating = valueForMoneyRating;
	}

	public int getCommunicationRating() {
		return communicationRating;
	}

	public void setCommunicationRating(int communicationRating) {
		this.communicationRating = communicationRating;
	}

	@Override
	public String toString() {
		return "CfReceiverReview [id=" + id + ", status=" + status
				+ ", insertedAt=" + insertedAt + ", updatedAt=" + updatedAt
				+ ", userId=" + userId + ", offerId=" + offerId
				+ ", overallSatisfaction=" + overallSatisfaction + ", review="
				+ review + ", safetyRating=" + safetyRating
				+ ", locationRating=" + locationRating
				+ ", valueForMoneyRating=" + valueForMoneyRating
				+ ", communicationRating=" + communicationRating + "]";
	}
	
	
	
}
