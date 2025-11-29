package camada_servico;

import camada_negocio.Venda;
import camada_persistencia.Venda_DAO;
import java.util.List;

public class Vendas_Service {

    private Venda_DAO vendaDAO;

    public Vendas_Service() {
        this.vendaDAO = new Venda_DAO();
    }

    public void registrarVenda(Venda venda) {
        vendaDAO.inserir(venda);
    }

    public List<Venda> listarVendas() {
        return vendaDAO.listarVendas();
    }

    public Venda buscarPorId(int idVenda) {
        return vendaDAO.buscarPorId(idVenda);
    }
}
