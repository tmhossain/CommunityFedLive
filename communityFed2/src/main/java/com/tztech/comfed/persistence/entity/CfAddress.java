package com.tztech.comfed.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table (name="CF_ADDRESS")
public class CfAddress {
	
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	private long id;
	
	@Column (name="USER_ID")
	private long userId;
	 
	@Column (name="ADDRESS_TYPE")
	private String addressType;
	 
	@Column (name="STREET")
	private String street;
	 
	@Column (name="CITY")
	private String city;
	 
	@Column (name="POSTAL_ZIP")
	private String zip;
	 
	@Column (name="STATE_PROVINCE")
	private String state;
	
	@Column (name="COUNTRY")
	private String country;
	 
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

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
		return "CfAddress [id=" + id + ", userId=" + userId + ", addressType="
				+ addressType + ", street=" + street + ", city=" + city
				+ ", zip=" + zip + ", state=" + state + ", country=" + country
				+ ", status=" + status + ", insertedAt=" + insertedAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	
}
