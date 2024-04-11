import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GrafoNoDirigido grafo = new GrafoNoDirigido();

        while (true) {
            System.out.println("¿Qué acción desea realizar?");
            System.out.println("1. Agregar ubicación");
            System.out.println("2. Agregar arista con peso");
            System.out.println("3. Calcular ruta más corta desde una ubicación");
            System.out.println("4. Representación rutas óptimas para conectar las ubicaciones");
            System.out.println("5. Salir");
            System.out.print("Ingrese el número de la acción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la ubicación: ");
                    String nombreUbicacion = scanner.nextLine();
                    grafo.agregarUbicacion(new Ubicacion(nombreUbicacion));
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del origen: ");
                    String nombreOrigen = scanner.nextLine();
                    System.out.print("Ingrese el nombre del destino: ");
                    String nombreDestino = scanner.nextLine();
                    System.out.print("Ingrese el peso de la arista: ");
                    int peso = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    grafo.agregarArista(new Ubicacion(nombreOrigen), new Ubicacion(nombreDestino), peso);
                    break;
                case 3:
                    System.out.print("Ingrese el nombre de la ubicación de origen: ");
                    String nombreOrigenRuta = scanner.nextLine();
                    Ubicacion ubicacionOrigen = new Ubicacion(nombreOrigenRuta);
                    if (!grafo.existeUbicacion(ubicacionOrigen)) {
                        System.out.println("La ubicación de origen no existe en el grafo.");
                        break;
                    }
                    System.out.println("Distancias desde la ubicación: " + nombreOrigenRuta);
                    Map<Ubicacion, Integer> distancias = grafo.rutaMasCorta(ubicacionOrigen);
                    for (Map.Entry<Ubicacion, Integer> entry : distancias.entrySet()) {
                        System.out.println("A " + entry.getKey().getNombre() + ": " + entry.getValue());
                    }
                    break;
                case 4:
                    System.out.println("Función no implementada aún.");
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción inválida! Por favor, ingrese un número del 1 al 5.");
            }
        }
    }
}