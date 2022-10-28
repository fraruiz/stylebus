package ar.edu.ungs.stylebus.modules.products.domain;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
	List<Product> search(ProductCriteria criteria);

	Optional<Product> findById(String id);
}
