package ar.edu.ungs.stylebus.app.controllers;

import ar.edu.ungs.stylebus.modules.products.application.ProductResponse;
import ar.edu.ungs.stylebus.modules.products.application.search.ProductsSearcher;
import ar.edu.ungs.stylebus.modules.products.domain.ProductCriteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public final class GetProductsController {
	private final ProductsSearcher searcher;

	public GetProductsController(ProductsSearcher searcher) {
		this.searcher = searcher;
	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductResponse>> index() {
		List<ProductResponse> response = searcher.search(new ProductCriteria());

		return ResponseEntity.ok(response);
	}
}
