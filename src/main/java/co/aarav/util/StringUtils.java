/**
*
* @author  : Durgesh Mudras
* @Date    : 16-10-2019
* @version : 1.0.0
* 
*/
package co.aarav.util;

/**
 * 
 * @author Durgesh Mudras 
 * As the name suggests StringUtils class is meant for
 *         putting all string utilities which very generalised
 * 
 */

public class StringUtils {

	/**
	 * Checks if subject string is empty or null. If there are only whitespace
	 * characters or presence of null or empty string results in returning true
	 * otherwise false is returned.
	 * 
	 * @param subject
	 *            is a string which needs to be tested for emptiness or
	 *            nullness.
	 * 
	 * @return true if subject is null or subject contains any number of
	 *         occurrence of whitespace. else @return false
	 */
	public static boolean testEmpty(String subject) {
		if (subject == null || subject.matches("^\\s*$")
				|| subject.trim().length() == 0 || subject.equals("-1")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean containsOnlyNumbers(String str) {

		// It can't contain only numbers if it's null or empty...
		if (str == null || str.length() == 0) {
			return false;
		}

		for (int i = 0; i < str.length(); i++) {

			// If we find a non-digit character we return false.
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Prepends space characters to a string until it reaches given length. if
	 * string is already longer than length, string is cut.
	 * 
	 * @param text original text
	 * @param length final length
	 * @return string with given length and prepended spaces
	 */
	public static String prependSpaces(String text, int length) {
		if (text.length() > length) {
			return text.substring(0, length);
		}
		StringBuilder buffer = new StringBuilder(text);
		for (int i = text.length(); i < length; i++) {
			buffer.insert(0, ' ');
		}
		return buffer.toString();
	}
	
	/**
	 * Appends space characters to a string until it reaches given length. if
	 * string is already longer than length, string is cut.
	 * 
	 * @param text original text
	 * @param length final length
	 * @return string with given length and prepended spaces
	 */
	public static String appendSpaces(String text, int length) {
		if (text.length() > length) {
			return text.substring(0, length);
		}
		StringBuilder buffer = new StringBuilder(text);
		for (int i = text.length(); i < length; i++) {
			buffer.append(' ');
		}
		return buffer.toString();
	}
	
	public static String prependChars(String text, int length, char c) {
		if (text.length() > length) {
			return text.substring(0, length);
		}
		StringBuilder buffer = new StringBuilder(text);
		for (int i = text.length(); i < length; i++) {
			buffer.insert(0, c);
		}
		return buffer.toString();
	}
}
