import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jugador {
    private final String nombre;
    private final List<UNO> mano;  // Cartas en la mano del jugador

    // Constructor
    public Jugador(String nombre, Pozo pozo) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
        repartirCartas(pozo);
    }

    // Método para repartir 7 cartas al azar desde el pozo
    private void repartirCartas(Pozo pozo) {
        for (int i = 0; i < 7; i++) {
            UNO carta = pozo.sacarCarta();  // Sacamos cartas del pozo
            if (carta != null) {
                mano.add(carta);
            }
        }
    }

    // Método para mostrar las cartas del jugador
    public void mostrarCartas() {
        System.out.println("Cartas de " + nombre + ":");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println((i + 1) + ". " + mano.get(i));  // Numerar las cartas
        }
        System.out.println();
    }

    // Método para jugar una carta
    public UNO jugarCarta(UNO carta) {
        if (mano.remove(carta)) {  // Si la carta está en la mano, la eliminamos
            return carta;
        } else {
            System.out.println("No tienes esa carta.");
            return null;
        }
    }

    // Método para seleccionar carta para jugar
    public UNO seleccionarCarta() {
        Scanner scanner = new Scanner(System.in);
        mostrarCartas();
        System.out.print("Selecciona el número de la carta que deseas jugar (0 para cancelar): ");
        int seleccion = scanner.nextInt();

        if (seleccion > 0 && seleccion <= mano.size()) {
            return mano.get(seleccion - 1);  // Devolvemos la carta seleccionada
        } else {
            System.out.println("Selección no válida. Turno cancelado.");
            return null;
        }
    }

    public String getNombre() {
        return nombre;
    }
}
