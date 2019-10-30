/**
*
* @author  : Durgesh Mudras
* @Date    : 16-10-2019
* @version : 1.0.0
* 
*/
package co.aarav.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public final class WebUtil {
	  
	  /**
	  * Validate the form of an email address.
	  *
	  * <P>Return <tt>true</tt> only if 
	  *<ul> 
	  * <li> <tt>aEmailAddress</tt> can successfully construct an 
	  * {@link javax.mail.internet.InternetAddress} 
	  *	</li>
	  *</ul>
	  *
	  *<P> The second condition arises since local email addresses, simply of the form
	  * "<tt>albert</tt>", for example, are valid for 
	  * {@link javax.mail.internet.InternetAddress}, but almost always undesired.
	  */
	  public static boolean isValidEmailAddress(String aEmailAddress){
	    if (aEmailAddress == null) return false;
	    boolean result = true;
	    try {
	      InternetAddress emailAddr = new InternetAddress(aEmailAddress);
	      emailAddr.validate();
	    }
	    catch (AddressException ex){
	      result = false;
	    }
	    return result;
	  }

	 
	}
