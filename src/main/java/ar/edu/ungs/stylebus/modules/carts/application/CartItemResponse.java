package ar.edu.ungs.stylebus.modules.carts.application;

import ar.edu.ungs.stylebus.modules.carts.domain.CartItem;
import ar.edu.ungs.stylebus.modules.products.application.ProductResponse;

import java.math.BigDecimal;

public final class CartItemResponse {
	private final ProductResponse product;
	private final BigDecimal amount;

	public static CartItemResponse map(CartItem item) {
		return new CartItemResponse(ProductResponse.map(item.product()), item.amount());
	}

	public CartItemResponse(ProductResponse product, BigDecimal amount) {
		this.product = product;
		this.amount = amount;
	}

	public ProductResponse product() {
		return product;
	}

	public BigDecimal amount() {
		return amount;
	}
}
