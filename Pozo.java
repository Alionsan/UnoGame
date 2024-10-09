import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pozo {
    private final List<UNO> cartas;  // Lista de cartas en el pozo

    // Constructor para inicializar el pozo con cartas correctas
    public Pozo() {
        this.cartas = new ArrayList<>();
        inicializarPozo();
    }

    // Método para inicializar el pozo con cartas correctas
    private void inicializarPozo() {
        String[] colores = {"Rojo", "Amarillo", "Verde", "Azul"};

        // Cartas numéricas: 0 a 9 (1 copia de 0, 2 copias de 1 a 9 por color)
        for (String color : colores) {
            cartas.add(new UNO(color, 0, false, ""));  // Solo una carta 0 por color
            for (int i = 1; i <= 9; i++) {
                cartas.add(new UNO(color, i, false, ""));  // Dos copias de cada número
                cartas.add(new UNO(color, i, false, ""));
            }
        }

        // Cartas especiales: Reversa, Salto, +2 (2 copias por color)
        for (String color : colores) {
            for (int i = 0; i < 2; i++) {
                cartas.add(new UNO(color, -1, false, "Reversa"));
                cartas.add(new UNO(color, -1, false, "Salto"));
                cartas.add(new UNO(color, -1, false, "+2"));
            }
        }

        // Comodines: Cambio de color y +4 (4 copias de cada uno)
        for (int i = 0; i < 4; i++) {
            cartas.add(new UNO("", -1, true, "Cambio de Color"));
            cartas.add(new UNO("", -1, true, "+4"));
        }

        // Barajar el mazo de cartas
        Collections.shuffle(cartas);
    }

    // Método para sacar una carta del pozo
    public UNO sacarCarta() {
        if (!cartas.isEmpty()) {
            return cartas.remove(0);  // Elimina y devuelve la primera carta
        } else {
            System.out.println("El pozo está vacío.");
            return null;  // Si no hay cartas
        }
    }

    // Método para contar cuántas cartas quedan en el pozo
    public int contarCartas() {
        return cartas.size();
    }

    // Método para revisar las cartas que quedan en el pozo
    public void revisarPozo() {
        System.out.println("Cartas en el pozo:");
        for (UNO carta : cartas) {
            System.out.println(carta);
        }
    }
}
