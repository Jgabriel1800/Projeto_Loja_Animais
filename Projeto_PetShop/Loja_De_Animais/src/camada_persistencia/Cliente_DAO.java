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
            e.printStackTrace();
        }
    }

    public void salvar(Cliente c) {
        if (c.getId() == 0) { 
            c.setId(geradorId++);
        }
        clientes.add(c);
        salvar();
    }

    public Cliente buscarPorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public boolean remover(int id) {
        boolean removido = clientes.removeIf(c -> c.getId() == id);
        if (removido) salvar();
        return removido;
    }

    public List<Cliente> listar() {
        return clientes;
    }

    public Cliente buscarPorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equalsIgnoreCase(cpf)) {
                return c;
            }
        }
        return null;
    }
}