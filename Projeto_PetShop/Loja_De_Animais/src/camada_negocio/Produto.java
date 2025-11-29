package camada_negocio;

import java.io.Serializable;

public abstract class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

    private static int geradorId = 1;  // Gera IDs automáticos para cada produto

    private int id;
    private String nome;
    private double preco;
    private int estoque;
    private int estoqueMinimo;

    public Produto(String nome, double preco, int estoque) {
        this.id = geradorId++; // ID único para cada produto criado
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.estoqueMinimo = 5; // valor padrão já existente
    }

    // ---------- GETTERS E SETTERS ----------

    public int getId() {
        return id;
    }
    
    // Setter adicionado conforme solicitado
    public void setId(int id) {
        this.id = id;

        // Ajusta o gerador para evitar duplicações
        if (id >= geradorId) {
            geradorId = id + 1;
        }
    }

    // ID não terá setter para evitar alterar depois de criado
    
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

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    // ---------- MÉTODO DE CONTROLE DE ESTOQUE ----------

    public boolean precisaRepor() {
        return estoque < estoqueMinimo;
    }

    // ---------- TO STRING PARA DEPURAÇÃO ----------

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
