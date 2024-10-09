public class UNO {
    private final String color;  // Color de la carta
    private final int numero;    // -1 para cartas especiales (comodines)
    private final boolean esComodin; // Indica si es un comodín
    private final String tipoComodin; // Tipo de comodín

    // Constructor
    public UNO(String color, int numero, boolean esComodin, String tipoComodin) {
        this.color = color;
        this.numero = numero;
        this.esComodin = esComodin;
        this.tipoComodin = tipoComodin;
    }

    // Método para representar la carta como una cadena
    @Override
    public String toString() {
        if (esComodin) {
            return "Carta Comodín: " + tipoComodin;
        } else if (numero == -1) {
            return "Carta Especial " + tipoComodin + " de color " + color;
        } else {
            return "Carta " + color + " " + numero;
        }
    }

    // Método para saber si la carta es un comodín
    public boolean esComodin() {
        return esComodin;
    }
}

