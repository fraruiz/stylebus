package ar.edu.ungs.stylebus.modules.carts.application.delete;

import ar.edu.ungs.stylebus.modules.carts.domain.Cart;
import ar.edu.ungs.stylebus.modules.carts.domain.CartRepository;
import ar.edu.ungs.stylebus.modules.carts.domain.find.DomainCartByUserFinder;
import ar.edu.ungs.stylebus.modules.products.domain.Product;
import ar.edu.ungs.stylebus.modules.products.domain.find.ProductFinder;
import org.springframework.stereotype.Service;

@Service
public final class CartItemDeleter {
	private final CartRepository repository;
	private final DomainCartByUserFinder finder;
	private final ProductFinder productFinder;

	public CartItemDeleter(CartRepository repository, ProductFinder productFinder) {
		this.repository = repository;
		this.finder = new DomainCartByUserFinder(repository);
		this.productFinder = productFinder;
	}

	public void delete(String userId, String productId) {
		Cart cart = this.finder.find(userId);
		Product product = this.productFinder.find(productId);

		cart.delete(product);

		this.repository.save(cart);
	}
}
