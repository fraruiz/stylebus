package ar.edu.ungs.stylebus.modules.carts.infrastructure.persistence.inmemory;

import ar.edu.ungs.stylebus.modules.carts.domain.Cart;
import ar.edu.ungs.stylebus.modules.carts.domain.CartRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public final class InMemoryCartRepository implements CartRepository {
	private final Map<String, Cart> values;

	public InMemoryCartRepository() {
		this.values = new HashMap<>();
	}

	@Override
	public void save(Cart cart) {
		this.values.put(cart.id(), cart);
	}

	@Override
	public Optional<Cart> findByUser(String userId) {
		return this.values.values()
		                  .stream()
		                  .filter(cart -> cart.client().id().equals(userId))
		                  .findFirst();
	}
}
