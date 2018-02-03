package br.com.clinicaformare.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.clinicaformare.daos.AtendimentoPadraoDao;
import br.com.clinicaformare.daos.PacoteDao;
import br.com.clinicaformare.daos.usuario.UsuarioDao;
import br.com.clinicaformare.model.atendimento.AtendimentoPadrao;
import br.com.clinicaformare.model.atendimento.Pacote;
import br.com.clinicaformare.model.usuario.Paciente;
import br.com.clinicaformare.model.usuario.ResponsavelFinanceiro;
import br.com.clinicaformare.model.usuario.TipoProfissional;
import br.com.clinicaformare.model.usuario.Usuario;

@Named
@ViewScoped
public class NovoPacoteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioDao usuarioDao;
	@Inject
	PacoteDao pacoteDao;
	@Inject
	AtendimentoPadraoDao atendimentoPadraoDao;

	private ResponsavelFinanceiro responsavelFinanceiro = new ResponsavelFinanceiro();
	private Paciente paciente = new Paciente();
	private Long sociaResponsavelId = 0L;
	private Long responsavelFinanceiroId = 0L;
	private Long pacienteId = 0L;
	private Long pacotePadraoId = 0L;
	private List<AtendimentoPadrao> atendimentosPadrao = new ArrayList<>();
	private boolean mostraDesconto = true;
	
	
	// Getters and Setters
	

//	public Long getListaTodosUsuarioClienteDoMaisNovoAoMaisVelhoId() {
//		return listaTodosUsuarioClienteDoMaisNovoAoMaisVelhoId;
//	}
//
//
//	public void setListaTodosUsuarioClienteDoMaisNovoAoMaisVelhoId(Long listaTodosUsuarioClienteDoMaisNovoAoMaisVelhoId) {
//		this.listaTodosUsuarioClienteDoMaisNovoAoMaisVelhoId = listaTodosUsuarioClienteDoMaisNovoAoMaisVelhoId;
//	}
//
//
//	public Long getListaSociasId() {
//		return listaSociasId;
//	}
//
//
//	public void setListaSociasId(Long listaSociasId) {
//		this.listaSociasId = listaSociasId;
//	}
//
//



