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

package eu.fp7.scase.giftcaseapi.giftStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import eu.fp7.scase.giftcaseapi.utilities.HibernateController;

/* JavaAlgogiftStatusController class is responsible to handle incoming http requests for the giftStatus resource. Since this one 
 is not automatable the developer has to manually define its RESTful API.*/
@Path("/users/{usersId}/status")
public class JavaAlgogiftStatusController {

	Logger logger = Logger.getLogger(JavaAlgogiftStatusController.class);
	JSONObject obj = new JSONObject();
	HibernateController call = new HibernateController();

	@Context
	private UriInfo oApplicationUri;

	@Path("/")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String changeGiftStatus(JSONObject giftWithChangedStatus,
			@QueryParam("categoryId") int categoryId) throws JSONException {

		String sendTime;
		String status;
		
		try {
			sendTime =  (String)  giftWithChangedStatus.get("sendTime");
			status = (String) giftWithChangedStatus.get("status");
		} catch (JSONException e) {
			obj.put("InfoException", "Status is updated!");
			return obj + "";
		}

		switch (categoryId) {
		case 1:
		case 2:
			if (call.updateMusicMovieGift(sendTime, status)) {
				obj.put("InfoException", "Status is updated!");
				return obj + "";
			} else {
				obj.put("Exception", "Status is not updated!");
				return obj + "";
			}

		case 3:
			if (call.updateBookGift(sendTime, status)) {
				obj.put("InfoException", "Status is updated!");
				return obj + "";
			} else {
				obj.put("Exception", "Status is not updated!");
				return obj + "";
			}

		case 4:
			if (call.updateEventGift(sendTime, status)) {
				obj.put("InfoException", "Status is updated!");
				return obj + "";
			} else {
				obj.put("Exception", "Status is not updated!");
				return obj + "";
			}

		default:
			obj.put("Exception", "Invalid query parameter \"categoryId\"!");
			return obj + "";
		}

	}
}
