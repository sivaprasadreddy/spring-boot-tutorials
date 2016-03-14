package com.sivalabs.demo;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

/**
 * Created by Siva on 18-02-2016.
 */
@SpringBootApplication
public class SpringBootSolrApplication
{

	@Bean(name = "solrServer")
	@Profile("dev")
	public SolrServer embeddedSolrServer() throws ParserConfigurationException, IOException, SAXException
	{
		EmbeddedSolrServerFactory factory = new EmbeddedSolrServerFactory("classpath:demo/solr");
		return factory.getSolrServer();
	}

	@Bean
	@Profile("dev")
	public SolrOperations solrTemplate(SolrServer solrServer)
	{
		return new SolrTemplate(solrServer, "core1");
	}

	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootSolrApplication.class, args);
	}

	/*@Override
	public void run(String... arg0) throws Exception
	{
		Product product = new Product();
		product.setId("100");
		product.setName("P1");
		product.setPrice(new BigDecimal(100));
		productRepository.save(product);
		
		List<Product> p = productRepository.findByName("P1");
		System.out.println(p);
	}*/
}
