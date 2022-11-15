package ar.edu.ungs.stylebus.app.controllers.rest;

import ar.edu.ungs.stylebus.modules.carts.application.delete.CartItemDeleter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
public final class DeleteCartItemController {
	private final CartItemDeleter deleter;

	public DeleteCartItemController(CartItemDeleter deleter) {
		this.deleter = deleter;
	}

	@DeleteMapping("/carts/{userId}/items/{productId}")
	public ResponseEntity<?> index(@PathVariable String userId,
	                               @PathVariable String productId) {
		try {
			deleter.delete(userId, productId);

			return ResponseEntity.ok(Map.of("message", String.format("the product <%s> was deleted", productId)));
		} catch (Exception error) {
			return ResponseEntity.ok(Map.of("message", error.getMessage()));
		}
	}
}
