package camada_negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estoque implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private List<Produto> produtos;

    public Estoque() {
        produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto p) {
        produtos.add(p);
    }

    public void removerProduto(Produto p) {
        produtos.remove(p);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Produto buscarPorNome(String nome) {
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    public void listarProdutos() {
        for (Produto p : produtos) {
            System.out.println(p.getNome() + " - Estoque: " + p.getEstoque());
        }
    }
}