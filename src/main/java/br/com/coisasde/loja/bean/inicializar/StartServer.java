package br.com.coisasde.loja.bean.inicializar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.coisasde.loja.daos.ParametroDao;
import br.com.coisasde.loja.daos.model.usuario.LogradouroDao;
import br.com.coisasde.loja.daos.model.usuario.PaesciDao;
import br.com.coisasde.loja.daos.model.usuario.ProfissionalDao;
import br.com.coisasde.loja.daos.model.usuario.SociaDao;
import br.com.coisasde.loja.daos.model.usuario.TipoProfissionalDao;
import br.com.coisasde.loja.daos.model.usuario.TipoTelefoneDao;
import br.com.coisasde.loja.daos.model.usuario.UsuarioDao;
import br.com.coisasde.loja.model.Parametro;
import br.com.coisasde.loja.model.usuario.Profissional;
import br.com.coisasde.loja.model.usuario.Socia;
import br.com.coisasde.loja.model.usuario.TipoProfissional;
import br.com.coisasde.loja.model.usuario.Usuario;
import br.com.coisasde.loja.model.usuario.endereco.Logradouro;
import br.com.coisasde.loja.model.usuario.endereco.Paesci;
import br.com.coisasde.loja.model.usuario.telefone.TipoTelefone;

@Named
@RequestScoped
public class StartServer {

	@Transactional
	public void allMainValues() {
			logradouro();
			tipoTelefone();
			paesci();
	}
	
	@Inject
	private LogradouroDao logradouroDao;

