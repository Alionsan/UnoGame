import java.util.ArrayList;
import java.util.List;

public class Juego {
    public static void main(String[] args) {
        // Crear el pozo de cartas
        Pozo pozo = new Pozo();

        //by Kevyn Medina
        // Crear la carta inicial en el centro
        UNO cartaCentro = pozo.sacarCarta();
        System.out.println("Carta en el centro para comenzar el juego: " + cartaCentro);

        // Crear jugadores
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Jugador 1", pozo));
        jugadores.add(new Jugador("Jugador 2", pozo));

        // Turno de los jugadores para jugar
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre() + " es su turno.");
            UNO cartaJugada = jugador.seleccionarCarta();
            if (cartaJugada != null) {
                cartaCentro = jugador.jugarCarta(cartaJugada); // Jugar la carta
                System.out.println(jugador.getNombre() + " ha jugado: " + cartaCentro);
            }
        }

        // Contar cuántas cartas quedan en el pozo
        System.out.println("Cartas restantes en el pozo: " + pozo.contarCartas());

        // Revisar las cartas en el pozo
        pozo.revisarPozo();
    }
}
