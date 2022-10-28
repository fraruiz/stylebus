package ar.edu.ungs.stylebus.modules.products.domain;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Product {
	private final String id;
	private final BigDecimal amount;

	public Product(String id, BigDecimal amount) {
		this.id = id;
		this.amount = amount;
	}

	public String id() {
		return id;
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
		Product product = (Product) o;
		return Objects.equals(id, product.id) && Objects.equals(amount, product.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, amount);
	}

	@Override
	public String toString() {
		return "Product{" + "id=" + id + ", amount=" + amount + '}';
	}
}
