import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GrafoNoDirigido grafo = new GrafoNoDirigido();

        while (true) {
            System.out.println("¿Que accion desea realizar?");
            System.out.println("1. Agregar ubicacion");
            System.out.println("2. Agregar arista con peso");
            System.out.println("3. Calcular ruta mas corta desde una ubicacion");
            System.out.println("4 Representacion rutas óptimas para conectar las ubicaciones");
            System.out.println("5. Salir");
            System.out.print("Ingrese el numero de la accion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la ubicacion: ");
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
                    System.out.print("Ingrese el nombre de la ubicacion de origen: ");
                    String nombreOrigenRuta = scanner.nextLine();
                    Ubicacion ubicacionOrigen = new Ubicacion(nombreOrigenRuta);
                    if (!grafo.existeUbicacion(ubicacionOrigen)) {
                        System.out.println("La ubicacion de origen no existe en el grafo.");
                        break;
                    }
                    System.out.println("Distancias desde la ubicacion: " + nombreOrigenRuta);
                    Map<Ubicacion, Integer> distancias = grafo.rutaMasCorta(ubicacionOrigen);
                    for (Map.Entry<Ubicacion, Integer> entry : distancias.entrySet()) {
                        System.out.println("A " + entry.getKey().getNombre() + ": " + entry.getValue());
                    }
                    break;
                    
                case 4:
                    System.out.print("Ingrese el nombre de la ubicacion de origen: ");
                    String nombreOrigenRuta1 = scanner.nextLine();
                    Ubicacion ubicacionOrigen1 = new Ubicacion(nombreOrigenRuta1);
                    if (!grafo.existeUbicacion(ubicacionOrigen1)) {
                        System.out.println("La ubicacion de origen no existe en el grafo.");
                        break;
                    }
                    System.out.println("Distancias desde la ubicacion: " + nombreOrigenRuta1);
                    Map<Ubicacion, Integer> distancias1 = grafo.rutaMasCorta(ubicacionOrigen1);
                    for (Map.Entry<Ubicacion, Integer> entry : distancias1.entrySet()) {
                        System.out.println("A " + entry.getKey().getNombre() + ": " + entry.getValue());
                    }
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opcion invalida! Por favor, ingrese un numero del 1 al 5.");
            }
        }
    }
}
