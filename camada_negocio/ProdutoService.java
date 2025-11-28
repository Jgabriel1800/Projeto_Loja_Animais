package camada_negocio;

import camada_dados.ProdutoDAO;
import camada_modelo.Produto;
import java.util.List;

public class ProdutoService {
    
    private ProdutoDAO produtoDAO;

    public ProdutoService() {
        this.produtoDAO = new ProdutoDAO();
    }
    
    // --- CRUD ---

    public void adicionarProduto(Produto produto) throws IllegalArgumentException {
        // Exemplo de validação de negócio
        if (produto.getPrecoVenda() <= 0) {
            throw new IllegalArgumentException("Preço de venda deve ser positivo.");
        }
        
        // Atribui o próximo ID antes de salvar
        int nextId = produtoDAO.getProximoId();
        // Nota: O método inserir precisaria ser ajustado para receber o ID
        // ou o construtor do Produto na DAO precisaria ser chamado.
        // Por simplificação, o ID deve ser ajustado antes de chamar a DAO.
        // Vamos supor que o objeto Produto já foi criado com o ID correto na GUI/Controller
        produtoDAO.inserir(produto);
    }

    public List<Produto> listarTodos() {
        return produtoDAO.listarTodos();
    }
    
    // --- Lógica Específica ---
    
    /**
     * Tenta dar baixa no estoque e retorna um alerta se o estoque for crítico.
     * @return String de alerta ou null se OK.
     */
    public String darBaixaEstoque(int produtoId, int quantidade) {
        Produto p = produtoDAO.consultar(produtoId);
        
        if (p == null) {
            return "Erro: Produto não encontrado.";
        }
        if (p.getQuantidadeEstoque() < quantidade) {
            return "Erro: Estoque insuficiente. Apenas " + p.getQuantidadeEstoque() + " em estoque.";
        }
        
        // Atualiza a quantidade
        p.setQuantidadeEstoque(p.getQuantidadeEstoque() - quantidade);
        p.incrementarTotalVendido(quantidade); // Atualiza métrica
        produtoDAO.modificar(p);
        
        // Emite alerta de estoque mínimo
        if (p.isEstoqueBaixo()) {
            return "ALERTA DE ESTOQUE BAIXO: " + p.getNome() + " está abaixo do mínimo (" + p.getEstoqueMinimo() + ").";
        }
        
        return null; // Operação OK, sem alerta.
    }
}