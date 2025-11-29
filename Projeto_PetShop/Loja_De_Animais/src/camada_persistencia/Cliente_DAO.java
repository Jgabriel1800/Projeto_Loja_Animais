package camada_persistencia;

import java.util.ArrayList;
import camada_negocio.Cliente;
import java.util.List;
import java.io.IOException;

public class Cliente_DAO {

	private static final String CAMINHO = "C:/Users/soare/OneDrive/Área de Trabalho/Projeto_PetShop/Loja_De_Animais/database/clientes.dat";
    private List<Cliente> clientes;
    private int geradorId = 1;

    public Cliente_DAO() {
        carregar();
    }

    @SuppressWarnings("unchecked")
    private void carregar() {
        try {
            Object obj = Arquivo_Util.carregarObjeto(CAMINHO);
            if (obj != null) {
                clientes = (List<Cliente>) obj;

                // Atualiza o gerador de ID baseado no maior ID existente
                if (!clientes.isEmpty()) {
                    geradorId = clientes.stream()
                            .mapToInt(Cliente::getId)
                            .max()
                            .orElse(0) + 1;
                }

            } else {
                clientes = new ArrayList<>();
            }
        } catch (Exception e) {
            clientes = new ArrayList<>();
        }
    }

    private void salvar() {
        try {
            Arquivo_Util.salvarObjeto(CAMINHO, clientes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
            e.printStackTrace(); // imprime detalhes do erro
        }
    }


    // =============================
    //  MÉTODOS USADOS PELO SERVICE
    // =============================

    // substitui "inserir"
    public void salvar(Cliente c) {
        if (c.getId() == 0) { 
            c.setId(geradorId++);
        }
        clientes.add(c);
        salvar();
    }

    // busca por ID (necessário pro SERVICE)
    public Cliente buscarPorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    // remove usando ID (necessário pro SERVICE)
    public boolean remover(int id) {
        boolean removido = clientes.removeIf(c -> c.getId() == id);
        if (removido) salvar();
        return removido;
    }

    // mantém listar()
    public List<Cliente> listar() {
        return clientes;
    }

    // mantém buscarPorCpf()
    public Cliente buscarPorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equalsIgnoreCase(cpf)) {
                return c;
            }
        }
        return null;
    }
}
