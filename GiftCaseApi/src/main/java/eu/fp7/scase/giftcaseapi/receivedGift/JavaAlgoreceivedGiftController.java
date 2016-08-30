/*
 * ARISTOTLE UNIVERSITY OF THESSALONIKI
 * Copyright (C) 2015
 * Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering
 * Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 *
 * Project             : GiftCaseApi
 * WorkFile            : 
 * Compiler            : 
 * File Description    : 
 * Document Description: 
* Related Documents	   : 
* Note				   : 
* Programmer		   : RESTful MDE Engine created by Christoforos Zolotas
* Contact			   : christopherzolotas@issel.ee.auth.gr
*/


package eu.fp7.scase.giftcaseapi.receivedGift;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import eu.fp7.scase.giftcaseapi.utilities.HibernateController;

/* JavaAlgoreceivedGiftController class is responsible to handle incoming http requests for the receivedGift resource. Since this one 
 is not automatable the developer has to manually define its RESTful API.*/
@Path("/users/{usersId}/receivedGifts")
public class JavaAlgoreceivedGiftController{

    @Context
    private UriInfo oApplicationUri;

	/* This function handles http  requests  
    and returns any response formatted as stated in the @Produces JAX-RS annotation below.*/
	@Path("/")
	@GET
	@Produces("application/JSON")
    public String getreceivedGift(@PathParam("usersId") int usersId){
	
		HibernateController call = new HibernateController();
		
		List<Object> listOfGifts = new ArrayList<Object>();
		
		listOfGifts.addAll(call.getReceivedEventGift(usersId));
		listOfGifts.addAll(call.getReceivedBookGift(usersId));
		listOfGifts.addAll(call.getReceivedMusicMovieGift(usersId));
	

		String json = new Gson().toJson(listOfGifts);
		
		return json;

    }
}

