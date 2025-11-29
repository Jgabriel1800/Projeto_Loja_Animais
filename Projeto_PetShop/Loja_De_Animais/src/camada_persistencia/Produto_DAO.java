package camada_persistencia;

import java.util.ArrayList;
import java.util.List;
import camada_negocio.Produto;

public class Produto_DAO {

	private static final String CAMINHO = "C:/Users/soare/OneDrive/Área de Trabalho/Projeto_PetShop/Loja_De_Animais/database/produtos.dat";
    private List<Produto> produtos;
    private int geradorId = 1;

    public Produto_DAO() {
        carregar();
    }

    @SuppressWarnings("unchecked")
    private void carregar() {
        try {
            Object obj = Arquivo_Util.carregarObjeto(CAMINHO);
            if (obj != null) {
                produtos = (List<Produto>) obj;

                // Ajustar ID automaticamente
                if (!produtos.isEmpty()) {
                    geradorId = produtos.stream()
                            .mapToInt(Produto::getId)
                            .max()
                            .orElse(0) + 1;
                }

            } else {
                produtos = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
            e.printStackTrace();
            produtos = new ArrayList<>();
        }
    }

    private void salvar() {
        try {
            Arquivo_Util.salvarObjeto(CAMINHO, produtos);
            System.out.println("Produtos salvos em: " + CAMINHO);
        } catch (Exception e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // -------------------------
    // MÉTODOS CRUD
    // -------------------------
    public void inserir(Produto p) {
        if (p.getId() == 0) {
            p.setId(geradorId++);
        }
        produtos.add(p);
        salvar();
    }

    public void atualizar(Produto novo) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == novo.getId()) {
                produtos.set(i, novo);
                salvar();
                return;
            }
        }
    }

    public void remover(int id) {
        produtos.removeIf(p -> p.getId() == id);
        salvar();
    }

    public List<Produto> listar() {
        return produtos;
    }

    public Produto buscar(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public Produto buscarPorNome(String nome) {
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }
}
