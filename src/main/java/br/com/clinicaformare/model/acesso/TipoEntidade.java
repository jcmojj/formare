package br.com.clinicaformare.model.acesso;

public enum TipoEntidade {
//	LOGRADOURO;
	LOGRADOURO("Logradouro"), ENDERECO("Endereço"), PAESCI("Paesci");
	
	private String nome;

//	private TipoEntidade() {
//		
//	}
	private TipoEntidade(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	
}
