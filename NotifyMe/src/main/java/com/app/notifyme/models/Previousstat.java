package com.app.notifyme.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the Previousstat database table.
 * 
 */
@Entity
@NamedQuery(name = "Previousstat.findAll", query = "SELECT p FROM Previousstat p")
public class Previousstat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prevUpdateId;

	private Date date;

	private double minimumPrice;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "ProductId")
	private Product product;

	public Previousstat() {
	}

	public int getPrevUpdateId() {
		return prevUpdateId;
	}

	public void setPrevUpdateId(int prevUpdateId) {
		this.prevUpdateId = prevUpdateId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
