package ar.edu.ungs.stylebus.modules.users.domain.find;

import ar.edu.ungs.stylebus.modules.users.domain.User;
import ar.edu.ungs.stylebus.modules.users.domain.UserNotExists;
import ar.edu.ungs.stylebus.modules.users.domain.UserRepository;

public final class UserByEmailFinder {
	private final UserRepository repository;

	public UserByEmailFinder(UserRepository repository) {
		this.repository = repository;
	}

	public User find(String email) {
		return this.repository.findByEmail(email).orElseThrow(() -> new UserNotExists(email));
	}
}
