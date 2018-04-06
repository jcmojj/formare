package br.com.clinicaformare.bean;

import java.io.Serializable;
import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.clinicaformare.bean.inicializar.StartServer;
import br.com.clinicaformare.daos.usuario.AdministradorDao;
import br.com.clinicaformare.daos.usuario.UsuarioDao;
import br.com.clinicaformare.daos.usuario.endereco.EnderecoDao;
import br.com.clinicaformare.daos.usuario.endereco.LogradouroDao;
import br.com.clinicaformare.daos.usuario.endereco.PaesciDao;
import br.com.clinicaformare.daos.usuario.endereco.TelefoneDao;
import br.com.clinicaformare.daos.usuario.endereco.TipoEnderecoDao;
import br.com.clinicaformare.daos.usuario.endereco.TipoTelefoneDao;
import br.com.clinicaformare.model.usuario.Administrador;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.usuario.endereco.Endereco;
import br.com.clinicaformare.usuario.endereco.Telefone;

@SessionScoped // --> nao pode ser SessionScoped porque ta injetando o map
@Named
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDao usuarioDao;
	@Inject
	private StartServer startServer;
	@Inject
	private PaesciDao paesciDao;
	@Inject
	private AdministradorDao administradorDao;
	@Inject
	private TelefoneDao telefoneDao;
	@Inject
	private TipoTelefoneDao tipoTelefoneDao;
	@Inject
	private TipoEnderecoDao tipoEnderecoDao;
	@Inject
	private LogradouroDao logradouroDao;
	@Inject
	private EnderecoDao enderecoDao;
//	@Inject
//	@SessionMap
//	private Map<String, Object> sessionMap;// ---> precisa nao pode te por conta do viewscoped

	private boolean logado = false;
	private String email = "";
    private String password = "";
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
    
	public void logar() {
		FacesMessage message = null;
		if (usuarioDao.existe(email, password)) {
			System.out.println("Login Valido:" + email + " " + password);
			this.logado = true;
			Usuario usuario = usuarioDao.buscaLoginPassword(email, password);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuario); // --->> precisa
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-Vindo", usuario.getNome());
			posLogado();
			// FacesContext context = FacesContext.getCurrentInstance();
			// context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			// System.out.println("Login Valido2:"+ sessionMap.get("usuarioLogado"));
			// System.out.println();
			// parameterMap.put("usuarioLogadoId", ((Long)this.usuario.getId()).toString());
		} else {
			this.logado = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro de login", "Dados inválidos");
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.addCallbackParam("loggedIn", this.logado);
//		return "home?faces-redirect-true";

		// System.out.println("IMPRIMIR LOGAR ANTES DO IF");
		// System.out.println("usuario:" + usuario);
		// System.out.println("usuarioDao.existe(usuario):" + usuarioDao.existe(usuario));
	}

	public void deslogar() {
		// usuario = null;
		// sessionMap.remove("usuarioLogado");
		// parameterMap.remove("usuarioLogadoId");
		this.logado = false;
	}


	public boolean isLogado() {
		return logado;
	}

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
		}
	}

	public void posLogado() {
		if (paesciDao.buscaPorId(1L) == null) {
			Usuario usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
			startServer.paesci();
			startServer.logradouro();
			startServer.tipoTelefone();
			startServer.tipoEndereco();
			usuario.setLocalNascimento(paesciDao.buscaPorId(paesciDao.getId("Brasil", "SP", "São Paulo")));

			System.out.println("Telefone");
			Telefone telefone = new Telefone("11", "991318300", tipoTelefoneDao.buscaPorId(3L));
			// telefone.getUsuarios().add(usuario);
			telefone = telefoneDao.adicionaVolta(telefone);
			usuario.getTelefones().add(telefone); // chefe da relação
			usuarioDao.atualiza(usuario); // persiste aqui
			telefoneDao.atualiza(telefone);

			System.out.println("Endereco");
			Endereco endereco = new Endereco();
			endereco.setBairro("   Campo     belo");
			endereco.setCep("04    634-          031       ");
			endereco.setComplemento("apto 72A");
			endereco.setEndereco("  rua tebas, 296  ");
			endereco.setLogradouro(logradouroDao.buscaPorNome("Rua"));
			endereco.setNumero("296");
			endereco.setPaesci(paesciDao.buscaPorId(paesciDao.getId("Brasil", "SP", "São Paulo")));
			endereco.setTipoEndereco(tipoEnderecoDao.buscaPorNome("Residencial"));
			// endereco.getUsuarios().add(usuario);
			endereco = enderecoDao.adicionaVolta(endereco);
			usuario.getEnderecos().add(endereco); // chefe da relação
			usuarioDao.atualiza(usuario); // persiste aqui
			enderecoDao.atualiza(endereco);

			System.out.println("Administrador");
			Administrador administrador = new Administrador(usuario);
			administrador = administradorDao.adicionaVolta(administrador);
			usuario.setAdministrador(administrador);
		}
	}

}
