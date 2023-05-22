package leilaoOnline;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
public class InterfaceLeilao {
	private Leilao leilao;
	private int modoAtual = 0;
	private int senha;

	public InterfaceLeilao(int senha) {
		this.senha = senha;
		this.leilao = new Leilao();
	}

	private void adicionarLote() {
		if (leilao.getEncerrado() == false) {
			String descricao = getString(" a descrição do lote");
			boolean adicionado = leilao.adicionaLote(descricao);
			if (adicionado) {
				System.out.println("Lote adicionado com sucesso!");
			} else {
				System.out.println("Erro ao adicionar o lote.");
			}
		} else {
			System.err.println("Leilão já encerrado");
		}
	}

	private String removerLote(int numero) {
		if (leilao.getEncerrado() == false) {
			Lote loteExcluido = leilao.removeLote(numero);

			return "Lote n°: " + loteExcluido.getNumero() + "(" + loteExcluido.getDescricao() + ")"
					+ "Foi removido com sucesso";

		} else {
			return "Leilão já encerrado";
		}
	}

	private void exibirLotes() {
		String listaLotes = leilao.mostraLotes();
		System.out.println(listaLotes);
	}

	private void fazerLance() {
		int numeroLote = getInt("Digite o número do lote: ");
		double valorLance = getValor("Digite o valor do lance: ");
		String resultado = leilao.lancePara(valorLance, numeroLote);
		if (resultado == null) {
			System.out.println("Lance realizado com sucesso!");
		} else {
			System.out.println(resultado);
		}
	}

	private void fecharLeilao() {
		String detalhes = leilao.fechaLeilao();
		System.out.println(detalhes);
	}
private String exibirNãoVendidos() {
	 ArrayList<Lote> naoVendidos = leilao.getNaoVendidos();
	 if(!naoVendidos.isEmpty()) {
		 Iterator<Lote> it= naoVendidos.iterator();
		 StringBuilder exibiNaoVendidos= new StringBuilder();
		 while(it.hasNext()) {
			 Lote loteAtual = it.next();
				exibiNaoVendidos.append("\n").append(loteAtual.getNumero()).append(":").append(loteAtual.getDescricao());
				Lance melhorLance = loteAtual.getMaiorLance();

				if (melhorLance != null) {
					exibiNaoVendidos.append(" Lance: ").append(melhorLance.getValor()).append("\n").append("Não foi vendido");

				} else {
					exibiNaoVendidos.append(" (Nenhum Lance)").append("\n").append("Não foi vendido");
				}
		 }
		 return exibiNaoVendidos.toString();
	 }
	 else {
		 return"Todos os Lotes foram vendidos";
	 }
}
	private int getOpcao() {
		int opcao;
		if (this.modoAtual == 0) {
			if (leilao.getEncerrado()) {
				do {
					System.out.println("=== Interface do Administrador(Pós Leilão) ===");
					System.out.println("2. Exibir Lotes");
					System.out.println("8. Exibir Lotes não vendidos");
					System.out.println("0. Sair");
					opcao = getInt("uma opcao");
				} while (opcao != 2 & opcao != 8 & opcao != 0);
				return opcao;
			} else {
				do {
					System.out.println("=== Interface do Administrador ===");
					System.out.println("1. Adicionar Lote");
					System.out.println("2. Exibir Lotes");
					System.out.println("4. Fechar Leilão");
					System.out.println("6. Iniciar Leilão");
					System.out.println("7. Remover Lote");
					System.out.println("0. Sair");
					opcao = getInt("uma opcao");
				} while (opcao != 2 & opcao != 8 & opcao != 0 & opcao != 1 & opcao != 4 & opcao != 6 & opcao != 7);
				return opcao;
			}
		} else {
			if (leilao.getEncerrado()) {
				do {
					System.out.println("=== Interface Principal(Pós Leilão) ===");
					System.out.println("2. Exibir Lotes");
					System.out.println("5.Interface de Administrador");
					System.out.println("0. Sair");
					System.out.println("8. Exibir Lotes não vendidos");
					opcao = getInt("uma opcao");
				} while (opcao != 2 & opcao != 8 & opcao != 0 & opcao != 5);
				return opcao;
			} else {
				do {
				System.out.println("=== Interface Principal ===");
				System.out.println("2. Exibir Lotes");
				System.out.println("3. Fazer Lance");
				System.out.println("5.Interface de Administrador");
				System.out.println("0. Sair");
				opcao = getInt("uma opcao");
				}while (opcao != 2 & opcao != 3 & opcao != 0 & opcao != 5);
				return opcao;
			}
		}

	}

	public void iniciaOperacao() {
		int opcao = getOpcao();
		do {
			switch (opcao) {
			case 1:
				this.adicionarLote();
				break;
			case 2:
				this.exibirLotes();
				break;
			case 3:
				this.fazerLance();
				break;
			case 4:
				this.fecharLeilao();
				break;
			case 5:
				int senha = getInt(" a senha do Leilão para mudar a Interface:");
				if (this.senha == senha) {
					this.modoAtual = 0;
				} else {
					System.out.println("Senha incorreta");
				}
				break;
			case 6:
				this.modoAtual = 1;
				break;
			case 7:
				System.out.println(this.removerLote(getInt("Numero do Lote")));
				break;
			case 8:
				System.out.println(this.exibirNãoVendidos());
				break;
			case 0:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}

			opcao = getOpcao();
		}while (opcao != 0);

	}

	private int getInt(String string) {
		Scanner r = new Scanner(System.in);
		System.out.println("Entre com " + string);
		if (r.hasNextInt()) {
			return r.nextInt();
		}
		String st = r.next();
		System.out.println("Erro na Leitura de Dados");
		return 0;
	}

	private String getString(String string) {
		Scanner r = new Scanner(System.in);
		System.out.println("Entre com " + string);
		return r.nextLine();

	}

	private double getValor(String string) {
		Scanner r = new Scanner(System.in);
		System.out.println("Entre com " + string);
		if (r.hasNextDouble()) {
			return r.nextDouble();
		}
		String st = r.next();
		System.out.println("Erro na Leitura de Dados");
		return 0;
	}
}
