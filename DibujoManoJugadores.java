import java.awt.*;
import static java.lang.System.exit;
import javax.swing.*;

public class DibujoManoJugadores extends JFrame {
    private JPanel panelPrincipal;
    private JScrollPane scrollPanelCartasJugador1;
    private JScrollPane scrollPanelCartasJugador2;
    private PanelCartas panelCartasJugador1;
    private PanelCartas panelCartasJugador2;
    private PanelCartaPozo panelCartaPozo;
    private Jugador jugador1;
    private Jugador jugador2;
    private Pozo pozo;
    private UNO cartaDelPozo;
    private JLabel turnoLabel;
    private boolean turnoJugador1;

    public DibujoManoJugadores(Jugador jugador1, Jugador jugador2, Pozo pozo) {
        // Configurar la ventana
        setTitle("Cartas de UNO");
        setSize(800, 800); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.pozo = pozo; // Inicializa el pozo
        this.cartaDelPozo = pozo.sacarCarta(); // Saca la carta del pozo inicial
        this.turnoJugador1 = true;

        // Crear el panel principal sin layout
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null); // Usar un layout nulo para colocar componentes con coordenadas

        // Crear los paneles de cartas personalizadas con FlowLayout para que las cartas se ajusten dinámicamente
        panelCartasJugador1 = new PanelCartas(jugador1.getMano());
        panelCartasJugador1.setLayout(new FlowLayout(FlowLayout.LEFT)); // FlowLayout para ajustar las cartas

        panelCartasJugador2 = new PanelCartas(jugador2.getMano());
        panelCartasJugador2.setLayout(new FlowLayout(FlowLayout.LEFT)); // FlowLayout para ajustar las cartas

        panelCartaPozo = new PanelCartaPozo(cartaDelPozo);
        turnoLabel = new JLabel("Turno: " + jugador1.getNombre(), SwingConstants.CENTER);

