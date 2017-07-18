package com.app.notifyme.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the usercriteria database table.
 * 
 */
@Entity
@NamedQuery(name="Usercriteria.findAll", query="SELECT u FROM Usercriteria u")
public class Usercriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int trackId;

	private double criteria;

	@Temporal(TemporalType.DATE)
	private Date date;

	private double finalPrice;

	@Temporal(TemporalType.DATE)
	private Date notifiedDate;

	private double startPrice;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Email")
	private User user;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="ProductId")
	private Product product;

	public Usercriteria() {
	}

	public int getTrackId() {
		return this.trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public double getCriteria() {
		return this.criteria;
	}

	public void setCriteria(double criteria) {
		this.criteria = criteria;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getFinalPrice() {
		return this.finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Date getNotifiedDate() {
		return this.notifiedDate;
	}

	public void setNotifiedDate(Date notifiedDate) {
		this.notifiedDate = notifiedDate;
	}

	public double getStartPrice() {
		return this.startPrice;
	}

	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}