package com.training.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Payment")
public class Prodct{    //pojo
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	@Column(name="name")
	private String sku_Name;
	@Column(name="Qty")
	private int quantity;
	@Column(name="cost")
	private double sku_Cost;
	@Column(name="tCost")
	private double total_Cost;
	
//setters and getters	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSku_Name() {
		return sku_Name;
	}

	public void setSku_Name(String sku_Name) {
		this.sku_Name = sku_Name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSku_Cost() {
		return sku_Cost;
	}

	public void setSku_Cost(double sku_Cost) {
		this.sku_Cost = sku_Cost;
	}

	public double getTotal_Cost() {
		return total_Cost;
	}

	public void setTotal_Cost(double total_Cost) {
		this.total_Cost = total_Cost;
	}
}