package log_and_reg;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("pwdvalidator")
public class pwdvalidator implements Validator{

	String pwdconsist="^[0-9a-zA-Z_]{1,}$";
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		// TODO Auto-generated method stub
		String arg=(String)arg2;
		System.out.print(arg.length());
		if(arg.length()>20||arg.length()<6)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "输入有误", "密码长度应为6-20位");
			arg0.addMessage("password", message);
			throw new ValidatorException(message);
		}
		else if(!arg.matches(pwdconsist))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "输入有误", "密码应由数字、字母或下划线组成");
			arg0.addMessage("password", message);
			throw new ValidatorException(message);
		}
	}

	
}
