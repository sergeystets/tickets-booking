package epam.cdp.spring.task1.bean;

import org.hibernate.validator.constraints.NotBlank;

public class RegistrationUserBean {

	@NotBlank
	private String login;

	@NotBlank
	private String password;

	@NotBlank
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
