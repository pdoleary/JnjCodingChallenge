package com.jnj.web;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jnj.atm.exceptions.ATMException;
import com.jnj.atm.exceptions.InsuffientFundsException;
import com.jnj.services.ATMBean;
import com.jnj.services.AtmWithDrawalObject;

@Path("/atm")
public class ATMRestResource {
	
	@Inject
	ATMBean atmBean;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{accountNo}/{pin}")
	public Response getAccountBalance(@PathParam("accountNo")final int accountNo, @PathParam("pin")final int pin){
		try{
			final int balance = atmBean.getBalance(accountNo, pin);
			return Response.status(200).entity(balance).build();
		} catch (ATMException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/withdraw/{accountNo}/{pin}")
	public Response atmWithdrawal(@PathParam("accountNo")final int accountNo, @PathParam("pin")final int pin, final int amount){
		try {
			AtmWithDrawalObject atmWithdrawal = atmBean.withdrawFromAccount(accountNo, pin, amount);
			return Response.status(200).entity(atmWithdrawal).build();
		} catch (ATMException e) {
			return Response.status(500).entity(e.getMessage()).build();
		} catch (InsuffientFundsException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

}
