package co.aarav.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
public class EmployeeController {

	private static Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	MasterService masterService;

	@Autowired
	UserService userService;

	@Autowired
	@Qualifier("employeeValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "master-employees.html", method = RequestMethod.GET)
	public ModelAndView listEmployees(ModelMap model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("listUsers****************");
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("master-employee");
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}
			List<Employee> employeeList = masterService.getEmployeeList();
			logger.info("employeeList :" + employeeList.size());
			mav.addObject("employeeList", employeeList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception :", e);
		}
		return mav;
	}

	@RequestMapping(value = "add-new-employee", method = RequestMethod.GET)
	public ModelAndView addNewEmployee(HttpServletRequest request, Model model) {
		logger.info("addNewEmployee...");
		ModelAndView mav = new ModelAndView("master-employee-add-update");
		try {

			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}
			mav.addObject("edit", false);

			List<RoleMaster> roleMasterList = masterService.getRoleMastersList();
			logger.info("roleMasterList :" + roleMasterList.size());
			mav.addObject("roleMasterList", roleMasterList);

			model.addAttribute("employee", new Employee());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception ", e);
		}
		return mav;
	}

	@RequestMapping(value = "employeeForm", params = "new", method = RequestMethod.POST)
	public ModelAndView saveNewEmployee(HttpServletRequest request, Model model,
			@ModelAttribute("employee") @Validated Employee employee, BindingResult bindingResult) {
		logger.info("saveNewEmployee...");
		ModelAndView mav = new ModelAndView("master-roles");
		try {

			Employee employeeObj = (Employee) request.getSession().getAttribute("employee");
			if (employeeObj == null) {
				// return "sessionOut";
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}

			if (bindingResult.hasErrors()) {
				logger.info("Returning master-roles-add-new.jsp page");
				mav.setViewName("master-employee-add-update");
				// return "master-roles-add-new";
				return mav;
			}

			Long employeedId = masterService.saveEmployee(employee);
			logger.info("EMployeee id created..:" + employeedId);

			// model.addAttribute("roleMaster", new RoleMaster());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception ", e);
		}
		// return "master-roles";
		return new ModelAndView(new RedirectView(request.getContextPath() + "/master-employees.html"));
	}

	@RequestMapping(value = "deleteEmployee", method = RequestMethod.POST)
	public ModelAndView deleteEmployee(HttpServletRequest request, @RequestParam("employeeId") Long employeeId) {
		logger.info("deleteEmployee..." + employeeId);
		// ModelAndView mav = new ModelAndView("master-roles");
		try {

			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				// return "sessionOut";
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}

			masterService.deleteEmployee(employeeId);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception ", e);
		}
		// return "master-roles";
		return new ModelAndView(new RedirectView(request.getContextPath() + "/master-employees.html"));
	}

	@RequestMapping(value = "editEmployee/{id}", method = RequestMethod.GET)
	public ModelAndView editEmployee(HttpServletRequest request, @PathVariable("id") Long id, Model model) {
		logger.info("editEmployee..." + id);
		ModelAndView mav = new ModelAndView("master-employee-add-update");
		try {

			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				// return "sessionOut";
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}

			Employee emp = masterService.getEmployee(id);

			model.addAttribute("employee", emp);
			mav.addObject("edit", true);

			List<RoleMaster> roleMasterList = masterService.getRoleMastersList();
			logger.info("roleMasterList :" + roleMasterList.size());
			mav.addObject("roleMasterList", roleMasterList);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception ", e);
		}
		// return "master-roles";
		return mav;
	}

	@RequestMapping(value = "employeeForm", params = "update", method = RequestMethod.POST)
	public ModelAndView updateEmployee(HttpServletRequest request, Model model,
			@ModelAttribute("employee") @Validated Employee employee1, BindingResult bindingResult) {
		logger.info("updateEmployee...");
		ModelAndView mav = new ModelAndView("master-roles");
		try {

			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if (employee == null) {
				// return "sessionOut";
				return new ModelAndView(new RedirectView(request.getContextPath() + "/sessionOut"));
			}
			mav.addObject("edit", true);
			masterService.updateEmployee(employee1);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception ", e);
		}
		// return "master-roles";
		return new ModelAndView(new RedirectView(request.getContextPath() + "/master-employees.html"));
	}
}
