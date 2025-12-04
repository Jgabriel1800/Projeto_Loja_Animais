package camada_negocio;

import java.io.Serializable;

public abstract class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int geradorId = 1;

    private int id;
    private String nome;
    private double preco;
    private int estoque;
    private int estoqueMinimo;

    public Produto(String nome, double preco, int estoque) {
        this.id = geradorId++;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.estoqueMinimo = 5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

        if (id >= geradorId) {
            geradorId = id + 1;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getQuantidade() {
        return estoque;
    }

    public void setQuantidade(int quantidade) {
        this.estoque = quantidade;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public boolean precisaRepor() {
        return estoque < estoqueMinimo;
    }

    @Override
    public String toString() {
        return "Produto {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", estoque=" + estoque +
                ", estoqueMinimo=" + estoqueMinimo +
                '}';
    }
}