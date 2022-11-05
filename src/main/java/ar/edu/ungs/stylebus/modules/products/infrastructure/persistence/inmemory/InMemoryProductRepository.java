package ar.edu.ungs.stylebus.modules.products.infrastructure.persistence.inmemory;

import ar.edu.ungs.stylebus.modules.products.domain.Package;
import ar.edu.ungs.stylebus.modules.products.domain.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public final class InMemoryProductRepository implements ProductRepository {
	private final Map<String, Product> values;

	public InMemoryProductRepository() {
		this.values = new HashMap<>();

		this.setup();
	}

	@Override
	public List<Product> search(ProductCriteria criteria) {
		return this.values.values()
		                  .stream()
				          .filter(product -> verifyByType(product, criteria))
						  .filter(product -> verifyByCity(product, criteria))
						  .filter(product -> verifyByCategory(product, criteria))
						  .filter(product -> verifyByGoingBack(product, criteria))
						  .filter(product -> verifyByPassengersQuantity(product, criteria))
				          .collect(Collectors.toList());
	}

	private boolean verifyByPassengersQuantity(Product product, ProductCriteria criteria) {
		if (Trip.class.equals(product.getClass())) {
			Trip trip = (Trip) product;

			return criteria.passengersQuantity() <= trip.category().passengersQuantity();
		}

		return false;
	}

	private boolean verifyByGoingBack(Product product, ProductCriteria criteria) {
		if (Trip.class.equals(product.getClass())) {
			Trip trip = (Trip) product;

			return trip.goingBack().equals(criteria.goingBack());
		}

		return true;
	}

	private boolean verifyByCategory(Product product, ProductCriteria criteria) {
		if (criteria.category().isEmpty()) {
			return true;
		}

		if (Trip.class.equals(product.getClass())) {
			Trip trip = (Trip) product;

			return trip.category().equals(criteria.category().get());
		}

		return true;
	}

	private boolean verifyByCity(Product product, ProductCriteria criteria) {
		if (criteria.city().isEmpty()) {
			return true;
		}

		if (Trip.class.equals(product.getClass())) {
			Trip trip = (Trip) product;

			return trip.origin().city().equalsIgnoreCase(criteria.city().get()) || trip.destiny().city().equalsIgnoreCase(criteria.city().get());
		}

		if (Excursion.class.equals(product.getClass())) {
			Excursion excursion = (Excursion) product;

			return excursion.ubication().city().equalsIgnoreCase(criteria.city().get());
		}

		if (Package.class.equals(product.getClass())) {
			Package aPackage = ((Package) product);

			return verifyByCity(aPackage.excursion(), criteria);
		}

		return false;
	}

	private boolean verifyByType(Product product, ProductCriteria criteria) {
		if (ProductTypeCriteria.TRIP.equals(criteria.type()) && Trip.class.equals(product.getClass())) {
			return true;
		}

		if (ProductTypeCriteria.PACKAGE.equals(criteria.type()) && Package.class.equals(product.getClass())) {
			return true;
		}

		if (ProductTypeCriteria.EXCURSION.equals(criteria.type()) && Excursion.class.equals(product.getClass())) {
			return true;
		}

		return false;
	}

	@Override
	public Optional<Product> findById(String id) {
		return Optional.ofNullable(this.values.get(id));
	}

	private void setup() {
		Location retiroLocation = new Location(new Point(-34.5893799, -58.3855431), "Terminal de Omnibus Retiro");
		Location barilocheLocation = new Location(new Point(-41.1336437, -71.3117545), "San Carlos de Bariloche");
		Location mendozaLocation = new Location(new Point(-34.5660941, -58.4682105), "Mendoza");

		Trip trip1 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), retiroLocation, barilocheLocation, Category.PREMIUM, true);
		Trip trip2 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), barilocheLocation, retiroLocation, Category.PREMIUM, false);
		Trip trip3 = new Trip(UUID.randomUUID().toString(), new BigDecimal("500.0"), retiroLocation, mendozaLocation, Category.STANDARD, true);
		Trip trip4 = new Trip(UUID.randomUUID().toString(), new BigDecimal("500.0"), mendozaLocation, retiroLocation, Category.STANDARD_PLUS, false);

		Excursion excursion1 = new Excursion(UUID.randomUUID().toString(), new BigDecimal("2.0"), retiroLocation);
		Excursion excursion2 = new Excursion(UUID.randomUUID().toString(), new BigDecimal("1000.0"), barilocheLocation);
		Excursion excursion3 = new Excursion(UUID.randomUUID().toString(), new BigDecimal("500.0"), mendozaLocation);

		Package packageProduct = new Package(UUID.randomUUID().toString(), new BigDecimal("1534.0"), excursion1, trip2);
		Package packageProduct2 = new Package(UUID.randomUUID().toString(), new BigDecimal("3000.0"), excursion2, trip1);
		Package packageProduct3 = new Package(UUID.randomUUID().toString(), new BigDecimal("2000.0"), excursion3, trip4);

		this.values.put(trip1.id(), trip1);
		this.values.put(trip2.id(), trip2);
		this.values.put(trip3.id(), trip3);
		this.values.put(trip4.id(), trip4);
		this.values.put(excursion1.id(), excursion1);
		this.values.put(excursion2.id(), excursion2);
		this.values.put(excursion3.id(), excursion3);
		this.values.put(packageProduct.id(), packageProduct);
		this.values.put(packageProduct2.id(), packageProduct2);
		this.values.put(packageProduct3.id(), packageProduct3);
	}
}
