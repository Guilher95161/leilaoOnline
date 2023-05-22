package leilaoOnline;

public class Lote {
	 private int numero;
	  private String descricao;
	  private Lance maiorLance;

	  public Lote(int numero, String descricao) {
	    this.numero = numero;
	    this.descricao = descricao;
	  }

	  public int getNumero() {
	    return numero;
	  }
public void decrementaNumero() {
	this.numero--;
}
	  public String getDescricao() {
	    return descricao;
	  }

	  public Lance getMaiorLance() {
	    return maiorLance;
	  }
	  public void setMaiorLance(Lance maiorLance) {
	    this.maiorLance = maiorLance;
	  }
}
