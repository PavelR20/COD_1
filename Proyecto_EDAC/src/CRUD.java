import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

class Ubicacion {
    private String nombre;

    public Ubicacion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}

class Arista {
    private Ubicacion destino;
    private int peso;

    public Arista(Ubicacion destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public Ubicacion getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }
}

class GrafoNoDirigido {
    private Map<Ubicacion, List<Arista>> listaAdyacencia;

    public GrafoNoDirigido() {
        listaAdyacencia = new HashMap<>();
    }

    public void agregarUbicacion(Ubicacion ubicacion) {
        listaAdyacencia.put(ubicacion, new ArrayList<>());
    }

    public void agregarArista(Ubicacion origen, Ubicacion destino, int peso) {
        listaAdyacencia.get(origen).add(new Arista(destino, peso));
        listaAdyacencia.get(destino).add(new Arista(origen, peso));
    }

    public Map<Ubicacion, Integer> rutaMasCorta(Ubicacion origen) {
        Map<Ubicacion, Integer> distancias = new HashMap<>();
        PriorityQueue<Arista> colaPrioridad = new PriorityQueue<>((a, b) -> a.getPeso() - b.getPeso());
        Set<Ubicacion> visitados = new HashSet<>();

        for (Ubicacion ubicacion : listaAdyacencia.keySet()) {
            distancias.put(ubicacion, Integer.MAX_VALUE);
        }
        distancias.put(origen, 0);
        colaPrioridad.offer(new Arista(origen, 0));

        while (!colaPrioridad.isEmpty()) {
            Arista aristaActual = colaPrioridad.poll();
            Ubicacion actual = aristaActual.getDestino();

            if (visitados.contains(actual)) continue;
            visitados.add(actual);

            System.out.println("Visitando: " + actual.getNombre());

            for (Arista arista : listaAdyacencia.get(actual)) {
                Ubicacion vecino = arista.getDestino();
                int peso = arista.getPeso();

                if (!visitados.contains(vecino) && distancias.get(actual) != Integer.MAX_VALUE
                        && distancias.get(actual) + peso < distancias.get(vecino)) {
                    distancias.put(vecino, distancias.get(actual) + peso);
                    colaPrioridad.offer(new Arista(vecino, distancias.get(vecino)));
                    System.out.println("Actualizando distancia de " + vecino.getNombre() + " a " + distancias.get(vecino));
                }
            }
        }

        return distancias;
    }

    public void imprimirLista() {
        for (Map.Entry<Ubicacion, List<Arista>> entry : listaAdyacencia.entrySet()) {
            System.out.print("Ubicación " + entry.getKey().getNombre() + " está conectada con: ");
            for (Arista arista : entry.getValue()) {
                System.out.print(arista.getDestino().getNombre() + " (Peso: " + arista.getPeso() + ") ");
            }
            System.out.println();
        }
    }
}