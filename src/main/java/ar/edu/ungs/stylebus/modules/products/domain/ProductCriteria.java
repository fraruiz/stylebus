package ar.edu.ungs.stylebus.modules.products.domain;

import java.util.Objects;
import java.util.Optional;

public final class ProductCriteria {
	private final Optional<String> city;
	private final Optional<Boolean> going;
	private final Optional<Boolean> back;
	private final Optional<Integer> quantityPassengers;
	private final Optional<String> busCategory;

	public ProductCriteria(Optional<String> city,
	                       Optional<Boolean> going,
	                       Optional<Boolean> back,
	                       Optional<Integer> quantityPassengers,
	                       Optional<String> busCategory) {
		this.city = city;
		this.going = going;
		this.back = back;
		this.quantityPassengers = quantityPassengers;
		this.busCategory = busCategory;
	}

	public Optional<String> city() {
		return city;
	}

	public Optional<Boolean> going() {
		return going;
	}

	public Optional<Boolean> back() {
		return back;
	}

	public Optional<Integer> quantityPassengers() {
		return quantityPassengers;
	}

	public Optional<String> busCategory() {
		return busCategory;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductCriteria that = (ProductCriteria) o;
		return Objects.equals(city, that.city) && Objects.equals(going, that.going) &&
		       Objects.equals(back, that.back) && Objects.equals(quantityPassengers, that.quantityPassengers) &&
		       Objects.equals(busCategory, that.busCategory);
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, going, back, quantityPassengers, busCategory);
	}

	@Override
	public String toString() {
		return "ProductCriteria{" + "city=" + city + ", going=" + going + ", back=" + back + ", quantityPassengers=" +
		       quantityPassengers + ", busCategory=" + busCategory + '}';
	}
}
