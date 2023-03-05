package log_and_reg;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import DB.DB_Operation;

@FacesValidator("advalidator")
public class advalidator  implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		// TODO Auto-generated method stub
		String arg=(String)arg2;
		String reg="^[0-9a-zA-Z_]{1,}$";
		DB_Operation dop=new DB_Operation();
		try {
			if(!arg.matches(reg))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "输入有误", "账号必须由字母、数字或下划线组成");
				arg0.addMessage("userid", message);
				throw new ValidatorException(message);
			}
			else if(dop.isadminexist(arg))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "输入有误", "该账号已存在");
				arg0.addMessage("userid", message);
				throw new ValidatorException(message);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
