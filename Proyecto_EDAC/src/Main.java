import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Crear un Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        // Crear una instancia de GrafoNoDirigido para representar el grafo
        GrafoNoDirigido grafo = new GrafoNoDirigido();
        // Un conjunto para almacenar los nombres de las ubicaciones
        Set<String> nombresUbicaciones = new HashSet<>();
        boolean agregarMasTiempos = true;

        // Bucle principal que muestra el men� y maneja las acciones del usuario
        while (true) {
            // Limpiar la consola dependiendo del sistema operativo
            try {
                if (System.getProperty("os.name").contains("Windows")) {
                    // Limpiar la consola en Windows
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }

            // Mostrar el men� principal

            System.out.println("\n\n------Menu Principal-----\n");
            System.out.println("�Qu� acci�n desea realizar?");
            System.out.println("1. Agregar ubicaci�n");
            System.out.println("2. Agregar arista con peso y Tiempo");
            System.out.println("3. Calcular ruta m�s corta desde una ubicaci�n");
            System.out.println("4. Mostrar todas las ubicaciones");
            System.out.println("5. Modificar Ubicaciones");
            System.out.println("6. Eliminar ubicaci�n");
            System.out.println("7. Modificar Peso");
            System.out.println("8. Eliminar Peso");
            System.out.println("9. Modificar Tiempo");
            System.out.println("10. Eliminar Tiempo");
            System.out.println("11. Calcular �rbol de expansi�n m�nima desde un punto de origen (Prim)");
            System.out.println("12. Calcular �rbol de expansi�n m�nima desde un punto de origen (Kruskal)");
            System.out.println("13. Algoritmo Floyd-Warshall");
            System.out.println("14. Minimizar Distancia");
            System.out.println("15. Minimizar Tiempo");
            System.out.println("\n");
            System.out.println("16. Salir");

            // Solicitar la selecci�n del usuario
            System.out.print("\nIngrese el n�mero de la acci�n: ");
            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un n�mero v�lido.");
                continue;
            }

            // Realizar la acci�n correspondiente seg�n la opci�n seleccionada por el usuario
            switch (opcion) {
                case 1:
                    // Agregar una ubicaci�n al grafo
                	boolean agregarUbicaciones = true;
                	while (agregarUbicaciones) {
                	
                    System.out.print("Ingrese el nombre de la ubicaci�n: ");
                    String nombreUbicacion = scanner.nextLine().toLowerCase();
                    grafo.agregarUbicacion(new Ubicacion(nombreUbicacion));
                    nombresUbicaciones.add(nombreUbicacion);

                    // Preguntar al usuario si desea agregar otra ubicaci�n
                    System.out.print("�Desea agregar otra ubicacion? (s/n): ");
                    String respuesta = scanner.nextLine().toLowerCase();
                    if (!respuesta.equals("s")) {
                    	agregarUbicaciones = false;
                    }
                	}
                    break;

                case 2:
                    // Agregar una arista con peso y tiempo al grafo
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
                            System.out.println("Ingrese un n�mero v�lido para el peso.");
                            continue;
                        }
                        if (peso <= 0) {
                            System.out.println("El peso debe ser un n�mero positivo mayor que cero.");
                            continue;
                        }

                        System.out.print("Ingrese el tiempo de la arista: ");
                        int tiempo;
                        try {
                            tiempo = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException ex) {
                            System.out.println("Ingrese un n�mero v�lido para el tiempo.");
                            continue;
                        }
                        if (tiempo <= 0) {
                            System.out.println("El tiempo debe ser un n�mero positivo mayor que cero.");
                            continue;
                        }

                        if (grafo.existeArista(nombreOrigen, nombreDestino)) {
                            System.out.println("Ya se ha agregado un peso para la arista entre " + nombreOrigen + " y " + nombreDestino + ".");
                        } else {
                            grafo.agregarArista(new Ubicacion(nombreOrigen), new Ubicacion(nombreDestino), peso, tiempo);
                            System.out.println("Arista agregada correctamente.");
                        }
                        // Preguntar al usuario si desea agregar otro peso
                        System.out.print("�Desea agregar otro peso? (s/n): ");
                        String respuesta = scanner.nextLine().toLowerCase();
                        if (!respuesta.equals("s")) {
                            agregarMasPesos = false;
                        }
                    }
                    break;

                case 3:
                    // Calcular la ruta m�s corta desde una ubicaci�n dada
                    System.out.print("Ingrese el nombre de la ubicaci�n de origen: ");
                    String nombreOrigenRuta = scanner.nextLine().toLowerCase();
                    Ubicacion ubicacionOrigen = new Ubicacion(nombreOrigenRuta);
                    if (grafo.existeUbicacion(ubicacionOrigen)) {
                        System.out.println("Calculando ruta m�s corta desde " + nombreOrigenRuta + "...");
                        Map<Ubicacion, Integer> distancias = grafo.rutaMasCorta(ubicacionOrigen);
                        for (Map.Entry<Ubicacion, Integer> entry : distancias.entrySet()) {
                            System.out.println("Distancia a " + entry.getKey().getNombre() + ": " + entry.getValue());
                        }
                    } else {
                        System.out.println("La ubicaci�n de origen no existe en el grafo.");
                    }
                    break;

                case 4:
                    // Mostrar todas las ubicaciones del grafo
                    grafo.imprimirUbicaciones();
                    break;

                case 5:
                    // Modificar el nombre de una ubicaci�n existente en el grafo
                    System.out.print("Ingrese el nombre de la ubicaci�n que desea modificar: ");
                    String nombreUbicacionModificar = scanner.nextLine().toLowerCase();
                    if (grafo.existeUbicacion(new Ubicacion(nombreUbicacionModificar))) {
                        System.out.print("Ingrese el nuevo nombre para la ubicaci�n: ");
                        String nuevoNombreUbicacion = scanner.nextLine().toLowerCase();
                        grafo.modificarUbicacion(nombreUbicacionModificar, nuevoNombreUbicacion);
                    } else {
                        System.out.println("La ubicaci�n que desea modificar no existe en el grafo.");
                    }
                    break;

                case 6:
                    // Eliminar una ubicaci�n del grafo
                    if (nombresUbicaciones.isEmpty()) {
                        System.out.println("No hay ubicaciones agregadas para eliminar.");
                        break;
                    }
                    System.out.print("Ingrese el nombre de la ubicaci�n que desea eliminar: ");
                    String nombreUbicacionEliminar = scanner.nextLine().toLowerCase();
                    if (grafo.existeUbicacion(new Ubicacion(nombreUbicacionEliminar))) {
                        System.out.print("�Est� seguro de eliminar la ubicaci�n " + nombreUbicacionEliminar + "? (s/n): ");
                        String confirmacion = scanner.nextLine().toLowerCase();
                        if (confirmacion.equals("s")) {
                            grafo.eliminarUbicacion(nombreUbicacionEliminar);
                            nombresUbicaciones.remove(nombreUbicacionEliminar);
                        } else {
                            System.out.println("Operaci�n de eliminaci�n cancelada.");
                        }
                    } else {
                        System.out.println("La ubicaci�n que desea eliminar no existe en el grafo.");
                    }
                    break;

                case 7:
                    // Modificar el peso de una arista en el grafo
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
                    // Eliminar el peso de una arista en el grafo
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
                    // Modificar el tiempo de una arista en el grafo
                    if (nombresUbicaciones.isEmpty()) {
                        System.out.println("No hay tiempos agregados para Modificar.");
                        break;
                    }
                    System.out.print("Ingrese el nombre del origen: ");
                    String nombreOrigenModificarTiempo = scanner.nextLine().toLowerCase();
                    System.out.print("Ingrese el nombre del destino: ");
                    String nombreDestinoModificarTiempo = scanner.nextLine().toLowerCase();
                    System.out.print("Ingrese el nuevo tiempo para la arista: ");
                    int nuevoTiempo = Integer.parseInt(scanner.nextLine());
                    grafo.modificarTiempoArista(nombreOrigenModificarTiempo, nombreDestinoModificarTiempo, nuevoTiempo);
                    break;
                    
                case 10:
                    // Eliminar el tiempo de una arista en el grafo
                    if (nombresUbicaciones.isEmpty()) {
                        System.out.println("No hay tiempos agregadas para Eliminar.");
                        break;
                    }
                    System.out.print("Ingrese el nombre del origen: ");
                    String nombreOrigenEliminarTiempo = scanner.nextLine().toLowerCase();
                    System.out.print("Ingrese el nombre del destino: ");
                    String nombreDestinoEliminarTiempo = scanner.nextLine().toLowerCase();
                    grafo.eliminarArista(nombreOrigenEliminarTiempo, nombreDestinoEliminarTiempo);
                    break;
              

                case 11:
                    // Calcular Ruta con el metodo de �rbol de Expansi�n M�nima (Prim)
                    System.out.print("Ingrese el nombre de la ubicaci�n de origen para el �rbol de Expansi�n M�nima (Prim): ");
                    String nombreOrigenPrim = scanner.nextLine().toLowerCase();
                    Ubicacion ubicacionOrigenPrim = new Ubicacion(nombreOrigenPrim);
                    if (grafo.existeUbicacion(ubicacionOrigenPrim)) {
                        AlgoritmosGrafo algoritmos = new AlgoritmosGrafo();
                        int mstWeight = algoritmos.prim(grafo.generarMatrizAdyacencia(ubicacionOrigenPrim));
                        System.out.println("Peso del �rbol de expansi�n m�nima desde " + nombreOrigenPrim + ": " + mstWeight);
                    } else {
                        System.out.println("La ubicaci�n de origen no existe en el grafo.");
                    }
                    break;

                case 12:
                    // Calcular Ruta con el metodo de �rbol de Expansi�n M�nima (Kruskal)
                    System.out.print("Ingrese el nombre de la ubicaci�n de origen para el �rbol de Expansi�n M�nima (Kruskal): ");
                    String nombreOrigenKruskal = scanner.nextLine().toLowerCase();
                    Ubicacion ubicacionOrigenKruskal = new Ubicacion(nombreOrigenKruskal);
                    if (grafo.existeUbicacion(ubicacionOrigenKruskal)) {
                        List<Arista> arbolExpansionMinima = grafo.kruskal();
                        int totalWeight = 0;
                        for (Arista arista : arbolExpansionMinima) {
                            System.out.println(arista.getOrigen().getNombre() + " - " + arista.getDestino().getNombre() + ": " + arista.getPeso());
                            totalWeight += arista.getPeso();
                        }
                        System.out.println("Peso total del �rbol de expansi�n m�nima desde " + nombreOrigenKruskal + ": " + totalWeight);
                    } else {
                        System.out.println("La ubicaci�n de origen no existe en el grafo.");
                    }
                    break;

                case 13:
                    // Calcular Ruta con el metodo de algoritmo de Floyd-Warshall
                    System.out.print("Ingrese el nombre de la ubicaci�n de origen para el algoritmo de Floyd-Warshall: ");
                    String nombreOrigenFloyd = scanner.nextLine().toLowerCase();
                    Ubicacion ubicacionOrigenFloyd = new Ubicacion(nombreOrigenFloyd);
                    if (grafo.existeUbicacion(ubicacionOrigenFloyd)) {
                        System.out.println("Ejecutando el algoritmo de Floyd-Warshall...");
                        int[][] matrizAdyacencia = grafo.generarMatrizAdyacencia(ubicacionOrigenFloyd); // Generar matriz de adyacencia
                        AlgoritmosGrafo algoritmosGrafo = new AlgoritmosGrafo();
                        int[][] distancias = algoritmosGrafo.floydWarshall(matrizAdyacencia); // Pasar la matriz de adyacencia a floydWarshall

                        // Imprimir la matriz de distancias m�nimas
                        System.out.println("Matriz de distancias m�nimas:");
                        for (int i = 0; i < distancias.length; i++) {
                            for (int j = 0; j < distancias[i].length; j++) {
                                if (distancias[i][j] == AlgoritmosGrafo.INF) {
                                    System.out.print("INF ");
                                } else {
                                    System.out.print(distancias[i][j] + " ");
                                }
                            }
                            System.out.println();
                        }
                    } else {
                        System.out.println("La ubicaci�n de origen no existe en el grafo.");
                    }
                    break;


                case 14:
                    // Esto son los planificadores de ruta, mas cortas en ubicaciones.
                    System.out.print("Ingrese el nombre de la ubicaci�n de origen para planificar la ruta: ");
                    String nombreOrigenRutaPlanificacion = scanner.nextLine().toLowerCase();
                    Ubicacion ubicacionOrigenRuta = new Ubicacion(nombreOrigenRutaPlanificacion);

                    System.out.print("Ingrese el nombre de la ubicaci�n de destino para planificar la ruta: ");
                    String nombreDestinoRuta = scanner.nextLine().toLowerCase();
                    Ubicacion ubicacionDestinoRuta = new Ubicacion(nombreDestinoRuta);

                    if (grafo.existeUbicacion(ubicacionOrigenRuta) && grafo.existeUbicacion(ubicacionDestinoRuta)) {
                        System.out.println("Calculando ruta m�s corta desde " + nombreOrigenRutaPlanificacion + " a " + nombreDestinoRuta + "...");
                        Map<Ubicacion, Integer> distanciasRuta = grafo.planificarRuta(ubicacionOrigenRuta, ubicacionDestinoRuta, false);
                        for (Map.Entry<Ubicacion, Integer> entry : distanciasRuta.entrySet()) {
                            System.out.println("Distancia a " + entry.getKey().getNombre() + ": " + entry.getValue());
                        }
                    } else {
                        System.out.println("Una o ambas ubicaciones no existen en el grafo.");
                    }
                    break;

                case 15:
                    // Este calcular la ruta mas corta planificada, pero con el tiempo.
                    System.out.print("Ingrese el nombre de la ubicaci�n de origen para planificar la ruta: ");
                    String nombreOrigenTiempo = scanner.nextLine().toLowerCase();
                    Ubicacion ubicacionOrigenTiempo = new Ubicacion(nombreOrigenTiempo);

                    System.out.print("Ingrese el nombre de la ubicaci�n de destino para planificar la ruta: ");
                    String nombreDestinoTiempo = scanner.nextLine().toLowerCase();
                    Ubicacion ubicacionDestinoTiempo = new Ubicacion(nombreDestinoTiempo);

                    if (grafo.existeUbicacion(ubicacionOrigenTiempo) && grafo.existeUbicacion(ubicacionDestinoTiempo)) {
                        System.out.println("Calculando ruta m�s corta minimizando el tiempo desde " + nombreOrigenTiempo + " a " + nombreDestinoTiempo + "...");
                        Map<Ubicacion, Integer> distanciasTiempo = grafo.planificarRuta(ubicacionOrigenTiempo, ubicacionDestinoTiempo, true);
                        for (Map.Entry<Ubicacion, Integer> entry : distanciasTiempo.entrySet()) {
                            System.out.println("Tiempo a " + entry.getKey().getNombre() + ": " + entry.getValue());
                        }
                    } else {
                        System.out.println("Una o ambas ubicaciones no existen en el grafo.");
                    }
                    break;

                case 16:
                    // La opcion De salir, mensaje de despedida
                    System.out.println("�Hasta luego!");
                    System.exit(0);

                    // Validador si las opciones puestas son incorrectas
                default:
                    System.out.println("Opci�n inv�lida! Por favor, ingrese un n�mero del 1 al 5.");
            }
        }
    }
}
