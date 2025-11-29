package camada_negocio;

public class Produto_Brinquedo extends Produto {
	private static final long serialVersionUID = 1L;
	
    private int idadeIndicada;

    public Produto_Brinquedo(String nome, double preco, int estoque, int idadeIndicada) {
        super(nome, preco, estoque);
        this.idadeIndicada = idadeIndicada;
    }

    public int getIdadeIndicada() {
        return idadeIndicada;
    }

    public void setIdadeIndicada(int idadeIndicada) {
        this.idadeIndicada = idadeIndicada;
    }
}