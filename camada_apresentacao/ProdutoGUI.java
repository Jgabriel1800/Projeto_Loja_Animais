package camada_apresentacao;

import camada_modelo.Alimento;
import camada_modelo.Produto;
import camada_negocio.ProdutoService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class ProdutoGUI extends JDialog {
    
    private ProdutoService produtoService;
    private JTextArea displayArea;

    public ProdutoGUI(JFrame parent) {
        super(parent, "Gestão de Produtos", true);
        produtoService = new ProdutoService();
        
        setLayout(new BorderLayout());
        setSize(600, 400);
        setLocationRelativeTo(parent);

        // Painel de Botões
        JPanel buttonPanel = new JPanel();
        JButton btnInserir = new JButton("Inserir");
        JButton btnListar = new JButton("Listar Todos");

        btnInserir.addActionListener(e -> inserirProduto());
        btnListar.addActionListener(e -> listarProdutos());

        buttonPanel.add(btnInserir);
        buttonPanel.add(btnListar);
        
        add(buttonPanel, BorderLayout.NORTH);

        // Área de Exibição
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        
        // Exibe os produtos ao iniciar
        listarProdutos();
    }
    
    private void inserirProduto() {
        // Exemplo Simplificado de Inserção (apenas para Alimento)
        try {
            String nome = JOptionPane.showInputDialog(this, "Nome do Produto:");
            double preco = Double.parseDouble(JOptionPane.showInputDialog(this, "Preço de Venda:"));
            int estoque = Integer.parseInt(JOptionPane.showInputDialog(this, "Estoque Inicial:"));
            int min = Integer.parseInt(JOptionPane.showInputDialog(this, "Estoque Mínimo:"));
            
            // Para simplificar, data de validade é sempre 1 ano a partir de hoje.
            LocalDate validade = LocalDate.now().plusYears(1); 

            // O ID será determinado pela DAO/Service
            int proximoId = produtoService.listarTodos().stream().mapToInt(Produto::getId).max().orElse(0) + 1;
            
            Produto novoProduto = new Alimento(proximoId, nome, preco, estoque, min, validade);
            produtoService.adicionarProduto(novoProduto);
            
            JOptionPane.showMessageDialog(this, "Produto Inserido com sucesso!");
            listarProdutos();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro no formato do número.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void listarProdutos() {
        List<Produto> produtos = produtoService.listarTodos();
        displayArea.setText("");
        if (produtos.isEmpty()) {
            displayArea.append("Nenhum produto cadastrado.\n");
            return;
        }
        
        displayArea.append("--- LISTA DE PRODUTOS ---\n");
        for (Produto p : produtos) {
            displayArea.append(p.toString());
            if (p instanceof Alimento) {
                 displayArea.append(" | Validade: " + ((Alimento) p).getDataValidade());
            }
            if (p.isEstoqueBaixo()) {
                displayArea.append(" *** ALERTA DE ESTOQUE BAIXO ***");
            }
            displayArea.append("\n");
        }
    }
}