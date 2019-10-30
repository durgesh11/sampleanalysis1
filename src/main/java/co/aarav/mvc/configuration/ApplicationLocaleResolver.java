/**
*
* @author  : Durgesh Mudras
* @Date    : 16-10-2019
* @version : 1.0.0
* 
*/
package co.aarav.mvc.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration("ApplicationLocaleResolver")
public class ApplicationLocaleResolver extends SessionLocaleResolver {

	@Bean(name = "localeResolver")
	public LocaleResolver localeResolver() {
		return new ApplicationLocaleResolver();
	}

	/**
     * Configure resolveLocale for locale.
     */
//	@Override
//	public Locale resolveLocale(HttpServletRequest request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		EmployeeSecured employeeObj = (EmployeeSecured) auth.getPrincipal();
//		
//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		String userName = securityContext.getAuthentication().getName();
//		String localeOption = userService.getUserLocale();
//		Locale userLocale = Locale.forLanguageTag("de");
//		return userLocale;
//	}

	/**
     * Configure messageResourse to serve messages.. etc...
     */
	@Bean(name = "messageSource")
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource ret = new ReloadableResourceBundleMessageSource();
		ret.setBasename("classpath:messages");
		ret.setCacheSeconds(1);
		ret.setUseCodeAsDefaultMessage(true);
		ret.setDefaultEncoding("utf-8");
		return ret;
	}
	
	/**
     * Configure setLocale for set dynamic locale.
     */

	/*
	 * @Override public void setLocale(HttpServletRequest request,
	 * HttpServletResponse response, Locale locale) { super.setLocale(request,
	 * response, locale);
	 * 
	 * SecurityContext securityContext = SecurityContextHolder.getContext(); String
	 * userName = securityContext.getAuthentication().getName();
	 * //userService.saveUsersPreferedLocaleOption(userName, locale); }
	 */

}