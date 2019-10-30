package co.aarav.mvc.controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import co.aarav.mvc.model.Employee;
import co.aarav.mvc.model.EmployeeRolePrivileges;
import co.aarav.mvc.model.Menu;
import co.aarav.mvc.model.MenuHeader;
import co.aarav.mvc.model.RoleMaster;
import co.aarav.mvc.service.MasterService;
import co.aarav.mvc.service.UserService;

@Controller
public class EmployeeRolePrivilegesController {

	private static Logger logger = Logger.getLogger(EmployeeRolePrivilegesController.class);

	@Autowired
	MasterService masterService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "master-employee-privileges.html", method = RequestMethod.GET)
	public ModelAndView listEmployeesPrivileges(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("listEmployeesPrivileges****************");
		ModelAndView mav = new ModelAndView();
		try {
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}
			mav.setViewName("master-employee-privileges");

			List<RoleMaster> roleMasterList = masterService.getRoleMastersList();
			logger.info("roleMasterList :" + roleMasterList.size());
			mav.addObject("roleMasterList", roleMasterList);
			model.addAttribute("employeeRolePrivileges", new EmployeeRolePrivileges());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception :", e);
		}
		return mav;
	}

	@RequestMapping(value = "show-employee-privileges", method = RequestMethod.POST)
	public ModelAndView showEmployeePrivileges(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestParam("roleId") Long roleId) {
		logger.info("showEmployeePrivileges****************" + roleId);
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("master-employee-privileges-list");
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}

			List<RoleMaster> roleMasterList = masterService.getRoleMastersList();
			logger.info("roleMasterList :" + roleMasterList.size());
			mav.addObject("roleMasterList", roleMasterList);

			List<MenuHeader> menuHeaderList = userService.getAllMenuHeader();
			mav.addObject("menuHeaderList", menuHeaderList);

			List<EmployeeRolePrivileges> rolePrivilegesList = userService.getEmployeePrivilegesByRole(roleId, null);
			mav.addObject("rolePrivilegesList", rolePrivilegesList);
			logger.info("rolePrivilegesList :" + rolePrivilegesList.size());

			List<Menu> menuList = userService.getAllMenus();
			mav.addObject("menuList", menuList);
			model.addAttribute("employeeRolePrivileges", new EmployeeRolePrivileges());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception :", e);
		}
		return mav;
	}

	@RequestMapping(value = "saveEmployeeRolePrivileges", method = RequestMethod.POST)
	public ModelAndView saveEmployeeRolePrivileges(HttpServletRequest request,
			@ModelAttribute("employeeRolePrivileges") EmployeeRolePrivileges employeeRolePrivileges) {
		logger.info("saveEmployeeRolePrivileges****************");
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("master-employee-privileges-list");
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}

			logger.info("employeeRolePrivileges :" + employeeRolePrivileges.toString());

			String[] multipleRole = employeeRolePrivileges.getMultipleRole();
			logger.info("multipleRole :" + multipleRole);

			List<EmployeeRolePrivileges> empPrevilegesList = userService
					.getEmployeePrivilegesByRole(employeeRolePrivileges.getRoleId(), null);
			for (Iterator iterator = empPrevilegesList.iterator(); iterator.hasNext();) {
				EmployeeRolePrivileges ePrivileges = (EmployeeRolePrivileges) iterator.next();

				Long id = ePrivileges.getId();
				
				Boolean isChecked=isListContainsArrValue(multipleRole, String.valueOf(id));
				EmployeeRolePrivileges employeeRolePrivilegesObj = new EmployeeRolePrivileges();
				 

				
				if(isChecked) {
					employeeRolePrivilegesObj.setHidden(false);
				} else {
					employeeRolePrivilegesObj.setHidden(true);
				}
				
				employeeRolePrivilegesObj.setId(id);
				logger.info("employeeRolePrivileges :" + employeeRolePrivilegesObj.toString());
				userService.updateEmployeePrivileges(employeeRolePrivilegesObj);
//
//				for (int i = 0; i < multipleRole.length; i++) {
//					logger.info("multipleRole[i] :" + multipleRole[i]);
//					
//				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception :", e);
		}
		return new ModelAndView(new RedirectView(request.getContextPath() + "/master-employee-privileges.html"));
	}
	
	public   boolean isListContainsArrValue(String[] arr, String targetValue) {
		return Arrays.asList(arr).contains(targetValue);
	}

}
