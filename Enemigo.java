
// Esta clase ha sido heredada de la clase Combatiente
public class Enemigo extends Combatiente {
    private Habilidad habilidad;

    // Constructor
    public Enemigo(String nombre, Rol rol, Habilidad habilidad) {
        super(nombre, rol);
        this.habilidad = habilidad;
    }

    // Gets y Sets 
    public Habilidad getHabilidad() { 
        return habilidad; 
    }
    public void setHabilidad(Habilidad habilidad) { 
        this.habilidad = habilidad; 
    }
    public String getNombre() {
        return super.getNombre();
    }
    // Método para usar la habilidad del enemigo en un combatiente
    public String usarHabilidad(Combatiente objetivo) {
        return habilidad.aplicar(objetivo);
    }

    @Override
    // Representación en cadena del enemigo
    public String toString() {
        return "Enemigo: " + getNombre() + ", " + getRol().toString() + ", " + habilidad.toString();
    }
}