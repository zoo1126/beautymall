package log_and_reg;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import DB.DB_Operation;

@FacesValidator("telvalidator")
public class telvalidator implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		// TODO Auto-generated method stub
		String arg=(String)arg2;
		String reg="^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
		DB_Operation dop=new DB_Operation();
		try {
			if(!arg.matches(reg))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "输入有误", "请输入正确的手机号");
				arg0.addMessage("telnumber", message);
				throw new ValidatorException(message);
			}
			else if(dop.isTelexist(arg))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "输入有误", "该手机号已注册");
				arg0.addMessage("telnumber", message);
				throw new ValidatorException(message);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
