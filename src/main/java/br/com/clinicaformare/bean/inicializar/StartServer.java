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

import br.com.clinicaformare.daos.AtendimentoPadraoDao;
import br.com.clinicaformare.daos.PacoteDao;
import br.com.clinicaformare.daos.ParametroDao;
import br.com.clinicaformare.daos.usuario.LogradouroDao;
import br.com.clinicaformare.daos.usuario.PacienteDao;
import br.com.clinicaformare.daos.usuario.PaesciDao;
import br.com.clinicaformare.daos.usuario.ProfissionalDao;
import br.com.clinicaformare.daos.usuario.ResponsavelFinanceiroDao;
import br.com.clinicaformare.daos.usuario.SociaDao;
import br.com.clinicaformare.daos.usuario.TipoEnderecoDao;
import br.com.clinicaformare.daos.usuario.TipoProfissionalDao;
import br.com.clinicaformare.daos.usuario.TipoTelefoneDao;
import br.com.clinicaformare.daos.usuario.UsuarioDao;
import br.com.clinicaformare.model.Parametro;
import br.com.clinicaformare.model.atendimento.AtendimentoPadrao;
import br.com.clinicaformare.model.atendimento.Pacote;
import br.com.clinicaformare.model.usuario.Paciente;
import br.com.clinicaformare.model.usuario.Profissional;
import br.com.clinicaformare.model.usuario.ResponsavelFinanceiro;
import br.com.clinicaformare.model.usuario.Socia;
import br.com.clinicaformare.model.usuario.TipoProfissional;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.model.usuario.telefone.TipoTelefone;
import br.com.clinicaformare.usuario.endereco.Logradouro;
import br.com.clinicaformare.usuario.endereco.Paesci;
import br.com.clinicaformare.usuario.endereco.TipoEndereco;

@Named
@RequestScoped
public class StartServer {

	@Transactional
	public void allMainValues() {
//		System.out.println("AllMainValues"+":"+"paesci");paesci();
//		System.out.println("AllMainValues"+":"+"logradouro");logradouro();
//		System.out.println("AllMainValues"+":"+"tipoTelefone");tipoTelefone();
//		System.out.println("AllMainValues"+":"+"tipoEndereco");tipoEndereco();
		System.out.println("AllMainValues"+":"+"tipoProfissional");tipoProfissional();
		System.out.println("AllMainValues"+":"+"profissional");profissional();
		System.out.println("AllMainValues"+":"+"socia");socia();
		System.out.println("AllMainValues"+":"+"paciente");paciente();
		System.out.println("AllMainValues"+":"+"atendimentoPadrao");atendimentoPadrao();
		System.out.println("AllMainValues"+":"+"responsavelFinanceiroPaciente");responsavelFinanceiroPaciente();
	}

	@Inject
	private LogradouroDao logradouroDao;

