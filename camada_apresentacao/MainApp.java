package camada_apresentacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainApp {

    public static void main(String[] args) {
        // Garante que a pasta de dados exista
        new File("dados_persistidos").mkdirs();
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("PetShopApp - Gestão");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            
            // Cria o Menu Principal
            JMenuBar menuBar = criarMenuBar(frame);
            frame.setJMenuBar(menuBar);

            // Adiciona um painel principal
            frame.add(new JLabel("Bem-vindo(a) ao sistema PetShop!", SwingConstants.CENTER));
            
            frame.setVisible(true);
        });
    }

    private static JMenuBar criarMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        
        // Menu Cadastro
        JMenu cadastroMenu = new JMenu("Cadastros");
        JMenuItem produtoItem = new JMenuItem("Produtos");
        JMenuItem clienteItem = new JMenuItem("Clientes");
        
        // Adicionar Ações (Exemplo para Produto)
        produtoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de CRUD de Produtos
                new ProdutoGUI(frame).setVisible(true);
            }
        });

        cadastroMenu.add(produtoItem);
        cadastroMenu.add(clienteItem);

        // Menu Transações
        JMenu transacaoMenu = new JMenu("Transações");
        JMenuItem vendaItem = new JMenuItem("Nova Venda");
        transacaoMenu.add(vendaItem);
        
        // Menu Relatórios
        JMenu relatorioMenu = new JMenu("Relatórios");
        JMenuItem vendasMesItem = new JMenuItem("Vendas do Mês");
        relatorioMenu.add(vendasMesItem);

        menuBar.add(cadastroMenu);
        menuBar.add(transacaoMenu);
        menuBar.add(relatorioMenu);
        
        return menuBar;
    }
}