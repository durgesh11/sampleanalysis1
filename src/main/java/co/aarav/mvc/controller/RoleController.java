package co.aarav.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import co.aarav.mvc.model.Employee;
import co.aarav.mvc.model.RoleMaster;
import co.aarav.mvc.service.MasterService;
import co.aarav.mvc.service.UserService;

@Controller
public class RoleController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	MasterService masterService;
	
	@Autowired
	UserService userService;

	@Autowired
	@Qualifier("roleMasterValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "master-roles.html", method = RequestMethod.GET)
	public ModelAndView masterRoles(HttpServletRequest request) {
		logger.info("LoginPage...");
		ModelAndView mav = new ModelAndView("master-roles");
		try {

			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}

			List<RoleMaster> roleMasterList = masterService.getRoleMastersList();
			logger.info("roleMasterList :" + roleMasterList.size());
			mav.addObject("roleMasterList", roleMasterList);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception ", e);
		}
		return mav;
	}

	@RequestMapping(value = "add-new-role", method = RequestMethod.GET)
	public ModelAndView addNewRole(HttpServletRequest request, Model model) {
		logger.info("add-new-role...");
		ModelAndView mav = new ModelAndView("master-roles-add-update");
		try {

			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}

			/*
			 * List<RoleMaster> roleMasterList = masterService.getRoleMastersList();
			 * logger.info("roleMasterList :" + roleMasterList.size());
			 * mav.addObject("roleMasterList", roleMasterList);
			 */

			model.addAttribute("roleMaster", new RoleMaster());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception ", e);
		}
		return mav;
	}

	@RequestMapping(value = "roleForm", params = "new", method = RequestMethod.POST)
	public ModelAndView saveNewRole(HttpServletRequest request, Model model,
			@ModelAttribute("roleMaster") @Validated RoleMaster roleMaster, BindingResult bindingResult) {
		logger.info("saveNewRole...");
		ModelAndView mav = new ModelAndView("master-roles");
		try {

			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				// return "sessionOut";
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}

			if (bindingResult.hasErrors()) {
				logger.info("Returning master-roles-add-update.jsp page");
				mav.setViewName("master-roles-add-update");
				return mav;
			}

			Long roleId=masterService.saveRoleMaster(roleMaster);
			if (roleId>0) {
				userService.saveEmployeePrivileges(roleId);
			}
			
			

			logger.info("RoleMAster :" + roleMaster.toString());
			List<RoleMaster> roleMasterList = masterService.getRoleMastersList();
			logger.info("roleMasterList :" + roleMasterList.size());
			mav.addObject("roleMasterList", roleMasterList);

			// model.addAttribute("roleMaster", new RoleMaster());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception ", e);
		}
		// return "master-roles";
		return mav;
	}

	@RequestMapping(value = "deleteRole", method = RequestMethod.POST)
	public ModelAndView deleteRole(HttpServletRequest request, @RequestParam("roleId") Long roleId) {
		logger.info("deleteRole..." + roleId);
		//ModelAndView mav = new ModelAndView("master-roles");
		try {

			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				// return "sessionOut";
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}

			masterService.deleteRoleMaster(roleId);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception ", e);
		}
		// return "master-roles";
		return new ModelAndView(new RedirectView(request.getContextPath() + "/master-roles.html"));
	}

	@RequestMapping(value = "editRole/{id}", method = RequestMethod.GET)
	public ModelAndView editRole(HttpServletRequest request, @PathVariable("id") Long id,Model model) {
		logger.info("editRole..." + id);
		ModelAndView mav = new ModelAndView("master-roles-add-update");
		try {

			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				// return "sessionOut";
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}
			
			RoleMaster roleMaster=masterService.getRoleMaster(id);
			
			model.addAttribute("roleMaster", roleMaster);
			mav.addObject("edit", true);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception ", e);
		}
		// return "master-roles";
		return mav;
	}

	@RequestMapping(value = "roleForm", params = "update", method = RequestMethod.POST)
	public ModelAndView updateRole(HttpServletRequest request, Model model,
			@ModelAttribute("roleMaster") @Validated RoleMaster roleMaster, BindingResult bindingResult) {
		logger.info("updateRole...");
		ModelAndView mav = new ModelAndView("master-roles");
		try {

			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				// return "sessionOut";
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}
			mav.addObject("edit", true);
			if (bindingResult.hasErrors()) {
				logger.info("Returning master-roles-add-update.jsp page");
				mav.setViewName("master-roles-add-update");
				// return "master-roles-add-update";
				return mav;
			}
			logger.info("roleMaster :"+roleMaster.toString());
			masterService.updateRoleMaster(roleMaster);

			/*
			 * logger.info("RoleMAster :" + roleMaster.toString()); List<RoleMaster>
			 * roleMasterList = masterService.getRoleMastersList();
			 * logger.info("roleMasterList :" + roleMasterList.size());
			 * mav.addObject("roleMasterList", roleMasterList);
			 */

			// model.addAttribute("roleMaster", new RoleMaster());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception ", e);
		}
		// return "master-roles";
		return new ModelAndView(new RedirectView(request.getContextPath() + "/master-roles.html"));
	}

}
