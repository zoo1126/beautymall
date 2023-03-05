package log_and_reg;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import DB.DB_Operation;

@FacesValidator("idvalidator")
public class idvalidator  implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		// TODO Auto-generated method stub
		String arg=(String)arg2;
		String reg="^[0-9a-zA-Z_]{1,}$";
		DB_Operation dop=new DB_Operation();
		try {
			if(!arg.matches(reg))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "��������", "Face�ű�������ĸ�����ֻ��»������");
				arg0.addMessage("userid", message);
				throw new ValidatorException(message);
			}
			else if(dop.isUserexist(arg))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "��������", "��Face���Ѵ���");
				arg0.addMessage("userid", message);
				throw new ValidatorException(message);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
