package camada_apresentacao;

import javax.swing.*;
import java.awt.*;
import camada_servico.Produto_Service;
import camada_negocio.*;

public class Tela_Cadastro_Produto extends JFrame {
	private static final long serialVersionUID = 1L;

    public Tela_Cadastro_Produto() {
        setTitle("Cadastro de Produtos");
        setSize(450, 450);
        setLocationRelativeTo(null);

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblPreco = new JLabel("Preço:");
        JLabel lblEstoque = new JLabel("Qtd Estoque:");
        JLabel lblEstoqueMin = new JLabel("Estoque Mínimo:");
        JLabel lblTipo = new JLabel("Tipo do Produto:");

        JTextField txtNome = new JTextField();
        JTextField txtPreco = new JTextField();
        JTextField txtEstoque = new JTextField();
        JTextField txtEstoqueMin = new JTextField();

        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Alimento", "Brinquedo", "Higiene"});

        JButton btnSalvar = new JButton("Salvar Produto");
        JButton btnListar = new JButton("Listar Produtos");
        JButton btnEditar = new JButton("Editar Produto");
        JButton btnRemover = new JButton("Remover Produto");

        JPanel panel = new JPanel(new GridLayout(9, 2, 5, 5));
        panel.add(lblNome); panel.add(txtNome);
        panel.add(lblPreco); panel.add(txtPreco);
        panel.add(lblEstoque); panel.add(txtEstoque);
        panel.add(lblEstoqueMin); panel.add(txtEstoqueMin);
        panel.add(lblTipo); panel.add(cbTipo);

        panel.add(btnSalvar); panel.add(btnListar);
        panel.add(btnEditar); panel.add(btnRemover);

        add(panel);

        Produto_Service produtoService = new Produto_Service();

        btnSalvar.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                double preco = Double.parseDouble(txtPreco.getText());
                int estoque = Integer.parseInt(txtEstoque.getText());
                int estoqueMin = txtEstoqueMin.getText().isEmpty() ? 5 : Integer.parseInt(txtEstoqueMin.getText());
                String tipo = (String) cbTipo.getSelectedItem();

                Produto produto;
                switch (tipo) {
                    case "Alimento":
                        produto = new Produto_Alimento(nome, preco, estoque, "01/01/2025");
                        break;
                    case "Brinquedo":
                        produto = new Produto_Brinquedo(nome, preco, estoque, 3);
                        break;
                    case "Higiene":
                        produto = new Produto_Higiene(nome, preco, estoque, false);
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo inválido");
                }

                produto.setEstoqueMinimo(estoqueMin);
                produtoService.cadastrarProduto(produto);
                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage());
            }
        });

        btnListar.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Produto p : produtoService.listarProdutos()) {
                sb.append(p).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "Nenhum produto cadastrado.");
        });

        btnEditar.addActionListener(e -> new Tela_Editar_Produto().setVisible(true));
        btnRemover.addActionListener(e -> new Tela_Remover_Produto().setVisible(true));
    }
}