package camada_persistencia;

import java.util.ArrayList;
import java.util.List;
import camada_negocio.Venda;

public class Venda_DAO {

    private static final String CAMINHO = "C:/Users/soare/OneDrive/Área de Trabalho/Projeto_PetShop/Loja_De_Animais/database/vendas.dat";
    private List<Venda> vendas;
    private int geradorId = 1;

    public Venda_DAO() {
        carregar();
    }

    @SuppressWarnings("unchecked")
    private void carregar() {
        try {
            Object obj = Arquivo_Util.carregarObjeto(CAMINHO);
            if (obj != null) {
                vendas = (List<Venda>) obj;

                if (!vendas.isEmpty()) {
                    geradorId = vendas.stream()
                            .mapToInt(Venda::getId)
                            .max()
                            .orElse(0) + 1;
                }

            } else {
                vendas = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar vendas: " + e.getMessage());
            e.printStackTrace();
            vendas = new ArrayList<>();
        }
    }

    private void salvar() {
        try {
            Arquivo_Util.salvarObjeto(CAMINHO, vendas);
            System.out.println("Vendas salvas em: " + CAMINHO);
        } catch (Exception e) {
            System.out.println("Erro ao salvar vendas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void inserir(Venda v) {
        if (v.getId() == 0) {
            v.setId(geradorId++);
        }

        vendas.add(v);
        salvar();
    }

    public List<Venda> listarVendas() {
        return vendas;
    }

    public Venda buscarPorId(int id) {
        for (Venda v : vendas) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }
}