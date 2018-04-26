package br.com.clinicaformare.model;

import java.time.LocalDateTime;

import br.com.clinicaformare.model.usuario.Usuario;

public abstract class Modelo {
	protected abstract Long getId();
	public abstract int hashCode();
	public abstract boolean equals(Object obj);
	public abstract void quandoCriar();
	public abstract void quandoAtualizar();
	public abstract LocalDateTime getDataCriacao();
	public abstract LocalDateTime getDataAlteracao();
	public abstract Usuario getAlterador();
	public abstract Usuario getCriador();
	public abstract Class<?> getClasse();
}
