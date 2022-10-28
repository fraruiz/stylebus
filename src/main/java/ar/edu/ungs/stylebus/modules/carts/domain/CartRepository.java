package ar.edu.ungs.stylebus.modules.carts.domain;

import java.util.Optional;

public interface CartRepository {
	void save(Cart cart);

	Optional<Cart> findByUser(String userId);
}
