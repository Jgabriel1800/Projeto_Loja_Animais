package camada_servico;

import java.util.List;
import camada_persistencia.Produto_DAO;
import camada_negocio.Produto;

public class Produto_Service {

    private Produto_DAO produtoDAO;

    public Produto_Service() {
        this.produtoDAO = new Produto_DAO();
    }

    public void cadastrarProduto(Produto produto) {
        produtoDAO.inserir(produto);
    }

    public Produto buscarPorId(int id) {
        return produtoDAO.buscar(id);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listar();
    }

    public void atualizarProduto(Produto produto) {
        produtoDAO.atualizar(produto);
    }

    public void removerProduto(int id) {
        produtoDAO.remover(id);
    }
}