	@Transactional
	public void logradouro() {
		try {
			InputStream is = new FileInputStream(
					"/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/logradouro");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String logradouro = br.readLine();

			while (logradouro != null) {

				logradouroDao.adiciona(new Logradouro(logradouro));

				logradouro = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Inject
	private TipoTelefoneDao tipoTelefoneDao;

	@Transactional
	public void tipoTelefone() {
		try {
			InputStream is = new FileInputStream(
					"/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/tipoTelefone");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String tipoTelefone = br.readLine();

			while (tipoTelefone != null) {

				tipoTelefoneDao.adiciona(new TipoTelefone(tipoTelefone));

				tipoTelefone = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Inject
	private PaesciDao paesciDao;

	@Transactional
	public void paesci() {
		System.out.println("Entrou tb");
		try {
			InputStream is = new FileInputStream(
					"/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/paesci");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String s = br.readLine();
			Integer first;
			Integer second;
			Integer third;
			Integer lenght;
			String pais;
			String estado;
			String cidade;

			while (s != null) {
				first = 0;
				second = s.indexOf("-", first);
				third = s.indexOf("-", second + 1);
				lenght = s.length();
				pais = s.substring(first, second);
				estado = s.substring(second + 1, third);
				cidade = s.substring(third + 1, lenght);
				paesciDao.adiciona(new Paesci(pais, estado, cidade));
				s = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Inject
	private ParametroDao parametroDao ;

	@Transactional
	public void parametro() {
		try {
			InputStream is = new FileInputStream(
					"/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/parametros");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String parametroString = br.readLine();
			Integer first;
			Integer second;
			Integer third;
			Integer lenght;

			while (parametroString != null) {
				Parametro parametro = new Parametro();
				first = 0;
				second = parametroString.indexOf(";", first);
				third = parametroString.indexOf(";", second + 1);
				lenght = parametroString.length();
				parametro.setParametro			(parametroString.substring(first,second));
				parametro.setValorPorcentagem	(Double.parseDouble(parametroString.substring(second+1,third)));
				parametro.setValorReais			(Double.parseDouble(parametroString.substring(third+1,lenght)));
				parametroDao.adiciona(parametro);
				

				parametroString = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Inject
	private TipoProfissionalDao tipoProfissionalDao ;

	@Transactional
	public void tipoProfissional() {
		try {
			InputStream is = new FileInputStream(
					"/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/tipoProfissional");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String tipoProfissionalString = br.readLine();
			Integer first;
			Integer second;
			Integer third;
			Integer lenght;
			String tipo; 
			Double valorBrutoHora; 
			Double valorLiquidoHora;
			

			while (tipoProfissionalString != null) {
				first = 0;
				second = tipoProfissionalString.indexOf(";", first);
				third = tipoProfissionalString.indexOf(";", second + 1);
				lenght = tipoProfissionalString.length();
				tipo = tipoProfissionalString.substring(first,second);
				valorBrutoHora = Double.parseDouble(tipoProfissionalString.substring(second+1,third));
				valorLiquidoHora = Double.parseDouble(tipoProfissionalString.substring(third+1,lenght));
				tipoProfissionalDao.adiciona(new TipoProfissional(tipo, valorBrutoHora, valorLiquidoHora));
				

				tipoProfissionalString = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//
	@Inject
	private ProfissionalDao profissionalDao ;

	
	@Transactional
	public void profissional() {
		try {
			InputStream is = new FileInputStream(
					"/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/profissionais");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String profissionalString = br.readLine();
			
			String nome;
			Long tipoProfissionalId;
			Integer second;
			Integer lenght;

			while (profissionalString != null) {
				second = profissionalString.indexOf(";", 0);
				lenght = profissionalString.length();
				nome =		(profissionalString.substring(0,second));
				System.out.println("AQUI9");
				tipoProfissionalId =			(Long.parseLong(profissionalString.substring(second+1,lenght)));
				System.out.println("AQUI11" + profissionalString.substring(second+1,lenght));
				TipoProfissional tipoProfissional = tipoProfissionalDao.buscaPorId(tipoProfissionalId);
				System.out.println("AQUI12" + tipoProfissional);
				Profissional profissional = new Profissional(tipoProfissional);
				System.out.println("AQUI5");
				
//				Usuario usuario = new Usuario(nome);
//				System.out.println("AQUI6 + usuario: " + usuario);
//				usuario = usuarioDao.adicionaVolta(usuario);
				
				Usuario usuario = adicionaUsuarioComNome(nome);
				System.out.println("AQUI7 + usuario: " + usuario);
				profissional.setUsuario(usuario);
				usuario.setProfissional(profissional);
				System.out.println("AQUI8");
				profissionalDao.adiciona(profissional);
				usuarioDao.atualiza(usuario);
				profissionalString = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Inject
	private UsuarioDao usuarioDao;
	
	@Transactional
	public Usuario adicionaUsuarioComNome(String nome) {
		Usuario usuario = new Usuario(nome);
		return usuarioDao.adicionaVolta(usuario);
	}

	@Inject
	private SociaDao sociaDao;
	
	@Transactional
	public void socia() {
		try {
			InputStream is = new FileInputStream(
					"/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/socias");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String sociaString = br.readLine();
			
			String nome;
			Long tipoProfissionalId;
			Integer second;
			Integer lenght;

			while (sociaString != null) {
				second = sociaString.indexOf(";", 0);
				lenght = sociaString.length();
				nome =		(sociaString.substring(0,second));
				System.out.println("AQUI9");
				tipoProfissionalId =			(Long.parseLong(sociaString.substring(second+1,lenght)));
				System.out.println("AQUI11" + sociaString.substring(second+1,lenght));
				TipoProfissional tipoProfissional = tipoProfissionalDao.buscaPorId(tipoProfissionalId);
				System.out.println("AQUI12" + tipoProfissional);
				Socia socia = new Socia(tipoProfissional);
				System.out.println("AQUI5");
				
//				Usuario usuario = new Usuario(nome);
//				System.out.println("AQUI6 + usuario: " + usuario);
//				usuario = usuarioDao.adicionaVolta(usuario);
				
				Usuario usuario = adicionaUsuarioComNome(nome);
				System.out.println("AQUI7 + usuario: " + usuario);
				socia.setUsuario(usuario);
				usuario.setSocia(socia);
				System.out.println("AQUI8");
				sociaDao.adiciona(socia);
				usuarioDao.atualiza(usuario);
				sociaString = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void paciente() {
		
	}
	
	@Transactional
	public void atendimentoPadrao() {
		
	}
	
}