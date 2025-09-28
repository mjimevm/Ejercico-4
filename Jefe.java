
// La clase Jefe es heredada de Enemigo y representa un enemigo más fuerte con una habilidad extra
public class Jefe extends Enemigo {
    private Habilidad habilidadExtra;

    // Constructor
    public Jefe(Enemigo base) {
        super("Jefe " + base.getNombre(), 
              new Rol(base.getRol().getNombre() + " Jefe", 
                      base.getRol().getPuntosAtaque() * 2,
                      base.getRol().getPuntosVida() * 2,   
                      base.getRol().getMaxItems()),
              base.getHabilidad());
        this.habilidadExtra = new Habilidad("Habilidad Jefe", 8);
    }

    @Override
    // Método para usar la habilidad del jefe, que puede usar su habilidad base o la extra
    public String usarHabilidad(Combatiente objetivo) {
        if (Math.random() < 0.5) {
            return super.usarHabilidad(objetivo);
        } else {
            return habilidadExtra.aplicar(objetivo);
        }
    }
}