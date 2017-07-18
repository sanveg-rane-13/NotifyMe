package com.app.notifyme.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the error database table.
 * 
 */
@Entity
@NamedQuery(name="Error.findAll", query="SELECT e FROM Error e")
public class Error implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int errorId;

	private int errorType;

	private String oldUrl;

	private int verdict;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="ProductId")
	private Product product;

	public Error() {
	}

	public int getErrorId() {
		return this.errorId;
	}

	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}

	public int getErrorType() {
		return this.errorType;
	}

	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}

	public String getOldUrl() {
		return this.oldUrl;
	}

	public void setOldUrl(String oldUrl) {
		this.oldUrl = oldUrl;
	}

	public int getVerdict() {
		return this.verdict;
	}

	public void setVerdict(int verdict) {
		this.verdict = verdict;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}