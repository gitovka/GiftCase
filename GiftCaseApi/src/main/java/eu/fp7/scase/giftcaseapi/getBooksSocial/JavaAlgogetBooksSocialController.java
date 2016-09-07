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

package eu.fp7.scase.giftcaseapi.getBooksSocial;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.QueryParam; 

import eu.fp7.scase.giftcaseapi.Users.JavaUsersModel;
import eu.fp7.scase.giftcaseapi.facebookLogin.ApplicationContext;
import eu.fp7.scase.giftcaseapi.utilities.HibernateController;

/*
 * JavaAlgogetBooksSocialController class is responsible to handle incoming HTTP requests for the getBooksSocial resource. More specifically
 * this resource controller handles client requests that are delegated to an external server, which is executed at
 * http://109.231.127.61:8080/GetBooksSocial-0.0.1-SNAPSHOT/rest/result/query
*/
@Path("/users/{usersId}/getBooksSocial")
public class JavaAlgogetBooksSocialController{

    @Context
    private UriInfo oApplicationUri;

	/* 
	 * This function is the WEB API of resource getBooksSocial and accepts http  requests,   
     * which it delegates to the underlying Handler GetgetBooksSocialHandler. 
     * It returns any response formatted as stated in the @Produces JAX-RS annotation below.
    */
	@Path("/")
	@GET
	@Produces("application/JSON")
	public JavaGetBooksSocialOutputModel getgetBooksSocial(
			@QueryParam("targetFacebookId") String targetFacebookId,
			@PathParam("usersId") int usersId) {

		String country0 = "DE";
		String maxResults = "1";
		String printType = "books";
		String fields = "books";
		String type = "books";
		String limit = "3";
		String q_num = "3";
		String q0_num = "3";

		HibernateController call = new HibernateController();
		List<JavaUsersModel> user = call.getUserByFacebookId(targetFacebookId);
		String access_token = user.get(0).getaccesstoken();

		// create a new GetgetBooksSocialHandler
		GetgetBooksSocialHandler oGetgetBooksSocialHandler = new GetgetBooksSocialHandler(country0, maxResults, printType, fields, ApplicationContext.apiKeyTasteKid, access_token, limit, type,q_num, q0_num,  oApplicationUri);
		return oGetgetBooksSocialHandler.getgetbookssocial();
	}
}