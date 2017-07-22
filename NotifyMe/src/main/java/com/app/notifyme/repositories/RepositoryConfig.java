package com.app.notifyme.repositories;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.app.notifyme.models.Product;
import com.app.notifyme.models.Productstat;
import com.app.notifyme.models.Usercriteria;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Product.class);
		config.exposeIdsFor(Usercriteria.class);
		config.exposeIdsFor(Error.class);
		config.exposeIdsFor(Productstat.class);
	}
}
