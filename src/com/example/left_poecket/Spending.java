package com.example.left_poecket;

import java.util.Date;

public class Spending {
	private float amount;
	private int type;
	private int form_payment;
	private Date date;
	
	public Spending(float amount, int type, int form_payment, Date date) {
		super();
		this.amount = amount;
		this.type = type;
		this.form_payment = form_payment;
		this.date = date;
	}
	
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getForm_payment() {
		return form_payment;
	}
	public void setForm_payment(int form_payment) {
		this.form_payment = form_payment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
