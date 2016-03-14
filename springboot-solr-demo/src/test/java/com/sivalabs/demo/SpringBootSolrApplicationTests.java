/**
 * 
 */
package com.sivalabs.demo;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Siva
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringBootSolrApplication.class)
public class SpringBootSolrApplicationTests
{
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void testLoads(){
		Product product = new Product();
		product.setId("100");
		product.setName("P1");
		product.setPrice(new BigDecimal(100));
		productRepository.save(product);
		
		List<Product> p = productRepository.findByName("P1");
		System.err.println(p);
		
	}
}
