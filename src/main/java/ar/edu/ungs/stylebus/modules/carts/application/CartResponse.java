package ar.edu.ungs.stylebus.modules.carts.application;

import ar.edu.ungs.stylebus.modules.carts.domain.Cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public final class CartResponse {
	private final String id;
	private final String userId;
	private final List<CartItemResponse> items;
	private final BigDecimal totalAmount;

	public static CartResponse map(Cart cart) {
		List<CartItemResponse> items = cart.items().stream().map(CartItemResponse::map).collect(Collectors.toList());

		return new CartResponse(cart.id(), cart.client().id(), items, cart.totalAmount());
	}

	public CartResponse(String id, String userId, List<CartItemResponse> items, BigDecimal totalAmount) {
		this.id = id;
		this.userId = userId;
		this.items = items;
		this.totalAmount = totalAmount;
	}

	public String id() {
		return id;
	}

	public String userId() {
		return userId;
	}

	public List<CartItemResponse> items() {
		return items;
	}

	public BigDecimal totalAmount() {
		return totalAmount;
	}
}
