package co.aarav.mvc.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import co.aarav.mvc.model.RoleMaster;
import co.aarav.mvc.service.MasterService;

@Service("roleMasterValidator")
public class RoleMasterValidator  implements Validator {
	
	@Autowired
	MasterService masterService;

	@Override
	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return RoleMaster.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		
		RoleMaster roleMaster = (RoleMaster) obj;
		Boolean isRoleMasterAlreadyExist= masterService.isRoleMasterAlreadyExist(roleMaster.getName());
		System.out.println("isRoleMasterAlreadyExist :"+isRoleMasterAlreadyExist);
		if (isRoleMasterAlreadyExist) {
			errors.rejectValue("name", "already.exist", new Object[]{"'Role Name'"}, "Role Name already exist");
			//errors.reject("name", "role.name.alreadyExist");
//			errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
		}
		
		
		
	}

}
