public class UNO {
    public String color;
    public int numero;  // Usamos números para cartas numéricas (0-9) y valores especiales para +2, +4, etc.
    public String tipoCarta;  // Puede ser "normal", "+2", "+4", "comodín", "reversa", "salto"

    // Constructor para cartas numéricas
    public UNO(String color, int numero) {
        this.color = color;
        this.numero = numero;
        this.tipoCarta = "normal";  // Cartas numéricas se consideran normales
    }

    // Constructor para cartas especiales
    public UNO(String tipoCarta) {
        this.color = "ninguno";  // Los comodines no tienen color inicial
        this.numero = 0;  // Las cartas especiales no tienen un número
        this.tipoCarta = tipoCarta;
    }

    // Métodos para identificar tipos de carta
    public boolean esMasDos() {
        return "+2".equals(tipoCarta);
    }

    public boolean esMasCuatro() {
        return "+4".equals(tipoCarta);
    }

    public boolean esComodin() {
        return "comodín".equals(tipoCarta);
    }

    // Para otras cartas especiales
    public boolean esReversa() {
        return "reversa".equals(tipoCarta);
    }

    public boolean esSalto() {
        return "salto".equals(tipoCarta);
    }

    @Override
    public String toString() {
        if ("comodín".equals(tipoCarta) || "normal".equals(tipoCarta)) {
            return tipoCarta.equals("normal") ? color + " " + numero : "Comodín";
        } else {
            return tipoCarta;  // Para +2, +4, etc.
        }
    }
}
