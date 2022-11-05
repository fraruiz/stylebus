package ar.edu.ungs.stylebus.modules.carts.domain.find;

import ar.edu.ungs.stylebus.modules.carts.domain.Cart;
import ar.edu.ungs.stylebus.modules.carts.domain.CartNotExists;
import ar.edu.ungs.stylebus.modules.carts.domain.CartRepository;
import org.springframework.stereotype.Service;

@Service
public final class DomainCartByUserFinder {
	private final CartRepository repository;

	public DomainCartByUserFinder(CartRepository repository) {
		this.repository = repository;
	}

	public Cart find(String userId) {
		return this.repository.findByUser(userId)
		                      .orElseThrow(() -> new CartNotExists(userId));
	}
}
