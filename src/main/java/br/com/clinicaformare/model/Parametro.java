package br.com.clinicaformare.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Parametro implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private double valorPorcentagem;
	private double valorReais;

	// Constructor
	public Parametro() {
		super();
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
