import java.util.ArrayList;
import java.util.List;

class Jugador {
    private final String nombre;
    private final List<UNO> mano;

    // Constructor
    public Jugador(String nombre, Pozo pozo) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
        repartirCartas(pozo);
    }

    // Método para repartir 7 cartas al azar desde el pozo
    private void repartirCartas(Pozo pozo) {
        for (int i = 0; i < 7; i++) {
            UNO carta = pozo.sacarCarta();
            if (carta != null) {
                mano.add(carta);
            }
        }
    }

    public UNO jugarAutomatico(UNO cartaDelPozo) {
        for (UNO carta : mano) {
            // Verificar si se puede jugar la carta
            if (puedeJugar(carta, cartaDelPozo)) {
                mano.remove(carta);
                return carta; // Juega la carta
            }
        }
        return null; // No puede jugar ninguna carta
    }

    public void agregarCarta(UNO carta) {
        mano.add(carta); // Agregar carta a la mano
    }

    public List<UNO> getMano() {
        return mano;
    }

    public String getNombre() {
        return nombre;
    }

    private boolean puedeJugar(UNO carta, UNO cartaDelPozo) {
        return carta.color.equals(cartaDelPozo.color) || carta.numero == cartaDelPozo.numero;
    }
}
