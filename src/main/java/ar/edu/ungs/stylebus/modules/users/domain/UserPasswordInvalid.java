package ar.edu.ungs.stylebus.modules.users.domain;

public final class UserPasswordInvalid extends RuntimeException {
	public UserPasswordInvalid(String email) {
		super(String.format("the password of user <%s> is invalid", email));
	}
}
