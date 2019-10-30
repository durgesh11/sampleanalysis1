package co.aarav.mvc.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import co.aarav.mvc.model.Employee;
import co.aarav.mvc.service.MasterService;

@Service("employeeValidator")
public class EmployeeValidator implements Validator {

	@Autowired
	MasterService masterService;

	@Override
	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return Employee.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "LastName", "LastName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephone", "telephone.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "emailAddress.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "confirmPassword.required");

		Employee employee = (Employee) obj;
		Boolean isEmployeeAlreadyExist = masterService.isEmployeeAlreadyExist(employee.getUserName());
		System.out.println("isEmployeeAlreadyExist :" + isEmployeeAlreadyExist);
		if (isEmployeeAlreadyExist) {
			errors.rejectValue("userName", "already.exist", new Object[] { "'Employee Name'" },
					"Employee Name already exist");
		}
		
		Boolean isEmployeeEmailAlreadyExist = masterService.isEmployeeEmailAlreadyExist(employee.getEmailAddress());
		System.out.println("isEmployeeEmailAlreadyExist :" + isEmployeeEmailAlreadyExist);
		if (isEmployeeEmailAlreadyExist) {
			errors.rejectValue("emailAddress", "already.exist", new Object[] { "'Employee E-mail Address'" },
					"E-mail Address already exist");
		}

	}

}
