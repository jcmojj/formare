package br.com.clinicaformare.bean.inicializar;

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

import br.com.clinicaformare.daos.usuario.NivelProfissionalDao;
import br.com.clinicaformare.model.usuario.NivelProfissional;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.util.UsuarioLogado;

@Named
@RequestScoped
public class StartServer {

//	@Inject
//	private UsuarioDao usuarioDao;
//
//	 @Transactional
//	 public Usuario adicionaAdministrador() {
//	 Usuario usuario = new Usuario();
//	 usuario.setNome("José Carlos");
//	 usuario.setSobrenome("Melo de Oliveira Júnior");
//	 usuario.setCpf("331.881.858-55");
//	 usuario.setRg("30.028.659-4");
//	 usuario.setProfissao("Developer");
//	 return usuarioDao.adicionaVolta(usuario);
//	 }
	@Inject @UsuarioLogado
	private Usuario usuarioLogado;
	
	public String getUsuarioLogado() {
		if(usuarioLogado == null) {
			return "Sem usuário logado";
		}
		return usuarioLogado.getNome() + usuarioLogado.getSobrenome();
	}
	
	@Inject
	private NivelProfissionalDao nivelProfissionalDao;

	@Transactional
	public String nivelProfissional() {
		System.out.println("");
		System.out.println("Nivel Profissional");
		try {
			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/nivelProfissional");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String linha = br.readLine();

			while (linha != null) {

				nivelProfissionalDao.adiciona(new NivelProfissional(linha));

				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "startserver?faces-redirect-true"; 
	}
	
	
	
	
	
	
	@Transactional
	public void allMainValues() {
		// System.out.println("AllMainValues"+":"+"paesci");paesci();
		// System.out.println("AllMainValues"+":"+"logradouro");logradouro();
		// System.out.println("AllMainValues"+":"+"tipoTelefone");tipoTelefone();
		// System.out.println("AllMainValues"+":"+"tipoEndereco");tipoEndereco();
//		System.out.println("AllMainValues" + ":" + "tipoProfissional");
//		tipoProfissional();
//		System.out.println("AllMainValues" + ":" + "profissional");
//		profissional();
//		System.out.println("AllMainValues" + ":" + "tipoSocia");
//		tipoSocia();
//		System.out.println("AllMainValues" + ":" + "socia");
//		socia();
//		System.out.println("AllMainValues" + ":" + "paciente");
//		paciente();
//		System.out.println("AllMainValues" + ":" + "atendimentoPadrao");
//		atendimentoPadrao();
//		System.out.println("AllMainValues" + ":" + "responsavelFinanceiroPaciente");
//		responsavelFinanceiroPaciente();
//		System.out.println("AllMainValues" + ":" + "banco");
//		banco();
//		System.out.println("AllMainValues" + ":" + "operacaoFinanceira");
//		operacaoFinanceira();
//		System.out.println("AllMainValues" + ":" + "operacaoFinanceiraTarifa");
//		operacaoFinanceiraTarifa();
//		System.out.println("AllMainValues" + ":" + "calculadoraParametrosTeste");
//		calculadoraParametrosTeste();
//		System.out.println("AllMainValues" + ":" + "feriado");
//		calendarios();
		System.out.println("AllMainValues" + ":" + "nivelProfissional");
		nivelProfissional();
		
	}
//
//	@Transactional
//	public void allMainFinanceValues() {
//		System.out.println("AllMainValues" + ":" + "banco");
//		banco();
//		System.out.println("AllMainValues" + ":" + "operacaoFinanceira");
//		operacaoFinanceira();
//		System.out.println("AllMainValues" + ":" + "operacaoFinanceiraTarifa");
//		operacaoFinanceiraTarifa();
////		System.out.println("AllMainValues" + ":" + "calculadoraParametrosTeste");
////		calculadoraParametrosTeste();
//
//	}
//
//	@Inject
//	private LogradouroDao logradouroDao;
//
//	@Transactional
//	public void logradouro() {
//		try {
//			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/logradouro");
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String logradouro = br.readLine();
//
//			while (logradouro != null) {
//
//				logradouroDao.adiciona(new Logradouro(logradouro));
//
//				logradouro = br.readLine();
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Inject
//	private TipoTelefoneDao tipoTelefoneDao;
//
//	@Transactional
//	public void tipoTelefone() {
//		try {
//			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/tipoTelefone");
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String tipoTelefone = br.readLine();
//
//			while (tipoTelefone != null) {
//
//				tipoTelefoneDao.adiciona(new TipoTelefone(tipoTelefone));
//
//				tipoTelefone = br.readLine();
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Inject
//	private TipoEnderecoDao tipoEnderecoDao;
//
//	@Transactional
//	public void tipoEndereco() {
//		try {
//			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/tipoEndereco");
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String tipoEndereco = br.readLine();
//
//			while (tipoEndereco != null) {
//
//				tipoEnderecoDao.adiciona(new TipoEndereco(tipoEndereco));
//
//				tipoEndereco = br.readLine();
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Inject
//	private PaesciDao paesciDao;
//
//	@Transactional
//	public void paesci() {
//		System.out.println("Entrou tb");
//		try {
//			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/paesci");
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String s = br.readLine();
//			Integer first;
//			Integer second;
//			Integer third;
//			Integer lenght;
//			String pais;
//			String estado;
//			String cidade;
//
//			while (s != null) {
//				first = 0;
//				second = s.indexOf("-", first);
//				third = s.indexOf("-", second + 1);
//				lenght = s.length();
//				pais = s.substring(first, second);
//				estado = s.substring(second + 1, third);
//				cidade = s.substring(third + 1, lenght);
//				paesciDao.adiciona(new Paesci(pais, estado, cidade));
//				s = br.readLine();
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Inject
//	private TipoProfissionalDao tipoProfissionalDao;
//
//	@Transactional
//	public void tipoProfissional() {
//		try {
//			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/tipoProfissional");
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String tipoProfissionalString = br.readLine();
//			Integer i1;
//			Integer i2;
//			Integer i3;
//			Integer i4;
//			Integer lenght;
//			String tipo;
//			Double valorBrutoHora;
//			Double valorLiquidoHora;
//			boolean especialista = false;
//
//			while (tipoProfissionalString != null) {
//				i1 = 0;
//				i2 = tipoProfissionalString.indexOf(";", i1);
//				i3 = tipoProfissionalString.indexOf(";", i2 + 1);
//				i4 = tipoProfissionalString.indexOf(";", i3 + 1);
//				lenght = tipoProfissionalString.length();
//				tipo = tipoProfissionalString.substring(i1, i2);
//				valorBrutoHora = Double.parseDouble(tipoProfissionalString.substring(i2 + 1, i3));
//				valorLiquidoHora = Double.parseDouble(tipoProfissionalString.substring(i3 + 1, i4));
//
//				if (Integer.parseInt(tipoProfissionalString.substring(i4 + 1, lenght)) == 1) {
//					especialista = true;
//				} else {
//					especialista = false;
//				}
//
//				tipoProfissionalDao.adiciona(new EspecializacaoDoProfissional(tipo, valorBrutoHora, valorLiquidoHora, especialista));
//
//				tipoProfissionalString = br.readLine();
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	//
//	@Inject
//	private ProfissionalDao profissionalDao;
//
//	@Transactional
//	public void profissional() {
//		try {
//			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/profissionais");
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String profissionalString = br.readLine();
//
//			String nome;
//			String sobrenome;
//			String email;
//			Long tipoProfissionalId;
//			Integer i1 = 0;
//			Integer i2;
//			Integer i3;
//			Integer i4;
//			Integer lenght;
//
//			while (profissionalString != null) {
//				i2 = profissionalString.indexOf(" ", 0);
//				i3 = profissionalString.indexOf(";", i2 + 1);
//				i4 = profissionalString.indexOf(";", i3 + 1);
//				lenght = profissionalString.length();
//				nome = (profissionalString.substring(i1, i2));
//				sobrenome = (profissionalString.substring(i2 + 1, i3));
//				tipoProfissionalId = (Long.parseLong(profissionalString.substring(i3 + 1, i4)));
//				email = (profissionalString.substring(i4 + 1, lenght));
//				EspecializacaoDoProfissional tipoProfissional = tipoProfissionalDao.buscaPorId(tipoProfissionalId);
//				Profissional profissional = new Profissional(tipoProfissional);
//				Usuario usuarioProfissional = new Usuario(nome, sobrenome, email);
//				usuarioProfissional = usuarioDao.adicionaVolta(usuarioProfissional);
//				profissional.setUsuario(usuarioProfissional);
//				usuarioProfissional.setProfissional(profissional);
//				profissionalDao.adiciona(profissional);
//
//				profissionalString = br.readLine();
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//

//
//	@Inject
//	private TipoSociaDao tipoSociaDao;
//
//	@Transactional
//	public void tipoSocia() {
//		try {
//			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/tipoSocia");
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String tipoSociaString = br.readLine();
//			Integer i1;
//			Integer i2;
//			Integer i3;
//			Integer lenght;
//			String tipo;
//			Double valorBrutoHora;
//			Double valorLiquidoHora;
//
//			while (tipoSociaString != null) {
//				i1 = 0;
//				i2 = tipoSociaString.indexOf(";", i1);
//				i3 = tipoSociaString.indexOf(";", i2 + 1);
//				lenght = tipoSociaString.length();
//				tipo = tipoSociaString.substring(i1, i2);
//				valorBrutoHora = Double.parseDouble(tipoSociaString.substring(i2 + 1, i3));
//				valorLiquidoHora = Double.parseDouble(tipoSociaString.substring(i3 + 1, lenght));
//				tipoSociaDao.adiciona(new TipoSocia(tipo, valorBrutoHora, valorLiquidoHora));
//				tipoSociaString = br.readLine();
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	//
//	@Inject
//	private SociaDao sociaDao;
//
//	@Transactional
//	public void socia() {
//		try {
//			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/socias");
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String sociaString = br.readLine();
//
//			String nome;
//			String sobrenome;
//			String email;
//			Long tipo1SociaId;
//			Long tipo2SociaId;
//			Integer i1;
//			Integer i2;
//			Integer i3;
//			Integer i4;
//			Integer i5;
//			Integer lenght;
//			//
//			while (sociaString != null) {
//				i1 = 0;
//				i2 = sociaString.indexOf(";", i1 + 1);
//				i3 = sociaString.indexOf(";", i2 + 1);
//				i4 = sociaString.indexOf(";", i3 + 1);
//				i5 = sociaString.indexOf(";", i4 + 1);
//				lenght = sociaString.length();
//				nome = (sociaString.substring(i1, i2));
//				sobrenome = (sociaString.substring(i2 + 1, i3));
//				tipo1SociaId = (Long.parseLong(sociaString.substring(i3 + 1, i4)));
//				tipo2SociaId = (Long.parseLong(sociaString.substring(i4 + 1, i5)));
//				email = (sociaString.substring(i5, lenght));
//				Usuario usuarioSocia = new Usuario(nome, sobrenome, email);
//				usuarioSocia = usuarioDao.adicionaVolta(usuarioSocia);
//
//				TipoSocia tipoSocia1 = tipoSociaDao.buscaPorId(tipo1SociaId);
//				TipoSocia tipoSocia2 = tipoSociaDao.buscaPorId(tipo2SociaId);
//				Socia socia = new Socia();
//				socia = sociaDao.adicionaVolta(socia);
//				usuarioSocia.setSocia(socia);
//				socia.getTiposSocias().add(tipoSocia1);
//				socia.getTiposSocias().add(tipoSocia2);
//				socia.setUsuario(usuarioSocia);
//				sociaString = br.readLine();
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Transactional
//	public Usuario adicionaUsuarioComNomeSobrenomeEmail(String nome, String sobrenome, String email) {
//		Usuario usuario = new Usuario(nome, sobrenome, email);
//		return usuarioDao.adicionaVolta(usuario);
//	}
//
//	@Inject
//	ResponsavelFinanceiroDao responsavelFinanceiroDao;
//	@Inject
//	PacienteDao pacienteDao;
//	@Inject
//	PacoteDao pacoteDao;
//
//	@Transactional
//	public void responsavelFinanceiroPaciente() {
//		try {
//			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/respfinanceiropaciente");
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String linha = br.readLine();
//			
//			Long id;
//			String nomePai;
//			String sobrenomePai;
//			String emailPai;
//			String nomeFilho;
//			String sobrenomeFilho;
//			String emailFilho;
//			Integer i1;
//			Integer i2;
//			Integer i3;
//			Integer i4;
//			Integer i5;
//			Integer i6;
//			Integer i7;
//			Integer lenght;
//
//			while (linha != null) {
//				i1 = 0;
//				System.out.println("i1");
//				i2 = linha.indexOf(";", i1 + 1);
//				System.out.println("i2-" + i2);
//				i3 = linha.indexOf(" ", i2 + 1);
//				System.out.println("i3-" + i3);
//				i4 = linha.indexOf(";", i3 + 1);
//				System.out.println("i4-" + i4);
//				i5 = linha.indexOf(" ", i4 + 1);
//				System.out.println("i5-" + i5);
//				i6 = linha.indexOf(";", i5 + 1);
//				System.out.println("i6-" + i6);
//				i7 = linha.indexOf(";", i6 + 1);
//				System.out.println("i7-" + i7);
//				lenght = linha.length();
//				System.out.println("i8-" + lenght);
//				id = (Long.parseLong(linha.substring(i1, i2)));
//				System.out.println("i0");
//				nomePai = linha.substring(i2 + 1, i3);
//				System.out.println("ia");
//				sobrenomePai = linha.substring(i3 + 1, i4);
//				System.out.println("ib");
//				nomeFilho = linha.substring(i4 + 1, i5);
//				System.out.println("ic");
//				sobrenomeFilho = linha.substring(i5 + 1, i6);
//				System.out.println("id");
//				emailPai = linha.substring(i6 + 1, i7);
//				System.out.println("ie");
//				emailFilho = linha.substring(i7 + 1, lenght);
//				System.out.println("if");
//
//				// Usuario pai = adicionaUsuarioComNomeSobrenomeEmail(nomePai,sobrenomePai, emailPai);
//				// Usuario filho = adicionaUsuarioComNomeSobrenomeEmail(nomeFilho,sobrenomeFilho, emailFilho);
//
//				// ResponsavelFinanceiro responsavelFinanceiro = responsavelFinanceiroDao.adicionaVolta(new ResponsavelFinanceiro(pai));
//				// System.out.println("1-" + responsavelFinanceiro);
//				// pai.setResponsavelFinanceiro(responsavelFinanceiro);
//				// System.out.println("2-" + pai);
//				// usuarioDao.atualiza(pai);
//				// System.out.println("3-" + pai);
//				//
//				// Paciente paciente = pacienteDao.adicionaVolta(new Paciente(filho));
//				// System.out.println("4-" + paciente);
//				// filho.setPaciente(paciente);
//				// System.out.println("5-" + filho);
//				// usuarioDao.atualiza(filho);
//				// System.out.println("6-" + filho);
//				//
//				// Pacote pacote = pacoteDao.adicionaVolta(new Pacote(responsavelFinanceiro, paciente));
//				// System.out.println("6-" + pacote);
//				// System.out.println("7-" + responsavelFinanceiro);
//				// responsavelFinanceiroDao.atualiza(responsavelFinanceiro);
//				// System.out.println("8-" + responsavelFinanceiro);
//				//
//				// System.out.println("9-" + paciente);
//				// pacienteDao.atualiza(paciente);
//				// System.out.println("10-" + paciente);
//				// System.out.println("11-" + pacote);
//				//
//
//				// responsavelFinanceiro.getPacotes().add(pacote);
//				//// System.out.println("12-" + responsavelFinanceiro);
//				// responsavelFinanceiroDao.atualiza(responsavelFinanceiro);
//				//// System.out.println("13-" + responsavelFinanceiro);
//				// paciente.getPacotes().add(pacote);
//				//// System.out.println("14-" + paciente);
//				// pacienteDao.atualiza(paciente);
//				//// System.out.println("15-" + paciente);
//				// Pacote pacote = pacoteDao.adicionaVolta(new Pacote(responsavelFinanceiro, paciente));
//
//				// Opcao 2
//				Usuario pai = new Usuario(nomePai, sobrenomePai, emailPai);
//				Usuario filho = new Usuario(nomeFilho, sobrenomeFilho, emailFilho);
//				ResponsavelFinanceiro responsavelFinanceiro = new ResponsavelFinanceiro(pai);
//				Paciente paciente = new Paciente(filho);
//				// pai.setResponsavelFinanceiro(responsavelFinanceiro);
//				// filho.setPaciente(paciente);
//				Pacote pacote = new Pacote(responsavelFinanceiro, paciente);
//				responsavelFinanceiroDao.adiciona(responsavelFinanceiro);
//				pacienteDao.adiciona(paciente);
//				usuarioDao.adiciona(pai);
//				usuarioDao.adiciona(filho);
//				pacoteDao.adiciona(pacote);
//
//				System.out.println(pacote.getPaciente());
//				for (Pacote pacotu : paciente.getPacotes()) {
//					System.out.println(pacotu);
//				}
//				paciente.getPacotes().forEach(n -> System.out.println(n));
//
//				System.out.println(pacote.getResponsavelFinanceiro());
//				responsavelFinanceiro.getPacotes().forEach(n -> System.out.println(n));
//				System.out.println(pai.getResponsavelFinanceiro());
//				System.out.println(responsavelFinanceiro.getUsuario());
//				System.out.println(filho.getPaciente());
//				System.out.println(paciente.getUsuario());
//
//				linha = br.readLine();
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Transactional
//	public void paciente() {
//
//	}
//
//	@Inject
//	AtendimentoPadraoDao atendimentoPadraoDao;
//
//	@Transactional
//	public void atendimentoPadrao() {
//		try {
//			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/pacotePadrao");
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String linha = br.readLine();
//
//			String nomePacote;
//			Integer quantidadeAtendimentosMensaisSupervisao;
//			Integer quantidadeAtendimentosMensaisTerapiaMaster;
//			Integer quantidadeAtendimentosMensaisTerapiaFamiliar;
//			// Integer quantidadeAtendimentosMensaisPsicologa;
//			// Integer quantidadeAtendimentosMensaisPsicologaEspecialista;
//
//			Integer i1;
//			Integer i2;
//			Integer i3;
//			Integer i4;
//			Integer lenght;
//
//			while (linha != null) {
//				i1 = 0;
//				i2 = linha.indexOf(";", i1 + 1);
//				i3 = linha.indexOf(";", i2 + 1);
//				i4 = linha.indexOf(";", i3 + 1);
//				lenght = linha.length();
//				System.out.println("lenght-" + lenght);
//				nomePacote = linha.substring(i1, i2);
//				System.out.println("nomePacote");
//				quantidadeAtendimentosMensaisSupervisao = Integer.parseInt(linha.substring(i2 + 1, i3));
//				System.out.println("quantidadeAtendimentosMensaisSupervisao" + ":" + quantidadeAtendimentosMensaisSupervisao);
//				quantidadeAtendimentosMensaisTerapiaMaster = Integer.parseInt(linha.substring(i3 + 1, i4));
//				System.out.println("quantidadeAtendimentosMensaisTerapeuta" + ":" + quantidadeAtendimentosMensaisTerapiaMaster);
//				quantidadeAtendimentosMensaisTerapiaFamiliar = Integer.parseInt(linha.substring(i4 + 1, lenght));
//				System.out.println("quantidadeAtendimentosMensaisTerapiaFamiliar" + ":" + quantidadeAtendimentosMensaisTerapiaFamiliar);
//				// quantidadeAtendimentosMensaisPsicologa = Integer.parseInt(linha.substring(i5 + 1, i6));
//				// System.out.println("quantidadeAtendimentosMensaisPsicologa" + ":" + quantidadeAtendimentosMensaisPsicologa);
//				// quantidadeAtendimentosMensaisPsicologaEspecialista = Integer.parseInt(linha.substring(i6 + 1, lenght));
//				// System.out.println("quantidadeAtendimentosMensaisPsicologaEspecialista" + ":" + quantidadeAtendimentosMensaisPsicologaEspecialista);
//
//				Pacote pacote = new Pacote();
//				pacote = pacoteDao.adicionaVolta(pacote);
//				pacote.setEhPacotePadrao(true);
//				pacote.setNome(nomePacote);
//
//				TipoSocia tipoSociaSupervisao = tipoSociaDao.buscaPorTipo("Supervisão");
//				AtendimentoPadrao supervisao = new AtendimentoPadrao(quantidadeAtendimentosMensaisSupervisao, tipoSociaSupervisao, 0.0, true);
//				supervisao = atendimentoPadraoDao.adicionaVolta(supervisao);
//				supervisao.setPacote(pacote);
//				pacote.getAtendimentosPadrao().add(supervisao);
//
//				TipoSocia tipoSociaTerapiaMaster = tipoSociaDao.buscaPorTipo("Terapia Master");
//				AtendimentoPadrao terapiaMaster = new AtendimentoPadrao(quantidadeAtendimentosMensaisTerapiaMaster, tipoSociaTerapiaMaster, 0.0, true);
//				terapiaMaster = atendimentoPadraoDao.adicionaVolta(terapiaMaster);
//				terapiaMaster.setPacote(pacote);
//				pacote.getAtendimentosPadrao().add(terapiaMaster);
//
//				TipoSocia tipoSociaTerapiaFamiliar = tipoSociaDao.buscaPorTipo("Terapia Familiar");
//				AtendimentoPadrao terapiaFamiliar = new AtendimentoPadrao(quantidadeAtendimentosMensaisTerapiaFamiliar, tipoSociaTerapiaFamiliar, 0.0, true);
//				terapiaFamiliar = atendimentoPadraoDao.adicionaVolta(terapiaFamiliar);
//				terapiaFamiliar.setPacote(pacote);
//				pacote.getAtendimentosPadrao().add(terapiaFamiliar);
//
//				// pacoteDao.adiciona(pacote);
//				// pacote.getAtendimentosPadrao().forEach(atendimentoPadrao -> atendimentoPadrao.setPacote(final pacote));
//
//				linha = br.readLine();
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void teste() {
////		String tipoProfissional = "Fisioterapeuta";
////		List<Usuario> listaUsuarioComTipoProfissionalEEspecialidade = usuarioDao.listaUsuarioComTipoProfissionalEEspecialidade(tipoProfissional, false);
////		listaUsuarioComTipoProfissionalEEspecialidade.forEach(Usuario -> System.out.println(Usuario));
////		System.out.println("paciente - 1");
////		Paciente pac = pacienteDao.buscaPorId(1L);
////		System.out.println("paciente - 2");
////		pac = pacienteDao.buscaPorId(1L);
////		System.out.println("Tarifa Operacional - 1");
////		TarifaOperacaoFinanceira tar = tarifaOperacaoFinanceiraDao.buscaPorId(1L);
////		System.out.println("Tarifa Operacional - 2");
////		tar = tarifaOperacaoFinanceiraDao.buscaPorId(1L);
//		
//		System.out.println("teste - 1-LISTAR");
//		tarifaOperacaoFinanceiraDao.listarTarifaOperacaoFinanceira();
//		System.out.println("teste - 2-LISTAR");
//		tarifaOperacaoFinanceiraDao.listarTarifaOperacaoFinanceira();
//		System.out.println("teste - 3-LISTAR");
//		tarifaOperacaoFinanceiraDao.listarTarifaOperacaoFinanceira();
//		System.out.println("teste - 11");
//		ColetorTarifaOperacaoFinanceira coletor = coletorTarifaDao.getCacaio();
//		System.out.println("teste - 1");
//		tarifaOperacaoFinanceiraDao.tarifaFixaInternaEntreSubContasIugu(coletor);
//		System.out.println("teste - 2");
//		tarifaOperacaoFinanceiraDao.tarifaFixaInternaEntreSubContasIugu(coletor);
//		System.out.println("teste - 3");
//		tarifaOperacaoFinanceiraDao.tarifaFixaInternaEntreSubContasIugu(coletor);
//		System.out.println("teste - 44");
//		coletor = coletorTarifaDao.getImposto();
//		System.out.println("teste - 4");
//		tarifaOperacaoFinanceiraDao.tarifaFixaInternaEntreSubContasIugu(coletor);
//		System.out.println("teste - 5");
//		tarifaOperacaoFinanceiraDao.tarifaFixaInternaEntreSubContasIugu(coletor);
//		System.out.println("teste - 6");
//		tarifaOperacaoFinanceiraDao.tarifaFixaInternaEntreSubContasIugu(coletor);
//		
//		
//	}
//
//	@Inject
//	private BancoDao bancoDao;
//
//	@Transactional
//	public void banco() {
//		try {
//			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/banco");
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String linha = br.readLine();
//
//			String codigoCompensacao;
//			String nome;
//			String email;
//			Integer i1;
//			Integer i2;
//			Integer i3;
//			Integer lenght;
//			Banco banco;
//
//			while (linha != null) {
//				i1 = 0;
//				i2 = linha.indexOf(";", i1 + 1);
//				i3 = linha.indexOf(";", i2 + 1);
//				lenght = linha.length();
//
//				codigoCompensacao = (linha.substring(i1, i2));
//				nome = (linha.substring(i2 + 1, i3));
//				email = (linha.substring(i3 + 1, lenght));
//				banco = new Banco(nome, codigoCompensacao, email);
//				bancoDao.adiciona(banco);
//
//				linha = br.readLine();
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Inject
//	private OperadorFinanceiroDao operadorFinanceiroDao;
//	@Inject
//	private FormaTransferenciaOperacaoFinanceiraDao formaTransferenciaOperacaoFinanceiraDao;
//	@Inject
//	private TipoContaOperacaoFinanceiraDao tipoContaOperacaoFinanceiraDao;
//	@Inject
//	private TipoTarifaOperacaoFinanceiraDao tipoTarifaOperacaoFinanceiraDao;
//	@Inject
//	private ColetorTarifaOperacaoFinanceiraDao coletorTarifaOperacaoFinanceiraDao;
//	@Inject
//	private TarifaOperacaoFinanceiraDao tarifaOperacaoFinanceiraDao;
//
//	@Transactional
//	public void operacaoFinanceira() {
//		try {
//			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/operacaoFinanceira");
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String linha = br.readLine();
//
//			Integer pos1 = 0;
//			Integer pos2 = linha.indexOf(";", 0);
//			Integer total = Integer.parseInt(linha.substring(pos1, pos2));
//			String nome;
//			OperadorFinanceiro operadorFinanceiro;
//			for (int i = 0; i < total; i++) {
//				pos1 = pos2 + 1;
//				pos2 = linha.indexOf(";", pos1);
//				nome = linha.substring(pos1, pos2);
//				operadorFinanceiro = new OperadorFinanceiro(nome);
//				if (nome.equals("Iugu")) {
//					operadorFinanceiro.setOperadorFinanceiroPadrao(true);
//				}
//				operadorFinanceiroDao.adiciona(operadorFinanceiro);
//			}
//			linha = br.readLine();
//
//			pos1 = 0;
//			pos2 = linha.indexOf(";", 0);
//			total = Integer.parseInt(linha.substring(pos1, pos2));
//			FormaTransferenciaOperacaoFinanceira formaTransferenciaOperacaoFinanceira;
//			for (int i = 0; i < total; i++) {
//				pos1 = pos2 + 1;
//				pos2 = linha.indexOf(";", pos1);
//				nome = linha.substring(pos1, pos2);
//				formaTransferenciaOperacaoFinanceira = new FormaTransferenciaOperacaoFinanceira(nome);
//				formaTransferenciaOperacaoFinanceiraDao.adiciona(formaTransferenciaOperacaoFinanceira);
//			}
//			linha = br.readLine();
//
//			pos1 = 0;
//			pos2 = linha.indexOf(";", 0);
//			total = Integer.parseInt(linha.substring(pos1, pos2));
//			TipoContaOperacaoFinanceira tipoContaOperacaoFinanceira;
//			for (int i = 0; i < total; i++) {
//				pos1 = pos2 + 1;
//				pos2 = linha.indexOf(";", pos1);
//				nome = linha.substring(pos1, pos2);
//				tipoContaOperacaoFinanceira = new TipoContaOperacaoFinanceira(nome);
//				tipoContaOperacaoFinanceiraDao.adiciona(tipoContaOperacaoFinanceira);
//			}
//			linha = br.readLine();
//
//			pos1 = 0;
//			pos2 = linha.indexOf(";", 0);
//			total = Integer.parseInt(linha.substring(pos1, pos2));
//			TipoTarifaOperacaoFinanceira tipoTarifaOperacaoFinanceira;
//			for (int i = 0; i < total; i++) {
//				pos1 = pos2 + 1;
//				pos2 = linha.indexOf(";", pos1);
//				nome = linha.substring(pos1, pos2);
//				tipoTarifaOperacaoFinanceira = new TipoTarifaOperacaoFinanceira(nome);
//				tipoTarifaOperacaoFinanceiraDao.adiciona(tipoTarifaOperacaoFinanceira);
//			}
//			linha = br.readLine();
//
//			pos1 = 0;
//			pos2 = linha.indexOf(";", 0);
//			total = Integer.parseInt(linha.substring(pos1, pos2));
//			ColetorTarifaOperacaoFinanceira coletorTarifaOperacaoFinanceira;
//			for (int i = 0; i < total; i++) {
//				pos1 = pos2 + 1;
//				pos2 = linha.indexOf(";", pos1);
//				nome = linha.substring(pos1, pos2);
//				coletorTarifaOperacaoFinanceira = new ColetorTarifaOperacaoFinanceira(nome);
//				coletorTarifaOperacaoFinanceiraDao.adiciona(coletorTarifaOperacaoFinanceira);
//				System.out.println("AQUI1");
//			}
//			System.out.println("AQUI2");
//			linha = br.readLine();
//
//			br.close();
//		} catch (
//
//		FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Transactional
//	public void operacaoFinanceiraTarifa() {
//		try {
//			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/operacaoFinanceiraTarifa");
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String linha = br.readLine();
//			Integer i1;
//			Integer i2;
//			Integer i3;
//			Integer i4;
//			Integer i5;
//			Integer i6;
//			Integer i7;
//			Integer i8;
//			Integer lenght;
//			OperadorFinanceiro operadorFinanceiro;
//			FormaTransferenciaOperacaoFinanceira formaTransferenciaOperacaoFinanceira;
//			TipoTarifaOperacaoFinanceira tipoTarifaOperacaoFinanceira;
//
//			TipoContaOperacaoFinanceira tipoContaOrigem;
//			TipoContaOperacaoFinanceira tipoContaDestino;
//			// TipoTarifaOperacaoFinanceira tipoTarifaOperacaoFinanceira;
//			ColetorTarifaOperacaoFinanceira imposto;
//			ColetorTarifaOperacaoFinanceira iugu;
//			ColetorTarifaOperacaoFinanceira cacaio;
//			BigDecimal tarifaImpostoBigDecimal;
//			BigDecimal tarifaIuguBigDecimal;
//			BigDecimal tarifaCacaioBigDecimal;
//
//			while (linha != null) {
//				while (linha.indexOf(";", 0) == 0) {
//					System.out.println("linha.indexOf(\";\", 0)" + ":" + linha.indexOf(";", 0));
//					linha = br.readLine();
//				}
//				i1 = 0;
//				i2 = linha.indexOf(";", i1 + 1);
//				i3 = linha.indexOf(";", i2 + 1);
//				i4 = linha.indexOf(";", i3 + 1);
//				i5 = linha.indexOf(";", i4 + 1);
//				i6 = linha.indexOf(";", i5 + 1);
//				i7 = linha.indexOf(";", i6 + 1);
//				i8 = linha.indexOf(";", i7 + 1);
//				lenght = linha.length();
//
//				String operadorFinanceiroString = linha.substring(i1, i2);
//				System.out.println("operadorFinanceiroString" + ": " + operadorFinanceiroString);
//				operadorFinanceiro = operadorFinanceiroDao.getOperadorByOperadorString(operadorFinanceiroString);
//
//				String formaTransferenciaOperacaoFinanceiraString = linha.substring(i2 + 1, i3);
//				System.out.println("formaTransferenciaOperacaoFinanceiraString" + ": " + formaTransferenciaOperacaoFinanceiraString);
//				formaTransferenciaOperacaoFinanceira = formaTransferenciaOperacaoFinanceiraDao.getFormaByFormaString(formaTransferenciaOperacaoFinanceiraString);
//
//				String tipoContaOrigemString = linha.substring(i3 + 1, i4);
//				System.out.println("tipoContaOrigemString" + ": " + tipoContaOrigemString);
//				tipoContaOrigem = tipoContaOperacaoFinanceiraDao.getTipoByTipoString(tipoContaOrigemString);
//
//				String tipoContaDestinoString = linha.substring(i4 + 1, i5);
//				System.out.println("tipoContaDestinoStringo" + ": " + tipoContaDestinoString);
//				tipoContaDestino = tipoContaOperacaoFinanceiraDao.getTipoByTipoString(tipoContaDestinoString);
//
//				String tipoTarifaOperacaoFinanceiraString = linha.substring(i5 + 1, i6);
//				System.out.println("tipoTarifaOperacaoFinanceiraString" + ": " + tipoTarifaOperacaoFinanceiraString);
//				tipoTarifaOperacaoFinanceira = tipoTarifaOperacaoFinanceiraDao.getTipoByTipoString(tipoTarifaOperacaoFinanceiraString);
//
//				tarifaImpostoBigDecimal = new BigDecimal(linha.substring(i6 + 1, i7));
//				tarifaIuguBigDecimal = new BigDecimal(linha.substring(i7 + 1, i8));
//				tarifaCacaioBigDecimal = new BigDecimal(linha.substring(i8 + 1, lenght));
//
//				imposto = coletorTarifaOperacaoFinanceiraDao.getColetorByColetorString("Imposto");
//				iugu = coletorTarifaOperacaoFinanceiraDao.getColetorByColetorString("Iugu");
//				cacaio = coletorTarifaOperacaoFinanceiraDao.getColetorByColetorString("Cacaio");
//
//				TarifaOperacaoFinanceira tarifaImposto = new TarifaOperacaoFinanceira();
//				tarifaImposto.setOperadorFinanceiro(operadorFinanceiro);
//				tarifaImposto.setFormaTransferencia(formaTransferenciaOperacaoFinanceira);
//				tarifaImposto.setTipoContaOrigem(tipoContaOrigem);
//				tarifaImposto.setTipoContaDestino(tipoContaDestino);
////				tarifaImposto.setTipoTarifa(tipoTarifaOperacaoFinanceira);
//				tarifaImposto.setColetorTarifa(imposto);
//				tarifaImposto.setFixa(tarifaImpostoBigDecimal);
//				System.out.println("Imposto" + ": " + tarifaImposto);
//				tarifaOperacaoFinanceiraDao.adiciona(tarifaImposto);
//
//				TarifaOperacaoFinanceira tarifaIugu  = new TarifaOperacaoFinanceira();
//				tarifaIugu.setOperadorFinanceiro(operadorFinanceiro);
//				tarifaIugu.setFormaTransferencia(formaTransferenciaOperacaoFinanceira);
//				tarifaIugu.setTipoContaOrigem(tipoContaOrigem);
//				tarifaIugu.setTipoContaDestino(tipoContaDestino);
////				tarifaIugu.setTipoTarifa(tipoTarifaOperacaoFinanceira);
//				tarifaIugu.setColetorTarifa(iugu);
//				tarifaIugu.setFixa(tarifaIuguBigDecimal);
//				System.out.println("Iugu" + ": " + tarifaIugu);
//				tarifaOperacaoFinanceiraDao.adiciona(tarifaIugu);
//
//				TarifaOperacaoFinanceira tarifaCacaio = new TarifaOperacaoFinanceira();
//				tarifaCacaio.setOperadorFinanceiro(operadorFinanceiro);
//				tarifaCacaio.setFormaTransferencia(formaTransferenciaOperacaoFinanceira);
//				tarifaCacaio.setTipoContaOrigem(tipoContaOrigem);
//				tarifaCacaio.setTipoContaDestino(tipoContaDestino);
////				tarifaCacaio.setTipoTarifa(tipoTarifaOperacaoFinanceira);
//				tarifaCacaio.setColetorTarifa(cacaio);
//				tarifaCacaio.setFixa(tarifaCacaioBigDecimal);
//				System.out.println("Cacaio" + ": " + tarifaCacaio);
//				tarifaOperacaoFinanceiraDao.adiciona(tarifaCacaio);
//
//				linha = br.readLine();
//				
//				i1 = 0;
//				i2 = linha.indexOf(";", i1 + 1);
//				i3 = linha.indexOf(";", i2 + 1);
//				i4 = linha.indexOf(";", i3 + 1);
//				i5 = linha.indexOf(";", i4 + 1);
//				i6 = linha.indexOf(";", i5 + 1);
//				i7 = linha.indexOf(";", i6 + 1);
//				i8 = linha.indexOf(";", i7 + 1);
//				lenght = linha.length();
//				tarifaImpostoBigDecimal = new BigDecimal(linha.substring(i6 + 1, i7));
//				tarifaIuguBigDecimal = new BigDecimal(linha.substring(i7 + 1, i8));
//				tarifaCacaioBigDecimal = new BigDecimal(linha.substring(i8 + 1, lenght));
//				tarifaImposto.setPercentual(tarifaImpostoBigDecimal);
//				tarifaIugu.setPercentual(tarifaIuguBigDecimal);
//				tarifaCacaio.setPercentual(tarifaCacaioBigDecimal);
//				
//				linha = br.readLine();
//				
//			} // fim do while
//
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Inject
//	CalculadoraParametro calculadora;
//	@Inject
//	private ColetorTarifaOperacaoFinanceiraDao coletorTarifaDao;
//
//	// @Transactional
//	public void calculadoraParametrosTeste() {
//		System.out.println(calculadora.getOperadorFinanceiro());
//
////		// Tarifa Entre Subcontas Iugu
////		System.out.println("todasTarifasFixaInternaEntreSubContasIugu" + ": " + calculadora.todasTarifasFixaInternaEntreSubContasIugu());
////		System.out.println("tarifaFixaInternaEntreSubContasIugu.getCacaio" + ": " + calculadora.tarifaFixaInternaEntreSubContasIugu(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaFixaInternaEntreSubContasIugu.getImposto" + ": " + calculadora.tarifaFixaInternaEntreSubContasIugu(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaFixaInternaEntreSubContasIugu.getIugu" + ": " + calculadora.tarifaFixaInternaEntreSubContasIugu(coletorTarifaDao.getIugu()));
////
////		System.out.println("todasTarifasPorcentagemInternaEntreSubContasIugu" + ": " + calculadora.todasTarifasPorcentagemInternaEntreSubContasIugu());
////		System.out.println("tarifaPorcentagemInternaEntreSubContasIugu.getCacaio" + ": " + calculadora.tarifaPorcentagemInternaEntreSubContasIugu(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaPorcentagemInternaEntreSubContasIugu.getImposto" + ": " + calculadora.tarifaPorcentagemInternaEntreSubContasIugu(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaPorcentagemInternaEntreSubContasIugu.getIugu" + ": " + calculadora.tarifaPorcentagemInternaEntreSubContasIugu(coletorTarifaDao.getIugu()));
////
////		// Tarifa Entre Conta Master e Subcontas Iugu
////		System.out.println("todasTarifasFixaInternaEntreMasterIuguESubcontaIugu" + ": " + calculadora.todasTarifasFixaInternaEntreMasterIuguESubcontaIugu());
////		System.out.println("tarifaFixaInternaEntreMasterIuguESubcontaIugu.getCacaio" + ": " + calculadora.tarifaFixaInternaEntreMasterIuguESubcontaIugu(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaFixaInternaEntreMasterIuguESubcontaIugu.getImposto" + ": " + calculadora.tarifaFixaInternaEntreMasterIuguESubcontaIugu(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaFixaInternaEntreMasterIuguESubcontaIugu.getIugu" + ": " + calculadora.tarifaFixaInternaEntreMasterIuguESubcontaIugu(coletorTarifaDao.getIugu()));
////
////		System.out.println("todasTarifasPorcentagemInternaEntreMasterIuguESubcontaIugu" + ": " + calculadora.todasTarifasPorcentagemInternaEntreMasterIuguESubcontaIugu());
////		System.out.println("tarifaPorcentagemInternaEntreMasterIuguESubcontaIugu.getCacaio" + ": " + calculadora.tarifaPorcentagemInternaEntreMasterIuguESubcontaIugu(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaPorcentagemInternaEntreMasterIuguESubcontaIugu.getImposto" + ": " + calculadora.tarifaPorcentagemInternaEntreMasterIuguESubcontaIugu(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaPorcentagemInternaEntreMasterIuguESubcontaIugu.getIugu" + ": " + calculadora.tarifaPorcentagemInternaEntreMasterIuguESubcontaIugu(coletorTarifaDao.getIugu()));
////
////		// Tarifa Entre Subconta e Conta Master Iugu
////		System.out.println("todasTarifasFixaInternaEntreSubcontaIuguEMasterIugu" + ": " + calculadora.todasTarifasFixaInternaEntreSubcontaIuguEMasterIugu());
////		System.out.println("tarifaFixaInternaEntreSubcontaIuguEMasterIugu.getCacaio" + ": " + calculadora.tarifaFixaInternaEntreSubcontaIuguEMasterIugu(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaFixaInternaEntreSubcontaIuguEMasterIugu.getImposto" + ": " + calculadora.tarifaFixaInternaEntreSubcontaIuguEMasterIugu(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaFixaInternaEntreSubcontaIuguEMasterIugu.getIugu" + ": " + calculadora.tarifaFixaInternaEntreSubcontaIuguEMasterIugu(coletorTarifaDao.getIugu()));
////
////		System.out.println("todasTarifasPorcentagemInternaEntreSubcontaIuguEMasterIugu" + ": " + calculadora.todasTarifasPorcentagemInternaEntreSubcontaIuguEMasterIugu());
////		System.out.println("tarifaPorcentagemInternaEntreSubcontaIuguEMasterIugu.getCacaio" + ": " + calculadora.tarifaPorcentagemInternaEntreSubcontaIuguEMasterIugu(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaPorcentagemInternaEntreSubcontaIuguEMasterIugu.getImposto" + ": " + calculadora.tarifaPorcentagemInternaEntreSubcontaIuguEMasterIugu(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaPorcentagemInternaEntreSubcontaIuguEMasterIugu.getIugu" + ": " + calculadora.tarifaPorcentagemInternaEntreSubcontaIuguEMasterIugu(coletorTarifaDao.getIugu()));
////
////		
////		// Tarifa de Saque Subconta - Conta Bancária
////		System.out.println("todasTarifasFixaSaqueSubcontaContaBancaria" + ": " + calculadora.todasTarifasFixaSaqueSubcontaContaBancaria());
////		System.out.println("tarifaFixaSaqueSubcontaContaBancaria.getCacaio" + ": " + calculadora.tarifaFixaSaqueSubcontaContaBancaria(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaFixaSaqueSubcontaContaBancaria.getImposto" + ": " + calculadora.tarifaFixaSaqueSubcontaContaBancaria(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaFixaSaqueSubcontaContaBancaria.getIugu" + ": " + calculadora.tarifaFixaSaqueSubcontaContaBancaria(coletorTarifaDao.getIugu()));
////
////		System.out.println("todasTarifasPorcentagemSaqueSubcontaContaBancaria" + ": " + calculadora.todasTarifasPorcentagemSaqueSubcontaContaBancaria());
////		System.out.println("tarifaPorcentagemSaqueSubcontaContaBancaria.getCacaio" + ": " + calculadora.tarifaPorcentagemSaqueSubcontaContaBancaria(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaPorcentagemSaqueSubcontaContaBancaria.getImposto" + ": " + calculadora.tarifaPorcentagemSaqueSubcontaContaBancaria(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaPorcentagemSaqueSubcontaContaBancaria.getIugu" + ": " + calculadora.tarifaPorcentagemSaqueSubcontaContaBancaria(coletorTarifaDao.getIugu()));
////
////		// Tarifa de Saque Conta Master - Conta Bancária
////		System.out.println("todasTarifasFixaSaqueContaMasterIuguContaBancaria" + ": " + calculadora.todasTarifasFixaSaqueContaMasterIuguContaBancaria());
////		System.out.println("tarifaFixaSaqueContaMasterIuguContaBancaria.getCacaio" + ": " + calculadora.tarifaFixaSaqueContaMasterIuguContaBancaria(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaFixaSaqueContaMasterIuguContaBancaria.getImposto" + ": " + calculadora.tarifaFixaSaqueContaMasterIuguContaBancaria(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaFixaSaqueContaMasterIuguContaBancaria.getIugu" + ": " + calculadora.tarifaFixaSaqueContaMasterIuguContaBancaria(coletorTarifaDao.getIugu()));
////
////		System.out.println("todasTarifasPorcentagemSaqueContaMasterIuguContaBancaria" + ": " + calculadora.todasTarifasPorcentagemSaqueContaMasterIuguContaBancaria());
////		System.out.println("tarifaPorcentagemSaqueContaMasterIuguContaBancaria.getCacaio" + ": " + calculadora.tarifaPorcentagemSaqueContaMasterIuguContaBancaria(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaPorcentagemSaqueContaMasterIuguContaBancaria.getImposto" + ": " + calculadora.tarifaPorcentagemSaqueContaMasterIuguContaBancaria(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaPorcentagemSaqueContaMasterIuguContaBancaria.getIugu" + ": " + calculadora.tarifaPorcentagemSaqueContaMasterIuguContaBancaria(coletorTarifaDao.getIugu()));
////
////		// Tarifa de Deposito na Conta Master Iugu - Cartão de Crédito
////		System.out.println("todasTarifasFixaDepositoCartaoDeCredito" + ": " + calculadora.todasTarifasFixaDepositoCartaoDeCredito());
////		System.out.println("tarifaFixaDepositoCartaoDeCredito.getCacaio" + ": " + calculadora.tarifaFixaDepositoCartaoDeCredito(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaFixaDepositoCartaoDeCredito.getImposto" + ": " + calculadora.tarifaFixaDepositoCartaoDeCredito(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaFixaDepositoCartaoDeCredito.getIugu" + ": " + calculadora.tarifaFixaDepositoCartaoDeCredito(coletorTarifaDao.getIugu()));
////
////		System.out.println("todasTarifasPorcentagemDepositoCartaoDeCredito" + ": " + calculadora.todasTarifasPorcentagemDepositoCartaoDeCredito());
////		System.out.println("tarifaPorcentagemDepositoCartaoDeCredito.getCacaio" + ": " + calculadora.tarifaPorcentagemDepositoCartaoDeCredito(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaPorcentagemDepositoCartaoDeCredito.getImposto" + ": " + calculadora.tarifaPorcentagemDepositoCartaoDeCredito(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaPorcentagemDepositoCartaoDeCredito.getIugu" + ": " + calculadora.tarifaPorcentagemDepositoCartaoDeCredito(coletorTarifaDao.getIugu()));
////
////		
////		// Tarifa de Deposito na Conta Master Iugu - Boleto
////		System.out.println("todasTarifasFixaDepositoBoleto" + ": " + calculadora.todasTarifasFixaDepositoBoleto());
////		System.out.println("tarifaFixaDepositoBoleto.getCacaio" + ": " + calculadora.tarifaFixaDepositoBoleto(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaFixamDepositoBoleto.getImposto" + ": " + calculadora.tarifaFixaDepositoBoleto(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaFixamDepositoBoleto.getIugu" + ": " + calculadora.tarifaFixaDepositoBoleto(coletorTarifaDao.getIugu()));
////
////		System.out.println("todasTarifasPorcentagemDepositoBoleto" + ": " + calculadora.todasTarifasPorcentagemDepositoBoleto());
////		System.out.println("tarifaPorcentagemDepositoBoleto.getCacaio" + ": " + calculadora.tarifaPorcentagemDepositoBoleto(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaPorcentagemDepositoBoleto.getImposto" + ": " + calculadora.tarifaPorcentagemDepositoBoleto(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaPorcentagemDepositoBoleto.getIugu" + ": " + calculadora.tarifaPorcentagemDepositoBoleto(coletorTarifaDao.getIugu()));
////		
////		
////		// Tarifa de Deposito na Conta Master Itau - Transferencia Bancaria
////		System.out.println("todasTarifasFixaDepositoContaBancariaNoItau" + ": " + calculadora.todasTarifasFixaDepositoContaBancariaNoItau());
////		System.out.println("tarifaFixaDepositoContaBancariaNoItau.getCacaio" + ": " + calculadora.tarifaFixaDepositoContaBancariaNoItau(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaFixaDepositoContaBancariaNoItau.getImposto" + ": " + calculadora.tarifaFixaDepositoContaBancariaNoItau(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaFixaDepositoContaBancariaNoItau.getIugu" + ": " + calculadora.tarifaFixaDepositoContaBancariaNoItau(coletorTarifaDao.getIugu()));
////
////		System.out.println("todasTarifasPorcentagemDepositoContaBancariaNoItau" + ": " + calculadora.todasTarifasPorcentagemDepositoContaBancariaNoItau());
////		System.out.println("tarifaPorcentagemDepositoContaBancariaNoItau.getCacaio" + ": " + calculadora.tarifaPorcentagemDepositoContaBancariaNoItau(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaPorcentagemDepositoContaBancariaNoItau.getImposto" + ": " + calculadora.tarifaPorcentagemDepositoContaBancariaNoItau(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaPorcentagemDepositoContaBancariaNoItau.getIugu" + ": " + calculadora.tarifaPorcentagemDepositoContaBancariaNoItau(coletorTarifaDao.getIugu()));		
////		
////		
////		
////		// Tarifa de Deposito na Conta Master Itau - Boleto
////		System.out.println("todasTarifasFixaDepositoBoletoNoItau" + ": " + calculadora.todasTarifasFixaDepositoBoletoNoItau());
////		System.out.println("tarifaFixaDepositoBoletoNoItau.getCacaio" + ": " + calculadora.tarifaFixaDepositoBoletoNoItau(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaFixaDepositoBoletoNoItau.getImposto" + ": " + calculadora.tarifaFixaDepositoBoletoNoItau(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaFixaDepositoBoletoNoItau.getIugu" + ": " + calculadora.tarifaFixaDepositoBoletoNoItau(coletorTarifaDao.getIugu()));
////
////		System.out.println("todasTarifasPorcentagemDepositoBoletoNoItau" + ": " + calculadora.todasTarifasPorcentagemDepositoBoletoNoItau());
////		System.out.println("tarifaPorcentagemDepositoBoletoNoItau.getCacaio" + ": " + calculadora.tarifaPorcentagemDepositoBoletoNoItau(coletorTarifaDao.getCacaio()));
////		System.out.println("tarifaPorcentagemDepositoBoletoNoItau.getImposto" + ": " + calculadora.tarifaPorcentagemDepositoBoletoNoItau(coletorTarifaDao.getImposto()));
////		System.out.println("tarifaPorcentagemDepositoBoletoNoItau.getIugu" + ": " + calculadora.tarifaPorcentagemDepositoBoletoNoItau(coletorTarifaDao.getIugu()));	
//	}
//
//	// @Inject
//	// private ParametroFormareDao parametroFormareDao;
//	//
//	// @Transactional
//	// public void parametro() {
//	// try {
//	// InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/parametros");
//	// InputStreamReader isr = new InputStreamReader(is);
//	// BufferedReader br = new BufferedReader(isr);
//	// String parametroString = br.readLine();
//	// Integer first;
//	// Integer second;
//	// Integer third;
//	// Integer lenght;
//	//
//	// while (parametroString != null) {
//	// ParametroFormare parametro = new ParametroFormare();
//	// first = 0;
//	// second = parametroString.indexOf(";", first);
//	// third = parametroString.indexOf(";", second + 1);
//	// lenght = parametroString.length();
//	// parametro.setNome(parametroString.substring(first, second));
//	// parametro.setValorPorcentagem(Double.parseDouble(parametroString.substring(second + 1, third)));
//	// parametro.setValorReais(Double.parseDouble(parametroString.substring(third + 1, lenght)));
//	// ParametroFormareDao.adiciona(parametro);
//	//
//	// parametroString = br.readLine();
//	// }
//	// br.close();
//	// } catch (FileNotFoundException e) {
//	// e.printStackTrace();
//	// } catch (IOException e) {
//	// e.printStackTrace();
//	// }
//	// 
//	@Inject @CalendarioQualifier(calendario = CalendarioDiasUteis.SEMSABADO_SEMDOMINGO_SEMFERIADO)
//	private CalendarioDao<?> calendarioComDiasUteisNaoContendoSabadoDomingoFeriadoDao;
////	CalendarioDao<CalendarioComDiasUteisNaoContendoSabadoDomingoFeriado> calendarioDao;
////	@Inject
////	private CalendarioComDiasUteisNaoContendoSabadoDomingoFeriadoDao calendarioComDiasUteisNaoContendoSabadoDomingoFeriadoDao;
//	@Inject @CalendarioQualifier(calendario = CalendarioDiasUteis.SEMSABADO_SEMDOMINGO)
//	private CalendarioDao<?> calendarioComDiasUteisNaoContendoSabadoDomingoDao;
//	@Inject @CalendarioQualifier(calendario = CalendarioDiasUteis.SEMDOMINGO_SEMFERIADO)
//	private CalendarioDao<?> calendarioComDiasUteisNaoContendoDomingoFeriadoDao;
//	@Inject @CalendarioQualifier(calendario = CalendarioDiasUteis.SEMDOMINGO)
//	private CalendarioDao<?> calendarioComDiasUteisNaoContendoDomingoDao;
////	
//	@Transactional
//	public void calendarios() {
//		LocalDate primeiroDia = LocalDate.of(2018, 1, 1);
//		LocalDate ultimoDia = LocalDate.of(2018, 12, 31);
//		
//		for(LocalDate data = primeiroDia; data.isBefore(ultimoDia.plusDays(1)); data = data.plusDays(1)) {
//			calendarioComDiasUteisNaoContendoSabadoDomingoFeriadoDao.adiciona(new CalendarioComDiasUteisNaoContendoSabadoDomingoFeriado(data));
//			calendarioComDiasUteisNaoContendoSabadoDomingoDao.adiciona(new CalendarioComDiasUteisNaoContendoSabadoDomingo(data));
//			calendarioComDiasUteisNaoContendoDomingoFeriadoDao.adiciona(new CalendarioComDiasUteisNaoContendoDomingoFeriado(data));
//			calendarioComDiasUteisNaoContendoDomingoDao.adiciona(new CalendarioComDiasUteisNaoContendoDomingo(data));
//		}
//		
//		try {
//			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/feriados2018");
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String linha = br.readLine();
//			
//			Integer dayOfMonth;
//			Integer month;
//			Integer year;
//			String name;
//			LocalDate localData;
//			Integer i1;
//			Integer i2;
//			Integer i3;
//			Integer i4;
//			Integer lenght;
//			
//			while (linha != null) {
//				i1 = 0;
//				System.out.println("i1-" + i1);
//				i2 = linha.indexOf("/", i1 + 1);
//				System.out.println("i2-" + i2);
//				i3 = linha.indexOf("/", i2 + 1);
//				System.out.println("i3-" + i3);
//				i4 = linha.indexOf(" ", i3 + 1);
//				System.out.println("i4-" + i4);
//				lenght = linha.length();
//				System.out.println("lenght-" + lenght);
//				
//				dayOfMonth = (Integer.parseInt(linha.substring(i1, i2)));
//				System.out.println("dayOfMonth-" + dayOfMonth);
//				month = (Integer.parseInt(linha.substring(i2+1, i3)));
//				System.out.println("month-" + month);
//				year = (Integer.parseInt(linha.substring(i3+1, i4)));
//				System.out.println("year-" + year);
//				name = linha.substring(i4 + 1, lenght);
//				System.out.println("name-" + name);
//				localData = LocalDate.of(year, month, dayOfMonth);
////				
//				Calendario data1 = calendarioComDiasUteisNaoContendoSabadoDomingoFeriadoDao.buscaData(localData);
//				data1.setDiaUtil(false);
//				data1.setNomeFeriado(name);
//				Calendario data2 = calendarioComDiasUteisNaoContendoDomingoFeriadoDao.buscaData(localData);
//				data2.setDiaUtil(false);
//				data2.setNomeFeriado(name);
//				
//				linha = br.readLine();
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
////		for(int i=1;i<13;i++) {
////			System.out.println("--------->getQdeSemanasMes["+i+"]:"+calendarioDao.getQdeSemanasMes(2018, i));
////		}
////		
////		for(int i=1;i<13;i++) {
////			System.out.println("--------->getQdeDiasUteisSemanaDoMes["+i+"]:"+calendarioDao.getTotalDiasUteisSemanaDoMes(2018, i, 1));
////		}
//	}
//
////	Long id;
////	String nomePai;
////	String sobrenomePai;
////	String emailPai;
////	String nomeFilho;
////	String sobrenomeFilho;
////	String emailFilho;
////	Integer i1;
////	Integer i2;
////	Integer i3;
////	Integer i4;
////	Integer i5;
////	Integer i6;
////	Integer i7;
////	Integer lenght;
////
////	while (linha != null) {
////		i1 = 0;
////		System.out.println("i1");
////		i2 = linha.indexOf(";", i1 + 1);
////		System.out.println("i2-" + i2);
////		i3 = linha.indexOf(" ", i2 + 1);
////		System.out.println("i3-" + i3);
////		i4 = linha.indexOf(";", i3 + 1);
////		System.out.println("i4-" + i4);
////		i5 = linha.indexOf(" ", i4 + 1);
////		System.out.println("i5-" + i5);
////		i6 = linha.indexOf(";", i5 + 1);
////		System.out.println("i6-" + i6);
////		i7 = linha.indexOf(";", i6 + 1);
////		System.out.println("i7-" + i7);
////		lenght = linha.length();
////		System.out.println("i8-" + lenght);
////		id = (Long.parseLong(linha.substring(i1, i2)));
////		System.out.println("i0");
////		nomePai = linha.substring(i2 + 1, i3);
////		System.out.println("ia");
////		sobrenomePai = linha.substring(i3 + 1, i4);
////		System.out.println("ib");
////		nomeFilho = linha.substring(i4 + 1, i5);
////		System.out.println("ic");
////		sobrenomeFilho = linha.substring(i5 + 1, i6);
////		System.out.println("id");
////		emailPai = linha.substring(i6 + 1, i7);
////		System.out.println("ie");
////		emailFilho = linha.substring(i7 + 1, lenght);
////		System.out.println("if");
//	
	
}
