package br.com.clinicaformare.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.primefaces.context.RequestContext;

import br.com.clinicaformare.bean.entity.StartEntity;
import br.com.clinicaformare.daos.usuario.AdministradorDao;
import br.com.clinicaformare.daos.usuario.FinanceiroDao;
import br.com.clinicaformare.daos.usuario.ProfissionalDao;
import br.com.clinicaformare.daos.usuario.SecretariaDao;
import br.com.clinicaformare.daos.usuario.SociaDao;
import br.com.clinicaformare.daos.usuario.UsuarioDao;
import br.com.clinicaformare.daos.usuario.endereco.EnderecoDao;
import br.com.clinicaformare.daos.usuario.endereco.LogradouroDao;
import br.com.clinicaformare.daos.usuario.endereco.PaesciDao;
import br.com.clinicaformare.daos.usuario.endereco.TelefoneDao;
import br.com.clinicaformare.daos.usuario.endereco.TipoEnderecoDao;
import br.com.clinicaformare.daos.usuario.endereco.TipoTelefoneDao;
import br.com.clinicaformare.model.acesso.AcessoProducer;
import br.com.clinicaformare.model.usuario.Administrador;
import br.com.clinicaformare.model.usuario.Financeiro;
import br.com.clinicaformare.model.usuario.Secretaria;
import br.com.clinicaformare.model.usuario.Usuario;

@SessionScoped // --> nao pode ser SessionScoped porque ta injetando o map
@Named
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDao usuarioDao;
	@Inject
	private StartEntity startEntity;
	@Inject
	private PaesciDao paesciDao;
	@Inject
	private AdministradorDao administradorDao;
	@Inject
	private FinanceiroDao financeiroDao;
	@Inject
	private TelefoneDao telefoneDao;
	@Inject
	private TipoTelefoneDao tipoTelefoneDao;
	@Inject
	private TipoEnderecoDao tipoEnderecoDao;
