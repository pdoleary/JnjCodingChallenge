/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.jnj.services;

public class BankAccount {
	
	private int accountNo;
	private int pin;
	private int openingBalance;
	private int overdraft;	
	
	public BankAccount(final int accountNo, final int pin, final int openingBalance, final int overdraft) {
		this.accountNo = accountNo;
		this.pin = pin;
		this.openingBalance = openingBalance;
		this.overdraft = overdraft;
	}
	
	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(int openingBalance) {
		this.openingBalance = openingBalance;
	}

	public int getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(int overdraft) {
		this.overdraft = overdraft;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankAccount [accountNo=");
		builder.append(accountNo);
		builder.append(", pin=");
		builder.append(pin);
		builder.append(", openingBalance=");
		builder.append(openingBalance);
		builder.append(", overdraft=");
		builder.append(overdraft);
		builder.append("]");
		return builder.toString();
	}
	
}
