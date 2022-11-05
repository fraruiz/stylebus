package ar.edu.ungs.stylebus.modules.products.domain;

import java.util.Optional;

public final class ProductCriteria {
	private final ProductTypeCriteria type;
	private final Optional<String> city;
	private final Optional<Category> category;
	private final Boolean goingBack;
	private final Integer passengersQuantity;


	public ProductCriteria(ProductTypeCriteria type,
	                       String city,
	                       Category category,
	                       Boolean goingBack,
	                       Integer passengersQuantity) {
		this.type = type;
		this.city = Optional.ofNullable(city);
		this.category = Optional.ofNullable(category);
		this.goingBack = goingBack;
		this.passengersQuantity = passengersQuantity;
	}

	public ProductTypeCriteria type() {
		return type;
	}

	public Optional<String> city() {
		return city;
	}

	public Optional<Category> category() {
		return category;
	}

	public Boolean goingBack() {
		return goingBack;
	}

	public Integer passengersQuantity() {
		return passengersQuantity;
	}
}
