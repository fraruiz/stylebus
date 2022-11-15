package ar.edu.ungs.stylebus.modules.carts.domain;

import ar.edu.ungs.stylebus.modules.products.domain.Product;
import ar.edu.ungs.stylebus.modules.users.domain.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Cart {
	private final String id;
	private final User client;
	private final List<CartItem> items;
	private BigDecimal totalAmount;

	public static Cart create(String id, User client) {
		return new Cart(id, client, new ArrayList<>(), BigDecimal.ZERO);
	}

	public Cart(String id, User client, List<CartItem> items, BigDecimal totalAmount) {
		this.id = id;
		this.client = client;
		this.items = items;
		this.totalAmount = totalAmount;
	}

	public String id() {
		return id;
	}

	public List<CartItem> items() {
		return items;
	}

	public void add(Product product) {
		CartItem item = new CartItem(product, product.amount());
		this.items.add(item);

		this.totalAmount = this.totalAmount.add(product.amount());
	}

	public void delete(Product product) {
		this.items.stream()
		          .filter(x -> x.product().equals(product))
		          .findFirst()
		          .ifPresent(this.items::remove);
	}

	public User client() {
		return client;
	}

	public BigDecimal totalAmount() {
		return totalAmount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Cart cart = (Cart) o;
		return Objects.equals(id, cart.id) && Objects.equals(client, cart.client) &&
		       Objects.equals(items, cart.items) && Objects.equals(totalAmount, cart.totalAmount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, client, items, totalAmount);
	}

	@Override
	public String toString() {
		return "Cart{" + "id='" + id + '\'' + ", client=" + client + ", items=" + items + ", totalAmount=" +
		       totalAmount + '}';
	}
}
