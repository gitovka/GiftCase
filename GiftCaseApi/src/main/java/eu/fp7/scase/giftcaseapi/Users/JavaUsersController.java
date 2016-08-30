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


package eu.fp7.scase.giftcaseapi.Users;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;


/* This class defines the web API of the individual Users resource. It may handle PUT, GET and/or DELETE requests 
   depending on the specific CIM of the service.*/

@Path("/Users/{UsersId}")
public class JavaUsersController{

    @Context
    private UriInfo oApplicationUri;

	/* This function handles http GET requests  
    and returns any response formatted as stated in the @Produces JAX-RS annotation below.*/
	@Path("/")
	@GET
	@Produces("application/JSON")
    public JavaUsersModel getUsers(@PathParam("UsersId") int UsersId){
        GetUsersHandler oGetUsersHandler = new GetUsersHandler(UsersId, oApplicationUri);
        return oGetUsersHandler.getJavaUsersModel();
    }

	/* This function handles http PUT requests that are sent with any media type stated in the @Consumes JAX-RS annotation below 
    and returns any response formatted as stated in the @Produces JAX-RS annotation below.*/
	@Path("/")
	@PUT
	@Produces("application/JSON")
	@Consumes("application/JSON")
    public JavaUsersModel putUsers(@PathParam("UsersId") int UsersId,  JavaUsersModel oJavaUsersModel){
        PutUsersHandler oPutUsersHandler = new PutUsersHandler(UsersId, oJavaUsersModel, oApplicationUri);
        return oPutUsersHandler.putJavaUsersModel();
    }

}

