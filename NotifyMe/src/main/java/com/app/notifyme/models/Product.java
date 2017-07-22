package com.app.notifyme.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name = "products")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	private double currentPrice;

	private int faultStatus;

	private String productName;

	private int trackCount;

	private String url;

	private String XPath;

	// bi-directional many-to-one association to Error
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<Error> errors;

	// bi-directional many-to-one association to Productstat
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<Productstat> productstats;

	// bi-directional many-to-one association to Previousstat
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<Previousstat> prevstats;

	// bi-directional many-to-one association to Usercriteria
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private List<Usercriteria> usercriterias;

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getCurrentPrice() {
		return this.currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public int getFaultStatus() {
		return this.faultStatus;
	}

	public void setFaultStatus(int faultStatus) {
		this.faultStatus = faultStatus;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getTrackCount() {
		return this.trackCount;
	}

	public void setTrackCount(int trackCount) {
		this.trackCount = trackCount;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getXPath() {
		return this.XPath;
	}

	public void setXPath(String XPath) {
		this.XPath = XPath;
	}

	public List<Error> getErrors() {
		return this.errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public Error addError(Error error) {
		getErrors().add(error);
		error.setProduct(this);

		return error;
	}

	public Error removeError(Error error) {
		getErrors().remove(error);
		error.setProduct(null);

		return error;
	}

	public List<Productstat> getProductstats() {
		return this.productstats;
	}

	public void setProductstats(List<Productstat> productstats) {
		this.productstats = productstats;
	}

	public Productstat addProductstat(Productstat productstat) {
		getProductstats().add(productstat);
		productstat.setProduct(this);

		return productstat;
	}

	public Productstat removeProductstat(Productstat productstat) {
		getProductstats().remove(productstat);
		productstat.setProduct(null);

		return productstat;
	}

	public List<Usercriteria> getUsercriterias() {
		return this.usercriterias;
	}

	public void setUsercriterias(List<Usercriteria> usercriterias) {
		this.usercriterias = usercriterias;
	}

	public Usercriteria addUsercriteria(Usercriteria usercriteria) {
		getUsercriterias().add(usercriteria);
		usercriteria.setProduct(this);

		return usercriteria;
	}

	public Usercriteria removeUsercriteria(Usercriteria usercriteria) {
		getUsercriterias().remove(usercriteria);
		usercriteria.setProduct(null);

		return usercriteria;
	}

}