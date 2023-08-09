package com.ecommerce.store.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.store.domain.Category;
import com.ecommerce.store.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT prod "
			+"FROM Product prod "
			+"JOIN FETCH prod.categories "
			+"WHERE prod.id = :productId")
	public Product findByProductId(Long productId);


	@Query(value = "SELECT prod "
			+ "FROM Product prod "
			+ "INNER JOIN prod.categories cats "
			+ "WHERE (:categories IS NULL OR cats IN :categories) AND "
			+ "(LOWER(prod.name) LIKE LOWER(CONCAT('%', :name, '%')))")
	public Page<Product> searchProduct(Pageable pageable, List<Category> categories, String name);
	
}
