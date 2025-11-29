package camada_apresentacao;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tela_Menu_Principal().setVisible(true));
    }
}