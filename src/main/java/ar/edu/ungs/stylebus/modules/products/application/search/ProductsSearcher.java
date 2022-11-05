package ar.edu.ungs.stylebus.modules.products.application.search;

import ar.edu.ungs.stylebus.modules.products.application.ProductResponse;
import ar.edu.ungs.stylebus.modules.products.domain.ProductCriteria;
import ar.edu.ungs.stylebus.modules.products.domain.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public final class ProductsSearcher {
	private final ProductRepository repository;

	public ProductsSearcher(ProductRepository repository) {
		this.repository = repository;
	}

	public List<ProductResponse> search(ProductCriteria criteria) {
		return this.repository.search(criteria)
		                      .stream()
		                      .map(ProductResponse::map)
		                      .collect(Collectors.toList());
	}
}
