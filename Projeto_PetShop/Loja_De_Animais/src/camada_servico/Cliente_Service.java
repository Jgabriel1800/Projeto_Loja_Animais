package camada_servico;

import camada_persistencia.Cliente_DAO;
import camada_negocio.Cliente;
import java.util.List;

public class Cliente_Service {

    private Cliente_DAO clienteDAO;

    public Cliente_Service() {
        this.clienteDAO = new Cliente_DAO();
    }

    public void cadastrarCliente(Cliente cliente) {
        clienteDAO.salvar(cliente);
    }

    public Cliente buscarPorId(int id) {
        return clienteDAO.buscarPorId(id);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listar();
    }

    public boolean removerCliente(int id) {
        return clienteDAO.remover(id);
    }
}