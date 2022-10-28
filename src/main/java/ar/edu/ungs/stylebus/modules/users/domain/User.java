package ar.edu.ungs.stylebus.modules.users.domain;

import java.util.Objects;

public final class User {
	private final String id;
	private final UserRole role;
	private final String name;
	private final String lastname;
	private final String email;
	private final String password;

	public User(String id, UserRole role, String name, String lastname, String email, String password) {
		this.id = id;
		this.role = role;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	public String id() {
		return id;
	}

	public UserRole role() {
		return role;
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
		User user = (User) o;
		return Objects.equals(id, user.id) && role == user.role && Objects.equals(name, user.name) &&
		       Objects.equals(lastname, user.lastname) && Objects.equals(email, user.email) &&
		       Objects.equals(password, user.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, role, name, lastname, email, password);
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", role=" + role + ", name='" + name + '\'' + ", lastname='" + lastname + '\'' +
		       ", email='" + email + '\'' + ", password='" + password + '\'' + '}';
	}
}
