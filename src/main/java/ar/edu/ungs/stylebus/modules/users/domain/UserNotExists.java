package ar.edu.ungs.stylebus.modules.users.domain;

public final class UserNotExists extends RuntimeException {
	public UserNotExists(String email) {
		super(String.format("the user <%s> not exists", email));
	}
}
