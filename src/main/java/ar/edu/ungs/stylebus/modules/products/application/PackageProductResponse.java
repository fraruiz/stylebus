package ar.edu.ungs.stylebus.modules.products.application;

import ar.edu.ungs.stylebus.modules.products.domain.Package;

import java.math.BigDecimal;

public final class PackageProductResponse extends ProductResponse {
	private final ExcursionProductResponse excursion;
	private final TripProductResponse trip;

	public PackageProductResponse() {
		super();
		this.excursion = null;
		this.trip = null;
	}

	public PackageProductResponse(String id,
	                              BigDecimal amount,
	                              ExcursionProductResponse excursion,
	                              TripProductResponse trip) {
		super(id, amount, "package");
		this.excursion = excursion;
		this.trip = trip;
	}

	public static PackageProductResponse map(Package packageProduct) {
		return new PackageProductResponse(packageProduct.id(), packageProduct.amount(), ExcursionProductResponse.map(packageProduct.excursion()), TripProductResponse.map(packageProduct.trip()));
	}

	public ExcursionProductResponse excursion() {
		return excursion;
	}

	public TripProductResponse trip() {
		return trip;
	}
}
