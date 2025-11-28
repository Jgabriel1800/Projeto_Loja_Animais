package camada_modelo;

import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nome;
    private String nif;
    private double totalGasto; 

    public Cliente(int id, String nome, String nif) {
        this.id = id;
        this.nome = nome;
        this.nif = nif;
        this.totalGasto = 0.0;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getNif() { return nif; }
    public double getTotalGasto() { return totalGasto; }

    public void adicionarGasto(double valor) {
        this.totalGasto += valor;
    }

    @Override
    public String toString() {
        return nome + " (NIF: " + nif + ")";
    }
}