        // Crear JScrollPane para agregar scroll a los paneles de cartas
        scrollPanelCartasJugador1 = new JScrollPane(panelCartasJugador1, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanelCartasJugador1.setPreferredSize(new Dimension(700, 150)); // Ajustar el tamaño visible
        panelCartasJugador1.setPreferredSize(new Dimension(1500, 150)); // Tamaño preferido mayor para activar el scroll horizontal

        scrollPanelCartasJugador2 = new JScrollPane(panelCartasJugador2, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanelCartasJugador2.setPreferredSize(new Dimension(700, 150)); // Ajustar el tamaño visible
        panelCartasJugador2.setPreferredSize(new Dimension(1500, 150)); // Tamaño preferido mayor para activar el scroll horizontal

        // Establecer dimensiones y posiciones para los JScrollPane
        scrollPanelCartasJugador1.setBounds(50, 500, 700, 168); // Jugador 1 en la parte inferior
        scrollPanelCartasJugador2.setBounds(50, 50, 700, 168);  // Jugador 2 en la parte superior

        // Calcular la posición X para centrar el panel de la carta del pozo
        int xPozo = (getWidth() - 300) / 2; // Centrado en la ventana (300 es el ancho de la carta)
        panelCartaPozo.setBounds(xPozo, 250, 300, 200);  // Establecer posición centrada

        turnoLabel.setBounds(300, 680, 200, 30); // Etiqueta de turno en la parte inferior

        // Añadir los JScrollPane al panel principal
        panelPrincipal.add(scrollPanelCartasJugador1);
        panelPrincipal.add(scrollPanelCartasJugador2);
        panelPrincipal.add(panelCartaPozo);
        panelPrincipal.add(turnoLabel);

        // Añadir el panel principal a la ventana
        add(panelPrincipal);
    }

    private void jugarTurno() {
        Jugador jugadorActual = turnoJugador1 ? jugador1 : jugador2;
        Jugador siguienteJugador = turnoJugador1 ? jugador2 : jugador1;

        UNO cartaJugada = jugadorActual.jugarAutomatico(cartaDelPozo);

        // Comprobar si se jugó una carta
        if (cartaJugada != null) {
            cartaDelPozo = cartaJugada; // Actualizar carta del pozo
            panelCartaPozo.actualizarCarta(cartaDelPozo); // Actualizar visualmente

            // Actualiza la mano del jugador actual
            if (turnoJugador1) {
                panelCartasJugador1.actualizarCartas(jugador1.getMano());
            } else {
                panelCartasJugador2.actualizarCartas(jugador2.getMano());
            }

            // Verificar si el jugador actual ha ganado
            if (jugadorActual.getMano().isEmpty()) {
                mostrarGanador(jugadorActual.getNombre());
                return; // Termina el juego
            }

            // Lógica para cartas especiales +2 y +4
            if (cartaJugada.esMasDos()) { // Si es un +2
                for (int i = 0; i < 2; i++) {
                    UNO nuevaCarta = pozo.sacarCarta();
                    if (nuevaCarta != null) {
                        siguienteJugador.agregarCarta(nuevaCarta);
                    }
                }
                // Actualizar la mano del siguiente jugador
                if (!turnoJugador1) {
                    panelCartasJugador1.actualizarCartas(jugador1.getMano());
                } else {
                    panelCartasJugador2.actualizarCartas(jugador2.getMano());
                }
            } else if (cartaJugada.esMasCuatro()) { // Si es un +4
                for (int i = 0; i < 4; i++) {
                    UNO nuevaCarta = pozo.sacarCarta();
                    if (nuevaCarta != null) {
                        siguienteJugador.agregarCarta(nuevaCarta);
                    }
                }
                // Actualizar la mano del siguiente jugador
                if (!turnoJugador1) {
                    panelCartasJugador1.actualizarCartas(jugador1.getMano());
                } else {
                    panelCartasJugador2.actualizarCartas(jugador2.getMano());
                }
            }

            // Cambiar turno después de que se actualice la mano del siguiente jugador
            turnoJugador1 = !turnoJugador1;

            // Actualizar el texto de la etiqueta del turno
            turnoLabel.setText("Turno: " + (turnoJugador1 ? jugador1.getNombre() : jugador2.getNombre()));
            
        } else {
            // Si el jugador no puede jugar, toma una carta del pozo
            UNO nuevaCarta = pozo.sacarCarta();
            if (nuevaCarta != null) {
                jugadorActual.agregarCarta(nuevaCarta);
                // Actualiza la mano del jugador actual
                if (turnoJugador1) {
                    panelCartasJugador1.actualizarCartas(jugador1.getMano());
                } else {
                    panelCartasJugador2.actualizarCartas(jugador2.getMano());
                }
            }
        }
        actualizarPaneles();
    }

    private void mostrarGanador(String nombre) {
        JOptionPane.showMessageDialog(this, "¡Felicidades " + nombre + "! Has ganado el juego.", "Ganador", JOptionPane.INFORMATION_MESSAGE);
        SwingUtilities.invokeLater(() -> dispose()); // Cerrar la ventana usando una expresión lambda
        exit(1);
    }

    private void actualizarPaneles() {
        panelCartasJugador1.actualizarCartas(jugador1.getMano());
        panelCartasJugador2.actualizarCartas(jugador2.getMano());
        panelCartasJugador1.revalidate(); // Recalcular el layout del panel de cartas
        panelCartasJugador2.revalidate();
    }

    public void iniciarJuego() {
        while (true) {
            jugarTurno(); // Jugar el turno automáticamente
            try {
                Thread.sleep(1000); // Pausa para simular el turno
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Crear pozo y jugadores
        Pozo pozo = new Pozo();
        Jugador jugador1 = new Jugador("Jugador 1", pozo);
        Jugador jugador2 = new Jugador("Jugador 2", pozo);

        // Crear y mostrar la ventana, pasando los jugadores y el pozo
        DibujoManoJugadores ventana = new DibujoManoJugadores(jugador1, jugador2, pozo);
        ventana.setVisible(true);

        // Iniciar el juego
        ventana.iniciarJuego();
    }
}
