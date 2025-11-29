package excessoes;

public class Produto_Nao_Encontrado_Exception extends Exception {
	private static final long serialVersionUID = 1L;
	
    public Produto_Nao_Encontrado_Exception(String mensagem) {
        super(mensagem);
    }
}