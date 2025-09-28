public class Habilidad {
    private String nombre;
    private int poder;

    // Constructor
    public Habilidad(String nombre, int poder) {
        this.nombre = nombre;
        this.poder = poder;
    }
    
    // Gets y Sets
    public String getNombre() {
        return nombre;
    }
    public int getPoder() {
        return poder;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPoder(int poder) {
        this.poder = poder;
    }

    // Método para aplicar la habilidad a un combatiente
    public String aplicar(Combatiente objetivo) {
        objetivo.rol.setPuntosVida(objetivo.rol.getPuntosVida() - poder);
        return "Usando la habilidad: " + nombre + " con poder: " + poder + " sobre " + objetivo.getNombre();
    }
    @Override
    public String toString() {
        return "Habilidad: " + nombre + ", Poder: " + poder;
    }
}