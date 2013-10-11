package epam.cdp.spring.task1.bean;

import org.hibernate.validator.constraints.NotBlank;

public class RegistrationUserBean {

	@NotBlank(message="login can not be blank")
	private String login;

	@NotBlank (message="password can not be blank")
	private String password;

	@NotBlank (message="passwordRepeat can not be blank")
	private String passwordRepeat;

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}