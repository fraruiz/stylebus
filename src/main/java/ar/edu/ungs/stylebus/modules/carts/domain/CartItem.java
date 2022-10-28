package ar.edu.ungs.stylebus.modules.carts.domain;

import ar.edu.ungs.stylebus.modules.products.domain.Product;

import java.math.BigDecimal;
import java.util.Objects;

public final class CartItem {
	private final Product product;
	private final BigDecimal amount;

	public CartItem(Product product, BigDecimal amount) {
		this.product = product;
		this.amount = amount;
	}

	public Product product() {
		return product;
	}

	public BigDecimal amount() {
		return amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CartItem cartItem = (CartItem) o;
		return Objects.equals(product, cartItem.product) && Objects.equals(amount, cartItem.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(product, amount);
	}

	@Override
	public String toString() {
		return "CartItem{" + "product=" + product + ", amount=" + amount + '}';
	}
}
