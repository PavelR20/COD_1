import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GrafoNoDirigido grafo = new GrafoNoDirigido();

        while (true) {
            System.out.println("¿       Qué acción desea realizar?            ");
            System.out.println("1.           Agregar ubicación                  ");
            System.out.println("2.         Agregar arista con peso              ");
            System.out.println("3.  Calcular ruta más corta desde una ubicación");
            System.out.println("4.      Mostrar todas las ubicaciones         ");
            System.out.println("5.         Modificar Ubicaciones              ");
            System.out.println("6.            Eliminar ubicacion           ");
            System.out.println("7.              Eliminar Peso       ");
            System.out.println("8.              Modificar Peso                      ");
            System.out.println("9.                  Salir                     ");
            System.out.print("        Ingrese el número de la acción:");

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
                    grafo.agregarUbicacion(new Ubicacion(nombreUbicacion));
                    break;
                case 2:
                    if (nombresUbicaciones.isEmpty()) {
                        System.out.println("No hay ubicaciones agregadas para asignarle un peso.");
                        break;
                    }

                    System.out.print("Ingrese el nombre del origen: ");
                    String nombreOrigen = scanner.nextLine().toLowerCase();
                    System.out.print("Ingrese el nombre del destino: ");
                    String nombreDestino = scanner.nextLine().toLowerCase();

                    if (!nombresUbicaciones.contains(nombreOrigen) || !nombresUbicaciones.contains(nombreDestino)) {
                        System.out.println("Uno o ambos nombres de ubicación no existen en el grafo.");
                        break;
                    }

                    System.out.print("Ingrese el peso de la arista: ");
                    int peso;
                    try {
                        peso = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException ex) {
                        System.out.println("Ingrese un número válido para el peso.");
                        break;
                    }

                    if (peso <= 0) {
                        System.out.println("El peso debe ser un número positivo mayor que cero.");
                        break;
                    }

                    if (grafo.existeArista(nombreOrigen, nombreDestino)) {
                        System.out.println("Ya se ha agregado un peso para la arista entre " + nombreOrigen + " y " + nombreDestino + ".");
                    } else {
                        grafo.agregarArista(new Ubicacion(nombreOrigen), new Ubicacion(nombreDestino), peso);
                        System.out.println("Arista agregada correctamente.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el nombre de la ubicación de origen: ");
                    String nombreOrigenRuta = scanner.nextLine().toLowerCase();
                    Ubicacion ubicacionOrigen = new Ubicacion(nombreOrigenRuta);
                    if (grafo.existeUbicacion(ubicacionOrigen)) {
                        System.out.println("Calculando ruta más corta desde " + nombreOrigenRuta + "...");
                        Map<Ubicacion, Integer> distancias = grafo.rutaMasCorta(ubicacionOrigen);
                        for (Map.Entry<Ubicacion, Integer> entry : distancias.entrySet()) {
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
                    if (grafo.existeUbicacion(new Ubicacion(nombreUbicacionModificar))) {
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
                    if (grafo.existeUbicacion(new Ubicacion(nombreUbicacionEliminar))) {
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
                         System.out.println("No hay pesos agregados para eliminar.");
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
                         System.out.println("No hay pesos agregadas para Modificar.");
                         break;
                     }
                    System.out.print("Ingrese el nombre del origen: ");
                    String nombreOrigenEliminarArista = scanner.nextLine().toLowerCase();
                    System.out.print("Ingrese el nombre del destino: ");
                    String nombreDestinoEliminarArista = scanner.nextLine().toLowerCase();
                    grafo.eliminarArista(nombreOrigenEliminarArista, nombreDestinoEliminarArista);
                    break;
                    
                case 9:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción inválida! Por favor, ingrese un número del 1 al 5.");
            }
        }
    }
}