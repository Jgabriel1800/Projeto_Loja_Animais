package camada_negocio;

public class Produto_Alimento extends Produto {
	private static final long serialVersionUID = 1L;
	
    private String dataValidade;

    public Produto_Alimento(String nome, double preco, int estoque, String dataValidade) {
        super(nome, preco, estoque);
        this.dataValidade = dataValidade;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }
}