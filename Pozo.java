import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Pozo {
    private final List<UNO> cartas;

    public Pozo() {
        cartas = new ArrayList<>();
        inicializarCartas(); // Inicializa las cartas del pozo
        barajar(); // Barajar el pozo
    }

    private void inicializarCartas() {
        // Agregar cartas numéricas y comodines al pozo
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j <= 9; j++) {
                cartas.add(new UNO("Rojo", j));
                cartas.add(new UNO("Verde", j));
                cartas.add(new UNO("Azul", j));
                cartas.add(new UNO("Amarillo", j));
            }
            cartas.add(new UNO("Rojo", -1)); // Comodín
            cartas.add(new UNO("Verde", -1)); // Comodín
            cartas.add(new UNO("Azul", -1)); // Comodín
            cartas.add(new UNO("Amarillo", -1)); // Comodín
        }
    }

    private void barajar() {
        Collections.shuffle(cartas); // Barajar las cartas
    }

    public UNO sacarCarta() {
        if (cartas.isEmpty()) return null; // Si el pozo está vacío
        return cartas.remove(0); // Sacar la primera carta del pozo
    }
}
