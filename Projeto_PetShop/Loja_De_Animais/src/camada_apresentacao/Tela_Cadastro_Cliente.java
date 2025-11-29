package camada_apresentacao;

import javax.swing.*;
import java.awt.*;
import camada_negocio.Cliente;
import camada_servico.Cliente_Service;

public class Tela_Cadastro_Cliente extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public Tela_Cadastro_Cliente() {
	    setTitle("Cadastro de Clientes");
	    setSize(400, 300);
	    setLocationRelativeTo(null);

	    JLabel lblNome = new JLabel("Nome:");
	    JLabel lblTelefone = new JLabel("Telefone:");
	    JLabel lblEmail = new JLabel("Email:");
	    JLabel lblCPF = new JLabel("CPF:");

	    JTextField txtNome = new JTextField();
	    JTextField txtTelefone = new JTextField();
	    JTextField txtEmail = new JTextField();
	    JTextField txtCPF = new JTextField();


	    JButton btnSalvar = new JButton("Salvar Cliente");
	    JButton btnListar = new JButton("Listar Clientes");

	    JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
	    panel.add(lblNome); panel.add(txtNome);
	    panel.add(lblTelefone); panel.add(txtTelefone);
	    panel.add(lblEmail); panel.add(txtEmail);
	    panel.add(lblCPF); panel.add(txtCPF);
	    panel.add(btnSalvar); panel.add(btnListar);

	    add(panel);

	    // Conexão com o Service
	    Cliente_Service clienteService = new Cliente_Service();

	    btnSalvar.addActionListener(e -> {
	        try {
	            String nome = txtNome.getText();
	            String telefone = txtTelefone.getText();
	            String cpf = txtCPF.getText(); // você está usando txtEmail como CPF aqui
	            if (nome.isEmpty() || telefone.isEmpty() || cpf.isEmpty()) {
	                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
	                return;
	            }
	            clienteService.cadastrarCliente(new Cliente(0, nome, cpf, telefone));
	            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + ex.getMessage());
	        }
	    });

	    btnListar.addActionListener(e -> {
	        StringBuilder sb = new StringBuilder();
	        for (Cliente c : clienteService.listarClientes()) {
	            sb.append(c).append("\n");
	        }
	        JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "Nenhum cliente cadastrado.");
	    });
	}
}
