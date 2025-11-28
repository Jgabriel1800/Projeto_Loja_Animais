package camada_dados;

import camada_modelo.Produto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements GenericDAO<Produto> {
    
    private final String ARQUIVO_PRODUTOS = "dados_persistidos/produtos.dat";

    private List<Produto> carregar() {
        List<Produto> produtos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_PRODUTOS))) {
            produtos = (List<Produto>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Arquivo não existe, retorna lista vazia (primeira execução)
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados de produtos: " + e.getMessage());
        }
        return produtos;
    }

    private void salvar(List<Produto> produtos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_PRODUTOS))) {
            oos.writeObject(produtos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados de produtos: " + e.getMessage());
        }
    }
    
    // --- Implementação da Interface ---

    @Override
    public void inserir(Produto produto) {
        List<Produto> produtos = carregar();
        produtos.add(produto);
        salvar(produtos);
    }

    @Override
    public void modificar(Produto produtoAtualizado) {
        List<Produto> produtos = carregar();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produtoAtualizado.getId()) {
                produtos.set(i, produtoAtualizado);
                salvar(produtos);
                return;
            }
        }
    }

    @Override
    public Produto consultar(int id) {
        return listarTodos().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public void apagar(int id) {
        List<Produto> produtos = carregar();
        produtos.removeIf(p -> p.getId() == id);
        salvar(produtos);
    }

    @Override
    public List<Produto> listarTodos() {
        return carregar();
    }

    @Override
    public int getProximoId() {
        return listarTodos().stream()
                .mapToInt(Produto::getId)
                .max()
                .orElse(0) + 1;
    }
}