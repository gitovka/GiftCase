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

package eu.fp7.scase.giftcaseapi.getMusicSocial;

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
 * JavaAlgogetMusicSocialController class is responsible to handle incoming HTTP requests for the getMusicSocial resource. More specifically
 * this resource controller handles client requests that are delegated to an external server, which is executed at
 * http://109.231.127.61:8080/GetMusicSocial-0.0.1-SNAPSHOT/rest/result/query
*/
@Path("/users/{usersId}/getMusicSocial")
public class JavaAlgogetMusicSocialController{

    @Context
    private UriInfo oApplicationUri;

	/* 
	 * This function is the WEB API of resource getMusicSocial and accepts http  requests,   
     * which it delegates to the underlying Handler GetgetMusicSocialHandler. 
     * It returns any response formatted as stated in the @Produces JAX-RS annotation below.
    */
	@Path("/")
	@GET
	@Produces("application/JSON")
    public JavaGetMusicSocialOutputModel getgetMusicSocial(@QueryParam("targetFacebookId") String targetFacebookId,
			@PathParam("usersId") int userId){
    		
    		String fields = "music";
    		String Type0 = "music";
    		String k = ApplicationContext.apiKeyTasteKid;
    		String pageSize = "1";
    		String sort = "";
    		String apiKey = ApplicationContext.apiKeyBestBuy;
    		String show ="name,details.name,image,regularPrice,longDescription,shortDescription";
    		String limit = "3";
    		String format ="json";
    		String q_num = "3";
    		String search_num = "3";
    		
    		HibernateController call = new HibernateController();
    		List<JavaUsersModel> user = call.getUserByFacebookId(targetFacebookId);
    		String access_token = user.get(0).getaccesstoken();

		//create a new GetgetMusicSocialHandler
		GetgetMusicSocialHandler oGetgetMusicSocialHandler = new GetgetMusicSocialHandler(access_token,  fields,  Type0,  k,  pageSize,  sort,  apiKey,  show,  limit,  format,  q_num, search_num, oApplicationUri);
		return oGetgetMusicSocialHandler.getgetmusicsocial();
    }
}

