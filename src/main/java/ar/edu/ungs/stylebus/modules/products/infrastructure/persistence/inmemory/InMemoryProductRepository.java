package ar.edu.ungs.stylebus.modules.products.infrastructure.persistence.inmemory;

import ar.edu.ungs.stylebus.modules.products.domain.Product;
import ar.edu.ungs.stylebus.modules.products.domain.ProductCriteria;
import ar.edu.ungs.stylebus.modules.products.domain.ProductRepository;
import ar.edu.ungs.stylebus.modules.users.domain.User;
import ar.edu.ungs.stylebus.modules.users.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public final class InMemoryProductRepository implements ProductRepository {
	private final Map<String, Product> values;

	public InMemoryProductRepository() {
		this.values = new HashMap<>();
	}

	@Override
	public List<Product> search(ProductCriteria criteria) {
		return null;
	}

	@Override
	public Optional<Product> findById(String id) {
		return Optional.ofNullable(this.values.get(id));
	}
}
