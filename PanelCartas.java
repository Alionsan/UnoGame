import java.awt.*;
import java.util.List;
import javax.swing.*;

class PanelCartas extends JPanel {
    private List<UNO> cartas;

    public PanelCartas(List<UNO> cartas) {
        this.cartas = cartas;
        setPreferredSize(new Dimension(800, 150)); // Tamaño del panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        for (UNO carta : cartas) {
            dibujarCarta(g, carta, x, 10);
            x += 110; // Separación entre las cartas
        }
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

    // Método para obtener el color según el color de la carta
    private Color obtenerColorCarta(String color) {
        switch (color) {
            case "Rojo": return Color.RED;
            case "Azul": return Color.BLUE;
            case "Verde": return Color.GREEN;
            case "Amarillo": return Color.YELLOW;
            default: return Color.BLACK; // Comodín o error
        }
    }

    public void actualizarCartas(List<UNO> nuevasCartas) {
        this.cartas = nuevasCartas;
        repaint(); // Vuelve a dibujar las cartas
    }
}
