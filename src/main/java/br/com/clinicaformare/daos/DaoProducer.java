package br.com.clinicaformare.daos;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class DaoProducer {

	@Produces
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Dao produzir(InjectionPoint ip) throws ClassNotFoundException {
		ParameterizedType type = (ParameterizedType) ip.getType();
		Class classe = ((Class) type.getActualTypeArguments()[0]);
		return new Dao(classe);
		
	}
	
}
