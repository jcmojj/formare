package br.com.clinicaformare.model;

import java.time.LocalDateTime;

import br.com.clinicaformare.model.usuario.Usuario;

public abstract class Modelo {
	protected abstract Long getId();
//	protected abstract String getTipo();
//	protected abstract void setTipo(String tipo);
	//	public String toString(){
//		return null;
//	}
	public abstract int hashCode();
	public abstract boolean equals(Object obj);
	public abstract void quandoCriar();
	public abstract void quandoAtualizar();
	public abstract LocalDateTime getDataCriacao();
	public abstract LocalDateTime getDataAlteracao();
	public abstract Usuario getAlterador();
	public abstract Usuario getCriador();
	public abstract Class<?> getClasse();
////	public String getNome(){}
////	public void setNome(String nome){}
}
