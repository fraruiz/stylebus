package ar.edu.ungs.stylebus.app.controllers.rest;

import ar.edu.ungs.stylebus.modules.users.application.authenticate.UserAuthenticator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

@CrossOrigin
@RestController
public final class AuthenticationController {
	private final UserAuthenticator authenticator;

	public AuthenticationController(UserAuthenticator authenticator) {
		this.authenticator = authenticator;
	}

	@PostMapping("/users/auth")
	public ResponseEntity<Map<String, String>> index(@RequestBody Map<String, Serializable> body) {
		String email = (String) body.get("email");
		String password = (String) body.get("password");

		try {
			String userId = this.authenticator.auth(email, password);

			return ResponseEntity.ok(Map.of("message", "user authenticated", "user_id", userId));
		} catch (Exception error) {
			return ResponseEntity.status(401).body(Map.of("message", error.getMessage()));
		}
	}
}
