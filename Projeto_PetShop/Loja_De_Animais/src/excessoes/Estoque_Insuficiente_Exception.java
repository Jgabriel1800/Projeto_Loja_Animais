package excessoes;

public class Estoque_Insuficiente_Exception extends Exception {
	private static final long serialVersionUID = 1L;
	
    public Estoque_Insuficiente_Exception(String mensagem) {
        super(mensagem);
    }
}