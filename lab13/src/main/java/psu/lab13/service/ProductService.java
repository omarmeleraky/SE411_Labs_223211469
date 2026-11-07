package psu.lab13.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import psu.lab13.model.Product;
import psu.lab13.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Iterable<Product> findAll() {
        return productRepository.findAll();
    }
	
	public Optional<Product> findById(Integer id) {
	    return productRepository.findById(id);
	}
}
