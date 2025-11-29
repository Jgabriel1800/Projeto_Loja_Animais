package camada_negocio;

public class Produto_Higiene extends Produto {
	private static final long serialVersionUID = 1L;
	
    private boolean usoVeterinario;

    public Produto_Higiene(String nome, double preco, int estoque, boolean usoVeterinario) {
        super(nome, preco, estoque);
        this.usoVeterinario = usoVeterinario;
    }

    public boolean isUsoVeterinario() {
        return usoVeterinario;
    }

    public void setUsoVeterinario(boolean usoVeterinario) {
        this.usoVeterinario = usoVeterinario;
    }
}