package ar.edu.ungs.stylebus.modules.carts.application;

import ar.edu.ungs.stylebus.modules.carts.domain.Cart;

import java.util.List;
import java.util.stream.Collectors;

public final class CartResponse {
	private final String id;
	private final String userId;
	private final List<CartItemResponse> items;

	public static CartResponse map(Cart cart) {
		List<CartItemResponse> items = cart.items().stream().map(CartItemResponse::map).collect(Collectors.toList());

		return new CartResponse(cart.id(), cart.client().id(), items);
	}

	public CartResponse(String id, String userId, List<CartItemResponse> items) {
		this.id = id;
		this.userId = userId;
		this.items = items;
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
}
