package br.com.clinicaformare.util;

import javax.inject.Inject;
import javax.persistence.Cache;

public class CacheUtil<T> {
	@Inject
	Cache cache;

	public boolean contains(Class<T> classe, Long id) {
		return cache.contains(classe, Long.valueOf(id));

	}

	// Remove a specific entity object from the shared cache:
	public void evictClassId(Class<T> classe, Long id) {
		cache.evict(classe, Long.valueOf(id));
	}

	// Remove all the instances of a specific class from the cache:
	public void evictClass(Class<T> classe) {
		cache.evict(classe);
	}

	// Clear the shared cache by removing all the cached entity objects:
	public void evictAllClasses() {
		cache.evictAll();
	}
}
