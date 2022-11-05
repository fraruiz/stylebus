package ar.edu.ungs.stylebus.modules.carts.application.find;

import ar.edu.ungs.stylebus.modules.carts.application.CartResponse;
import ar.edu.ungs.stylebus.modules.carts.domain.Cart;
import ar.edu.ungs.stylebus.modules.carts.domain.CartNotExists;
import ar.edu.ungs.stylebus.modules.carts.domain.CartRepository;
import ar.edu.ungs.stylebus.modules.carts.domain.find.DomainCartByUserFinder;
import org.springframework.stereotype.Service;

@Service
public final class CartByUserFinder {
	private final DomainCartByUserFinder finder;

	public CartByUserFinder(CartRepository repository) {
		this.finder = new DomainCartByUserFinder(repository);
	}

	public CartResponse find(String userId) {
		Cart cart = this.finder.find(userId);

		return CartResponse.map(cart);
	}
}
