import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GrafoNoDirigido grafo = new GrafoNoDirigido();

        while (true) {
            System.out.println("---- Men� ----");
            System.out.println("1. Agregar ubicaci�n");
            System.out.println("2. Agregar arista con peso");
            System.out.println("3. Calcular ruta m�s corta desde una ubicaci�n");
            System.out.println("4. Mostrar todas las ubicaciones registradas");
            System.out.println("5. Salir");
            System.out.print("Ingrese el n�mero de la acci�n: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la ubicaci�n: ");
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
                    System.out.print("Ingrese el nombre de la ubicaci�n de origen: ");
                    String nombreOrigenRuta = scanner.nextLine();
                    Ubicacion ubicacionOrigen = new Ubicacion(nombreOrigenRuta);

                    if (!grafo.existeUbicacion(ubicacionOrigen)) {
                        System.out.println("La ubicaci�n de origen no existe en el grafo.");
                    } else {
                        Map<Ubicacion, Integer> distancias = grafo.rutaMasCorta(ubicacionOrigen);
                        System.out.println("Ruta m�s corta desde " + nombreOrigenRuta + ":");

                        for (Map.Entry<Ubicacion, Integer> entry : distancias.entrySet()) {
                            if (entry.getValue() != Integer.MAX_VALUE) {
                                System.out.println("A " + entry.getKey().getNombre() + ": " + entry.getValue());
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("----- Ubicaciones Registradas -----");
                    Set<Ubicacion> ubicaciones = grafo.obtenerUbicaciones();
                    for (Ubicacion ubicacion : ubicaciones) {
                        System.out.println(ubicacion.getNombre());
                    }
                    break;
                case 5:
                    System.out.println("�Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opci�n inv�lida! Por favor, ingrese un n�mero del 1 al 5.");
            }
        }
    }
}