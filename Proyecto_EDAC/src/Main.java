import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GrafoNoDirigido grafo = new GrafoNoDirigido();

        while (true) {
            System.out.println("�Qu� acci�n desea realizar?");
            System.out.println("1. Agregar ubicaci�n");
            System.out.println("2. Agregar arista con peso");
            System.out.println("3. Calcular ruta m�s corta desde una ubicaci�n");
            System.out.println("4. Representaci�n rutas �ptimas para conectar las ubicaciones");
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
                        break;
                    }
                    System.out.println("Distancias desde la ubicaci�n: " + nombreOrigenRuta);
                    Map<Ubicacion, Integer> distancias = grafo.rutaMasCorta(ubicacionOrigen);
                    for (Map.Entry<Ubicacion, Integer> entry : distancias.entrySet()) {
                        System.out.println("A " + entry.getKey().getNombre() + ": " + entry.getValue());
                    }
                    break;
                case 4:
                    System.out.println("Funci�n no implementada a�n.");
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