import java.util.Map;

public class Main {
    public static void main(String[] args) {
        GrafoNoDirigido grafo = new GrafoNoDirigido();

        // Agregar ubicaciones
        Ubicacion ubicacionA = new Ubicacion("TORRE LA MALACA");
        Ubicacion ubicacionB = new Ubicacion("PUCMM");
        Ubicacion ubicacionC = new Ubicacion("LA VEGA");

        grafo.agregarUbicacion(ubicacionA);
        grafo.agregarUbicacion(ubicacionB);
        grafo.agregarUbicacion(ubicacionC);

        // Agregar aristas con pesos
        grafo.agregarArista(ubicacionA, ubicacionB, 87);
        grafo.agregarArista(ubicacionB, ubicacionC, 300);

        // Imprimir lista de adyacencia
        System.out.println("Lista de adyacencia:");
        grafo.imprimirLista();

        // Calcular ruta más corta desde la ubicación x
        System.out.println("\nDistancias desde la ubicación: " + ubicacionA.getNombre());
        Map<Ubicacion, Integer> distancias = grafo.rutaMasCorta(ubicacionA);
        for (Map.Entry<Ubicacion, Integer> entry : distancias.entrySet()) {
            System.out.println("A " + entry.getKey().getNombre() + ": " + entry.getValue());
        }
    }
}
