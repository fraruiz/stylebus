package ar.edu.ungs.stylebus.modules.users.domain.find;

import ar.edu.ungs.stylebus.modules.users.domain.User;
import ar.edu.ungs.stylebus.modules.users.domain.UserNotExists;
import ar.edu.ungs.stylebus.modules.users.domain.UserRepository;

public final class UserFinder {
	private final UserRepository repository;

	public UserFinder(UserRepository repository) {
		this.repository = repository;
	}

	public User find(String id) {
		return this.repository.findById(id).orElseThrow(() -> new UserNotExists(id));
	}
}
