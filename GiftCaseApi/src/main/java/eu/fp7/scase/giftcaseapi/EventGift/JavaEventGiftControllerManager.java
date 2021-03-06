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

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import eu.fp7.scase.giftcaseapi.Email.SendEmail;

/* This class defines the web API of the manager EventGift resource. It handles POST and GET HTTP requests, as it is prescribed by the meta-models.*/
@Path("/users/{usersId}/buyingEventGift")
public class JavaEventGiftControllerManager{

    @Context
    private UriInfo oApplicationUri;

	/* This function handles POST requests that are sent with any media type stated in the @Consumes JAX-RS annotation below 
     and returns any response in any media type stated in the @Produces JAX-RS annotation below.*/
	@Path("/")
	@POST
	@Produces("application/JSON")
	@Consumes("application/JSON")
    public JSONObject postEventGift(JavaEventGiftModel oJavaEventGiftModel, @PathParam("usersId") int sender) throws JSONException{
        PostEventGiftHandler oPostEventGiftHandler = new PostEventGiftHandler(oJavaEventGiftModel, oApplicationUri);
         oPostEventGiftHandler.postJavaEventGiftModel();
        
        JSONObject obj = new JSONObject();
        SendEmail mail = new SendEmail();
        
        if (mail.sendNotification(oJavaEventGiftModel.getrecipient(), sender)){
        	obj.put("InfoException", "Gift is sent! Your friend will get an e-mail notification.");
        } else {
        	obj.put("InfoException", "Gift is sent!");
        }
        return obj;
    }

    /* This function handles GET requests  
     and returns any response in any media type stated in the @Produces JAX-RS annotation below.*/
	@Path("/")
	@GET
	@Produces("application/JSON")
    public JavaEventGiftModelManager getEventGiftList(){
        GetEventGiftListHandler oGetEventGiftListHandler = new GetEventGiftListHandler(oApplicationUri);
        return oGetEventGiftListHandler.getJavaEventGiftModelManager();
    }
}
