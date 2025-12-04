package camada_apresentacao;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import camada_servico.*;
import camada_negocio.*;

public class Tela_Vendas extends JFrame {
	private static final long serialVersionUID = 1L;

    private List<Item_Venda> itensVenda = new ArrayList<>();

    public Tela_Vendas() {
        setTitle("Registrar Venda");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JLabel lblCliente = new JLabel("Cliente:");
        JLabel lblProduto = new JLabel("Produto:");
        JLabel lblQtd = new JLabel("Quantidade:");

        JComboBox<Cliente> cbCliente = new JComboBox<>();
        JComboBox<Produto> cbProduto = new JComboBox<>();
        JTextField txtQtd = new JTextField();

        JButton btnAdicionar = new JButton("Adicionar Item");
        JButton btnFinalizar = new JButton("Finalizar Venda");

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(lblCliente); panel.add(cbCliente);
        panel.add(lblProduto); panel.add(cbProduto);
        panel.add(lblQtd); panel.add(txtQtd);
        panel.add(btnAdicionar); panel.add(btnFinalizar);

        add(panel);

        Cliente_Service clienteService = new Cliente_Service();
        Produto_Service produtoService = new Produto_Service();
        Vendas_Service vendasService = new Vendas_Service();

        for (Cliente c : clienteService.listarClientes()) cbCliente.addItem(c);
        for (Produto p : produtoService.listarProdutos()) cbProduto.addItem(p);

        btnAdicionar.addActionListener(e -> {
            try {
                Produto produto = (Produto) cbProduto.getSelectedItem();
                int quantidade = Integer.parseInt(txtQtd.getText());
                if (produto == null) {
                    JOptionPane.showMessageDialog(this, "Selecione um produto!");
                    return;
                }
                if (quantidade <= 0 || quantidade > produto.getEstoque()) {
                    JOptionPane.showMessageDialog(this, "Quantidade inválida ou estoque insuficiente!");
                    return;
                }

                itensVenda.add(new Item_Venda(produto, quantidade));
                JOptionPane.showMessageDialog(this, "Item adicionado: " + produto.getNome() + " x" + quantidade);
                txtQtd.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao adicionar item: " + ex.getMessage());
            }
        });

        btnFinalizar.addActionListener(e -> {
            try {
                Cliente cliente = (Cliente) cbCliente.getSelectedItem();
                if (cliente == null) {
                    JOptionPane.showMessageDialog(this, "Selecione um cliente!");
                    return;
                }
                if (itensVenda.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Adicione pelo menos um item à venda!");
                    return;
                }

                Venda venda = new Venda(0, cliente);
                for (Item_Venda item : itensVenda) venda.adicionarItem(item);

                vendasService.registrarVenda(venda);
                JOptionPane.showMessageDialog(this, "Venda registrada com sucesso!");

                for (Item_Venda item : itensVenda) {
                    Produto p = item.getProduto();
                    p.setEstoque(p.getEstoque() - item.getQuantidade());
                    produtoService.atualizarProduto(p);
                }

                itensVenda.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao finalizar venda: " + ex.getMessage());
            }
        });
    }
}