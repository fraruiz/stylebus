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

		Trip trip1 = new Trip("9b4cc515-38e6-4e45-9576-316acb90df18", new BigDecimal("1234.0"), bsAsLocation, barilocheLocation, Category.PREMIUM, true);
		Trip trip2 = new Trip("bf09264d-86f4-45ae-95e5-232519107193", new BigDecimal("1234.0"), bsAsLocation, barilocheLocation, Category.STANDARD_PLUS, true);
		Trip trip3 = new Trip("8c7a37bc-f498-46e9-9560-1e8d64689411", new BigDecimal("1234.0"), bsAsLocation, barilocheLocation, Category.STANDARD, true);

		this.values.put(trip1.id(), trip1);
		this.values.put(trip2.id(), trip1);
		this.values.put(trip3.id(), trip1);

		Trip trip4 = new Trip("3dcb7909-d87e-4887-a883-13ec7705dd4d", new BigDecimal("1234.0"), bsAsLocation, barilocheLocation, Category.PREMIUM, false);
		Trip trip5 = new Trip("cec531ec-354f-473a-a1c8-52ec82eb2f07", new BigDecimal("1234.0"), bsAsLocation, barilocheLocation, Category.STANDARD_PLUS, false);
		Trip trip6 = new Trip("0a95df3f-208a-4147-b6a6-1322f80a4342", new BigDecimal("1234.0"), bsAsLocation, barilocheLocation, Category.STANDARD, false);

		this.values.put(trip4.id(), trip4);
		this.values.put(trip5.id(), trip5);
		this.values.put(trip6.id(), trip6);

		Trip trip7 = new Trip("c7c517a4-5b8f-4ed0-b317-8fbd408ae6df", new BigDecimal("1234.0"), barilocheLocation, bsAsLocation, Category.PREMIUM, true);
		Trip trip8 = new Trip("bcd90aca-8d18-4f16-b67c-77ddba1219ed", new BigDecimal("1234.0"), barilocheLocation, bsAsLocation, Category.STANDARD_PLUS, true);
		Trip trip9 = new Trip("9d6a0cfa-d73c-43c3-8cce-9a048149a99a", new BigDecimal("1234.0"), barilocheLocation, bsAsLocation, Category.STANDARD, true);

		this.values.put(trip7.id(), trip7);
		this.values.put(trip8.id(), trip8);
		this.values.put(trip9.id(), trip9);

		Trip trip10 = new Trip("89a95f10-6d14-463f-84f5-d2bc6b0b00c5", new BigDecimal("1234.0"), barilocheLocation, bsAsLocation, Category.PREMIUM, false);
		Trip trip11 = new Trip("8d77b503-65f4-47bd-a4a7-04f0bc435836", new BigDecimal("1234.0"), barilocheLocation, bsAsLocation, Category.STANDARD_PLUS, false);
		Trip trip12 = new Trip("1add7bdd-b2a3-4aa0-a91a-8ef9854bb65e", new BigDecimal("1234.0"), barilocheLocation, bsAsLocation, Category.STANDARD, false);

		this.values.put(trip10.id(), trip10);
		this.values.put(trip11.id(), trip11);
		this.values.put(trip12.id(), trip12);

		Trip trip13 = new Trip("694d67ab-bc7e-4efa-bedb-1ddbf09d381e", new BigDecimal("1234.0"), bsAsLocation, mendozaLocation, Category.PREMIUM, true);
		Trip trip14 = new Trip("ab8ffbc6-de23-4efe-9388-f4b58ad34577", new BigDecimal("1234.0"), bsAsLocation, mendozaLocation, Category.STANDARD_PLUS, true);
		Trip trip15 = new Trip("3a5e560b-346e-405e-9621-2500715a91a3", new BigDecimal("1234.0"), bsAsLocation, mendozaLocation, Category.STANDARD, true);

		this.values.put(trip13.id(), trip13);
		this.values.put(trip14.id(), trip14);
		this.values.put(trip15.id(), trip15);

		Trip trip16 = new Trip("3b92586d-b689-42c2-a9ce-d289ebf0ffb5", new BigDecimal("1234.0"), bsAsLocation, mendozaLocation, Category.PREMIUM, false);
		Trip trip17 = new Trip("18c97612-4fd3-4220-b652-537de1117fea", new BigDecimal("1234.0"), bsAsLocation, mendozaLocation, Category.STANDARD_PLUS, false);
		Trip trip18 = new Trip("475d50e9-7040-4a46-b40c-526be6deeb0c", new BigDecimal("1234.0"), bsAsLocation, mendozaLocation, Category.STANDARD, false);

		this.values.put(trip16.id(), trip16);
		this.values.put(trip17.id(), trip17);
		this.values.put(trip18.id(), trip18);

		Trip trip20 = new Trip("db9381c8-f710-4643-b769-8013becffe62", new BigDecimal("1234.0"), mendozaLocation, bsAsLocation, Category.PREMIUM, true);
		Trip trip21 = new Trip("92a3c298-3837-42d5-a1c2-519718dad022", new BigDecimal("1234.0"), mendozaLocation, bsAsLocation, Category.STANDARD_PLUS, true);
		Trip trip22 = new Trip("15426b43-7a48-48ff-9d78-bde28b942719", new BigDecimal("1234.0"), mendozaLocation, bsAsLocation, Category.STANDARD, true);

		this.values.put(trip20.id(), trip20);
		this.values.put(trip21.id(), trip21);
		this.values.put(trip22.id(), trip22);

		Trip trip23 = new Trip("ce7cf30a-98fe-49d2-a086-85776abc870f", new BigDecimal("1234.0"), mendozaLocation, bsAsLocation, Category.PREMIUM, false);
		Trip trip24 = new Trip("d1602278-928f-438c-9155-ac04c00e6af0", new BigDecimal("1234.0"), mendozaLocation, bsAsLocation, Category.STANDARD_PLUS, false);
		Trip trip25 = new Trip("e48dc277-e814-44e8-9bc4-132adfa8cde9", new BigDecimal("1234.0"), mendozaLocation, bsAsLocation, Category.STANDARD, false);

		this.values.put(trip23.id(), trip23);
		this.values.put(trip24.id(), trip24);
		this.values.put(trip25.id(), trip25);


		Excursion excursion1 = new Excursion("e19ad0e9-cb16-4b3e-b12a-4fb7f85cbb57", new BigDecimal("1234.0"), bsAsLocation);
		Excursion excursion2 = new Excursion("0e61d97e-d0d3-44c6-a094-acee9220dbe6", new BigDecimal("1234.0"), barilocheLocation);
		Excursion excursion3 = new Excursion("8a3a2fc7-d3a7-4f92-a3cb-52e268736312", new BigDecimal("1234.0"), mendozaLocation);

		this.values.put(excursion1.id(), excursion1);
		this.values.put(excursion2.id(), excursion2);
		this.values.put(excursion3.id(), excursion3);

		Package packageProduct1 = new Package("2380fd1f-fb07-454d-9936-db5a7684e846", new BigDecimal("1234.0"), excursion1, trip20);
		Package packageProduct2 = new Package("1c4cae7b-72c0-435b-8b17-e422e8dea6a8", new BigDecimal("1234.0"), excursion2, trip7);
		Package packageProduct3 = new Package("dc21653c-3a3a-4e95-82bc-8470bad16ede", new BigDecimal("1234.0"), excursion3, trip3);

		this.values.put(packageProduct1.id(), packageProduct1);
		this.values.put(packageProduct2.id(), packageProduct2);
		this.values.put(packageProduct3.id(), packageProduct3);
	}
}
