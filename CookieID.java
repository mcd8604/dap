
public class CookieID {
	
	public static String COOKIE_ID = "CookieID";
	
	public static String createCookieID() {
		 return java.util.UUID.randomUUID().toString();
	}
}
