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

package eu.fp7.scase.giftcaseapi.getFacebookFriends;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import eu.fp7.scase.giftcaseapi.Users.JavaUsersModel;
import eu.fp7.scase.giftcaseapi.facebookLogin.JavaAlgofacebookCallbackController;
import eu.fp7.scase.giftcaseapi.utilities.HibernateController;

/*
 * JavaAlgogetFacebookFriendsController class is responsible to handle incoming HTTP requests for the getFacebookFriends resource. More specifically
 * this resource controller handles client requests that are delegated to an external server, which is executed at
 * https://graph.facebook.com/v2.6/me
 */
@Path("/users/{usersId}/contacts")
public class JavaAlgogetFacebookFriendsController {

	final static Logger logger = Logger.getLogger(JavaAlgogetFacebookFriendsController.class);
	@Context
	private UriInfo oApplicationUri;

	/*
	 * This function is the WEB API of resource getFacebookFriends and accepts
	 * http requests, which it delegates to the underlying Handler
	 * GetgetFacebookFriendsHandler. It returns any response formatted as stated
	 * in the @Produces JAX-RS annotation below.
	 *
	@Path("/")
	@GET
	@Produces("application/JSON")
	public JavaGetFacebookFriendsOutputModel getgetFacebookFriends(@PathParam("usersId") int usersId)
			throws JSONException {

		String fields = "friends{birthday,first_name,last_name,location,picture.type(large)}";

		HibernateController call = new HibernateController();
		List<JavaUsersModel> user = call.getUserByUsersId(usersId);
		String access_token = user.get(0).getaccesstoken();

		JSONObject obj = new JSONObject();

		// create a new GetgetFacebookFriendsHandler
		GetgetFacebookFriendsHandler oGetgetFacebookFriendsHandler = new GetgetFacebookFriendsHandler(fields, access_token, oApplicationUri);

		return oGetgetFacebookFriendsHandler.getgetfacebookfriends();
	}*/

	@Path("/")
	@GET
	@Produces("application/JSON")
	public JSONArray getgetFacebookFriendsStatus(@PathParam("usersId") int usersId) throws JSONException {

		String fields = "friends{birthday,first_name,last_name,location,picture.type(large)}";

		HibernateController call = new HibernateController();
		List<JavaUsersModel> user = call.getUserByUsersId(usersId);
		String access_token = user.get(0).getaccesstoken();

		JSONObject obj = new JSONObject();

		// create a new GetgetFacebookFriendsHandler
		GetgetFacebookFriendsHandler oGetgetFacebookFriendsHandler = new GetgetFacebookFriendsHandler(fields,
				access_token, oApplicationUri);

		JavaGetFacebookFriendsOutputModel result = oGetgetFacebookFriendsHandler.getgetfacebookfriends();
		int numberOfFriends = result.getFriends().getData().size();
		logger.info("ddddd " + numberOfFriends);

		JSONArray ja = new JSONArray();
		for (int i = 0; i < numberOfFriends; i++) {
			
			
			String facebookId = result.getFriends().getData().get(i).getId();
			
			String location;
			try{
				 location = result.getFriends().getData().get(i).getLocation().getName();
				
			}catch (NullPointerException e) {
				location = "";
				
			}	
			
			String birthday;
			try{
				birthday = result.getFriends().getData().get(i).getBirthday();
				
			}catch (NullPointerException e) {
				birthday = "";
			}	
			String first_name = result.getFriends().getData().get(i).getFirst_name();
			String last_name = result.getFriends().getData().get(i).getLast_name();
			
			JSONObject jo = new JSONObject();
			jo.put("facebookId", facebookId);
			jo.put("birthday", birthday);
			jo.put("first_name", first_name);
			jo.put("last_name", last_name);
			jo.put("location", location);

			// STATUS
			user = call.getUserByFacebookId(facebookId);
			
			if (user.isEmpty()) logger.info("NEMAAAA NICEGAAAA ");
			boolean isuserloggedin = user.get(0).getisuserloggedin();
			if (isuserloggedin) {
				jo.put("status", "online");
			} else {
				jo.put("status", "offline");
			}
			
			ja.put(jo);
		}


		return ja;
	}

}
