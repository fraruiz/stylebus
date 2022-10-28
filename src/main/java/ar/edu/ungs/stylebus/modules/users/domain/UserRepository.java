package ar.edu.ungs.stylebus.modules.users.domain;

import java.util.Optional;

public interface UserRepository {
	Optional<User> findById(String id);

	Optional<User> findByEmail(String email);
}
