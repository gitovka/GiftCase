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


package eu.fp7.scase.giftcaseapi.getAboutUser;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.QueryParam; 

import eu.fp7.scase.giftcaseapi.utilities.HibernateController;
import java.util.List;
import eu.fp7.scase.giftcaseapi.Users.JavaUsersModel;
import eu.fp7.scase.giftcaseapi.utilities.HibernateController;

/*
 * JavaAlgogetAboutUserController class is responsible to handle incoming HTTP requests for the getAboutUser resource. More specifically
 * this resource controller handles client requests that are delegated to an external server, which is executed at
 * https://graph.facebook.com/v2.6/me
*/
@Path("/users/{usersId}/info")
public class JavaAlgogetAboutUserController{

    @Context
    private UriInfo oApplicationUri;

	/* 
	 * This function is the WEB API of resource getAboutUser and accepts http  requests,   
     * which it delegates to the underlying Handler GetgetAboutUserHandler. 
     * It returns any response formatted as stated in the @Produces JAX-RS annotation below.
    */
	@Path("/")
	@GET
	@Produces("application/JSON")
    public JavaGetAboutUserOutputModel getgetAboutUser(@PathParam("usersId") int usersId, @QueryParam("targetFacebookId") String targetFacebookId){

		String fields = "id,name,picture.type(large),email,first_name,last_name,location,birthday,hometown";
		HibernateController call = new HibernateController();
		
		
		List<JavaUsersModel> user;
		if(targetFacebookId == null && targetFacebookId.isEmpty()){
			user = call.getUserByUsersId(usersId);
		} else {
			user = call.getUserByFacebookId(targetFacebookId);
		}
		
		
		String access_token = user.get(0).getaccesstoken();
		
		//create a new GetgetAboutUserHandler
		GetgetAboutUserHandler oGetgetAboutUserHandler = new GetgetAboutUserHandler(access_token,  fields, oApplicationUri);
		return oGetgetAboutUserHandler.getgetaboutuser();
    }
	
    public JavaGetAboutUserOutputModel getgetAboutUser(@QueryParam("access_token") String access_token, @QueryParam("fields") String fields){
		
		//create a new GetgetAboutUserHandler
		GetgetAboutUserHandler oGetgetAboutUserHandler = new GetgetAboutUserHandler(access_token,  fields, oApplicationUri);
		return oGetgetAboutUserHandler.getgetaboutuser();
    }
}

