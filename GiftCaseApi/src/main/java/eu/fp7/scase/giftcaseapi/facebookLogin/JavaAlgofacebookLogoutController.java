package eu.fp7.scase.giftcaseapi.facebookLogin;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import eu.fp7.scase.giftcaseapi.Users.JavaUsersModel;
import eu.fp7.scase.giftcaseapi.utilities.HibernateController;

@Path("/users/{usersId}/logout")
public class JavaAlgofacebookLogoutController {

	final static Logger logger = Logger.getLogger(JavaAlgofacebookLogoutController.class);

	@Context
	private UriInfo uriInfo;

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject logout(@PathParam("usersId") int usersId) {

		JSONObject obj = new JSONObject();

		// get facebook instance
//		Facebook facebook = new FacebookFactory().getInstance();
//		facebook.setOAuthAppId(ApplicationContext.getAppId(), ApplicationContext.getAppSecret());
//		facebook.setOAuthPermissions(ApplicationContext.getScope());


		// if user doesn't exist or you want to logout user who is not logged
		// in, send an exception message
		HibernateController call = new HibernateController();
		List<JavaUsersModel> usermodel = call.getUserByUsersId(usersId);

		try {
			if (usermodel.isEmpty()) {
				obj.put("SystemException", "User doesn't exist!");
				return obj;
			} else {
				if (!(usermodel.get(0).getisuserloggedin())) {
					obj.put("SystemException", "User is not logged in!");
					return obj;
				}
			}
			// set isLooggedIn=false
			call.updateUser(usersId, false);
			obj.put("InfoException", "User is successfuly logged out!");

			return obj;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