//	@Inject @Entidade(tipo = TipoEntidade.LOGRADOURO)
	@Inject
	private LogradouroDao logradouroDao;
	@Inject
	private EnderecoDao enderecoDao;
	private Usuario usuarioLogado = new Usuario();
	@Inject
	private AcessoProducer acessoProducer;
	@Inject
	private SociaDao sociaDao;
	@Inject 
	private ProfissionalDao profissionalDao;
	@Inject
	private SecretariaDao secretariaDao;

	// @Inject
	// @SessionMap
	// private Map<String, Object> sessionMap;// ---> precisa nao pode te por conta do viewscoped

	// Variáveis
	private boolean logado = false;
	private boolean outro = false;
	private String email = "";
	private String password = "";
	private String userId = "";
	private List<Usuario> listaUsuariosComEmailESenha = new ArrayList<>();

	public void novoUsuario() {
		System.out.println("METODO: NOVO USUARIO");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-Vindo", "Preencha o cadastro");
		FacesContext.getCurrentInstance().addMessage(null, message);
		this.outro = true;
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.addCallbackParam("outro", this.outro); // no lugar de PrimeFaces.current().ajax().addCallbackParam
		rc.addCallbackParam("logado", this.logado);
	}

	public String novoUsuarioString() {
		return "usuariowizard?faces-redirect=true";
	}

	public void recuperarUsuario() {
		System.out.println("METODO: RECUPERAR USUARIO");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-Vindo", "Recuperando o seu usuário");
		FacesContext.getCurrentInstance().addMessage(null, message);
		this.outro = true;
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.addCallbackParam("outro", this.outro); // no lugar de PrimeFaces.current().ajax().addCallbackParam("logado", logado);
		rc.addCallbackParam("logado", this.logado);
	}

	public String recuperarUsuarioString() {
		return "recuperarusuario?faces-redirect=true"; // "recuperarusuario?faces-redirect=true";
	}

	public String selecionarString() {
		System.out.println("METODO: SELECIONAR STRING");
		this.outro = false;
		return "startserver?faces-redirect=true";
	}

	public String selecionar() {
		System.out.println("METODO: SELECIONAR --> ID:"+ this.userId);
		usuarioLogado = usuarioDao.buscaPorId(Long.valueOf(this.userId));
		System.out.println("Usuário:" + usuarioLogado);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-Vindo", usuarioLogado.getNome());
		FacesContext.getCurrentInstance().addMessage(null, message);
		this.logado = true;
		this.outro = false;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuarioLogado);
		posLogado();
		return "dashboard?faces-redirect=true";
	}

	public void logar() { // logar procura um usuario
		System.out.println("METODO: LOGAR");
		FacesMessage message = null;
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		if (usuarioDao.existe(email, password)) {
			System.out.println("Encontrado Usuario com:" + email + " " + password);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-Vindo", "Selecione o usuário");
			FacesContext.getCurrentInstance().addMessage(null, message);
			listaUsuariosComEmailESenha = usuarioDao.buscaUsuariosComLoginEPassword(this.email, this.password);
			this.logado = true;
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.addCallbackParam("logado", this.logado);
			System.out.println("LISTA de USUARIOS");
			listaUsuariosComEmailESenha.forEach(System.out::println);
		} else {
			System.out.println("Não Encontrado Usuário:" + email + " " + password);
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados inválidos", "Usuário ou senha não encontrados");
//			context.addMessage(null, new FacesMessage("Usuário não encontrado"));
			this.logado = false;
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.addCallbackParam("logado", this.logado);
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
	}


	public String deslogar() {
		System.out.println("METODO: DESLOGAR");
		
		this.logado = false;
		listaUsuariosComEmailESenha = null;
		usuarioLogado = null;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLogado");
		return "/home?faces-redirect=true";
	}
	
	public List<Usuario> getListaUsuariosComEmailESenha() {
		System.out.println("METODO: GET LISTA");
		listaUsuariosComEmailESenha.forEach(System.out::println);
		return listaUsuariosComEmailESenha;
	}

	// Getter and setter
	public boolean isOutro() {
		return outro;
	}

	public boolean isDeslogado() {
		return !logado;
	}

	public boolean isLogado() {
		return logado;
	}

	public String getEmail() {
		System.out.println("getEmail: " + email);
		return email;
	}

	public void setEmail(String email) {
		System.out.println("setUsername: " + email);
		this.email = email;
	}

	public String getPassword() {
		System.out.println("getPassword: " + password);
		return password;
	}

	public void setPassword(String password) {
		System.out.println("setPassword: " + password);
		this.password = password;
	}

	public String getUserId() {
		System.out.println("ENTROU GETUSERID");
		return userId;
	}

	public void setUserId(String userId) {
		System.out.println("ENTROU SETUSERID");
		this.userId = userId;
	}

	public Usuario getUsuarioLogado() {
		System.out.println("LoginBean: getUsuarioLogado");
		return usuarioLogado = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}

	// Métodos de inicialização do Usuário Raiz no Servidor
	@Transactional
	@PostConstruct
	public void criarUsuarioRaiz() {
		if (usuarioDao.buscaPorId(1L) == null) {
			Usuario usuario = new Usuario();
			usuario.setCpf("331.881.858-55");
			usuario.setNome("José Carlos");
			usuario.setSobrenome("Melo de Oliveira Júnior");
			usuario.setEmail("jcmojj@gmail.com");
			usuario.setRg("30.028.659-4");
			LocalDate dataDeNascimento = LocalDate.of(1984, 12, 11);
			usuario.setDataNascimento(dataDeNascimento);
			usuario.setProfissao("Developer");
			usuario.setPassword("123");
			usuario = usuarioDao.adicionaVolta(usuario);
			System.out.println("Usuario Criado:" + usuario);

			
			
			Usuario usuario2 = new Usuario();
			usuario2.setCpf("339.541.588-09");
			usuario2.setNome("Daniela");
			usuario2.setSobrenome("Barbiere");
			usuario2.setEmail("jcmojj@gmail.com");
			usuario2.setRg("30.028.659-4");
			LocalDate dataDeNascimento2 = LocalDate.of(1985, 10, 02);
			usuario2.setDataNascimento(dataDeNascimento2);
			usuario2.setProfissao("Publicitária");
			usuario2.setPassword("123");
			usuario2 = usuarioDao.adicionaVolta(usuario2);
			System.out.println("Usuario Criado:" + usuario2);

			
			
		}
	}

	@Transactional
	public void posLogado() {
		System.out.println("LoginBean - poslogado");
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");

		if (administradorDao.buscaPorId(1L) == null) {
//			Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
//			startEntity.paesci();
//			startEntity.logradouro();
//			startEntity.tipoTelefone();
//			startEntity.tipoEndereco();
//			usuario.setLocalNascimento(paesciDao.buscaPorId(paesciDao.getId("Brasil", "SP", "São Paulo")));
//
//			System.out.println("Telefone");
//			Telefone telefone = new Telefone("11", "991318300", tipoTelefoneDao.buscaPorId(3L));
//			// telefone.getUsuarios().add(usuario);
//			telefone = telefoneDao.adicionaVolta(telefone);
//			usuario.getTelefones().add(telefone); // chefe da relação
//			usuarioDao.atualiza(usuario); // persiste aqui
//			telefoneDao.atualiza(telefone);
//
//			System.out.println("Endereco");
//			Endereco endereco = new Endereco();
//			endereco.setBairro("   Campo     belo");
//			endereco.setCep("04    634-          031       ");
//			endereco.setComplemento("apto 72A");
//			endereco.setEndereco("  rua tebas, 296  ");
//			endereco.setLogradouro(logradouroDao.buscaPorNome("Rua"));
//			endereco.setNumero("296");
//			endereco.setPaesci(paesciDao.buscaPorId(paesciDao.getId("Brasil", "SP", "São Paulo")));
//			endereco.setTipoEndereco(tipoEnderecoDao.buscaPorNome("Residencial"));
//			// endereco.getUsuarios().add(usuario);
//			usuario.getEnderecos().add(endereco); // chefe da relação
//			usuarioDao.atualiza(usuario); // persiste aqui
//			enderecoDao.atualiza(endereco);
//
			acessoProducer.adicionarAcessoPadrao();
			
			System.out.println("Administrador");
			Administrador administrador = new Administrador(usuario);
			administrador = administradorDao.adicionaVolta(administrador);
			usuario.setAdministrador(administrador);
			
			System.out.println("Financeiro");
			Financeiro financeiro = new Financeiro(usuario);
			financeiro = financeiroDao.adicionaVolta(financeiro);
			usuario.setFinanceiro(financeiro);
			usuarioDao.atualiza(usuario);
			
			acessoProducer.criarNovoAcessoPara(usuario);
			
			Usuario usuario2 = usuarioDao.buscaPorId(2L);
			
			
//			System.out.println("Socia");
//			Socia socia = new Socia(usuario2);
//			socia = sociaDao.adicionaVolta(socia);
//			usuario2.setSocia(socia);
//			
//			System.out.println("Profissional");
//			Profissional profissional = new Profissional(usuario2);
//			profissional = profissionalDao.adicionaVolta(profissional);
//			usuario2.setProfissional(profissional);
//			usuarioDao.atualiza(usuario2);
			
			System.out.println("Secretária");
			Secretaria secretaria = new Secretaria(usuario2);
			secretaria = secretariaDao.adicionaVolta(secretaria);
			usuario2.setSecretaria(secretaria);
			usuarioDao.atualiza(usuario2);
			
			acessoProducer.criarNovoAcessoPara(usuario2);
			
			
		}
	}

}
