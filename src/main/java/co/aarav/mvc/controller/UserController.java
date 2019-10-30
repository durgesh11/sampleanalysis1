/**
*
* @author  : Durgesh Mudras
* @Date    : 18-11-2018
* @version : 1.0.0
* 
*/
package co.aarav.mvc.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import co.aarav.mvc.model.Employee;
import co.aarav.mvc.model.EmployeeRolePrivileges;
import co.aarav.mvc.model.EmployeeSecured;
import co.aarav.mvc.model.MenuHeader;
import co.aarav.mvc.service.MasterService;
import co.aarav.mvc.service.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes("Employee")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	MasterService masterService;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	private LocaleResolver localeResolver;

	/*
	 * @Autowired private CommonService commonService;
	 */

	/*
	 * @Autowired private EmployeeService employeeService;
	 */

	/**
	 * This method will list all existing Employees(Users).
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("listUsers****************");
		try {
			List<Employee> emp = userService.findAllEmployee();
			model.addAttribute("users", emp);
			model.addAttribute("loggedinuser", getPrincipal());

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			EmployeeSecured employeeObj = (EmployeeSecured) auth.getPrincipal();

			Employee employee = setEmployee(employeeObj);
			session.setAttribute("employee", employee);

			List<EmployeeRolePrivileges> employeeRolePrivilegesList = userService
					.getEmployeePrivilegesByRole(employee.getRole().getId(),false);
			logger.info("employeeRolePrivileges List :" + employeeRolePrivilegesList.size());
			session.setAttribute("employeeRolePrivilegesList", employeeRolePrivilegesList);

			if (employeeRolePrivilegesList != null && employeeRolePrivilegesList.size() > 0) {
				List<MenuHeader> menuHeaderList = userService.getAllMenuHeader();
				logger.info("menuHeaderList List :" + menuHeaderList.size());
				session.setAttribute("menuHeaderList", menuHeaderList);
			}

			String languageLocale = "en";// employeeObj.getChainOwner().getLanguage();

			Locale userLocale = Locale.forLanguageTag(languageLocale);
			localeResolver.setLocale(request, response, userLocale);

			// Update Last Login...

			Employee employee2 = new Employee();
			employee2.setId(employee.getId());
			employee2.setLastLogin(Calendar.getInstance());
			masterService.updateEmployeeLastLogin(employee2);

			logger.info("employee :" + employee.toString());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception :", e);
		}
		return "userslist";
	}

	/**
	 * This method handles login GET requests. If users is already logged-in and
	 * tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		logger.info("LoginPage...");

		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else {
			return "redirect:/list";
		}

	}

	/**
	 * This method handles logout requests. Toggle the handlers if you are
	 * 
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method handles sessionOut requests. Toggle the handlers if you are
	 * 
	 */
	@RequestMapping(value = "/sessionOut", method = RequestMethod.GET)
	public String sessionOut(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?sessionOut";
	}

	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	static String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	/**
	 * This method returns true if users is already authenticated [logged-in], else
	 * false.
	 */

	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	/**
	 * This method returns header Page
	 * 
	 */
	@RequestMapping(value = "/header.html")
	public ModelAndView getHeader(HttpServletResponse response) throws IOException {
		return new ModelAndView("header");
	}

	/**
	 * This method returns Footer Page
	 * 
	 */
	@RequestMapping(value = "footer.html")
	public ModelAndView getFooter(HttpServletResponse response) throws IOException {
		return new ModelAndView("footer");
	}

	/**
	 * This method returns Set EmployeeObject which could be used to put in session.
	 * 
	 */
	private Employee setEmployee(EmployeeSecured employeeSecured) {
		Employee employee = new Employee();
		employee.setActive(employeeSecured.getActive());

		employee.setDeleted(employeeSecured.getDeleted());
		employee.setEmailAddress(employeeSecured.getEmailAddress());
		employee.setEmployeeType(employeeSecured.getEmployeeType());
		employee.setFirstName(employeeSecured.getFirstName());
		employee.setId(employeeSecured.getId());
		employee.setLastLogin(employeeSecured.getLastLogin());
		employee.setLastName(employeeSecured.getLastName());

		employee.setLoginRole(employeeSecured.getLoginRole());
		employee.setPassword(employeeSecured.getPassword());
		employee.setRegistrationDate(employeeSecured.getRegistrationDate());

		employee.setSuspendedReason(employeeSecured.getSuspendedReason());
		employee.setTelephone(employeeSecured.getTelephone());
		employee.setRole(employeeSecured.getRole());

		return employee;
	}

	/**
	 * This method returns Error page.
	 * 
	 */
	@RequestMapping(value = "/{error}")
	public ModelAndView loginErrorView(@PathVariable("error") String error) {
		ModelAndView mav = new ModelAndView();
		logger.info("error:" + error);
		mav.setViewName("error");
		mav.addObject("error", error);
		return mav;
	}
}
