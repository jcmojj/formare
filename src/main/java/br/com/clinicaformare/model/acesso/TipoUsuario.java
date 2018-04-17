package br.com.clinicaformare.model.acesso;

public enum TipoUsuario {
	ADMINISTRADOR("Administrador"),
	FINANCEIRO("Financeiro"),
	SECRETARIA("Secretaria"),
	SOCIA("Socia"), 
	PACIENTE("Paciente"), 
	RESPONSAVELFINANCEIRO("ResponsavelFinanceiro"), 
	FORNECEDOR("Fornecedor"), 
	PROFISSIONAL("Profissional");
	
	private String tipo;

	private TipoUsuario(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return tipo;
	}
	
}
