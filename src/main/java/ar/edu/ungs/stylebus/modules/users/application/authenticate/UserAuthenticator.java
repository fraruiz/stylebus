package ar.edu.ungs.stylebus.modules.users.application.authenticate;

import ar.edu.ungs.stylebus.modules.users.domain.User;
import ar.edu.ungs.stylebus.modules.users.domain.UserPasswordInvalid;
import ar.edu.ungs.stylebus.modules.users.domain.UserRepository;
import ar.edu.ungs.stylebus.modules.users.domain.find.UserByEmailFinder;
import org.springframework.stereotype.Service;

@Service
public final class UserAuthenticator {
	private final UserByEmailFinder finder;

	public UserAuthenticator(UserRepository repository) {
		this.finder = new UserByEmailFinder(repository);
	}

	public void auth(String email, String password) {
		User user = this.finder.find(email);

		if (!user.password().equals(password)) {
			throw new UserPasswordInvalid(email);
		}
	}
}
