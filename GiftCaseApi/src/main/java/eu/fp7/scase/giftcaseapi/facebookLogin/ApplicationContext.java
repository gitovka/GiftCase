package eu.fp7.scase.giftcaseapi.facebookLogin;

/**
 * Facebook application context. Contains application ID, application secret and
 * application scope (permissions)
 * 
 */
public class ApplicationContext {

	private static final String appId = "1818891138333059";
	private static final String appSecret = "f3bf583d0a304584055ae79c61112c1b";
	private static final String scope = "user_likes,user_about_me,user_friends,email,user_location,user_hometown,public_profile,user_birthday"; //user_status,user_birthday,user_events,
	public static final String apiKeyTasteKid = "148163-Crazyapp-CUXF3LNU";
	
	// BestBuy
	public static final String apiKeyBestBuy = "bre9srsvtxpzxtm5qjsh6hmh";
	public static final String showBestBuy = "name,details.name,image,condition,regularPrice,longDescription,shortDescription";
	
	
	public static final String apiKeyEventful = "BzmbfWTn6sCrMTpw";	

	public static String getAppId() {
		return appId;
	}

	public static String getAppSecret() {
		return appSecret;
	}
	
	public static String getScope() {
		return scope;
	}

}