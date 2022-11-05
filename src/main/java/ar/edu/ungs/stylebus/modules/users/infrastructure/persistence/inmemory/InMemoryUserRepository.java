package ar.edu.ungs.stylebus.modules.users.infrastructure.persistence.inmemory;

import ar.edu.ungs.stylebus.modules.users.domain.User;
import ar.edu.ungs.stylebus.modules.users.domain.UserRepository;
import ar.edu.ungs.stylebus.modules.users.domain.UserRole;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public final class InMemoryUserRepository implements UserRepository {
	private final Map<String, User> values;

	public InMemoryUserRepository() {
		this.values = new HashMap<>();

		this.setUp();
	}

	private void setUp() {
		User client = new User(UUID.randomUUID().toString(), UserRole.CLIENT, "John", "Doe", "johndoe@gmail.com", "12345");

		this.values.put(client.id(), client);
	}

	@Override
	public void save(User user) {
		this.values.put(user.id(), user);
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
