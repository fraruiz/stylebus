package ar.edu.ungs.stylebus.app.controllers;

import ar.edu.ungs.stylebus.modules.carts.application.add.CartItemAdder;
import ar.edu.ungs.stylebus.modules.products.domain.ProductNotExists;
import ar.edu.ungs.stylebus.modules.users.domain.UserNotExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

@RestController
public final class PostCartItemController {
	private final CartItemAdder adder;

	public PostCartItemController(CartItemAdder adder) {
		this.adder = adder;
	}

	@PostMapping("/carts/{userId}/items/{productId}")
	public ResponseEntity<Map<String, Serializable>> index(@PathVariable String userId,
	                                                       @PathVariable String productId) {

		try {
			this.adder.add(userId, productId);

			return ResponseEntity.ok(Map.of("message", String.format("the item <%s> was added", productId)));
		} catch (ProductNotExists | UserNotExists error) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", error.getMessage()));
		}
	}
}
