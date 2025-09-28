public class Item {
    private String nombre;
    private int bonusVida;
    private int bonusAtaque;

    // Constructor
    public Item(String nombre, int bonusVida, int bonusAtaque) {
        this.nombre = nombre;
        this.bonusVida = bonusVida;
        this.bonusAtaque = bonusAtaque;
    }

    // Gets y Sets
    public String getNombre() {
        return nombre;
    }
    public int getBonusVida() {
        return bonusVida;
    }
    public int getBonusAtaque() {
        return bonusAtaque;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setBonusVida(int bonusVida) {
        this.bonusVida = bonusVida;
    }
    public void setBonusAtaque(int bonusAtaque) {
        this.bonusAtaque = bonusAtaque;
    }
    public Item[] getItems() {
        return new Item[]{this};
    }

    // Método para usar el ítem en un combatiente
    public String usar(Combatiente objetivo) {
        if (bonusVida > 0) {
            objetivo.rol.setPuntosVida(objetivo.rol.getPuntosVida() + bonusVida);
        }
        if (bonusAtaque > 0) {
            objetivo.rol.setPuntosAtaque(objetivo.rol.getPuntosAtaque() + bonusAtaque);
        }
        return "Usando el item: " + nombre + ". Bonus Vida: " + bonusVida + ", Bonus Ataque: " + bonusAtaque;
    }
    
    @Override
    // Representación en cadena del ítem
    public String toString() {
        return "Item: " + nombre + ", Bonus Vida: " + bonusVida + ", Bonus Ataque: " + bonusAtaque;
    }

}