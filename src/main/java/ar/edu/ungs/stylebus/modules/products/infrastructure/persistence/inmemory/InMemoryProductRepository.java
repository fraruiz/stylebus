package ar.edu.ungs.stylebus.modules.products.infrastructure.persistence.inmemory;

import ar.edu.ungs.stylebus.modules.products.domain.Package;
import ar.edu.ungs.stylebus.modules.products.domain.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public final class InMemoryProductRepository implements ProductRepository {
	private final Map<String, Product> values;

	public InMemoryProductRepository() {
		this.values = new HashMap<>();

		this.setup();
	}

	@Override
	public List<Product> search(ProductCriteria criteria) {
		return null;
	}

	@Override
	public Optional<Product> findById(String id) {
		return Optional.ofNullable(this.values.get(id));
	}

	private void setup() {
		Location retiroLocation = new Location(new Point(-34.5893799, -58.3855431), "Terminal de Omnibus Retiro");
		Location barilocheLocation = new Location(new Point(-41.1336437, -71.3117545), "San Carlos de Bariloche");
		Location mendozaLocation = new Location(new Point(-34.5660941, -58.4682105), "Mendoza");

		Trip trip1 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), retiroLocation, barilocheLocation);
		Trip trip2 = new Trip(UUID.randomUUID().toString(), new BigDecimal("1234.0"), barilocheLocation, retiroLocation);
		Trip trip3 = new Trip(UUID.randomUUID().toString(), new BigDecimal("500.0"), retiroLocation, mendozaLocation);
		Trip trip4 = new Trip(UUID.randomUUID().toString(), new BigDecimal("500.0"), mendozaLocation, retiroLocation);

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
