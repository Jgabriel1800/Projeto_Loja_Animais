package camada_apresentacao;

import javax.swing.*;
import java.awt.*;

public class Tela_Menu_Principal extends JFrame {
	private static final long serialVersionUID = 1L;
	
    public Tela_Menu_Principal() {
        setTitle("PetShop - Menu Principal");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnProdutos = new JButton("Cadastro de Produtos");
        JButton btnClientes = new JButton("Cadastro de Clientes");
        JButton btnVendas = new JButton("Registrar Venda");
        JButton btnRelatorios = new JButton("Relatórios");
        JButton btnSair = new JButton("Sair");

        btnProdutos.addActionListener(e -> new Tela_Cadastro_Produto().setVisible(true));
        btnClientes.addActionListener(e -> new Tela_Cadastro_Cliente().setVisible(true));
        btnVendas.addActionListener(e -> new Tela_Vendas().setVisible(true));
        btnRelatorios.addActionListener(e -> new Tela_Relatorios().setVisible(true));
        btnSair.addActionListener(e -> System.exit(0));

        setLayout(new GridLayout(5, 1, 10, 10));
        add(btnProdutos);
        add(btnClientes);
        add(btnVendas);
        add(btnRelatorios);
        add(btnSair);
    }
}