package com.example.left_poecket;

import java.util.Date;

public class Income {
	private float amount;
	private Date start_date;
	private Date end_date;
	
	public Income(float amount, Date start_date, Date end_date) {
		super();
		this.amount = amount;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	
}
