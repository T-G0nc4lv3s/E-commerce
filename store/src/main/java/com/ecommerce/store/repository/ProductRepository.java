package com.ecommerce.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.store.domain.Product;
import com.ecommerce.store.projections.ProductMinprojection;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true,
			value = "SELECT DISTINCT (tb_product.id), (tb_product.name), (tb_product.price) "
					+ "FROM TB_PRODUCT "
					+ "INNER JOIN tb_product_category ON tb_product.id = tb_product_category.product_id "
					+ "INNER JOIN tb_category ON tb_category.id = tb_product_category.category_id "
					)
	public List<ProductMinprojection> findAllProducts();
	
}
