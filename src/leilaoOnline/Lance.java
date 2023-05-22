package leilaoOnline;

public class Lance {
	private Pessoa licitante;
	  private double valor;

	  public Lance(Pessoa licitante, double valor) {
	    this.licitante = licitante;
	    this.valor = valor;
	  }

	  public Pessoa getLicitante() {
	    return licitante;
	  }

	  public double getValor() {
	    return valor;
	  }     
}
