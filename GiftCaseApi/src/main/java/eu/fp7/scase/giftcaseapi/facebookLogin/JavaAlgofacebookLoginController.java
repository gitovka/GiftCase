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


package eu.fp7.scase.giftcaseapi.facebookLogin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
//Please add any needed imports here.

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;

/* JavaAlgofacebookLoginController class is responsible to handle incoming http requests for the facebookLogin resource. Since this one 
 is not automatable the developer has to manually define its RESTful API.*/


@Path("/signin")
public class JavaAlgofacebookLoginController  {
		
	final static Logger logger = Logger.getLogger(JavaAlgofacebookLoginController.class);

	@Context
	private UriInfo uriInfo;

	//http://localhost:8080/SCasePilot/users/signin
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject facebookLogin() throws IOException  {
		
		JSONObject object = new JSONObject();
		
		// log4j
		Properties props = new Properties();
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("log4j.properties");
		props.load(inputStream);
		PropertyConfigurator.configure(props);

		//Facebook setting
		Facebook facebook = new FacebookFactory().getInstance();
		
		facebook.setOAuthAppId(ApplicationContext.getAppId(), ApplicationContext.getAppSecret());
		facebook.setOAuthPermissions(ApplicationContext.getScope());
		
		logger.info("dohvacen base url: " + uriInfo.getBaseUri());
		
		URI callbackURL = uriInfo.getBaseUri();			// http://localhost:8080/giftcaseapi/api/
		//String callback = callbackURL + "Callback"; giftcaseapi/api/
		String callback = callbackURL+"";
		callback = callback.substring(0, callback.length() - 16); //http://localhost:8080/ 
		callback = callback + "catch-facebook-login.html"; //za frontend
		
		
		//https://www.facebook.com/dialog/oauth?client_id=1606454626259180&redirect_uri=http://localhost:8080/pages/catchFacebookLogin.html&scope=user_likes,user_about_me,read_stream,user_friends,user_status,user_birthday,user_events,user_friends&callback=JSON_CALLBACK
		String facebookResponseCallback = facebook.getOAuthAuthorizationURL(callback);
		
		logger.info("facebook response callback: "+ facebookResponseCallback);
		
		try {
			object.put("facebookRedirect", facebookResponseCallback);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return object;	
	}
}

