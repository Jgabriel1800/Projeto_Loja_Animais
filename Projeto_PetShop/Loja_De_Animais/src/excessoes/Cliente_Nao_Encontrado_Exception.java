package excessoes;

public class Cliente_Nao_Encontrado_Exception extends Exception {
	private static final long serialVersionUID = 1L;
	
    public Cliente_Nao_Encontrado_Exception(String mensagem) {
        super(mensagem);
    }
}