package ar.edu.ungs.stylebus.app.controllers.rest;

import ar.edu.ungs.stylebus.modules.carts.application.CartResponse;
import ar.edu.ungs.stylebus.modules.carts.application.find.CartByUserFinder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public final class GetCartByUserController {
	private final CartByUserFinder finder;

	public GetCartByUserController(CartByUserFinder finder) {
		this.finder = finder;
	}

	@GetMapping("/carts/{userId}")
	public ResponseEntity<CartResponse> index(@PathVariable String userId) {
		CartResponse response = finder.find(userId);

		return ResponseEntity.ok(response);
	}
}
