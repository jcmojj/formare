package br.com.clinicaformare.model.acesso;

public enum TipoEntidade {		// Título Tabelas	-	Grupo		-	Nome do Bean			-	Nome da Entidade		-	File Name		- Short Path
	ENDERECO						("Endereço",				"Endereço",		"EnderecoBean",			"Endereco",			"endereco",			"/entity/usuario/endereco/"), 
	LOGRADOURO					("Logradouro",			"Endereço",		"LogradouroBean",		"Logradouro",		"logradouro",		"/entity/usuario/endereco/"),  
	TIPOENDERECO					("Tipo Endereço",		"Endereço",		"TipoEnderecoBean",		"TipoEndereco",		"tipoendereco",		"/entity/usuario/endereco/"), 
	PAESCI						("Cidade, Estado e País","Endereço",		"PaesciBean",			"Paesci",			"paesci",			"/entity/usuario/endereco/"),  
	TELEFONE						("Telefone",				"Telefone",		"TelefoneBean",			"Telefone",			"telefone",			"/entity/usuario/telefone/"),  
	TIPOTELEFONE					("Tipo de Telefone",		"Telefone",		"TipoTelefoneBean",		"TipoTelefone",		"tipotelefone",		"/entity/usuario/telefone/"),  
	CODIGOINTERNACIONALTELEFONICO("Código Internacional Telefônico","Telefone","CodigoInternacionalTelefonicoBean","CodigoInternacionalTelefonico",			"codigointernacionaltelefonico",			"/entity/usuario/telefone/"),;
	
	private String tipo;
	private String grupo;
	private String nomeBean;
	private String nomeEntidade;
	private String shortPath;
	private String fileName;

	private TipoEntidade(String tipo, String grupo, String nomeBean, String nomeEntidade, String fileName, String shortPath) {
		this.tipo = tipo;
		this.grupo = grupo;
		this.nomeBean = nomeBean;
		this.nomeEntidade = nomeEntidade;
		this.fileName = fileName;
		this.shortPath = shortPath;
	}

	public String getTipo() {
		return tipo;
	}
	public String getGrupo() {
		return grupo;
	}
	public String getNomeBean() {
		return nomeBean;
	}
	public String getNomeEntidade() {
		return nomeEntidade;
	}
	public String getShortPath() {
		return shortPath;
	}
	public String getFileName() {
		return fileName;
	}
	// toString volta em maiúscula
}
