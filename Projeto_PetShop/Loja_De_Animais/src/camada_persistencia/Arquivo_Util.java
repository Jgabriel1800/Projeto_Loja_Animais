package camada_persistencia;

import java.io.*;

public class Arquivo_Util {

    // Salva qualquer objeto serializável no caminho indicado
    public static void salvarObjeto(String caminho, Object obj) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(obj);
            oos.flush();
        } 
    }

    // Carrega qualquer objeto serializável do arquivo
    public static Object carregarObjeto(String caminho) throws IOException, ClassNotFoundException {
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            return null; // arquivo não existe ainda
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            return ois.readObject();
        }
    }
}
