package ar.edu.ungs.stylebus.modules.products.application;

import ar.edu.ungs.stylebus.modules.products.domain.Excursion;
import ar.edu.ungs.stylebus.modules.products.domain.Package;
import ar.edu.ungs.stylebus.modules.products.domain.Product;
import ar.edu.ungs.stylebus.modules.products.domain.Trip;

import java.math.BigDecimal;

public abstract class ProductResponse {
	private final String id;
	private final BigDecimal amount;
	private final String type;

	public ProductResponse() {
		this.id = null;
		this.amount = null;
		this.type = null;
	}

	public ProductResponse(String id, BigDecimal amount, String type) {
		this.id = id;
		this.amount = amount;
		this.type = type;
	}

	public static ProductResponse map(Product product) {
		if (Trip.class.equals(product.getClass())) {
			return TripProductResponse.map((Trip) product);
		}

		if (Excursion.class.equals(product.getClass())) {
			return ExcursionProductResponse.map((Excursion) product);
		}

		if (Package.class.equals(product.getClass())) {
			return PackageProductResponse.map((Package) product);
		}

		return null;
	}

	public String id() {
		return id;
	}

	public BigDecimal amount() {
		return amount;
	}

	public String type() {
		return type;
	}
}
