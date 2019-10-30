/**
*
* @author  : Durgesh Mudras
* @Date    : 16-10-2019
* @version : 1.0.0
* 
*/
package co.aarav.util;

import javax.servlet.http.HttpServletRequest;

public class GenericUtils {

 
	
	public static String getContextUrl(HttpServletRequest request) {
		String uri = request.getRequestURI();
		//uri = "/context/someAction"
		String url = request.getRequestURL().toString();
		// url = "http://server.name:8080/context/someAction"
		String ctxPath = request.getContextPath();
		// ctxPath = "/context";
		url = url.replaceFirst(uri, "");
		// url = "http://server.name:8080"
		url = url + ctxPath;
		//url = "http://server.name:8080/context"
		
		return url;
	}
	
	public static String sendEmail() {
		
		return null;
	}
	
	public static String getLocaleByLanguage(String language) {
		String locale="de_DE";
		if (language!=null && language.equals("de")) {
			return "EURO";
		} else if (language!=null && language.equals("en")) {
			return "EN_IN";
		} 
		
		return locale;
	}
	
	
}
