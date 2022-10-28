package ar.edu.ungs.stylebus.modules.carts.application.add;

import ar.edu.ungs.stylebus.modules.carts.domain.Cart;
import ar.edu.ungs.stylebus.modules.carts.domain.CartNotExists;
import ar.edu.ungs.stylebus.modules.carts.domain.CartRepository;
import ar.edu.ungs.stylebus.modules.carts.domain.create.CartCreator;
import ar.edu.ungs.stylebus.modules.carts.domain.find.CartByUserFinder;
import ar.edu.ungs.stylebus.modules.products.domain.Product;
import ar.edu.ungs.stylebus.modules.products.domain.ProductRepository;
import ar.edu.ungs.stylebus.modules.products.domain.find.ProductFinder;
import ar.edu.ungs.stylebus.modules.users.domain.UserRepository;
import org.springframework.stereotype.Service;

@Service
public final class CartItemAdder {
	private final CartRepository cartRepository;
	private final CartCreator creator;
	private final CartByUserFinder finder;
	private final ProductFinder productFinder;

	public CartItemAdder(CartRepository cartRepository,
	                     UserRepository userRepository,
	                     ProductRepository productRepository) {
		this.cartRepository = cartRepository;
		this.creator = new CartCreator(cartRepository, userRepository);
		this.finder = new CartByUserFinder(cartRepository);
		this.productFinder = new ProductFinder(productRepository);
	}

	public void add(String userId, String productId) {
		Cart cart = this.findOrCreate(userId);
		Product product = this.productFinder.find(productId);

		cart.add(product);

		this.cartRepository.save(cart);
	}

	private Cart findOrCreate(String userId) {
		try {
			return this.finder.find(userId);
		} catch (CartNotExists e) {
			this.creator.create(userId);

			return this.findOrCreate(userId);
		}
	}
}
