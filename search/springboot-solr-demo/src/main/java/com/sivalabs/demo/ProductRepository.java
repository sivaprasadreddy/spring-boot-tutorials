/**
 * 
 */
package com.sivalabs.demo;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * @author Siva
 *
 */
public interface ProductRepository extends SolrCrudRepository<Product, String>
{

	List<Product> findByName(String name);


}
