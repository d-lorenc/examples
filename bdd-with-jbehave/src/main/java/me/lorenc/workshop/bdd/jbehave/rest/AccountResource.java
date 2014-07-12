package me.lorenc.workshop.bdd.jbehave.rest;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import me.lorenc.workshop.bdd.jbehave.BankAccount;

@Path("/account")
public class AccountResource {

    private static BankAccount account = BankAccount.createEmpty();
    
    public static void reset() {
        account = BankAccount.createEmpty();
    }

    @POST
    @Path("/deposit/{amount}")
    public void deposit(@PathParam("amount") BigDecimal amount) {
        account.deposit(amount);
    }
    
    @POST
    @Path("/withdraw/{amount}")
    public Response withdraw(@PathParam("amount") BigDecimal amount) {
        try {
            account.withdraw(amount);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.FORBIDDEN).build();
        }
    }
    
    @GET
    public BigDecimal getBalance() {
        return account.getBalance();
    }
    
}
