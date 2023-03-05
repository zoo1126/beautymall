package users;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="userbean")
@SessionScoped
public class userbean {
	private String userid;//用户id
	private String username;
	private String password;//密码,设定必须是英文和数字通过验证器
	private String repassword;
	private String telnumber;//电话号,验证器检验是否是11位
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelnumber() {
		return telnumber;
	}
	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}

	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String logincheck() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8888/beautymall/logincheck");
		return "logincheck";
	}
	public String signincheck() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8888/beautymall/adduser_servlet");
		return "signincheck";
	}
	public String man_logincheck() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8888/beautymall/man_logincheck");
		return "man_logincheck";
	}
	public String man_signincheck() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8888/beautymall/man_signincheck");
		return "man_signincheck";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void adduser() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8888/beautymall/adduser_servlet");
	}
}
