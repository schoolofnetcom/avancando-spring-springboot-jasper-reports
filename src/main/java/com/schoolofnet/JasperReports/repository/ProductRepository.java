package com.schoolofnet.JasperReports.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.schoolofnet.JasperReports.model.Product;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
