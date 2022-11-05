package ar.edu.ungs.stylebus.modules.carts.domain.create;

import ar.edu.ungs.stylebus.modules.carts.domain.Cart;
import ar.edu.ungs.stylebus.modules.carts.domain.CartRepository;
import ar.edu.ungs.stylebus.modules.users.domain.User;
import ar.edu.ungs.stylebus.modules.users.domain.UserRepository;
import ar.edu.ungs.stylebus.modules.users.domain.find.UserFinder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class CartCreator {
	private final CartRepository repository;
	private final UserFinder userFinder;

	public CartCreator(CartRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userFinder = new UserFinder(userRepository);
	}

	public void create(String userId) {
		String id = UUID.randomUUID().toString();
		User client = this.userFinder.find(userId);

		Cart cart = Cart.create(id, client);

		this.repository.save(cart);
	}
}
