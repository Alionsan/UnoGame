import java.awt.*;
import javax.swing.*;

class PanelCartaPozo extends JPanel {
    private UNO carta;

    public PanelCartaPozo(UNO carta) {
        this.carta = carta;
        setPreferredSize(new Dimension(200, 200)); // Tamaño del panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Calcular las coordenadas para centrar la carta en el panel
        int x = (getWidth() - 100) / 2; // 100 es el ancho de la carta
        int y = (getHeight() - 150) / 2; // 150 es la altura de la carta
        dibujarCarta(g, carta, x, y);  // Dibujar la carta centrada
    }

    public void actualizarCarta(UNO carta) {
        this.carta = carta;
        repaint();
    }

    private void dibujarCarta(Graphics g, UNO carta, int x, int y) {
        // Obtener el color de la carta
        Color colorCarta = obtenerColorCarta(carta.color);

        // Dibujar el rectángulo que representa la carta con el color adecuado
        g.setColor(colorCarta);
        g.fillRect(x, y, 100, 150);

        // Dibujar el borde de la carta
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 100, 150);

        // Dibujar el número o símbolo centrado
        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        String textoCarta = carta.numero != -1 ? String.valueOf(carta.numero) : carta.toString();
        int textWidth = fm.stringWidth(textoCarta);
        int textHeight = fm.getAscent();
        g.drawString(textoCarta, x + (100 - textWidth) / 2, y + (150 + textHeight) / 2);
    }

    private Color obtenerColorCarta(String color) {
        switch (color) {
            case "Rojo": return Color.RED;
            case "Azul": return Color.BLUE;
            case "Verde": return Color.GREEN;
            case "Amarillo": return Color.YELLOW;
            default: return Color.BLACK; // Comodín o error
        }
    }
}
