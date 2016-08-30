package eu.fp7.scase.giftcaseapi.facebookLogin;

import java.net.URI;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

//import com.ericsson.scasepilot.core.ApplicationContext;
//import com.ericsson.scasepilot.dao.FactoryPattern;
//import com.ericsson.scasepilot.dao.UserDao;
//import com.ericsson.scasepilot.model.User;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import eu.fp7.scase.giftcaseapi.Users.JavaUsersModel;
import eu.fp7.scase.giftcaseapi.getAboutUser.JavaAlgogetAboutUserController;
import eu.fp7.scase.giftcaseapi.getAboutUser.JavaGetAboutUserOutputModel;
import eu.fp7.scase.giftcaseapi.utilities.HibernateController;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;

/**
 * Class that is specified in the callback-uri of SignIn class. It handles
 * facebook's "response".
 */
@Path("/Callback")
public class JavaAlgofacebookCallbackController {

	final static Logger logger = Logger.getLogger(JavaAlgofacebookCallbackController.class);
	
	@Context
	private UriInfo uriInfo;

	/**
	 * @param code is needed for fetching data from Facebook, code shall be replaced by access token
	 * @return json object with facebookId of user that is logged in
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String callbackCall(@QueryParam("code") String code) {

		JSONObject obj = new JSONObject();

		Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId(ApplicationContext.getAppId(), ApplicationContext.getAppSecret());
		facebook.setOAuthPermissions(ApplicationContext.getScope());
		
		URI callbackURL = uriInfo.getBaseUri(); // http://localhost:8080/SCasePilot/
		String callback = callbackURL + "";
		callback = callback.substring(0, callback.length() - 16); // http://localhost:8080/
	 	callback = callback + "catch-facebook-login.html"; //for frontend
		
		//callback = callback + "SCasePilot/Callback";
		facebook.setOAuthCallbackURL(callback);

		String oauthCode = code; // parameter

		String accesstoken = null;
		try {
			// ovdje facebook4j �alje get zahtjev na facebook i dohva�a access token
			facebook.getOAuthAccessToken(oauthCode);
			
			accesstoken = facebook.getOAuthAccessToken().getToken();
			logger.debug("access token :" + accesstoken);
		} catch (FacebookException  | NullPointerException e1) {
			try {
				obj.put("SystemException", "No token available");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//logger.error("No token available", e1);
			return obj + "";
		}

		//create user model and save it into database
		JavaAlgogetAboutUserController userController = new JavaAlgogetAboutUserController();
		JavaGetAboutUserOutputModel aboutUser = userController.getgetAboutUser(accesstoken, "id,name,picture.type(large),email,first_name,last_name,location,birthday,hometown");
		
		JavaUsersModel user = new JavaUsersModel();
		
		user.setaccesstoken(accesstoken);
		

		
		user.setfirstName(aboutUser.getFirst_name());
		user.setfacebookId(aboutUser.getId());
		user.setlastName(aboutUser.getLast_name());
		user.setisuserloggedin(true);
		
		String profilePicture;
		try{
			profilePicture = aboutUser.getPicture().getData().getUrl();
			user.sethometown(profilePicture);
		}catch (NullPointerException e) {
			profilePicture = "";
			user.sethometown(profilePicture);
		}	
		
		user.setprofilePicture(aboutUser.getPicture().getData().getUrl());
		
		String hometown;
		try{
			 hometown = aboutUser.getHometown().getName();
			user.sethometown(hometown);
		}catch (NullPointerException e) {
			 hometown = "";
			user.sethometown(hometown);
		}	
		String location;
		try{
			 location = aboutUser.getLocation().getName();
			user.setlocation(location);
		}catch (NullPointerException e) {
			 location = "";
			user.setlocation(location);
		}	
		
		String email;
		try{
			email = aboutUser.getEmail();
			user.setemail(email);
		}catch (NullPointerException e) {
			email = "";
			user.setemail(email);
		}
		
		
		user.setimsi("");
				
		logger.info("babla " + aboutUser.getEmail() + aboutUser.getHometown().getName());
		
	

		// if user doesn't exist, create new user, otherwise update old user
		HibernateController call = new HibernateController();
		List<JavaUsersModel> usermodel = call.getUserByFacebookId(aboutUser.getId());
		
		logger.info("facebookID: " + aboutUser.getId() );
			
		int usersId;
			if (usermodel.isEmpty()) {
				JavaUsersModel userNew = call.postUsers(user);
				logger.info(userNew.getfirstName()+ " is created");
				usersId = userNew.getUsersId();
			} else {
				call.updateUser(aboutUser.getId(), accesstoken, true, location, hometown, email, aboutUser.getFirst_name(), aboutUser.getLast_name(), profilePicture);
				logger.info( aboutUser.getFirst_name()+ " is updated");
				usersId = usermodel.get(0).getUsersId();
			}
		
			
			//return to frontend
			try {
				obj.put("lastName", aboutUser.getLast_name());
				obj.put("facebookId", aboutUser.getId());
				obj.put("usersId", usersId);
				obj.put("profilePicture", profilePicture);
				obj.put("firstName", aboutUser.getFirst_name());
				obj.put("birthday", aboutUser.getBirthday());
			} catch (JSONException e) {
				e.printStackTrace();
			}	
		// return "callback(" + obj + ")";
		return obj + "";
	}
}
