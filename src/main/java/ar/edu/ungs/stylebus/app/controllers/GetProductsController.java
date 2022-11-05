package ar.edu.ungs.stylebus.app.controllers;

import ar.edu.ungs.stylebus.modules.products.application.ProductResponse;
import ar.edu.ungs.stylebus.modules.products.application.search.ProductsSearcher;
import ar.edu.ungs.stylebus.modules.products.domain.Category;
import ar.edu.ungs.stylebus.modules.products.domain.ProductCriteria;
import ar.edu.ungs.stylebus.modules.products.domain.ProductTypeCriteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public final class GetProductsController {
	private final ProductsSearcher searcher;

	public GetProductsController(ProductsSearcher searcher) {
		this.searcher = searcher;
	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductResponse>> index(@RequestParam(value = "type") String type,
	                                                   @RequestParam(value = "city", required = false) String city,
	                                                   @RequestParam(value = "category", required = false) String category,
	                                                   @RequestParam(value = "going_back") Boolean goingBack,
	                                                   @RequestParam(value = "passengers_quantity", defaultValue = "0") Integer passengersQuantity) {
		ProductTypeCriteria productType = ProductTypeCriteria.valueOf(type.toUpperCase());
		Category categoryProduct = category == null ? null : Category.valueOf(category.toUpperCase());

		List<ProductResponse> response = searcher.search(new ProductCriteria(productType,
		                                                                     city,
		                                                                     categoryProduct,
		                                                                     goingBack,
		                                                                     passengersQuantity));

		return ResponseEntity.ok(response);
	}
}
