package br.com.clinicaformare.model.atendimento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import br.com.clinicaformare.model.usuario.EspecializacaoDoProfissional;
import br.com.clinicaformare.model.usuario.Paciente;
import br.com.clinicaformare.model.usuario.Profissional;
import br.com.clinicaformare.model.usuario.Socia;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.util.UsuarioLogado;

@Entity
public class Atendimento implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Transient
	@Inject
	private UsuarioLogado usuarioLogado;
	
	
	// Parâmetros Próprios
	LocalDate data;
	LocalTime horaInicio; // todo atendimento dura 1h
	LocalTime horaFinal;
	private Integer quantidadeProfissionais;
	private Integer quantidadeProfissionaisPagos;
	@ManyToOne
	private Pacote pacote;
	@ManyToOne
	Paciente paciente;
	@OneToMany(mappedBy = "atendimento")
	private List<AtendimentoPorProfissional> atendimentosPorProfissional = new ArrayList<>();
	@ManyToOne
	private EspecializacaoDoProfissional especializacaoProfissional;
	@ManyToOne
	private Socia sociaSupervisora;
	
	
	
//	private TipoAtendimento tipoAtendimento; // com mais de um profissional: rateia o valor ou cobra o valor cheio pra cada 
	@OneToOne
	private EspecializacaoDoProfissional tipoProfissional;
	private BigDecimal descontoPacotePaciente;
	private BigDecimal valorRecebidoPeloAtendimento;
	private boolean realizado;
	private boolean pago;
//	@Transient
	private boolean supervisionado;
	
	// Parâmetros de Persistência
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAlteracao;
	@OneToOne
	private Usuario alteradoPor;
	@OneToOne
	private Usuario criadoPor;
	
	
	// Constructor
	public Atendimento() {
		super();
	}
	public Atendimento(Long id) {
		super();
		this.id = id;
	}
	public Atendimento(LocalDate data, LocalTime horaInicio, Profissional profissional, EspecializacaoDoProfissional especializacaoDoProfissional) {
		super();
		this.data = data;
		this.horaInicio = horaInicio;
		this.quantidadeProfissionais = 1;
		this.quantidadeProfissionaisPagos = 2;
		AtendimentoPorProfissional atendimentoPorProfissional = new AtendimentoPorProfissional(this, profissional, especializacaoProfissional);
		atendimentosPorProfissional.add(atendimentoPorProfissional);
	}
	
	// Getters and Setters
	public Long getId() {
		return id;
	}
	
	
	

	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public boolean isRealizado() {
		return realizado;
	}
	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}



	public LocalTime getHoraFinal() {
		return horaFinal;
	}
//	public List<Usuario> getPacientes() {
//		return pacientes;
//	}
	public Integer getQuantidadeProfissionais() {
		return quantidadeProfissionais;
	}
	public Integer getQuantidadeProfissionaisPagos() {
		return quantidadeProfissionaisPagos;
	}	
	
	


	
	public BigDecimal getDescontoPacotePaciente() {
		return descontoPacotePaciente;
	}
	public void setDescontoPacotePaciente(BigDecimal descontoPacotePaciente) {
		this.descontoPacotePaciente = descontoPacotePaciente;
	}
	public BigDecimal getValorRecebidoPeloAtendimento() {
		return valorRecebidoPeloAtendimento;
	}
	
	
//	public void adicionaProfissional(Profissional profissional, EspecializacaoDoProfissional especializacaoDoProfissional) {
//		atendimentosPorProfissional.add(new AtendimentoPorProfissional(this, profissional, especializacaoDoProfissional));
//		atendimentosPorProfissional.stream().forEach(	u -> {u.setQuantidadeProfissionais(atendimentosPorProfissional.size());
//		});
//		Optional<BigDecimal> max = tendimentoPorProfissional.stream().map(p -> p.getValorPagoProfissional()).max(Comparator.naturalOrder());
//		valorRecebidoPeloAtendimento = max.orElse(new BigDecimal("300")); // arrumar
//		if(supervisionado) {
//			valorRecebidoPeloAtendimento.add(supervisora.getValorBrutoHora());
//		}
//
//		
//	}
//	public void removeProfissional(AtendimentoPorProfissional profissional) {
//		
//		
//	}
	public EspecializacaoDoProfissional getTipoProfissional() {
		return tipoProfissional;
	}
	public void setTipoProfissional(EspecializacaoDoProfissional tipoProfissional) {
		this.tipoProfissional = tipoProfissional;
	}
	
	
	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}
	// Supervisionado ou Não
	public Socia getSociaSupervisora() {
		return sociaSupervisora;
	}
	public void setSociaSupervisora(Socia sociaSupervisora) {
		if(sociaSupervisora == null) {
			supervisionado = false;
		}else{
			supervisionado = true;
			this.sociaSupervisora = sociaSupervisora;
		}
	}
	public boolean isSupervisionado() {
		return supervisionado;
	}
	
	// Métodos de Persistência
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}
	public Usuario getAlteradoPor() {
		return alteradoPor;
	}
	public Usuario getCriadoPor() {
		return criadoPor;
	}
	
	// String, hashCode and Equals
	@Override
	public String toString() {
		return "Atendimento [id=" + id + ", data=" + data + ", horaInicio=" + horaInicio + ", paciente=" + paciente + ", tipoProfissional=" + tipoProfissional + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atendimento other = (Atendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	// Método Callback para persistir
	@PrePersist
	public void quandoCriar() {
		this.dataCriacao = LocalDateTime.now();
		this.dataAlteracao = LocalDateTime.now();
		this.criadoPor = usuarioLogado.getUsuarioLogado();
		this.alteradoPor = usuarioLogado.getUsuarioLogado();
	}

	// Método Callback para update
	@PreUpdate
	public void quandoAtualizar() {
		this.dataAlteracao = LocalDateTime.now();;
		this.alteradoPor = usuarioLogado.getUsuarioLogado();
	}
}

//pacote
//List<Invoice> invoiceList = new ArrayList<>();
//populate
//Function<Invoice, BigDecimal> totalMapper = invoice -> invoice.getUnit_price().multiply(invoice.getQuantity());
//BigDecimal result = invoiceList.stream()
//      .map(totalMapper)
//      .reduce(BigDecimal.ZERO, BigDecimal::add);
