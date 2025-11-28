package camada_modelo;

import java.io.Serializable;

public abstract class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int id;
    protected String nome;
    protected double precoVenda;
    protected int quantidadeEstoque;
    protected int estoqueMinimo;
    protected int totalVendido;

    public Produto(int id, String nome, double precoVenda, int quantidadeEstoque, int estoqueMinimo) {
        this.id = id;
        this.nome = nome;
        this.precoVenda = precoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
        this.estoqueMinimo = estoqueMinimo;
        this.totalVendido = 0;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPrecoVenda() { return precoVenda; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
    public int getEstoqueMinimo() { return estoqueMinimo; }
    public int getTotalVendido() { return totalVendido; }
    public void incrementarTotalVendido(int quantidade) { this.totalVendido += quantidade; }

    // Método para alerta de estoque
    public boolean isEstoqueBaixo() {
        return this.quantidadeEstoque < this.estoqueMinimo;
    }

    @Override
    public String toString() {
        return nome + " (ID: " + id + ", Estoque: " + quantidadeEstoque + ")";
    }
}