package leilaoOnline;

import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Leilao {
	private ArrayList<Lote> lotes;
	private int numeroProxLote;
	private boolean encerrado;

	public Leilao() {
		this.lotes = new ArrayList<>();
		this.numeroProxLote = 1;
		this.encerrado = false;
	}

	public boolean adicionaLote(String descricao) {
		if (this.encerrado == false) {
			lotes.add(new Lote(this.numeroProxLote, descricao));
			this.numeroProxLote++;
			return true;
		} else {
			return false;
		}
	}

	public Lote removeLote(int numero) {
		Lote loteRemovido = this.getLote(numero);
		lotes.remove((numero - 1));
		numeroProxLote--;
		Iterator<Lote> it = lotes.iterator();
		while (it.hasNext()) {
			Lote loteAtual = it.next();
			if (loteAtual.getNumero() > numero) {
				loteAtual.decrementaNumero();
			}
		}
		return loteRemovido;
	}

	public String mostraLotes() {

		Iterator<Lote> it = lotes.iterator();
		StringBuilder mostraLotes = new StringBuilder();
		while (it.hasNext()) {
			Lote loteAtual = it.next();
			mostraLotes.append("\n").append(loteAtual.getNumero()).append(":").append(loteAtual.getDescricao());
			Lance melhorLance = loteAtual.getMaiorLance();

			if (melhorLance != null) {
				mostraLotes.append(" Lance: ").append(melhorLance.getValor());

			} else {
				mostraLotes.append(" (Nenhum Lance)");
			}
		}
		if (lotes.isEmpty()) {
			return "Não há Lotes";
		} else {
			return mostraLotes.toString();
		}
	}

	public Lote getLote(int numero) {
		if (this.encerrado == false) {
			if ((numero >= 1) & (numero < this.numeroProxLote)) {
				Lote loteSelecionado = this.lotes.get(numero - 1);
				if (loteSelecionado.getNumero() != numero) {
					return null;
				}
				return loteSelecionado;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public boolean getEncerrado() {
		return this.encerrado;
	}

	public String fechaLeilao() {
		if (encerrado == false) {
			Iterator<Lote> it = this.lotes.iterator();
			StringBuilder detalheLotes = new StringBuilder();
			while (it.hasNext()) {
				Lote loteAtual = it.next();
				if (loteAtual.getMaiorLance() == null) {
					detalheLotes.append("\n").append("Lote n°: ").append(loteAtual.getNumero()).append(" ")
							.append(loteAtual.getDescricao()).append("\n").append("NÃO VENDIDO");
				} else {
					Lance ganhador = loteAtual.getMaiorLance();
					Pessoa licitante = ganhador.getLicitante();
					detalheLotes.append("\n").append("Lote n°: ").append(loteAtual.getNumero()).append("\n")
							.append(loteAtual.getDescricao()).append("\n").append("VENDIDO PARA: ")
							.append(licitante.getNome()).append(" com o valor de: ").append(ganhador.getValor());
				}

			}
			this.encerrado = true;
			return detalheLotes.toString();
		} else {
			return "Leilão já encerrado";
		}
	}

	public ArrayList<Lote> getNaoVendidos() {
		if (this.encerrado == true) {
			ArrayList<Lote> naoVendidos = new ArrayList<>();
			Iterator<Lote> it = this.lotes.iterator();
			if (it.hasNext()) {
				Lote loteAtual = it.next();
				if (loteAtual.getMaiorLance() == null) {
					naoVendidos.add(loteAtual);
				}
			}

			return naoVendidos;
		} else {
			return null;
		}
	}

	public String lancePara(double valor, int numero) {
		if (this.encerrado == false) {
			Lote loteAtual = this.getLote(numero);
			if (loteAtual != null) {
				Lance lanceAtual = loteAtual.getMaiorLance();
				if (lanceAtual == null || (lanceAtual.getValor() < valor)) {
					 Scanner scanner = new Scanner(System.in);				
					System.out.print("Digite o nome do licitante: ");
					String nomeLicitante = scanner.nextLine();
					Pessoa licitante = new Pessoa(nomeLicitante);
					loteAtual.setMaiorLance(new Lance(licitante, valor));
					return null;
				} else {
					return "Lote n°:" + loteAtual.getNumero() + "(" + loteAtual.getDescricao() + ")"
							+ "Atualmente tem um lance de:" + lanceAtual.getValor();
				}
			} else {
				return "Lote excluído ou não existe";
			}
		}

		else {
			return "Leilão encerrado";
		}
	}
}
