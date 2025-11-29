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

    // Total de vendas do mês (somatória dos valores das vendas)
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

    // Produto mais vendido (por quantidade)
    public String produtoMaisVendido() {
        List<Venda> vendas = vendaDAO.listarVendas();
        Map<String, Integer> contagem = new HashMap<>();

        for (Venda v : vendas) {
            v.getItens().forEach(item -> {
                String nome = item.getProduto().getNome();
                contagem.put(nome, contagem.getOrDefault(nome, 0) + item.getQuantidade());
            });
        }

        return contagem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhum produto vendido");
    }

    // Melhor cliente (quem comprou mais em valor)
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

    // Produto menos vendido
    public String produtoMenosVendido() {
        List<Venda> vendas = vendaDAO.listarVendas();
        Map<String, Integer> contagem = new HashMap<>();

        for (Venda v : vendas) {
            v.getItens().forEach(item -> {
                String nome = item.getProduto().getNome();
                contagem.put(nome, contagem.getOrDefault(nome, 0) + item.getQuantidade());
            });
        }

        return contagem.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhum produto vendido");
    }

    // Dia com mais vendas
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