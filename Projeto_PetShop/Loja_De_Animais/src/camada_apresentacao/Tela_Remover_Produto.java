package camada_apresentacao;

import javax.swing.*;
import camada_servico.Produto_Service;

public class Tela_Remover_Produto extends JFrame {
	private static final long serialVersionUID = 1L;


    private Produto_Service service = new Produto_Service();

    public Tela_Remover_Produto() {
        setTitle("Remover Produto");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel labelId = new JLabel("ID do Produto:");
        labelId.setBounds(20, 20, 150, 25);
        add(labelId);

        JTextField txtId = new JTextField();
        txtId.setBounds(150, 20, 150, 25);
        add(txtId);

        JButton btnRemover = new JButton("Remover Produto");
        btnRemover.setBounds(80, 80, 180, 30);
        add(btnRemover);

        btnRemover.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                service.removerProduto(id);
                JOptionPane.showMessageDialog(this, "Produto removido com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover produto!");
            }
        });
    }
}