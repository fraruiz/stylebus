package ar.edu.ungs.stylebus.modules.users.infrastructure.persistence.inmemory;

import ar.edu.ungs.stylebus.modules.users.domain.User;
import ar.edu.ungs.stylebus.modules.users.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public final class InMemoryUserRepository implements UserRepository {
	private final Map<String, User> values;

	public InMemoryUserRepository() {
		this.values = new HashMap<>();
	}

	@Override
	public Optional<User> findById(String id) {
		return Optional.ofNullable(this.values.get(id));
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return this.values.values()
		                  .stream()
		                  .filter(user -> user.email().equals(email))
		                  .findFirst();
	}
}
