package ar.edu.ungs.stylebus.modules.users.application.registration;

import ar.edu.ungs.stylebus.modules.users.application.UserRequest;
import ar.edu.ungs.stylebus.modules.users.domain.User;
import ar.edu.ungs.stylebus.modules.users.domain.UserRepository;
import ar.edu.ungs.stylebus.modules.users.domain.UserRole;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class ClientUserRegistrar implements UserRegistrar {
	private final UserRepository userRepository;

	public ClientUserRegistrar(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void execute(UserRequest request) {
		String id = UUID.randomUUID().toString();
		UserRole role = UserRole.CLIENT;

		User client = new User(id, role, request.name(), request.lastname(), request.email(), request.password());

		this.userRepository.save(client);
	}
}
