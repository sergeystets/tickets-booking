package epam.cdp.spring.exception;

public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = -6515758617292480394L;

	public UserServiceException() {
		super();
	}

	public UserServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserServiceException(String message) {
		super(message);
	}

	public UserServiceException(Throwable cause) {
		super(cause);
	}
}
