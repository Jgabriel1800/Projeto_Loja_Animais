package camada_apresentacao;

import javax.swing.*;
import camada_servico.Produto_Service;
import camada_negocio.Produto;

public class Tela_Editar_Produto extends JFrame {
    private static final long serialVersionUID = 1L;

    private Produto_Service service = new Produto_Service();

    public Tela_Editar_Produto() {
        setTitle("Editar Produto");
        setSize(400, 360);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel labelId = new JLabel("ID do Produto:");
        labelId.setBounds(20, 20, 150, 25);
        add(labelId);

        JTextField txtId = new JTextField();
        txtId.setBounds(150, 20, 200, 25);
        add(txtId);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(150, 60, 200, 25);
        add(btnBuscar);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(20, 110, 150, 25);
        add(labelNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(150, 110, 200, 25);
        add(txtNome);

        JLabel labelPreco = new JLabel("Preço:");
        labelPreco.setBounds(20, 150, 150, 25);
        add(labelPreco);

        JTextField txtPreco = new JTextField();
        txtPreco.setBounds(150, 150, 200, 25);
        add(txtPreco);

        JLabel labelQtd = new JLabel("Quantidade:");
        labelQtd.setBounds(20, 190, 150, 25);
        add(labelQtd);

        JTextField txtQuantidade = new JTextField();
        txtQuantidade.setBounds(150, 190, 200, 25);
        add(txtQuantidade);

        JButton btnSalvar = new JButton("Salvar Alterações");
        btnSalvar.setBounds(80, 240, 230, 35);
        add(btnSalvar);

        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Produto p = service.buscarPorId(id);

                if (p == null) {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado!");
                    return;
                }

                txtNome.setText(p.getNome());
                txtPreco.setText(String.valueOf(p.getPreco()));
                txtQuantidade.setText(String.valueOf(p.getQuantidade()));

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "ID inválido!");
            }
        });

        btnSalvar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Produto p = service.buscarPorId(id);

                if (p == null) {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado!");
                    return;
                }

                p.setNome(txtNome.getText());
                p.setPreco(Double.parseDouble(txtPreco.getText()));
                p.setQuantidade(Integer.parseInt(txtQuantidade.getText()));

                service.atualizarProduto(p);

                JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar produto!");
            }
        });
    }
}