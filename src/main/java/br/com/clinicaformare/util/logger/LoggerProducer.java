package br.com.clinicaformare.util.logger;


import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.util.listeners.login.UsuarioLogado;

public class LoggerProducer {
	
	private Logger logger = LoggerFactory.getLogger(LoggerProducer.class);

	@Produces
	public Logger criaLogger(InjectionPoint ip, @UsuarioLogado Usuario usuario) {
		Class<?> classe = ip.getMember().getDeclaringClass();
		logger.info("Usuario " + usuario + " entrou na classe " + classe);
		return LoggerFactory.getLogger(classe.getName());
	}
	
}
