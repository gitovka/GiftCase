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


package eu.fp7.scase.giftcaseapi.EventGift;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;


/* This class defines the web API of the individual EventGift resource. It may handle PUT, GET and/or DELETE requests 
   depending on the specific CIM of the service.*/

@Path("/EventGift/{EventGiftId}")
public class JavaEventGiftController{

    @Context
    private UriInfo oApplicationUri;

	/* This function handles http GET requests  
    and returns any response formatted as stated in the @Produces JAX-RS annotation below.*/
	@Path("/")
	@GET
	@Produces("application/JSON")
    public JavaEventGiftModel getEventGift(@PathParam("EventGiftId") int EventGiftId){
        GetEventGiftHandler oGetEventGiftHandler = new GetEventGiftHandler(EventGiftId, oApplicationUri);
        return oGetEventGiftHandler.getJavaEventGiftModel();
    }


}

