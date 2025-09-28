public class Rol {
    // Atributos
    private String nombre;
    private int puntosAtaque;
    private int puntosVida;
    private int maxItems;

    // Constructor
    public Rol(String nombre, int puntosAtaque, int puntosVida, int maxItems) {
        this.nombre = nombre;
        this.puntosAtaque = puntosAtaque;
        this.puntosVida = puntosVida;
        this.maxItems = maxItems;
    }
    // Gets y Sets
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPuntosAtaque() {
        return puntosAtaque;
    }
    public int getMaxItems() {
        return maxItems;
    }
    public void setPuntosAtaque(int puntosAtaque) {
        this.puntosAtaque = puntosAtaque;
    }
    public int getPuntosVida() {
        return puntosVida;
    }
    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }
    
    @Override
    // Representación en cadena del rol
    public String toString() {
        return "Rol: " + nombre + ", Puntos de Ataque: " + puntosAtaque + ", Puntos de Vida: " + puntosVida;
    }
}