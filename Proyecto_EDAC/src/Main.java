import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GrafoNoDirigido grafo = new GrafoNoDirigido();
        Set<String> nombresUbicaciones = new HashSet<>();

        while (true) {
            System.out.println("�Qu� acci�n desea realizar?");
            System.out.println("1. Agregar ubicaci�n");
            System.out.println("2. Agregar arista con peso");
            System.out.println("3. Calcular ruta m�s corta desde una ubicaci�n");
            System.out.println("4. Mostrar todas las ubicaciones");
            System.out.println("5. Salir");
            System.out.print("Ingrese el n�mero de la acci�n: ");

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un n�mero v�lido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    boolean agregarMasUbicaciones = true;
                    while (agregarMasUbicaciones) {
                        System.out.print("Ingrese el nombre de la ubicaci�n: ");
                        String nombreUbicacion = scanner.nextLine().toLowerCase();
                        if (nombresUbicaciones.contains(nombreUbicacion)) {
                            System.out.println("La ubicaci�n ya ha sido agregada anteriormente.");
                        } else {
                            nombresUbicaciones.add(nombreUbicacion);
                            grafo.agregarUbicacion(new Ubicacion(nombreUbicacion));
                        }

                        System.out.print("�Desea agregar otra ubicaci�n? (s/n): ");
                        String respuesta = scanner.nextLine().toLowerCase();
                        if (!respuesta.equals("s")) {
                            agregarMasUbicaciones = false;
                        }
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el nombre del origen: ");
                    String nombreOrigen = scanner.nextLine().toLowerCase();
                    System.out.print("Ingrese el nombre del destino: ");
                    String nombreDestino = scanner.nextLine().toLowerCase();
                    System.out.print("Ingrese el peso de la arista: ");
                    int peso = Integer.parseInt(scanner.nextLine());
                    grafo.agregarArista(new Ubicacion(nombreOrigen), new Ubicacion(nombreDestino), peso);
                    break;
                case 3:
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
                    grafo.imprimirUbicaciones();
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