//	public Socia getSocia() {
//		return socia;
//	}
//
//
//	public void setSocia(Socia socia) {
//		this.socia = socia;
//	}


	public Long getSociaResponsavelId() {
		return sociaResponsavelId;
	}


	public void setSociaResponsavelId(Long sociaResponsavelId) {
		this.sociaResponsavelId = sociaResponsavelId;
	}


	public Long getPacotePadraoId() {
		return pacotePadraoId;
	}


	public void setPacotePadraoId(Long pacotePadraoId) {
		System.out.println("pacotePadraoId"+":"+pacotePadraoId);
		this.pacotePadraoId = pacotePadraoId;
		if(pacotePadraoId == null) {
			this.atendimentosPadrao.removeIf(aP -> aP.isAtendimentoPadraoDePacote());
			System.out.println("TESTE1");
//			this.atendimentosPadrao.forEach(atendimentoPadrao -> atendimentoPadrao.);
		}else {
			List<AtendimentoPadrao> atendimentosPadraoDePacote = atendimentoPadraoDao.listaAtendimentosPadraoDePacote(pacotePadraoId);
			this.atendimentosPadrao.removeIf(aP -> aP.isAtendimentoPadraoDePacote());System.out.println("TESTE2");
			for(AtendimentoPadrao atendimentoPadraoDePacote : atendimentosPadraoDePacote) {
				Integer quantidadeAtendimentosMensal = atendimentoPadraoDePacote.getQuantidadeAtendimentosMensal();
				TipoProfissional tipoProfissional = atendimentoPadraoDePacote.getTipoProfissional();
				Double desconto = atendimentoPadraoDePacote.getDesconto();
				boolean atendimentoPadraoDePacotes = true;
				
				AtendimentoPadrao atendimentoPadrao = new AtendimentoPadrao(quantidadeAtendimentosMensal, tipoProfissional, desconto, atendimentoPadraoDePacotes);
				atendimentoPadrao.setValorBrutoHora(atendimentoPadraoDePacote.getValorBrutoHora());System.out.println("TESTE2");
				atendimentoPadrao.setValorLiquidoHora(atendimentoPadraoDePacote.getValorLiquidoHora());System.out.println("TESTE3");
				atendimentoPadrao.setPorcentagemLiquidoSobreBruto(atendimentoPadraoDePacote.getPorcentagemLiquidoSobreBruto());System.out.println("TESTE4");
				
				this.atendimentosPadrao.add(atendimentoPadrao);System.out.println("TESTE5");
			}
			
			
			
//			this.atendimentosPadrao.clear();
			
//			this.atendimentosPadrao = new ArrayList<>(atendimentoPadraoDao.listaAtendimentosPadraoDePacote(pacotePadraoId));
//			System.out.println("PacotePadraoId:"+pacotePadraoId);
//			this.atendimentosPadrao = atendimentoPadraoDao.listaAtendimentosPadraoDePacote(pacotePadraoId);
			System.out.println("LISTA");
			atendimentosPadrao.forEach(atendimentoPadrao -> System.out.println(atendimentoPadrao));
//			this.atendimentosPadrao.forEach(atendimentoPadrao -> {atendimentoPadrao.setAtendimentoPadraoDePacote(false);atendimentoPadrao.setPacote(null);atendimentoPadrao.setId(0L);});
//			atendimentosPadrao.forEach(atendimentoPadrao -> System.out.println(atendimentoPadrao));
		}
		System.out.println("pacotePadraoId"+":"+pacotePadraoId);
	}


	public Long getPacienteId() {
		return pacienteId;
	}


	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}


	public Long getResponsavelFinanceiroId() {
		return responsavelFinanceiroId;
	}


	public void setResponsavelFinanceiroId(Long responsavelFinanceiroId) {
		this.responsavelFinanceiroId = responsavelFinanceiroId;
	}


	public ResponsavelFinanceiro getResponsavelFinanceiro() {
		return responsavelFinanceiro;
	}


	public void setResponsavelFinanceiro(ResponsavelFinanceiro responsavelFinanceiro) {
		this.responsavelFinanceiro = responsavelFinanceiro;
	}


	public Paciente getPaciente() {
		return paciente;
	}


	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<AtendimentoPadrao> getAtendimentosPadrao() {
		return atendimentosPadrao;
	}


	public void setAtendimentosPadrao(List<AtendimentoPadrao> atendimentosPadrao) {
		this.atendimentosPadrao = atendimentosPadrao;
	}


	public boolean isMostraDesconto() {
		return mostraDesconto;
	}


	public void setMostraDesconto(boolean mostraDesconto) {
		this.mostraDesconto = mostraDesconto;
	}


	// Especificos
	public List<Usuario> getListaTodosUsuarioClienteDoMaisNovoAoMaisVelho(){
		return usuarioDao.listaTodosUsuarioClienteDoMaisNovoAoMaisVelho();
	}
	public List<Usuario> getListaUsuariosTipoSocia(){
		List<Usuario> usuariosTipoSocia = usuarioDao.listaUsuariosTipoSocia();
		usuariosTipoSocia.forEach(u -> System.out.println(u));
		return usuariosTipoSocia;
		
	}
	public List<Pacote> getPacotesPadrao(){
		return pacoteDao.listaPacotesPadrao();
	}
	public Integer getQuantidadeAtendimentosPadrao() {
		return this.atendimentosPadrao.size();
	}
	
	// Outros
	public int getTotalAtendimentos() {
		return atendimentosPadrao.stream().mapToInt(AtendimentoPadrao::getQuantidadeAtendimentosMensal).sum();
	}
	
	public Double getTotalValorBrutoTotal() {
		return atendimentosPadrao.stream().mapToDouble(AtendimentoPadrao::getValorBrutoTotal).sum();
	}
	public Double getTotalValorLiquidoTotal() {
		return atendimentosPadrao.stream().mapToDouble(AtendimentoPadrao::getValorLiquidoTotal).sum();
	}
	
	// Salvar
	@Transactional
	public void salvar() {
//		FacesMessage msg = new FacesMessage("Salvo com sucesso!", "Bem-Vindo :" + usuario.getNome());
//		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	

}