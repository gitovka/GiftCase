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

package eu.fp7.scase.giftcaseapi.getEventsSocial;

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
 * JavaAlgogetEventsSocialController class is responsible to handle incoming HTTP requests for the getEventsSocial resource. More specifically
 * this resource controller handles client requests that are delegated to an external server, which is executed at
 * http://109.231.127.61:8080/GetEventsSocial-0.0.1-SNAPSHOT/rest/result/query
 */
@Path("/users/{usersId}/getEventsSocial")
public class JavaAlgogetEventsSocialController {

	@Context
	private UriInfo oApplicationUri;

	/*
	 * This function is the WEB API of resource getEventsSocial and accepts http
	 * requests, which it delegates to the underlying Handler
	 * GetgetEventsSocialHandler. It returns any response formatted as stated in
	 * the @Produces JAX-RS annotation below.
	 */
	@Path("/")
	@GET
	@Produces("application/JSON")
	public JavaGetEventsSocialOutputModel getgetEventsSocial(
			@QueryParam("targetFacebookId") String targetFacebookId,
			@PathParam("usersId") String usersId) {

		// call user
		HibernateController call = new HibernateController();
		List<JavaUsersModel> user = call.getUserByFacebookId(targetFacebookId);
		String access_token = user.get(0).getaccesstoken();

		String fields = "music";
		String units = "km";
		String include = "price";
		String within = "100";
		String keywords_num = "3";

		String location;
		if (user.get(0).getlocation() == null) {
			location = "";
		} else {
			location = user.get(0).getlocation();
		}

		// create a new GetgetEventsSocialHandler
		GetgetEventsSocialHandler oGetgetEventsSocialHandler = new GetgetEventsSocialHandler(
				access_token, fields, units, within, location, include,
				ApplicationContext.apiKeyEventful, keywords_num,
				oApplicationUri);
		return oGetgetEventsSocialHandler.getgeteventssocial();
	}
}
