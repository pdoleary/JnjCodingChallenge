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

import java.util.*;

import javax.ejb.Stateless;

import com.jnj.atm.exceptions.ATMException;
import com.jnj.atm.exceptions.InsuffientFundsException;

@Stateless
public class ATMBean {

	private Map<Integer, BankAccount> bankAccounts = new HashMap<Integer,BankAccount>();
	private int atmBalance = 2000;
	private int atmNoOfFiveNotes = 20;
	private int atmNoOfTenNotes = 30;
	private int atmNoOfTwentyNotes = 30;
	private int atmNoOfFifthyNotes = 20;


	public int getBalance(final int accountNo, final int pin) throws ATMException{
		checkListOfAccounts();
		BankAccount bankAccount = getBankAccountDetails(accountNo,pin);
		return bankAccount.getOpeningBalance();
	}

	private BankAccount getBankAccountDetails(final int accountNo, final int pin) throws ATMException {
		for(BankAccount bankAccount : bankAccounts.values()){
			if(bankAccount.getAccountNo() == accountNo && bankAccount.getPin() == pin){
				return bankAccount;
			}
		}
		throw new ATMException("Incorrect Account No or pin, please try again");
	}

	public AtmWithDrawalObject withdrawFromAccount(final int accountNo, final int pin, final int amount) throws ATMException, InsuffientFundsException{
		checkListOfAccounts();
		BankAccount bankAccount = getBankAccountDetails(accountNo,pin);
		validateWithdrawal(bankAccount, amount);
		
		AtmWithDrawalObject atmWithDrawal = getDenominations(amount);
		
		atmBalance -= amount;
		int bankAccountBal = bankAccount.getOpeningBalance();
		int newBankAccountBal = bankAccountBal - amount;
		bankAccount.setOpeningBalance(newBankAccountBal);
		bankAccounts.put(accountNo, bankAccount);
		atmWithDrawal.setRemainingBalance(newBankAccountBal);
		
		return atmWithDrawal;
	}
	
	private AtmWithDrawalObject getDenominations(int amount){
		int noFiftyNotes = 0;
		int noTwentyNotes = 0;
		int noTenNotes = 0;
		int noFiveNotes = 0;

		while (amount > 0){
			int numberOfFifties = (int) Math.floor(amount / 50);
			if(atmNoOfFifthyNotes > numberOfFifties && numberOfFifties >= 1){
				amount = amount - (50 * numberOfFifties);
				noFiftyNotes = numberOfFifties;
				continue;
			}
			int numberOfTwenties = (int) Math.floor(amount / 20);
			if(atmNoOfTwentyNotes > numberOfTwenties && numberOfTwenties >= 1){
				amount = amount - (20 * numberOfTwenties);
				noTwentyNotes = numberOfTwenties;
				continue;
			}
			int numberOfTens = (int) Math.floor(amount / 10);
			if(atmNoOfTenNotes > numberOfTens && numberOfTens >= 1){
				amount = amount - (10 * numberOfTens);
				noTenNotes = numberOfTens;
				continue;
			}			
			int numberOfFives = (int) Math.floor(amount / 5);
			if(atmNoOfFiveNotes > numberOfFives && numberOfFives >= 1){
				amount = amount - (10 * numberOfFives);
				noFiveNotes = numberOfFives;
				continue;
			}
		}
		
		atmNoOfFiveNotes -= noFiveNotes;
		atmNoOfTenNotes -= noTenNotes;
		atmNoOfTwentyNotes -= noTwentyNotes;
		atmNoOfFifthyNotes -= noFiftyNotes;
		
		return new AtmWithDrawalObject(noFiftyNotes, noTwentyNotes, noTenNotes, noFiveNotes, 0);
	}

	private void validateWithdrawal(final BankAccount bankAccount,final int amount) throws ATMException, InsuffientFundsException{
		int accountBalance = (bankAccount.getOpeningBalance() + bankAccount.getOverdraft());
		if(atmBalance < amount){ 		
			throw new ATMException("ATM has insuffient funds, please try again with a different amount");
		}
		if(amount > accountBalance) {
			throw new InsuffientFundsException("Insuffient funds in bank account, please try again with a different amount");
		}	
		if(amount % 5 != 0){
			throw new ATMException("Incorrect denomination, please try again");
		}
	}

	private void checkListOfAccounts(){
		if(bankAccounts.isEmpty()){
			bankAccounts.put(123456789, new BankAccount(123456789, 1234, 800, 200));
			bankAccounts.put(987654321, new BankAccount(987654321, 4321, 1230, 150));
		}
	}

}
