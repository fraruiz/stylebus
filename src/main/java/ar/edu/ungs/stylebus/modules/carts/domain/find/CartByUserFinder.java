package ar.edu.ungs.stylebus.modules.carts.domain.find;

import ar.edu.ungs.stylebus.modules.carts.domain.Cart;
import ar.edu.ungs.stylebus.modules.carts.domain.CartNotExists;
import ar.edu.ungs.stylebus.modules.carts.domain.CartRepository;
import org.springframework.stereotype.Service;

@Service
public final class CartByUserFinder {
	private final CartRepository repository;

	public CartByUserFinder(CartRepository repository) {
		this.repository = repository;
	}

	public Cart find(String userId) {
		return this.repository.findByUser(userId)
		                      .orElseThrow(() -> new CartNotExists(userId));
	}
}
