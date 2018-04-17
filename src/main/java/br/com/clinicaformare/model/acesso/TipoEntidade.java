package br.com.clinicaformare.model.acesso;

public enum TipoEntidade {
	LOGRADOURO("Logradouro"), ENDERECO("Endereço"), PAESCI("Paesci"), TELEFONE("Telefone");
	
	private String tipo;

	private TipoEntidade(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return tipo;
	}

	
}
