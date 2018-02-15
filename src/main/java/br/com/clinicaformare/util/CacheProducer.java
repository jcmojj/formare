package br.com.clinicaformare.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.Cache;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@ApplicationScoped
public class CacheProducer {

	@PersistenceUnit
	private EntityManagerFactory factory;

	@Produces
	@RequestScoped
	public Cache getCache() {
		return factory.getCache();
	}

	public void close(@Disposes Cache cache) {
	}

}
