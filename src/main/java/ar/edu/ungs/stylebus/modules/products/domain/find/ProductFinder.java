package ar.edu.ungs.stylebus.modules.products.domain.find;

import ar.edu.ungs.stylebus.modules.products.domain.Product;
import ar.edu.ungs.stylebus.modules.products.domain.ProductNotExists;
import ar.edu.ungs.stylebus.modules.products.domain.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public final class ProductFinder {
	private final ProductRepository repository;

	public ProductFinder(ProductRepository repository) {
		this.repository = repository;
	}

	public Product find(String id) {
		return this.repository.findById(id).orElseThrow(() -> new ProductNotExists(id));
	}
}
