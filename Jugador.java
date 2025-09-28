import java.util.ArrayList;

public class Jugador extends Combatiente {
    //  Arraylist del atributo items
    private ArrayList<Item> items;

    // Constructor
    public Jugador(String nombre, Rol rol, ArrayList<Item> items) {
        super(nombre, rol);
        this.items = items;
    }

    // Usa un ítem en la posición 'i' sobre el objetivo especificado
    public String usarItem(int i, Combatiente objetivo) {
        if (i >= 0 && i < items.size()) {
            Item item = items.get(i);
            return item.usar(objetivo);
        }
        return "Ítem no válido.";
    }

    // Usa el primer ítem disponible sobre el objetivo especificado
    public String usarItem(Combatiente objetivo) {
        return items.get(0).usar(objetivo);
    }

    // Gets
    public ArrayList<Item> getItems() {
        return items;
    }

    // Representación en texto del jugador, que muestra el nombre, rol e items
    @Override
    public String toString() {
        return "Jugador: " + getNombre() + ", " + getRol().toString() + ", Items: " + items.toString();
    }
}