// Universidad del Valle de Guatemala
// Programación Orientada a Objetos - POO
// Ejercicio 4 - Modelación con Herencia
// Uso de Modelo Vista Controlador - MVC
// María Jimena Vásquez 25092

// Main.java

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al juego de combate!");
        int opcion = 0;
        Controlador controlador = new Controlador(null, null);
        Scanner teclado = new Scanner(System.in);

        while (opcion != 3) {
            // Menú principal
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("1. Crear Jugador");
            System.out.println("2. Iniciar Combate");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                // Opción 1
                case 1: {
                    if (controlador.getJugador() != null) {
                        System.out.println("El jugador ya ha sido creado: " + controlador.getJugador());
                        System.out.println("Desea crear un nuevo jugador? (1: Si, 2: No)");
                        int respuesta = teclado.nextInt();
                        if (respuesta == 2) {
                            break;
                        }
                    }

                    // Crear un nuevo jugador
                    System.out.print("Ingrese el nombre del jugador: ");
                    String nombreJugador = teclado.next();

                    // Asignar rol
                    System.out.println("Opciones de rol:");
                    for (Rol r : controlador.getRolesJugador())
                        System.out.println("- " + r.getNombre() + ": " + r);
                    System.out.print("Ingrese el rol del jugador (Guerrero/Explorador): ");
                    String tipoJugador = teclado.next();
                    while (!tipoJugador.equalsIgnoreCase("Guerrero") && !tipoJugador.equalsIgnoreCase("Explorador")) {
                        System.out.print("Rol no valido. Ingrese Guerrero o Explorador: ");
                        tipoJugador = teclado.next();
                    }
                    Rol rolJugador = controlador.asignarRol(tipoJugador);

                    // Asignar items según el rol
                    int cantidadItems = rolJugador.getMaxItems();
                    System.out.println("Puedes elegir hasta " + cantidadItems + " items.");
                    ArrayList<Item> itemsJugador = new ArrayList<>();
                    for (int i = 0; i < cantidadItems; i++) {
                        for (Item it : controlador.getItems())
                            System.out.println("- " + it.getNombre() + ": " + it);
                        System.out.print("Ingrese el nombre del item #" + (i+1) + ": ");
                        String nombreItem = teclado.next();
                        Item item = controlador.asignarItem(nombreItem);
                        if (item != null) {
                            itemsJugador.add(item);
                        } else {
                            System.out.println("Item no válido.");
                            i--;
                        }
                    }
                    Jugador jugador = new Jugador(nombreJugador, rolJugador, itemsJugador);
                    controlador.setJugador(jugador);
                    System.out.println("Jugador creado: " + jugador);
                    break;
                }
                // Opción 2
                case 2: {
                    // Si el jugador no ha sido creado, no se puede iniciar el combate
                    if (controlador.getJugador() == null) {
                        System.out.println("Debe crear primero el jugador. Regresa cuando el jugador haya sido creado.");
                        break;
                    }

                    // Generar enemigos aleatorios para la batalla (1-3)
                    int cantidadEnemigos = (int) (Math.random() * 3) + 1;
                    Enemigo[] enemigos = new Enemigo[cantidadEnemigos];
                    for (int i = 0; i < cantidadEnemigos; i++) {
                        int tipo = i % controlador.getRolesEnemigo().length;
                        Habilidad habilidadEnemigo = controlador.getHabilidadEnemigo(tipo);
                        enemigos[i] = new Enemigo(controlador.getRolesEnemigo()[tipo].getNombre() + " #" + (i+1), controlador.getRolesEnemigo()[tipo], habilidadEnemigo);
                    }
                    controlador.setEnemigos(enemigos);

                    // Iniciar el registro de acciones
                    controlador.getRegistroAcciones().clear();

                    System.out.println("\n¡Batalla iniciada contra " + cantidadEnemigos + " enemigo(s)!");
                    System.out.println(controlador.getJugador().mensajeInicio());

                    // Mostrar enemigos
                    for (int i = 0; i < enemigos.length; i++) {
                        System.out.println("Enemigo " + (i+1) + ": " + enemigos[i]);
                    }

                    // Batalla principal
                    int batallaActiva = 1;
                    while (batallaActiva == 1) {
                        // Mostrar status de todos los combatientes
                        System.out.println("\n-- STATUS DE COMBATIENTES --");
                        System.out.println(controlador.getJugador());
                        for (int i = 0; i < enemigos.length; i++) {
                            System.out.println("Enemigo " + (i+1) + ": " + enemigos[i]);
                        }
                        // Mostrar registro de últimas 3 acciones
                        System.out.println("-- Últimas acciones --");
                        for (String accion : controlador.getRegistroAcciones()) {
                            System.out.println(accion);
                        }

                        // Turno del jugador
                        System.out.println("\nTurno del jugador:");
                        System.out.println("1. Atacar");
                        System.out.println("2. Usar item");
                        System.out.println("3. Salir de la batalla");
                        System.out.print("Seleccione acción: ");
                        int accionJugador = teclado.nextInt();

                        if (accionJugador == 3) { // Salir
                            System.out.println("¡Has huido de la batalla!");
                            batallaActiva = 0;
                            break;
                        }

                        // Seleccionar objetivo si hay más de uno vivo
                        ArrayList<Integer> enemigosVivos = new ArrayList<>();
                        for (int i = 0; i < enemigos.length; i++) {
                            if (enemigos[i].getPuntosVida() > 0) enemigosVivos.add(i);
                        }
                        if (enemigosVivos.isEmpty()) {
                            System.out.println("¡Todos los enemigos han sido derrotados!");
                            System.out.println(controlador.getJugador().mensajeGanar());
                            break;
                        }

                        int objetivo_i = enemigosVivos.size() == 1 ? enemigosVivos.get(0) : -1;
                        if (enemigosVivos.size() > 1) {
                            System.out.println("Seleccione el enemigo objetivo:");
                            for (int idx : enemigosVivos) {
                                System.out.println((idx + 1) + ": " + enemigos[idx].getNombre());
                            }
                            System.out.print("Ingrese el número del enemigo: ");
                            objetivo_i = teclado.nextInt() - 1;
                            while (!enemigosVivos.contains(objetivo_i)) {
                                System.out.print("Enemigo no válido. Ingrese nuevamente: ");
                                objetivo_i = teclado.nextInt() - 1;
                            }
                        }

                        Enemigo enemigoObjetivo = enemigos[objetivo_i];
                        String mensajeJugador = "";

                        if (accionJugador == 1) {
                            mensajeJugador = controlador.getJugador().atacar(enemigoObjetivo);
                        } else if (accionJugador == 2) { // Usar item
                            ArrayList<Item> items = controlador.getJugador().getItems();
                            if (items.isEmpty()) {
                                mensajeJugador = "No tienes items disponibles.";
                            } else {
                                System.out.println("Items disponibles:");
                                for (int k = 0; k < items.size(); k++)
                                    System.out.println((k+1) + ": " + items.get(k));
                                System.out.print("Selecciona el item a usar (número): ");
                                int itemIndex = teclado.nextInt() - 1;
                                if (itemIndex >= 0 && itemIndex < items.size()) {
                                    Item item = items.remove(itemIndex); // consumir item
                                    mensajeJugador = item.usar(controlador.getJugador()); // los items solo se pueden usar sobre ti mismo (puedes cambiar esto si lo deseas)
                                } else {
                                    mensajeJugador = "Ítem no válido.";
                                }
                            }
                        } else {
                            mensajeJugador = "Acción no válida. Pierdes turno.";
                        }
                        controlador.agregarAccion("Jugador: " + mensajeJugador);
                        System.out.println(mensajeJugador);
                        
                        if (enemigoObjetivo.getPuntosVida() <= 0) {
                            controlador.agregarAccion(enemigoObjetivo.mensajePerder());
                            System.out.println(enemigoObjetivo.mensajePerder());

                            // Invocar jefe tras vencer enemigo
                            Jefe jefeFinal = controlador.invocarJefe(enemigoObjetivo);
                            System.out.println("\n¡Ha aparecido el " + jefeFinal.getNombre() + "!");

                            // Batalla contra el jefe
                            while (controlador.getJugador().getPuntosVida() > 0 && jefeFinal.getPuntosVida() > 0) {
                                System.out.println("\nTurno del jugador contra Jefe:");
                                System.out.println("1. Atacar");
                                System.out.println("2. Usar item");
                                System.out.println("3. Salir de la batalla");
                                System.out.print("Seleccione acción: ");
                                int accionJugadorJefe = teclado.nextInt();
                                String mensajeJugadorJefe = "";

                                if (accionJugadorJefe == 3) {
                                    System.out.println("¡Te escapaste del Jefe!");
                                    batallaActiva = 0;
                                    break;
                                }
                                if (accionJugadorJefe == 1) {
                                    mensajeJugadorJefe = controlador.getJugador().atacar(jefeFinal);
                                } else if (accionJugadorJefe == 2) {
                                    ArrayList<Item> items = controlador.getJugador().getItems();
                                    if (items.isEmpty()) {
                                        mensajeJugadorJefe = "No tienes items disponibles.";
                                    } else {
                                        System.out.println("Items disponibles:");
                                        for (int k = 0; k < items.size(); k++)
                                            System.out.println((k+1) + ": " + items.get(k));
                                        System.out.print("Selecciona el item a usar (número): ");
                                        int itemIndex = teclado.nextInt() - 1;
                                        if (itemIndex >= 0 && itemIndex < items.size()) {
                                            Item item = items.remove(itemIndex); // consumir item
                                            mensajeJugadorJefe = item.usar(controlador.getJugador());
                                        } else {
                                            mensajeJugadorJefe = "Ítem no válido.";
                                        }
                                    }
                                } else {
                                    mensajeJugadorJefe = "Acción no válida. Pierdes turno.";
                                }
                                controlador.agregarAccion("Jugador vs Jefe: " + mensajeJugadorJefe);
                                System.out.println(mensajeJugadorJefe);

                                // Turno del jefe si está vivo
                                if (jefeFinal.getPuntosVida() > 0) {
                                    int accionEnemigo = (int)(Math.random() * 2) + 1;
                                    String mensajeJefe = "";
                                    if (accionEnemigo == 1) {
                                        mensajeJefe = jefeFinal.atacar(controlador.getJugador());
                                    } else {
                                        mensajeJefe = jefeFinal.usarHabilidad(controlador.getJugador());
                                    }
                                    controlador.agregarAccion("Jefe: " + mensajeJefe);
                                    System.out.println(mensajeJefe);
                                }

                                // Mostrar estatus
                                System.out.println("\nJugador: " + controlador.getJugador());
                                System.out.println("Jefe: " + jefeFinal);

                                // Fin de jefe
                                if (jefeFinal.getPuntosVida() <= 0) {
                                    System.out.println(controlador.getJugador().mensajeGanar());
                                    System.out.println(jefeFinal.mensajePerder());
                                    batallaActiva = 0;
                                    break;
                                }
                                if (controlador.getJugador().getPuntosVida() <= 0) {
                                    System.out.println(jefeFinal.mensajeGanar());
                                    System.out.println(controlador.getJugador().mensajePerder());
                                    batallaActiva = 0;
                                    break;
                                }
                            }
                            break;
                        }

                        // Mensaje si el jugador perdió
                        if (controlador.getJugador().getPuntosVida() <= 0) {
                            System.out.println(controlador.getJugador().getNombre() + " ha sido derrotado. Fin del juego.");
                            batallaActiva = 0;
                            break;
                        }

                        // Turno de los enemigos si queda alguno vivo
                        for (int e : enemigosVivos) {
                            Enemigo enemigo = enemigos[e];
                            if (enemigo.getPuntosVida() > 0) {
                                int accionEnemigo = (int)(Math.random() * 2) + 1;
                                String mensajeEnemigo;
                                if (accionEnemigo == 1) {
                                    mensajeEnemigo = enemigo.atacar(controlador.getJugador());
                                } else {
                                    mensajeEnemigo = enemigo.usarHabilidad(controlador.getJugador());
                                }
                                controlador.agregarAccion(enemigo.getNombre() + ": " + mensajeEnemigo);
                                System.out.println(mensajeEnemigo);

                                if (controlador.getJugador().getPuntosVida() <= 0) {
                                    System.out.println(enemigo.mensajeGanar());
                                    System.out.println(controlador.getJugador().mensajePerder());
                                    batallaActiva = 0;
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
                // Opción 3
                case 3: {
                    System.out.println("Saliendo del juego...");
                    System.out.println("Gracias por jugar, ¡hasta luego!");
                    break;
                }
                // Si el usuario ingresa una opción que no está en el rango o menú
                default: {
                    System.out.println("Opción no válida. Por favor, inténtelo de nuevo");
                    break;
                }
            }
        }
    }
}