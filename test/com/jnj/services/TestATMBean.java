package com.jnj.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jnj.atm.exceptions.ATMException;
import com.jnj.atm.exceptions.InsuffientFundsException;

import junit.framework.Assert;

public class TestATMBean {

	ATMBean atmBean;

	@Before
	public void setup() {
		atmBean = new ATMBean();
	}

	@Test
	public void testGetBalance() throws ATMException {
		int balance = atmBean.getBalance(123456789, 1234);
		Assert.assertEquals("Bank balance should have been equal", 800, balance);
		balance = atmBean.getBalance(987654321, 4321);
		Assert.assertEquals("Bank balance should have been equal", 1230, balance);
	}

	@Test (expected = ATMException.class)
	public void testGetBalanceException() throws ATMException {
		int balance = atmBean.getBalance(123456, 1234);
	}

	@Test
	public void testWithdrawDenominations() throws ATMException, InsuffientFundsException {
		AtmWithDrawalObject atmWithDrawObject = atmBean.withdrawFromAccount(123456789, 1234, 400);
		Assert.assertEquals(8, atmWithDrawObject.getNoFiftyNotes());
		atmWithDrawObject = atmBean.withdrawFromAccount(123456789, 1234, 40);
		Assert.assertEquals(2, atmWithDrawObject.getNoTwentyNotes());
		atmWithDrawObject = atmBean.withdrawFromAccount(123456789, 1234, 35);
		Assert.assertEquals(1, atmWithDrawObject.getNoTwentyNotes());
		Assert.assertEquals(1, atmWithDrawObject.getNoTenNotes());
		Assert.assertEquals(1, atmWithDrawObject.getNoFiveNotes());
	}
	
	@Test (expected = InsuffientFundsException.class)
	public void testInsuffientFundsException() throws ATMException, InsuffientFundsException {
		AtmWithDrawalObject atmWithDrawObject = atmBean.withdrawFromAccount(123456789, 1234, 1005);
	}
	
	@Test (expected = ATMException.class)
	public void testIncorrectWithdrawalAmount() throws ATMException, InsuffientFundsException {
		AtmWithDrawalObject atmWithDrawObject = atmBean.withdrawFromAccount(123456789, 1234, 112);
	}
	
	@Test
	public void testBankAccountBalanceUpdated() throws ATMException, InsuffientFundsException {
		AtmWithDrawalObject atmWithDrawObject = atmBean.withdrawFromAccount(123456789, 1234, 450);
		Assert.assertEquals(350, atmWithDrawObject.getRemainingBalance());
	}
	
	@Test
	public void testATMCannotDispenseMoreMoneyThanItHolds() {
		try {
			AtmWithDrawalObject atmWithDrawObject = atmBean.withdrawFromAccount(123456789, 1234, 2005);
		} catch (ATMException e) {
			Assert.assertEquals("ATM has insuffient funds, please try again with a different amount", e.getMessage());
		} catch (InsuffientFundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
