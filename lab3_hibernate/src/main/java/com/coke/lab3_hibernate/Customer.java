package com.coke.lab3_hibernate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contract")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy = GeneratedType.AUTO)
	@Column(name = "id", unique = true)
	private int id;
	
	@Column(name = "customer", nullable = false)
	private String customer;
	
	@Column(name = "date_start", nullable = false)
	private int date_start;
	
	@Column(name = "date_end", nullable = false)
	private int date_end;
	
	@Column(name = "price", nullable = false)
	private double price;

	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getDate_start() {
		return date_start;
	}

	public void setDate_start(int date_start) {
		this.date_start = date_start;
	}

	public int getDate_end() {
		return date_end;
	}

	public void setDate_end(int date_end) {
		this.date_end = date_end;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
