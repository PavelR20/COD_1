import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GrafoNoDirigido1 grafo = new GrafoNoDirigido1();
        Set<String> nombresUbicaciones = new HashSet<>();

        while (true) {
            // Limpia la consola dependiendo del sistema operativo
            try {
                if (System.getProperty("os.name").contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }

            System.out.println("\n\n¿  ------Menu Principal-----   \n");
            System.out.println("¿   Qué acción desea realizar?            ");
            System.out.println("1.  Agregar ubicación                  ");
            System.out.println("2.  Agregar arista con peso              ");
            System.out.println("3.  Calcular ruta más corta desde una ubicación");
            System.out.println("4.  Mostrar todas las ubicaciones         ");
            System.out.println("5.  Modificar Ubicaciones              ");
            System.out.println("6.  Eliminar ubicacion           ");
            System.out.println("7.  Modificar Peso       ");
            System.out.println("8.  Eliminar Peso                      ");
            System.out.println("9.  Calcular árbol de expansión mínima desde un punto de origen (Prim) ");
            System.out.println("10. Calcular árbol de expansión mínima desde un punto de origen (Kruskal)");
            System.out.println("11. Algoritmo Floyd-Warshall                   ");
            System.out.println("12.  Minimizar Distancia                  ");
            System.out.println("13. Minimizar Tiempo                   ");
            System.out.println("\n");
            System.out.println("12. Salir                     ");


            System.out.print("\n Ingrese el número de la acción:");
            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
                continue;
            }


            switch (opcion) {
                case 1:

                    System.out.print("Ingrese el nombre de la ubicación: ");
                    String nombreUbicacion = scanner.nextLine().toLowerCase();
                    grafo.agregarUbicacion(new Ubicacion1(nombreUbicacion));
                    boolean agregarMasUbicaciones = true;
                    while (agregarMasUbicaciones) {
                        System.out.print("Ingrese el nombre de la ubicación: ");
                        nombreUbicacion = scanner.nextLine().toLowerCase();
                        if (nombresUbicaciones.contains(nombreUbicacion)) {
                            System.out.println("La ubicación ya ha sido agregada anteriormente.");
                        } else {
                            nombresUbicaciones.add(nombreUbicacion);
                            grafo.agregarUbicacion(new Ubicacion1(nombreUbicacion));
                        }

                        System.out.print("¿Desea agregar otra ubicación? (s/n): ");
                        String respuesta = scanner.nextLine().toLowerCase();
                        if (!respuesta.equals("s")) {
                            agregarMasUbicaciones = false;
                        }
                    }

                    break;

                case 2:
                    if (nombresUbicaciones.isEmpty()) {
                        System.out.println("No hay ubicaciones agregadas para asignarle un peso.");
                        break;
                    }

                    boolean agregarMasPesos = true;
                    while (agregarMasPesos) {
                        System.out.print("Ingrese el nombre del origen: ");
                        String nombreOrigen = scanner.nextLine().toLowerCase();
                        System.out.print("Ingrese el nombre del destino: ");
                        String nombreDestino = scanner.nextLine().toLowerCase();

                        System.out.print("Ingrese el peso de la arista: ");
                        int peso;
                        try {
                            peso = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException ex) {
                            System.out.println("Ingrese un número válido para el peso.");
                            continue; // Volver al inicio del bucle para que el usuario pueda ingresar un peso válido
                        }

                        if (peso <= 0) {
                            System.out.println("El peso debe ser un número positivo mayor que cero.");
                            continue; // Volver al inicio del bucle para que el usuario pueda ingresar un peso válido
                        }

                        if (grafo.existeArista(nombreOrigen, nombreDestino)) {
                            System.out.println("Ya se ha agregado un peso para la arista entre " + nombreOrigen + " y " + nombreDestino + ".");
                        } else {
                            grafo.agregarArista(new Ubicacion1(nombreOrigen), new Ubicacion1(nombreDestino), peso);
                            System.out.println("Arista agregada correctamente.");
                        }

                        System.out.print("¿Desea agregar otro peso? (s/n): ");
                        String respuesta = scanner.nextLine().toLowerCase();
                        if (!respuesta.equals("s")) {
                            agregarMasPesos = false;
                        }
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el nombre de la ubicación de origen: ");
                    String nombreOrigenRuta = scanner.nextLine().toLowerCase();
                    Ubicacion1 ubicacionOrigen = new Ubicacion1(nombreOrigenRuta);
                    if (grafo.existeUbicacion(ubicacionOrigen)) {
                        System.out.println("Calculando ruta más corta desde " + nombreOrigenRuta + "...");
                        Map<Ubicacion1, Integer> distancias = grafo.rutaMasCorta(ubicacionOrigen);
                        for (Map.Entry<Ubicacion1, Integer> entry : distancias.entrySet()) {
                            System.out.println("Distancia a " + entry.getKey().getNombre() + ": " + entry.getValue());
                        }
                    } else {
                        System.out.println("La ubicación de origen no existe en el grafo.");
                    }
                    break;
                case 4:
                    grafo.imprimirUbicaciones();
                    break;
                    
                case 5:
                    System.out.print("Ingrese el nombre de la ubicación que desea modificar: ");
                    String nombreUbicacionModificar = scanner.nextLine().toLowerCase();
                    if (grafo.existeUbicacion(new Ubicacion1(nombreUbicacionModificar))) {
                        System.out.print("Ingrese el nuevo nombre para la ubicación: ");
                        String nuevoNombreUbicacion = scanner.nextLine().toLowerCase();
                        grafo.modificarUbicacion(nombreUbicacionModificar, nuevoNombreUbicacion);
                    } else {
                        System.out.println("La ubicación que desea modificar no existe en el grafo.");
                    }
                    break;
                    
                case 6:
                    if (nombresUbicaciones.isEmpty()) {
                        System.out.println("No hay ubicaciones agregadas para eliminar.");
                        break;
                    }
                    System.out.print("Ingrese el nombre de la ubicación que desea eliminar: ");
                    String nombreUbicacionEliminar = scanner.nextLine().toLowerCase();
                    if (grafo.existeUbicacion(new Ubicacion1(nombreUbicacionEliminar))) {
                        System.out.print("¿Está seguro de eliminar la ubicación " + nombreUbicacionEliminar + "? (s/n): ");
                        String confirmacion = scanner.nextLine().toLowerCase();
                        if (confirmacion.equals("s")) {
                            grafo.eliminarUbicacion(nombreUbicacionEliminar);
                            nombresUbicaciones.remove(nombreUbicacionEliminar);
                        } else {
                            System.out.println("Operación de eliminación cancelada.");
                        }
                    } else {
                        System.out.println("La ubicación que desea eliminar no existe en el grafo.");
                    }
                    break;
                    
                case 7:
                	 if (nombresUbicaciones.isEmpty()) {
                         System.out.println("No hay pesos agregados para Modificarr.");
                         break;
                     }
                    System.out.print("Ingrese el nombre del origen: ");
                    String nombreOrigenModificarPeso = scanner.nextLine().toLowerCase();
                    System.out.print("Ingrese el nombre del destino: ");
                    String nombreDestinoModificarPeso = scanner.nextLine().toLowerCase();
                    System.out.print("Ingrese el nuevo peso para la arista: ");
                    int nuevoPeso = Integer.parseInt(scanner.nextLine());
                    grafo.modificarPesoArista(nombreOrigenModificarPeso, nombreDestinoModificarPeso, nuevoPeso);
                    break;
                    
                case 8:
                	 if (nombresUbicaciones.isEmpty()) {
                         System.out.println("No hay pesos agregadas para Eliminar.");
                         break;
                     }
                    System.out.print("Ingrese el nombre del origen: ");
                    String nombreOrigenEliminarArista = scanner.nextLine().toLowerCase();
                    System.out.print("Ingrese el nombre del destino: ");
                    String nombreDestinoEliminarArista = scanner.nextLine().toLowerCase();
                    grafo.eliminarArista(nombreOrigenEliminarArista, nombreDestinoEliminarArista);
                    break;
                case 9:
                    System.out.print("Ingrese el nombre de la ubicación de origen para el Árbol de Expansión Mínima (Prim): ");
                    String nombreOrigenPrim = scanner.nextLine().toLowerCase();
                    Ubicacion1 ubicacionOrigenPrim = new Ubicacion1(nombreOrigenPrim);
                    if (grafo.existeUbicacion(ubicacionOrigenPrim)) {
                    	AlgoritmosGrafo1 algoritmos = new AlgoritmosGrafo1();
                        int mstWeight = algoritmos.prim(grafo.generarMatrizAdyacencia(ubicacionOrigenPrim));
                        System.out.println("Peso del árbol de expansión mínima desde " + nombreOrigenPrim + ": " + mstWeight);
                    } else {
                        System.out.println("La ubicación de origen no existe en el grafo.");
                    }
                    break;
                case 10:
                    System.out.print("Ingrese el nombre de la ubicación de origen para el Árbol de Expansión Mínima (Kruskal): ");
                    String nombreOrigenKruskal = scanner.nextLine().toLowerCase();
                    Ubicacion1 ubicacionOrigenKruskal = new Ubicacion1(nombreOrigenKruskal);
                    if (grafo.existeUbicacion(ubicacionOrigenKruskal)) {
                        List<Arista1> arbolExpansionMinima = grafo.kruskal();
                        int totalWeight = 0;
                        for (Arista1 arista : arbolExpansionMinima) {
                            System.out.println(arista.getDestino().getNombre() + " - " + arista.getDestino().getNombre() + ": " + arista.getPeso());
                            totalWeight += arista.getPeso();
                        }
                        System.out.println("Peso total del árbol de expansión mínima desde " + nombreOrigenKruskal + ": " + totalWeight);
                    } else {
                        System.out.println("La ubicación de origen no existe en el grafo.");
                    }
                    break;
                    
                case 11:
                    System.out.print("Ingrese el nombre de la ubicación de origen para el algoritmo de Floyd-Warshall: ");
                    String nombreOrigenFloyd = scanner.nextLine().toLowerCase();
                    Ubicacion1 ubicacionOrigenFloyd = new Ubicacion1(nombreOrigenFloyd);
                    if (grafo.existeUbicacion(ubicacionOrigenFloyd)) {
                        System.out.println("Ejecutando el algoritmo de Floyd-Warshall...");
                        int[][] matrizAdyacencia = grafo.generarMatrizAdyacencia(ubicacionOrigenFloyd); // Generar matriz de adyacencia
                        AlgoritmosGrafo1 algoritmosGrafo = new AlgoritmosGrafo1();
                        int[][] distancias = GrafoNoDirigido1.floydWarshall(matrizAdyacencia); // Pasar la matriz de adyacencia a floydWarshall
                        // Aquí puedes imprimir la matriz de distancias o hacer cualquier otra cosa con ella
                    } else {
                        System.out.println("La ubicación de origen no existe en el grafo.");
                    }
                    break;
                    
                case 12:
                    System.out.print("Ingrese el nombre de la ubicación de origen para planificar la ruta: ");
                    String nombreOrigenRutaPlanificacion = scanner.nextLine().toLowerCase();
                    Ubicacion1 ubicacionOrigenRuta = new Ubicacion1(nombreOrigenRutaPlanificacion);

                    System.out.print("Ingrese el nombre de la ubicación de destino para planificar la ruta: ");
                    String nombreDestinoRuta = scanner.nextLine().toLowerCase();
                    Ubicacion1 ubicacionDestinoRuta = new Ubicacion1(nombreDestinoRuta);

                    if (grafo.existeUbicacion(ubicacionOrigenRuta) && grafo.existeUbicacion(ubicacionDestinoRuta)) {
                        System.out.println("Calculando ruta más corta desde " + nombreOrigenRutaPlanificacion + " a " + nombreDestinoRuta + "...");
                        Map<Ubicacion1, Integer> distanciasRuta = grafo.planificarRuta(ubicacionOrigenRuta, ubicacionDestinoRuta, false);
                        for (Map.Entry<Ubicacion1, Integer> entry : distanciasRuta.entrySet()) {
                            System.out.println("Distancia a " + entry.getKey().getNombre() + ": " + entry.getValue());
                        }
                    } else {
                        System.out.println("Una o ambas ubicaciones no existen en el grafo.");
                    }
                    break;

                case 13:
                    System.out.print("Ingrese el nombre de la ubicación de origen para planificar la ruta: ");
                    String nombreOrigenTiempo = scanner.nextLine().toLowerCase();
                    Ubicacion1 ubicacionOrigenTiempo = new Ubicacion1(nombreOrigenTiempo);

                    System.out.print("Ingrese el nombre de la ubicación de destino para planificar la ruta: ");
                    String nombreDestinoTiempo = scanner.nextLine().toLowerCase();
                    Ubicacion1 ubicacionDestinoTiempo = new Ubicacion1(nombreDestinoTiempo);

                    if (grafo.existeUbicacion(ubicacionOrigenTiempo) && grafo.existeUbicacion(ubicacionDestinoTiempo)) {
                        System.out.println("Calculando ruta más corta minimizando el tiempo desde " + nombreOrigenTiempo + " a " + nombreDestinoTiempo + "...");
                        Map<Ubicacion1, Integer> distanciasTiempo = grafo.planificarRuta(ubicacionOrigenTiempo, ubicacionDestinoTiempo, true);
                        for (Map.Entry<Ubicacion1, Integer> entry : distanciasTiempo.entrySet()) {
                            System.out.println("Tiempo a " + entry.getKey().getNombre() + ": " + entry.getValue());
                        }
                    } else {
                        System.out.println("Una o ambas ubicaciones no existen en el grafo.");
                    }
                    break;
                case 14:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción inválida! Por favor, ingrese un número del 1 al 5.");
            }
        }
    }  
}
