package ar.edu.ungs.stylebus.modules.users.domain;

import java.util.Optional;

public interface UserRepository {
	void save(User user);

	Optional<User> findById(String id);

	Optional<User> findByEmail(String email);
}
