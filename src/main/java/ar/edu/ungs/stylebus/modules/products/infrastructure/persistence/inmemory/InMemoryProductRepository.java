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
		                  .filter(product -> verifyByCategory(product, criteria))
		                  .filter(product -> verifyByGoingBack(product, criteria))
		                  .filter(product -> verifyByPassengersQuantity(product, criteria))
		                  .filter(product -> verifyByType(product, criteria))
		                  .filter(product -> verifyByCity(product, criteria))
		                  .sorted(Comparator.comparing(Product::amount))
		                  .collect(Collectors.toList());
	}

	private boolean verifyByPassengersQuantity(Product product, ProductCriteria criteria) {
		if (Trip.class.equals(product.getClass())) {
			Trip trip = (Trip) product;

			return criteria.passengersQuantity() <= trip.category().passengersQuantity();
		}

		return true;
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
		Location bsAsLocation = new Location(new Point(-34.5893799, -58.3855431), "Buenos Aires");
		Location barilocheLocation = new Location(new Point(-41.1336437, -71.3117545), "Bariloche");
		Location mendozaLocation = new Location(new Point(-34.5660941, -58.4682105), "Mendoza");

		Trip trip1 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), bsAsLocation, barilocheLocation, Category.PREMIUM, true);
		Trip trip2 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), bsAsLocation, barilocheLocation, Category.STANDARD_PLUS, true);
		Trip trip3 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), bsAsLocation, barilocheLocation, Category.STANDARD, true);

		this.values.put(trip1.id(), trip1);
		this.values.put(trip2.id(), trip1);
		this.values.put(trip3.id(), trip1);

		Trip trip4 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), bsAsLocation, barilocheLocation, Category.PREMIUM, false);
		Trip trip5 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), bsAsLocation, barilocheLocation, Category.STANDARD_PLUS, false);
		Trip trip6 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), bsAsLocation, barilocheLocation, Category.STANDARD, false);

		this.values.put(trip4.id(), trip4);
		this.values.put(trip5.id(), trip5);
		this.values.put(trip6.id(), trip6);

		Trip trip7 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), barilocheLocation, bsAsLocation, Category.PREMIUM, true);
		Trip trip8 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), barilocheLocation, bsAsLocation, Category.STANDARD_PLUS, true);
		Trip trip9 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), barilocheLocation, bsAsLocation, Category.STANDARD, true);

		this.values.put(trip7.id(), trip7);
		this.values.put(trip8.id(), trip8);
		this.values.put(trip9.id(), trip9);

		Trip trip10 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), barilocheLocation, bsAsLocation, Category.PREMIUM, false);
		Trip trip11 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), barilocheLocation, bsAsLocation, Category.STANDARD_PLUS, false);
		Trip trip12 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), barilocheLocation, bsAsLocation, Category.STANDARD, false);

		this.values.put(trip10.id(), trip10);
		this.values.put(trip11.id(), trip11);
		this.values.put(trip12.id(), trip12);

		Trip trip13 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), bsAsLocation, mendozaLocation, Category.PREMIUM, true);
		Trip trip14 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), bsAsLocation, mendozaLocation, Category.STANDARD_PLUS, true);
		Trip trip15 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), bsAsLocation, mendozaLocation, Category.STANDARD, true);

		this.values.put(trip13.id(), trip13);
		this.values.put(trip14.id(), trip14);
		this.values.put(trip15.id(), trip15);

		Trip trip16 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), bsAsLocation, mendozaLocation, Category.PREMIUM, false);
		Trip trip17 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), bsAsLocation, mendozaLocation, Category.STANDARD_PLUS, false);
		Trip trip18 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), bsAsLocation, mendozaLocation, Category.STANDARD, false);

		this.values.put(trip16.id(), trip16);
		this.values.put(trip17.id(), trip17);
		this.values.put(trip18.id(), trip18);

		Trip trip20 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), mendozaLocation, bsAsLocation, Category.PREMIUM, true);
		Trip trip21 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), mendozaLocation, bsAsLocation, Category.STANDARD_PLUS, true);
		Trip trip22 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), mendozaLocation, bsAsLocation, Category.STANDARD, true);

		this.values.put(trip20.id(), trip20);
		this.values.put(trip21.id(), trip21);
		this.values.put(trip22.id(), trip22);

		Trip trip23 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), mendozaLocation, bsAsLocation, Category.PREMIUM, false);
		Trip trip24 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), mendozaLocation, bsAsLocation, Category.STANDARD_PLUS, false);
		Trip trip25 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), mendozaLocation, bsAsLocation, Category.STANDARD, false);

		this.values.put(trip23.id(), trip23);
		this.values.put(trip24.id(), trip24);
		this.values.put(trip25.id(), trip25);


		Excursion excursion1 = new Excursion(UUID.randomUUID().toString(), new BigDecimal("1234.0"), bsAsLocation);
		Excursion excursion2 = new Excursion(UUID.randomUUID().toString(), new BigDecimal("1234.0"), barilocheLocation);
		Excursion excursion3 = new Excursion(UUID.randomUUID().toString(), new BigDecimal("1234.0"), mendozaLocation);

		this.values.put(excursion1.id(), excursion1);
		this.values.put(excursion2.id(), excursion2);
		this.values.put(excursion3.id(), excursion3);

		Package packageProduct1 = new Package(UUID.randomUUID().toString(), new BigDecimal("1234.0"), excursion1, trip20);
		Package packageProduct2 = new Package(UUID.randomUUID().toString(), new BigDecimal("1234.0"), excursion2, trip7);
		Package packageProduct3 = new Package(UUID.randomUUID().toString(), new BigDecimal("1234.0"), excursion3, trip3);

		this.values.put(packageProduct1.id(), packageProduct1);
		this.values.put(packageProduct2.id(), packageProduct2);
		this.values.put(packageProduct3.id(), packageProduct3);
	}
}
