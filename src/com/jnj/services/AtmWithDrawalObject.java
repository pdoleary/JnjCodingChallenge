package com.jnj.services;

import java.io.Serializable;

public class AtmWithDrawalObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int noFiftyNotes;
	private int noTwentyNotes;
	private int noTenNotes;
	private int noFiveNotes;
	private int remainingBalance;
	
	public AtmWithDrawalObject(int noFiftyNotes, int noTwentyNotes, int noTenNotes, int noFiveNotes,
			int remainingBalance) {
		this.noFiftyNotes = noFiftyNotes;
		this.noTwentyNotes = noTwentyNotes;
		this.noTenNotes = noTenNotes;
		this.noFiveNotes = noFiveNotes;
		this.remainingBalance = remainingBalance;
	}

	public int getNoFiftyNotes() {
		return noFiftyNotes;
	}

	public void setNoFiftyNotes(int noFiftyNotes) {
		this.noFiftyNotes = noFiftyNotes;
	}

	public int getNoTwentyNotes() {
		return noTwentyNotes;
	}

	public void setNoTwentyNotes(int noTwentyNotes) {
		this.noTwentyNotes = noTwentyNotes;
	}

	public int getNoTenNotes() {
		return noTenNotes;
	}

	public void setNoTenNotes(int noTenNotes) {
		this.noTenNotes = noTenNotes;
	}

	public int getNoFiveNotes() {
		return noFiveNotes;
	}

	public void setNoFiveNotes(int noFiveNotes) {
		this.noFiveNotes = noFiveNotes;
	}

	public int getRemainingBalance() {
		return remainingBalance;
	}

	public void setRemainingBalance(int remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AtmWithDrawalObject [noFiftyNotes=");
		builder.append(noFiftyNotes);
		builder.append(", noTwentyNotes=");
		builder.append(noTwentyNotes);
		builder.append(", noTenNotes=");
		builder.append(noTenNotes);
		builder.append(", noFiveNotes=");
		builder.append(noFiveNotes);
		builder.append(", remainingBalance=");
		builder.append(remainingBalance);
		builder.append("]");
		return builder.toString();
	}
}
