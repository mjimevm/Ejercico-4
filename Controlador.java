// Controlador.java

import java.util.ArrayList;

public class Controlador {
    private Jugador jugador;
    private Enemigo[] enemigos;

    // Roles disponibles para el jugador
    private Rol[] rolesJugador = {
        new Rol("Guerrero", 10, 100, 2),
        new Rol("Explorador", 7, 90, 4)
    };

    // Roles disponibles para los enemigos
    private Rol[] rolesEnemigo = {
        new Rol("Goblin", 5, 50, 0),
        new Rol("Orco", 8, 80, 0)
    };

    // Lista de ítems disponibles
    private Item[] items = {
        new Item("Espada", 0, 5),
        new Item("Arco", 0, 3),
        new Item("Pocion", 20, 0),
        new Item("Escudo", 0, 2),
        new Item("Daga", 0, 4),
        new Item("Hacha", 0, 6)
    };

    // Lista de habilidades especiales para enemigos
    private Habilidad[] habilidades = {
        new Habilidad("Furia", 3),
        new Habilidad("Sigilo", 4)
    };

    // Registro de las últimas 3 acciones en la batalla
    private ArrayList<String> registroAcciones = new ArrayList<>();
    
    // Añade una nueva acción al registro y mantiene solo las últimas 3
    public void agregarAccion(String accion) {
        if (registroAcciones.size() == 3) registroAcciones.remove(0);
        registroAcciones.add(accion);
    }

    // Devuelve el registro de las últimas acciones
    public ArrayList<String> getRegistroAcciones() { 
        return registroAcciones; 
    }

    // Constructor del controlador (recibe jugador y enemigos iniciales)
    public Controlador(Jugador jugador, Enemigo[] enemigos) {
        this.jugador = jugador;
        this.enemigos = enemigos;
    }

    // Gets y Sets
    public Jugador getJugador() {
        return jugador;
    }
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    public Enemigo[] getEnemigos() {
        return enemigos;
    }
    public void setEnemigos(Enemigo[] enemigos) {
        this.enemigos = enemigos;
    }
    public Rol[] getRolesJugador() {
        return rolesJugador;
    }
    public Rol[] getRolesEnemigo() {
        return rolesEnemigo;
    }
    public Item[] getItems() {
        return items;
    }
    public Habilidad[] getHabilidades() {
        return habilidades;
    }


    // Asigna y devuelve un rol al jugador según su nombre
    public Rol asignarRol(String nombre) {
        for (Rol rol : rolesJugador) {
            if (rol.getNombre().equalsIgnoreCase(nombre))
                return new Rol(rol.getNombre(), rol.getPuntosAtaque(), rol.getPuntosVida(), rol.getMaxItems());
        }
        return null;
    }

    // Asigna y devuelve un ítem según el nombre
    public Item asignarItem(String nombre) {
        for (Item item : items) {
            if (item.getNombre().equalsIgnoreCase(nombre)) {
                return item;
            }
        }
        return null;
    }


    // Obtiene una habilidad de enemigo, según índice
    public Habilidad getHabilidadEnemigo(int i) {
        if (i >= 0 && i < habilidades.length) {
            return habilidades[i];
        }
        return null;
    }

    // Invoca y devuelve un jefe basado en un enemigo existente
    public Jefe invocarJefe(Enemigo enemigo) {
        return new Jefe(enemigo);
    }

    // Simula un turno de combate entre el jugador y un enemigo, devolviendo los mensajes del turno
    public String[] iniciarCombate(int accionJugador, int i) {
        String[] mensajes = new String[2];
        Enemigo enemigo = enemigos[i];
        if (accionJugador == 1) {
            mensajes[0] = jugador.atacar(enemigo);
        } else if (accionJugador == 2) {
            mensajes[0] = jugador.usarItem(enemigo);
        } else {
            mensajes[0] = "Acción no válida. Pierdes turno.";
        }
        if (enemigo.getPuntosVida() > 0) {
            int accionEnemigo = (int)(Math.random() * 2) + 1;
            if (accionEnemigo == 1) {
                mensajes[1] = enemigo.atacar(jugador);
            } else {
                mensajes[1] = enemigo.usarHabilidad(jugador);
            }
        } else {
            mensajes[1] = enemigo.mensajePerder();
        }
        return mensajes;
    }

    // Simula un turno de combate entre el jugador y un jefe, devolviendo los mensajes del turno
    public String[] iniciarCombateJefe(int accionJugador, Jefe jefe) {
        String[] mensajes = new String[2];
        if (accionJugador == 1) {
            mensajes[0] = jugador.atacar(jefe);
        } else if (accionJugador == 2) {
            mensajes[0] = jugador.usarItem(jefe);
        } else {
            mensajes[0] = "Acción no válida. Pierdes turno.";
        }
        if (jefe.getPuntosVida() > 0) {
            int accionEnemigo = (int)(Math.random() * 2) + 1;
            if (accionEnemigo == 1) {
                mensajes[1] = jefe.atacar(jugador);
            } else {
                mensajes[1] = jefe.usarHabilidad(jugador);
            }
        } else {
            mensajes[1] = jefe.mensajePerder();
        }
        return mensajes;
    }
}