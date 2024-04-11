public class Main {
    public static void main(String[] args) {
        GrafoNoDirigido grafo = new GrafoNoDirigido();

        // Agregar ubicaciones
        Ubicacion ubicacionA = new Ubicacion("A");
        Ubicacion ubicacionB = new Ubicacion("B");
        Ubicacion ubicacionC = new Ubicacion("C");

        grafo.agregarUbicacion(ubicacionA);
        grafo.agregarUbicacion(ubicacionB);
        grafo.agregarUbicacion(ubicacionC);

        // Agregar aristas
        grafo.agregarArista(ubicacionA, ubicacionB);
        grafo.agregarArista(ubicacionB, ubicacionC);

        // Imprimir lista de adyacencia
        System.out.println("Lista de adyacencia:");
        grafo.imprimirLista();

        // Eliminar una ubicación y sus aristas
        grafo.eliminarUbicacion(ubicacionB);

        // Imprimir lista de adyacencia después de eliminar una ubicación
        System.out.println("\nLista de adyacencia después de eliminar B:");
        grafo.imprimirLista();
    }
}