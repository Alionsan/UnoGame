import javax.swing.*;

public class DibujoManoJugadores extends JFrame {
    private JPanel panelPrincipal;
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
        setSize(1000, 800); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.pozo = pozo; // Inicializa el pozo
        this.cartaDelPozo = pozo.sacarCarta(); // Saca la carta del pozo inicial
        this.turnoJugador1 = true;

        // Crear el panel principal sin layout
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null); // Usar un layout nulo para colocar componentes con coordenadas

        // Crear los paneles de cartas personalizadas
        panelCartasJugador1 = new PanelCartas(jugador1.getMano());
        panelCartasJugador2 = new PanelCartas(jugador2.getMano());
        panelCartaPozo = new PanelCartaPozo(cartaDelPozo);
        turnoLabel = new JLabel("Turno: " + jugador1.getNombre(), SwingConstants.CENTER);

        // Establecer dimensiones y posiciones para los paneles
        panelCartasJugador1.setBounds(50, 500, 700, 150); // Jugador 1 en la parte inferior
        panelCartasJugador2.setBounds(50, 50, 700, 150);  // Jugador 2 en la parte superior

        // Calcular la posición X para centrar el panel de la carta del pozo
        int xPozo = (getWidth() - 300) / 2; // Centrado en la ventana (300 es el ancho de la carta)
        panelCartaPozo.setBounds(xPozo, 200, 300, 200);  // Establecer posición centrada

        turnoLabel.setBounds(300, 680, 200, 30); // Etiqueta de turno en la parte inferior

        // Añadir los paneles al panel principal
        panelPrincipal.add(panelCartasJugador1);
        panelPrincipal.add(panelCartasJugador2);
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
                if (turnoJugador1) {
                    panelCartasJugador2.actualizarCartas(jugador2.getMano());
                } else {
                    panelCartasJugador1.actualizarCartas(jugador1.getMano());
                }
            } else if (cartaJugada.esMasCuatro()) { // Si es un +4
                for (int i = 0; i < 4; i++) {
                    UNO nuevaCarta = pozo.sacarCarta();
                    if (nuevaCarta != null) {
                        siguienteJugador.agregarCarta(nuevaCarta);
                    }
                }
                // Actualizar la mano del siguiente jugador
                if (turnoJugador1) {
                    panelCartasJugador2.actualizarCartas(jugador2.getMano());
                } else {
                    panelCartasJugador1.actualizarCartas(jugador1.getMano());
                }
            }
    
            // Cambiar turno si no fue una carta especial +2 o +4
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
    }
    
    private void mostrarGanador(String nombre) {
        JOptionPane.showMessageDialog(this, "¡Felicidades " + nombre + "! Has ganado el juego.", "Ganador", JOptionPane.INFORMATION_MESSAGE);
        // Opcional: cerrar la ventana o reiniciar el juego
        dispose(); // Cerrar la ventana
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
