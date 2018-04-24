package br.com.clinicaformare.model.acesso;

import javax.enterprise.util.AnnotationLiteral;

public class EntidadeQualifier extends AnnotationLiteral<Entidade> implements Entidade {
	private static final long serialVersionUID = 1L;
	
	private TipoEntidade tipoEntidade;
	
	public EntidadeQualifier(TipoEntidade tipoEntidade) {
		this.tipoEntidade = tipoEntidade;
	}
	
	public EntidadeQualifier() {
	}

	@Override
	public TipoEntidade tipoEntidade() {
		return this.tipoEntidade;
	}

}
