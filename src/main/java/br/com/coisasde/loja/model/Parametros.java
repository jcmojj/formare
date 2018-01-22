package br.com.coisasde.loja.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Parametros implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double parametro;
	private double valorPorcentagem;
	private double valorReais;

	// Constructor
	public Parametros() {
		super();
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getParametro() {
		return parametro;
	}

	public void setParametro(double parametro) {
		this.parametro = parametro;
	}

	public double getValorPorcentagem() {
		return valorPorcentagem;
	}

	public void setValorPorcentagem(double valorPorcentagem) {
		this.valorPorcentagem = valorPorcentagem;
	}

	public double getValorReais() {
		return valorReais;
	}

	public void setValorReais(double valorReais) {
		this.valorReais = valorReais;
	}

}
