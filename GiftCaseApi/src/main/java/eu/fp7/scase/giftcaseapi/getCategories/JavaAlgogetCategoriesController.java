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


package eu.fp7.scase.giftcaseapi.getCategories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import eu.fp7.scase.giftcaseapi.Users.JavaUsersModel;
import eu.fp7.scase.giftcaseapi.utilities.HibernateController;

//Please add any needed imports here.

/* JavaAlgogetCategoriesController class is responsible to handle incoming http requests for the getCategories resource. Since this one 
 is not automatable the developer has to manually define its RESTful API.*/
@Path("/gifts/categories")
public class JavaAlgogetCategoriesController{

//   @Context
//   private UriInfo oApplicationUri;

    
    final static Logger logger = Logger.getLogger(JavaAlgogetCategoriesController.class);
	/* This function handles http  requests  
    and returns any response formatted as stated in the @Produces JAX-RS annotation below.*/
	@Path("/")
	@GET
	@Produces("application/JSON")
    public String getgetCategories(@QueryParam("targetFacebookId") String targetFacebookId){

				JSONObject obj = new JSONObject();
				HibernateController call = new HibernateController();
				
				List<JavaUsersModel> user = call.getUserByFacebookId(targetFacebookId);
				logger.info("is empty: "+ user.size());
				
				String imsi = user.get(0).getimsi();

				JSONObject json = null;
				try {
					json = readJsonFromUrl("http://social.tel.fer.hr:8080/Telco/api/" + imsi);
				} catch (JSONException | IOException e) {
					try {
						obj.put("Exception", "Problem with connecting Telco server");
					} catch (JSONException e1) {
					}
					return obj+"";
				}
				logger.info(json.toString());
				return json.toString();
	}

			
			private static String readAll(Reader rd) throws IOException {
				StringBuilder sb = new StringBuilder();
				int cp;
				while ((cp = rd.read()) != -1) {
					sb.append((char) cp);
				}
				return sb.toString();
			}

			public static JSONObject readJsonFromUrl(String url) throws IOException,
					JSONException {
				InputStream is = new URL(url).openStream();
				try {
					BufferedReader rd = new BufferedReader(new InputStreamReader(is,
							Charset.forName("UTF-8")));
					String jsonText = readAll(rd);
					JSONObject json = new JSONObject(jsonText);
					return json;
				} finally {
					is.close();
				}
			}

		
    }

