public class Combatiente {
    protected String nombre;
    protected Rol rol;

    // Constructor
    public Combatiente(String nombre, Rol rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    // Gets y Sets
    public String getNombre() {
        return nombre;
    }
    public Rol getRol() {
        return rol;
    }
    public int getPuntosVida() {
        return rol.getPuntosVida();
    }
    public int getPoderAtaque() {
        return rol.getPuntosAtaque();
    }
    public void setPuntosVida(int puntosVida) {
        rol.setPuntosVida(puntosVida);
    }
    public void setPoderAtaque(int poderAtaque) {
        rol.setPuntosAtaque(poderAtaque);
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    // Métodos de combate
    public String mensajeInicio() {
        return "¡Ha iniciado la batalla!";
    }
    public String mensajeGanar() {
        return this.nombre + " ha ganado la batalla.";
    }
    public String mensajePerder() {
        return this.nombre + " ha perdido la batalla.";
    }
    public String atacar(Combatiente objetivo) {
        objetivo.rol.setPuntosVida(objetivo.rol.getPuntosVida() - this.rol.getPuntosAtaque());
        System.out.println(this.nombre + " ataca a " + objetivo.nombre + " por " + this.rol.getPuntosAtaque() + " puntos de daño.");
        if (objetivo.rol.getPuntosVida() <= 0) {
            objetivo.rol.setPuntosVida(0);
            return objetivo.nombre + " ha sido derrotado.";
        } else {
            return objetivo.nombre + " tiene " + objetivo.rol.getPuntosVida() + " puntos de vida restantes.";
        }
    }
    @Override
    // Representación en cadena del combatiente
    public String toString() {
        return "Nombre: " + nombre + ", " + rol.toString();
    }
}