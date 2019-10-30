package co.aarav.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import co.aarav.mvc.model.Employee;
import co.aarav.mvc.service.MasterService;
import co.aarav.mvc.service.UserService;

@Controller
public class ComponentController {

	private static Logger logger = Logger.getLogger(ComponentController.class);

	@Autowired
	MasterService masterService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "master-component.html", method = RequestMethod.GET)
	public ModelAndView getMasterComponentList(ModelMap model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("getMasterComponentList****************");
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("master-component");
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

}
