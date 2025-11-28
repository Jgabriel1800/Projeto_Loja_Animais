package camada_dados;

import java.util.List;

public interface GenericDAO<T> {

    void inserir(T objeto);

    void modificar(T objeto);

    void apagar(int id);

    T consultar(int id);

    List<T> listarTodos();
    
    int getProximoId();
}