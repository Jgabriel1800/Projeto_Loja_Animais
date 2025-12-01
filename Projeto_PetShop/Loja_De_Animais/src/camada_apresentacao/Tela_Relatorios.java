package camada_apresentacao;

import javax.swing.*;
import java.awt.*;
import java.time.YearMonth;
import camada_persistencia.Relatorio_DAO;

public class Tela_Relatorios extends JFrame {
	private static final long serialVersionUID = 1L;

    public Tela_Relatorios() {
        setTitle("Relatórios da Loja");
        setSize(400, 350);
        setLocationRelativeTo(null);

        JButton btnTotalMes = new JButton("Total Vendido no Mês");
        JButton btnMelhorCliente = new JButton("Melhor Cliente");
        JButton btnDiaMaisVendas = new JButton("Dia com Mais Vendas");

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.add(btnTotalMes);
        panel.add(btnMelhorCliente);
        panel.add(btnDiaMaisVendas);

        add(panel);

        Relatorio_DAO relatorioDAO = new Relatorio_DAO();

        btnTotalMes.addActionListener(e -> {
            try {
                String mesStr = JOptionPane.showInputDialog(this, "Digite o mês (1-12):");
                String anoStr = JOptionPane.showInputDialog(this, "Digite o ano (ex: 2025):");

                int mes = Integer.parseInt(mesStr);
                int ano = Integer.parseInt(anoStr);

                double total = relatorioDAO.calcularTotalVendasMes(mes, ano);
                JOptionPane.showMessageDialog(this, "Total vendido em " + YearMonth.of(ano, mes) + ": R$ " + total);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        btnMelhorCliente.addActionListener(e -> {
            String cliente = relatorioDAO.melhorCliente();
            JOptionPane.showMessageDialog(this, "Melhor cliente: " + cliente);
        });

        btnDiaMaisVendas.addActionListener(e -> {
            String dia = relatorioDAO.diaComMaisVendas();
            JOptionPane.showMessageDialog(this, "Dia com mais vendas: " + dia);
        });
    }
}
