package com.app.notifyme.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private String name;

	private String password;

	private String phoneNumber;

	@Lob
	private byte[] profileImage;

	private byte type;

	//bi-directional many-to-one association to Usercriteria
	@OneToMany(mappedBy="user")
	private List<Usercriteria> usercriterias;

	public User() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public byte[] getProfileImage() {
		return this.profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public byte getType() {
		return this.type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public List<Usercriteria> getUsercriterias() {
		return this.usercriterias;
	}

	public void setUsercriterias(List<Usercriteria> usercriterias) {
		this.usercriterias = usercriterias;
	}

	public Usercriteria addUsercriteria(Usercriteria usercriteria) {
		getUsercriterias().add(usercriteria);
		usercriteria.setUser(this);

		return usercriteria;
	}

	public Usercriteria removeUsercriteria(Usercriteria usercriteria) {
		getUsercriterias().remove(usercriteria);
		usercriteria.setUser(null);

		return usercriteria;
	}

}