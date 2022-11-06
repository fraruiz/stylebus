package ar.edu.ungs.stylebus.app.controllers.rest;

import ar.edu.ungs.stylebus.modules.carts.application.CartItemResponse;
import ar.edu.ungs.stylebus.modules.carts.application.CartResponse;
import ar.edu.ungs.stylebus.modules.carts.application.add.CartItemAdder;
import ar.edu.ungs.stylebus.modules.carts.application.find.CartByUserFinder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
public final class PostCartItemController {
	private final CartItemAdder adder;

	public PostCartItemController(CartItemAdder adder) {
		this.adder = adder;
	}

	@PostMapping("/carts/{userId}/items/{productId}")
	public ResponseEntity<?> index(@PathVariable String userId,
	                                          @PathVariable String productId) {
		try {
			adder.add(userId, productId);

			return ResponseEntity.ok(Map.of("message", String.format("the product <%s> was added", productId)));
		} catch (Exception error) {
			return ResponseEntity.ok(Map.of("message", error.getMessage()));
		}
	}
}
