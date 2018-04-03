package br.com.clinicaformare.model.usuario;

// Define os acessos e os tipos de operações autorizadas
public enum TipoUsuario {

	SOCIA("Sócia"), PROFISSIONAL("Profissional") , Fornecedor("Fornecedor"), SECRETARIA("Secretária");

	private String descricao;

	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	@Override
	     public String toString() {
	        return this.descricao;
	}
}
