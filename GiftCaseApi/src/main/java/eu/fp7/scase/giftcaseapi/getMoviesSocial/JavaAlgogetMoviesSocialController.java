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


package eu.fp7.scase.giftcaseapi.getMoviesSocial;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.QueryParam; 

import eu.fp7.scase.giftcaseapi.facebookLogin.ApplicationContext;
import eu.fp7.scase.giftcaseapi.utilities.HibernateController;
import eu.fp7.scase.giftcaseapi.Users.JavaUsersModel;

import java.util.List;
/*
 * JavaAlgogetMoviesSocialController class is responsible to handle incoming HTTP requests for the getMoviesSocial resource. More specifically
 * this resource controller handles client requests that are delegated to an external server, which is executed at
 * http://109.231.127.61:8080/GetMoviesSocial-0.0.1-SNAPSHOT/rest/result/query
*/
@Path("/users/{usersId}/getMoviesSocial")
public class JavaAlgogetMoviesSocialController{

    @Context
    private UriInfo oApplicationUri;

	/* 
	 * This function is the WEB API of resource getMoviesSocial and accepts http  requests,   
     * which it delegates to the underlying Handler GetgetMoviesSocialHandler. 
     * It returns any response formatted as stated in the @Produces JAX-RS annotation below.
    */
	@Path("/")
	@GET
	@Produces("application/JSON")
    public JavaGetMoviesSocialOutputModel getgetMoviesSocial(@QueryParam("targetFacebookId") String targetFacebookId,
			@PathParam("usersId") int usersId){

		String fields = "movies";
		String Type0 = "movies";
		String k = ApplicationContext.apiKeyTasteKid;
		String pageSize = "1";
		String sort = "regularPrice.asc";
		String apiKey = ApplicationContext.apiKeyBestBuy;
		String show ="name,details.name,image,regularPrice,longDescription,shortDescription";
		String limit = "3";
		String format ="json";
		String q_num = "3";
		String search_num = "3";
		
		HibernateController call = new HibernateController();
		List<JavaUsersModel> user = call.getUserByFacebookId(targetFacebookId);
		String access_token = user.get(0).getaccesstoken();
		
		//create a new GetgetMoviesSocialHandler
		GetgetMoviesSocialHandler oGetgetMoviesSocialHandler = new GetgetMoviesSocialHandler(access_token,  fields,  Type0,  k,  pageSize,  apiKey,  show,  limit,  format,  sort, q_num, search_num, oApplicationUri);
		return oGetgetMoviesSocialHandler.getgetmoviessocial();
    }
}
