package ar.edu.ungs.stylebus.modules.carts.application.find;

import ar.edu.ungs.stylebus.modules.carts.application.CartResponse;
import ar.edu.ungs.stylebus.modules.carts.domain.Cart;
import ar.edu.ungs.stylebus.modules.carts.domain.CartNotExists;
import ar.edu.ungs.stylebus.modules.carts.domain.CartRepository;
import ar.edu.ungs.stylebus.modules.carts.domain.create.CartCreator;
import ar.edu.ungs.stylebus.modules.carts.domain.find.DomainCartByUserFinder;
import org.springframework.stereotype.Service;

@Service
public final class CartByUserFinder {
	private final DomainCartByUserFinder finder;
	private final CartCreator creator;

	public CartByUserFinder(CartRepository repository, CartCreator creator) {
		this.finder = new DomainCartByUserFinder(repository);
		this.creator = creator;
	}

	public CartResponse find(String userId) {
		try {
			Cart cart = this.finder.find(userId);

			return CartResponse.map(cart);
		} catch (CartNotExists error) {
			creator.create(userId);

			return this.find(userId);
		}
	}
}