	@Transactional
	public void logradouro() {
		try {
			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/logradouro");
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
			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/tipoTelefone");
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
	private TipoEnderecoDao tipoEnderecoDao;

	@Transactional
	public void tipoEndereco() {
		try {
			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/tipoEndereco");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String tipoEndereco = br.readLine();

			while (tipoEndereco != null) {

				tipoEnderecoDao.adiciona(new TipoEndereco(tipoEndereco));

				tipoEndereco = br.readLine();
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
			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/paesci");
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
	private ParametroDao parametroDao;

	@Transactional
	public void parametro() {
		try {
			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/parametros");
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
				parametro.setNome(parametroString.substring(first, second));
				parametro.setValorPorcentagem(Double.parseDouble(parametroString.substring(second + 1, third)));
				parametro.setValorReais(Double.parseDouble(parametroString.substring(third + 1, lenght)));
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
	private TipoProfissionalDao tipoProfissionalDao;

	@Transactional
	public void tipoProfissional() {
		try {
			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/tipoProfissional");
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
				tipo = tipoProfissionalString.substring(first, second);
				valorBrutoHora = Double.parseDouble(tipoProfissionalString.substring(second + 1, third));
				valorLiquidoHora = Double.parseDouble(tipoProfissionalString.substring(third + 1, lenght));
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
	private ProfissionalDao profissionalDao;

	@Transactional
	public void profissional() {
		try {
			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/profissionais");
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
				nome = (profissionalString.substring(0, second));
				System.out.println("AQUI9");
				tipoProfissionalId = (Long.parseLong(profissionalString.substring(second + 1, lenght)));
				System.out.println("AQUI11" + profissionalString.substring(second + 1, lenght));
				TipoProfissional tipoProfissional = tipoProfissionalDao.buscaPorId(tipoProfissionalId);
				System.out.println("AQUI12" + tipoProfissional);
				Profissional profissional = new Profissional(tipoProfissional);
				System.out.println("AQUI5");

				// Usuario usuario = new Usuario(nome);
				// System.out.println("AQUI6 + usuario: " + usuario);
				// usuario = usuarioDao.adicionaVolta(usuario);

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
			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/socias");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String sociaString = br.readLine();

			String nome;
			Long tipoProfissionalId;
			Integer second;
			Integer lenght;
			//
			while (sociaString != null) {
				second = sociaString.indexOf(";", 0);
				lenght = sociaString.length();
				nome = (sociaString.substring(0, second));
				// System.out.println("AQUI9");
				tipoProfissionalId = (Long.parseLong(sociaString.substring(second + 1, lenght)));
				// System.out.println("AQUI11" + sociaString.substring(second+1,lenght));
				TipoProfissional tipoProfissional = tipoProfissionalDao.buscaPorId(tipoProfissionalId);
				// System.out.println("AQUI12" + tipoProfissional);
				Socia socia = new Socia(tipoProfissional);
				// System.out.println("AQUI5");

				// Usuario usuario = new Usuario(nome);
				// System.out.println("AQUI6 + usuario: " + usuario);
				// System.out.println("AQUI6 + socia: " + socia);
				// usuario = usuarioDao.adicionaVolta(usuario);
				// System.out.println("AQUI8 + usuario: " + usuario);

				Usuario usuario = adicionaUsuarioComNome(nome);
				// System.out.println("AQUI7 + usuario: " + usuario);
				socia.setUsuario(usuario);
				// System.out.println("AQUI8 + socia: " + socia);
				usuario.setSocia(socia);
				// System.out.println("AQUI9 + usuario: " + usuario);
				// System.out.println("AQUI878");
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
	public Usuario adicionaUsuarioComNomeSobrenomeEmail(String nome, String sobrenome, String email) {
		Usuario usuario = new Usuario(nome, sobrenome, email);
		return usuarioDao.adicionaVolta(usuario);
	}

	@Inject
	ResponsavelFinanceiroDao responsavelFinanceiroDao;
	@Inject
	PacienteDao pacienteDao;
	@Inject
	PacoteDao pacoteDao;

	@Transactional
	public void responsavelFinanceiroPaciente() {
		try {
			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/respfinanceiropaciente");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String linha = br.readLine();

			Long id;
			String nomePai;
			String sobrenomePai;
			String emailPai;
			String nomeFilho;
			String sobrenomeFilho;
			String emailFilho;
			Integer i1;
			Integer i2;
			Integer i3;
			Integer i4;
			Integer i5;
			Integer i6;
			Integer i7;
			Integer lenght;

			while (linha != null) {
				i1 = 0;
				System.out.println("i1");
				i2 = linha.indexOf(";", i1 + 1);
				System.out.println("i2-" + i2);
				i3 = linha.indexOf(" ", i2 + 1);
				System.out.println("i3-" + i3);
				i4 = linha.indexOf(";", i3 + 1);
				System.out.println("i4-" + i4);
				i5 = linha.indexOf(" ", i4 + 1);
				System.out.println("i5-" + i5);
				i6 = linha.indexOf(";", i5 + 1);
				System.out.println("i6-" + i6);
				i7 = linha.indexOf(";", i6 + 1);
				System.out.println("i7-" + i7);
				lenght = linha.length();
				System.out.println("i8-" + lenght);
				id = (Long.parseLong(linha.substring(i1, i2)));
				System.out.println("i0");
				nomePai = linha.substring(i2 + 1, i3);
				System.out.println("ia");
				sobrenomePai = linha.substring(i3 + 1, i4);
				System.out.println("ib");
				nomeFilho = linha.substring(i4 + 1, i5);
				System.out.println("ic");
				sobrenomeFilho = linha.substring(i5 + 1, i6);
				System.out.println("id");
				emailPai = linha.substring(i6 + 1, i7);
				System.out.println("ie");
				emailFilho = linha.substring(i7 + 1, lenght);
				System.out.println("if");

				// Usuario pai = adicionaUsuarioComNomeSobrenomeEmail(nomePai,sobrenomePai, emailPai);
				// Usuario filho = adicionaUsuarioComNomeSobrenomeEmail(nomeFilho,sobrenomeFilho, emailFilho);

				// ResponsavelFinanceiro responsavelFinanceiro = responsavelFinanceiroDao.adicionaVolta(new ResponsavelFinanceiro(pai));
				// System.out.println("1-" + responsavelFinanceiro);
				// pai.setResponsavelFinanceiro(responsavelFinanceiro);
				// System.out.println("2-" + pai);
				// usuarioDao.atualiza(pai);
				// System.out.println("3-" + pai);
				//
				// Paciente paciente = pacienteDao.adicionaVolta(new Paciente(filho));
				// System.out.println("4-" + paciente);
				// filho.setPaciente(paciente);
				// System.out.println("5-" + filho);
				// usuarioDao.atualiza(filho);
				// System.out.println("6-" + filho);
				//
				// Pacote pacote = pacoteDao.adicionaVolta(new Pacote(responsavelFinanceiro, paciente));
				// System.out.println("6-" + pacote);
				// System.out.println("7-" + responsavelFinanceiro);
				// responsavelFinanceiroDao.atualiza(responsavelFinanceiro);
				// System.out.println("8-" + responsavelFinanceiro);
				//
				// System.out.println("9-" + paciente);
				// pacienteDao.atualiza(paciente);
				// System.out.println("10-" + paciente);
				// System.out.println("11-" + pacote);
				//

				// responsavelFinanceiro.getPacotes().add(pacote);
				//// System.out.println("12-" + responsavelFinanceiro);
				// responsavelFinanceiroDao.atualiza(responsavelFinanceiro);
				//// System.out.println("13-" + responsavelFinanceiro);
				// paciente.getPacotes().add(pacote);
				//// System.out.println("14-" + paciente);
				// pacienteDao.atualiza(paciente);
				//// System.out.println("15-" + paciente);
				// Pacote pacote = pacoteDao.adicionaVolta(new Pacote(responsavelFinanceiro, paciente));

				// Opcao 2
				Usuario pai = new Usuario(nomePai, sobrenomePai, emailPai);
				Usuario filho = new Usuario(nomeFilho, sobrenomeFilho, emailFilho);
				ResponsavelFinanceiro responsavelFinanceiro = new ResponsavelFinanceiro(pai);
				Paciente paciente = new Paciente(filho);
				// pai.setResponsavelFinanceiro(responsavelFinanceiro);
				// filho.setPaciente(paciente);
				Pacote pacote = new Pacote(responsavelFinanceiro, paciente);
				responsavelFinanceiroDao.adiciona(responsavelFinanceiro);
				pacienteDao.adiciona(paciente);
				usuarioDao.adiciona(pai);
				usuarioDao.adiciona(filho);
				pacoteDao.adiciona(pacote);

				System.out.println(pacote.getPaciente());
				for (Pacote pacotu : paciente.getPacotes()) {
					System.out.println(pacotu);
				}
				paciente.getPacotes().forEach(n -> System.out.println(n));

				System.out.println(pacote.getResponsavelFinanceiro());
				responsavelFinanceiro.getPacotes().forEach(n -> System.out.println(n));
				System.out.println(pai.getResponsavelFinanceiro());
				System.out.println(responsavelFinanceiro.getUsuario());
				System.out.println(filho.getPaciente());
				System.out.println(paciente.getUsuario());

				linha = br.readLine();
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

	@Inject
	AtendimentoPadraoDao atendimentoPadraoDao;

	@Transactional
	public void atendimentoPadrao() {
		try {
			InputStream is = new FileInputStream("/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/pacotePadrao");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String linha = br.readLine();

			String nomePacote;
			Integer quantidadeAtendimentosMensaisSupervisao;
			Integer quantidadeAtendimentosMensaisTerapiaMaster;
			Integer quantidadeAtendimentosMensaisTerapiaFamiliar;
//			Integer quantidadeAtendimentosMensaisPsicologa;
//			Integer quantidadeAtendimentosMensaisPsicologaEspecialista;

			Integer i1;
			Integer i2;
			Integer i3;
			Integer i4;
//			Integer i5;
//			Integer i6;
			Integer lenght;

			while (linha != null) {
				i1 = 0;
				System.out.println("i1");
				i2 = linha.indexOf(";", i1 + 1);
				System.out.println("i2-" + i2);
				i3 = linha.indexOf(";", i2 + 1);
				System.out.println("i3-" + i3);
				i4 = linha.indexOf(";", i3 + 1);
//				System.out.println("i4-" + i4);
//				i5 = linha.indexOf(";", i4 + 1);
//				System.out.println("i5-" + i5);
//				i6 = linha.indexOf(";", i5 + 1);
//				System.out.println("i6-" + i6);
				lenght = linha.length();
				System.out.println("lenght-" + lenght);
				nomePacote = linha.substring(i1, i2);
				System.out.println("nomePacote");
				quantidadeAtendimentosMensaisSupervisao = Integer.parseInt(linha.substring(i2 + 1, i3));
				System.out.println("quantidadeAtendimentosMensaisSupervisao" + ":" + quantidadeAtendimentosMensaisSupervisao);
				quantidadeAtendimentosMensaisTerapiaMaster = Integer.parseInt(linha.substring(i3 + 1, i4));
				System.out.println("quantidadeAtendimentosMensaisTerapeuta" + ":" + quantidadeAtendimentosMensaisTerapiaMaster);
				quantidadeAtendimentosMensaisTerapiaFamiliar = Integer.parseInt(linha.substring(i4 + 1, lenght));
				System.out.println("quantidadeAtendimentosMensaisTerapiaFamiliar" + ":" + quantidadeAtendimentosMensaisTerapiaFamiliar);
//				quantidadeAtendimentosMensaisPsicologa = Integer.parseInt(linha.substring(i5 + 1, i6));
//				System.out.println("quantidadeAtendimentosMensaisPsicologa" + ":" + quantidadeAtendimentosMensaisPsicologa);
//				quantidadeAtendimentosMensaisPsicologaEspecialista = Integer.parseInt(linha.substring(i6 + 1, lenght));
//				System.out.println("quantidadeAtendimentosMensaisPsicologaEspecialista" + ":" + quantidadeAtendimentosMensaisPsicologaEspecialista);

				Pacote pacote = new Pacote();
				pacote.setEhPacotePadrao(true);
				pacote.setNome(nomePacote);

				TipoProfissional tipoProfissionalSupervisao = tipoProfissionalDao.buscaPorTipo("Supervisão");
				System.out.println(tipoProfissionalSupervisao);
				AtendimentoPadrao supervisao = new AtendimentoPadrao(quantidadeAtendimentosMensaisSupervisao, tipoProfissionalSupervisao, Double.parseDouble("0"), true);
				System.out.println("supervisao" + ":" + supervisao);
				supervisao = atendimentoPadraoDao.adicionaVolta(supervisao);
				System.out.println("supervisao" + ":" + supervisao);
				pacote.getAtendimentosPadrao().add(supervisao);

				TipoProfissional tipoProfissionalTerapiaMaster = tipoProfissionalDao.buscaPorTipo("Terapia Master");
				System.out.println(tipoProfissionalTerapiaMaster);
				AtendimentoPadrao terapiaMaster = new AtendimentoPadrao(quantidadeAtendimentosMensaisTerapiaMaster, tipoProfissionalTerapiaMaster, Double.parseDouble("0"), true);
				System.out.println("Terapia Master" + ":" + tipoProfissionalTerapiaMaster);
				terapiaMaster = atendimentoPadraoDao.adicionaVolta(terapiaMaster);
				pacote.getAtendimentosPadrao().add(terapiaMaster);

				TipoProfissional tipoProfissionalTerapiaFamiliar = tipoProfissionalDao.buscaPorTipo("Terapia Familiar");
				System.out.println(tipoProfissionalTerapiaFamiliar);
				AtendimentoPadrao terapiaFamiliar = new AtendimentoPadrao(quantidadeAtendimentosMensaisTerapiaFamiliar, tipoProfissionalTerapiaFamiliar, Double.parseDouble("0"), true);
				System.out.println("familia" + ":" + terapiaFamiliar);
				terapiaFamiliar = atendimentoPadraoDao.adicionaVolta(terapiaFamiliar);
				pacote.getAtendimentosPadrao().add(terapiaFamiliar);

//				TipoProfissional tipoProfissionalPsicologa = tipoProfissionalDao.buscaPorTipo("Psicóloga");
//				System.out.println(tipoProfissionalPsicologa);
//				AtendimentoPadrao psicologa = new AtendimentoPadrao(quantidadeAtendimentosMensaisPsicologa, tipoProfissionalPsicologa, Double.parseDouble("0"), true);
//				System.out.println("psicologa" + ":" + psicologa);
//				psicologa = atendimentoPadraoDao.adicionaVolta(psicologa);
//				pacote.getAtendimentosPadrao().add(psicologa);
//
//				TipoProfissional tipoProfissionalPsicologaEspecialista = tipoProfissionalDao.buscaPorTipo("Psicóloga Especialista");
//				System.out.println(tipoProfissionalPsicologaEspecialista);
//				AtendimentoPadrao psicologaEspecialista = new AtendimentoPadrao(quantidadeAtendimentosMensaisPsicologaEspecialista, tipoProfissionalPsicologaEspecialista, Double.parseDouble("0"),
//						true);
//				System.out.println("psicologaEspecialista" + ":" + psicologaEspecialista);
//				psicologaEspecialista = atendimentoPadraoDao.adicionaVolta(psicologaEspecialista);
//				pacote.getAtendimentosPadrao().add(psicologaEspecialista);

				pacoteDao.adiciona(pacote);
				pacote.getAtendimentosPadrao().forEach(atendimentoPadrao -> atendimentoPadrao.setPacote(pacote));

				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
