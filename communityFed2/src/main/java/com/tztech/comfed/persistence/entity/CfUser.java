package com.tztech.comfed.persistence.entity;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;



@Entity
@Table (name="CF_USER")
public class CfUser {
	
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	private long id;
	
	@Column (name="first_name")
	private String firstName;	

	@Column (name="LAST_NAME")
	private String lastName;
	
	@Column (name="EMAIL")
	private String email;
	
	@Column (name="PHONE")
	private String phone;
	
	@Column (name="DOB")
	private Date dob;
	
	@Column (name="SHOW_AGE")
	private String showAge;
	
	@Column (name="PROFESSION")
	private String profession;
	
	@Column (name="LANGUAGE")
	private String language;
	
	@Column (name="USER_TYPE")
	private String userType;
	
	@Column (name="REASON_GIVING")
	private String reasonGiving;
	
	@Column (name="STATUS")
	private String status;
	
	@Column (name="INSERTED_DT")
	private Date insertedAt;
	
	@Column (name="UPDATED_DT")
	private Date updatedAt;
	
	
	@Column (name="USER_NAME")
	private String userName ;
	
	@Column (name="PASSWORD")
	private String password ;
	
	@OneToMany
	@JoinColumn(name="user_id")
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Collection<CfAddress> addresses = new ArrayList<CfAddress>();
	
	public Collection<CfAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<CfAddress> addresses) {
		this.addresses = addresses;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email!=null?email.toLowerCase():email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getShowAge() {
		return showAge;
	}

	public void setShowAge(String showAge) {
		this.showAge = showAge;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getReasonGiving() {
		return reasonGiving;
	}

	public void setReasonGiving(String reasonGiving) {
		this.reasonGiving = reasonGiving;
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
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CfUser [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phone=" + phone + ", dob="
				+ dob + ", showAge=" + showAge + ", profession=" + profession
				+ ", language=" + language + ", userType=" + userType
				+ ", reasonGiving=" + reasonGiving + ", status=" + status
				+ ", insertedAt=" + insertedAt + ", updatedAt=" + updatedAt
				+ "]";
	}
}
