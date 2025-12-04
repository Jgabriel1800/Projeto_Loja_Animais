package camada_servico;

import camada_persistencia.Venda_DAO;
import camada_negocio.Venda;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Relatorio_Service {

    private Venda_DAO vendaDAO;

    public Relatorio_Service() {
        this.vendaDAO = new Venda_DAO();
    }

    public double calcularTotalVendasMes(int mes, int ano) {
        List<Venda> vendas = vendaDAO.listarVendas();
        double total = 0.0;

        for (Venda v : vendas) {
            if (v.getData().getMonthValue() == mes && v.getData().getYear() == ano) {
                total += v.calcularTotal();
            }
        }
        return total;
    }

    public String melhorCliente() {
        List<Venda> vendas = vendaDAO.listarVendas();
        Map<String, Double> totalCliente = new HashMap<>();

        for (Venda v : vendas) {
            String cliente = v.getCliente().getNome();
            totalCliente.put(cliente, totalCliente.getOrDefault(cliente, 0.0) + v.calcularTotal());
        }

        return totalCliente.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhum cliente encontrado");
    }

    public String diaComMaisVendas() {
        List<Venda> vendas = vendaDAO.listarVendas();
        Map<String, Integer> contagemDias = new HashMap<>();

        for (Venda v : vendas) {
            String dia = v.getData().toString();
            contagemDias.put(dia, contagemDias.getOrDefault(dia, 0) + 1);
        }

        return contagemDias.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhuma venda registrada");
    }
}