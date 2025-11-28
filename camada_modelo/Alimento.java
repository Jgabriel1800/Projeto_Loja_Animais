package camada_modelo;

import java.time.LocalDate;

public class Alimento extends Produto {
    private static final long serialVersionUID = 1L;

    private LocalDate dataValidade;

    public Alimento(int id, String nome, double precoVenda, int quantidadeEstoque, int estoqueMinimo, LocalDate dataValidade) {
        super(id, nome, precoVenda, quantidadeEstoque, estoqueMinimo);
        this.dataValidade = dataValidade;
    }

    // Getter
    public LocalDate getDataValidade() { return dataValidade; }
    
    // Adicionar validação específica, se necessário
}