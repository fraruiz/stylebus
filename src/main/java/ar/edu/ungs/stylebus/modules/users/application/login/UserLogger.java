package ar.edu.ungs.stylebus.modules.users.application.login;

import ar.edu.ungs.stylebus.modules.users.domain.User;
import ar.edu.ungs.stylebus.modules.users.domain.UserPasswordInvalid;
import ar.edu.ungs.stylebus.modules.users.domain.UserRepository;
import ar.edu.ungs.stylebus.modules.users.domain.find.UserByEmailFinder;
import org.springframework.stereotype.Service;

@Service
public final class UserLogger {
	private final UserByEmailFinder finder;

	public UserLogger(UserRepository repository) {
		this.finder = new UserByEmailFinder(repository);
	}

	public void login(String email, String password) {
		User user = this.finder.find(email);

		if (!user.password().equals(password)) {
			throw new UserPasswordInvalid(email);
		}
	}
}
