package camada_negocio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private int id;
    private Cliente cliente;
    private LocalDate data;
    private List<Item_Venda> itens;

    public Venda(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.data = LocalDate.now();
        this.itens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public List<Item_Venda> getItens() {
        return itens;
    }

    public void adicionarItem(Item_Venda item) {
        itens.add(item);
    }

    public double getTotal() {
        double total = 0;
        for (Item_Venda item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }
    
    public double calcularTotal() {
        return getTotal();
    }

    @Override
    public String toString() {
        return "Venda ID: " + id + " | Cliente: " + cliente.getNome() + " | Total: R$ " + getTotal();
    }
}