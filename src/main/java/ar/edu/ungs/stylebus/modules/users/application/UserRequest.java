package ar.edu.ungs.stylebus.modules.users.application;

import java.util.Objects;

public final class UserRequest {
	private final String name;
	private final String lastname;
	private final String email;
	private final String password;

	public UserRequest(String name,
	                   String lastname,
	                   String email,
	                   String password) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	public String name() {
		return name;
	}

	public String lastname() {
		return lastname;
	}

	public String email() {
		return email;
	}

	public String password() {
		return password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserRequest that = (UserRequest) o;
		return Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname) &&
		       Objects.equals(email, that.email) && Objects.equals(password, that.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, lastname, email, password);
	}

	@Override
	public String toString() {
		return "UserRequest{" + "name='" + name + '\'' + ", lastname='" + lastname + '\'' + ", email='" + email + '\'' +
		       ", password='" + password + '\'' + '}';
	}
}
