package com.ecommerce.store.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.store.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	/*
	@Query(nativeQuery = true,
			value = "SELECT DISTINCT * "
					+ "FROM TB_PRODUCT "
					+ "INNER JOIN tb_product_category ON tb_product.id = tb_product_category.product_id "
					+ "INNER JOIN tb_category ON tb_category.id = tb_product_category.category_id "
					)
	public List<ProductMinprojection> findAllProducts();
	*/
	
	@Query("SELECT DISTINCT prod "
			+"FROM Product prod "
			+"JOIN FETCH prod.categories")
	public List<Product> findAllProducts();
	
	/*
	@Query(nativeQuery = true,
			value = "SELECT * "
					+ "FROM TB_PRODUCT "
					+ "WHERE tb_product.id = :productId")
	public ProductMinprojection findByProductId(Long productId);
	*/
	
	@Query("SELECT prod "
			+"FROM Product prod "
			+"JOIN FETCH prod.categories "
			+"WHERE prod.id = :productId")
	public Product findByProductId(Long productId);

	@Query(value = "SELECT prod "
			+ "FROM Product prod "
			+ "JOIN FETCH prod.categories")
	public Page<Product> findAllPaged(Pageable pageable);
	
}
