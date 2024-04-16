

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class GrafoNoDirigido1 {
	private static int INF = Integer.MAX_VALUE;
    private final Map<Ubicacion1, List<Arista1>> listaAdyacencia;

    public GrafoNoDirigido1() {
        listaAdyacencia = new HashMap<>();
    }

    public boolean hayUbicaciones() {
        return !listaAdyacencia.isEmpty();
    }

    
    public void modificarUbicacion(String nombreViejo, String nuevoNombre) {
    	Ubicacion1 ubicacionVieja = new Ubicacion1(nombreViejo);
    	Ubicacion1 ubicacionNueva = new Ubicacion1(nuevoNombre);

        if (!existeUbicacion(ubicacionVieja)) {
            System.out.println("La ubicación a modificar no existe en el grafo.");
            return;
        }

        if (existeUbicacion(ubicacionNueva)) {
            System.out.println("Ya existe una ubicación con el nuevo nombre en el grafo.");
            return;
        }

        listaAdyacencia.put(ubicacionNueva, listaAdyacencia.remove(ubicacionVieja));
        for (Ubicacion1 ubicacion1 : listaAdyacencia.keySet()) {
            List<Arista1> aristas = listaAdyacencia.get(ubicacion1);
            for (Arista1 arista : aristas) {
                if (arista.getDestino().equals(ubicacionVieja)) {
                    arista.setDestino(ubicacionNueva);
                }
            }
        }

        System.out.println("Ubicación modificada exitosamente de " + nombreViejo + " a " + nuevoNombre + ".");
    }


    public void eliminarUbicacion(String nombreUbicacion) {
    	Ubicacion1 ubicacionEliminar = new Ubicacion1(nombreUbicacion);

        if (!existeUbicacion(ubicacionEliminar)) {
            System.out.println("La ubicación a eliminar no existe en el grafo.");
            return;
        }

        listaAdyacencia.remove(ubicacionEliminar);
        for (List<Arista1> aristas : listaAdyacencia.values()) {
            aristas.removeIf(arista -> arista.getDestino().equals(ubicacionEliminar));
        }

        System.out.println("Ubicación " + nombreUbicacion + " eliminada exitosamente.");
    }


    public boolean existeArista(String origen, String destino) {
    	Ubicacion1 ubicacionOrigen = new Ubicacion1(origen);
    	Ubicacion1 ubicacionDestino = new Ubicacion1(destino);

        if (!existeUbicacion(ubicacionOrigen) || !existeUbicacion(ubicacionDestino)) {
            return false;
        }

        List<Arista1> aristasOrigen = listaAdyacencia.get(ubicacionOrigen);
        for (Arista1 arista : aristasOrigen) {
            if (arista.getDestino().equals(ubicacionDestino)) {
                return true;
            }
        }

        return false;
    }

    public void eliminarArista(String origen, String destino) {
        if (!existeArista(origen, destino)) {
            System.out.println("No existe una arista entre " + origen + " y " + destino + " para eliminar.");
            return;
        }

        Ubicacion1 ubicacionOrigen = new Ubicacion1(origen);
        Ubicacion1 ubicacionDestino = new Ubicacion1(destino);

        List<Arista1> aristasOrigen = listaAdyacencia.get(ubicacionOrigen);
        aristasOrigen.removeIf(arista -> arista.getDestino().equals(ubicacionDestino));
        System.out.println("Arista entre " + origen + " y " + destino + " eliminada exitosamente.");
    }


    public void modificarPesoArista(String origen, String destino, int nuevoPeso) {
        if (!existeArista(origen, destino)) {
            System.out.println("No existe una arista entre " + origen + " y " + destino + ".");
            return;
        }

        Ubicacion1 ubicacionOrigen = new Ubicacion1(origen);
        Ubicacion1 ubicacionDestino = new Ubicacion1(destino);

        List<Arista1> aristasOrigen = listaAdyacencia.get(ubicacionOrigen);
        for (Arista1 arista : aristasOrigen) {
            if (arista.getDestino().equals(ubicacionDestino)) {
                arista.setPeso(nuevoPeso);
                System.out.println("Peso de la arista entre " + origen + " y " + destino + " modificado a " + nuevoPeso + ".");
                break;
            }
        }
    }

    public void agregarUbicacion(Ubicacion1 ubicacion) {
        listaAdyacencia.put(ubicacion, new ArrayList<>());
    }

    public void agregarArista(Ubicacion1 origen, Ubicacion1 destino, int peso) {
        if (!existeUbicacion(origen) || !existeUbicacion(destino)) {
            System.out.println("Una o ambas ubicaciones no existen en el grafo.");
            return;
        }

        listaAdyacencia.get(origen).add(new Arista1(origen, destino, peso,0)); // Usando getOrigen()
        listaAdyacencia.get(destino).add(new Arista1(destino, origen, peso,0)); // Usando getDestino()

    }

    public boolean existeUbicacion(Ubicacion1 ubicacion) {
        return listaAdyacencia.containsKey(ubicacion);
    }

    public Map<Ubicacion1, Integer> rutaMasCorta(Ubicacion1 origen) {
        Map<Ubicacion1, Integer> distancias = new HashMap<>();
        PriorityQueue<Arista1> colaPrioridad = new PriorityQueue<>((a, b) -> a.getPeso() - b.getPeso());
        Set<Ubicacion1> visitados = new HashSet<>();

        for (Ubicacion1 ubicacion : listaAdyacencia.keySet()) {
            distancias.put(ubicacion, Integer.MAX_VALUE);
        }
        distancias.put(origen, 0);
        colaPrioridad.offer(new Arista1(origen, origen, 0,0)); // Usando getOrigen() para origen y destino

        while (!colaPrioridad.isEmpty()) {
        	Arista1 aristaActual = colaPrioridad.poll();
            Ubicacion1 actual = aristaActual.getDestino();

            if (visitados.contains(actual)) continue;
            visitados.add(actual);

            for (Arista1 arista : listaAdyacencia.get(actual)) {
            	Ubicacion1 vecino = arista.getDestino();
                int peso = arista.getPeso();

                if (!visitados.contains(vecino) && distancias.get(actual) != Integer.MAX_VALUE
                        && distancias.get(actual) + peso < distancias.get(vecino)) {
                    distancias.put(vecino, distancias.get(actual) + peso);
                    colaPrioridad.offer(new Arista1(actual, vecino, distancias.get(vecino),0));

                }
            }
        }

        return distancias;
    }

    public List<Arista1> kruskal() {
        List<Arista1> arbolExpansionMinima = new ArrayList<>();
        PriorityQueue<Arista1> colaAristas = new PriorityQueue<>(Comparator.comparingInt(Arista1::getPeso));
        DisjointSet disjointSet = new DisjointSet();

        for (Ubicacion1 origen : listaAdyacencia.keySet()) {
            for (Arista1 arista : listaAdyacencia.get(origen)) {
                colaAristas.offer(arista);
            }
            disjointSet.makeSet(origen);
        }

        while (!colaAristas.isEmpty() && arbolExpansionMinima.size() < listaAdyacencia.size() - 1) {
        	Arista1 arista = colaAristas.poll();
            Ubicacion1 origen = arista.getOrigen(); // Se obtiene la ubicación de origen de la arista
            Ubicacion1 destino = arista.getDestino(); // Se obtiene la ubicación de destino de la arista

            if (disjointSet.find(origen) != disjointSet.find(destino)) {
                arbolExpansionMinima.add(arista);
                disjointSet.union(origen, destino);
            }
        }


        return arbolExpansionMinima;
    }


    public int[][] generarMatrizAdyacencia(Ubicacion1 puntoOrigen) {
        List<Ubicacion1> ubicaciones = new ArrayList<>(listaAdyacencia.keySet());
        int n = ubicaciones.size();
        int[][] matriz = new int[n][n];

        // Mapear ubicaciones a índices
        Map<Ubicacion1, Integer> indiceMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indiceMap.put(ubicaciones.get(i), i);
        }

        // Inicializar matriz con valores de infinito
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = Integer.MAX_VALUE; // Corregido: se cambió 0 por Integer.MAX_VALUE
            }
        }

        // Llenar la matriz con los pesos de las aristas
        for (int i = 0; i < n; i++) {
        	Ubicacion1 origen = ubicaciones.get(i);
            for (Arista1 arista : listaAdyacencia.get(origen)) {
            	Ubicacion1 destino = arista.getDestino();
                int peso = arista.getPeso();
                matriz[i][indiceMap.get(destino)] = peso;
                matriz[indiceMap.get(destino)][i] = peso;
            }
        }

        return matriz;
    }

    public void imprimirUbicaciones() {
        System.out.println("Ubicaciones en el grafo:");
        for (Ubicacion1 ubicacion : listaAdyacencia.keySet()) {
            System.out.println(ubicacion.getNombre());
        }
    }
    
    public Map<Ubicacion1, Integer> planificarRuta(Ubicacion1 origen, Ubicacion1 destino, boolean minimizarTiempo) {
        Map<Ubicacion1, Integer> distancias = new HashMap<>();
        PriorityQueue<Arista1> colaPrioridad = new PriorityQueue<>((a, b) -> {
            if (minimizarTiempo) {
                return a.getTiempo() - b.getTiempo();
            } else {
                return a.getPeso() - b.getPeso();
            }
        });
        Set<Ubicacion1> visitados = new HashSet<>();

        for (Ubicacion1 ubicacion : listaAdyacencia.keySet()) {
            distancias.put(ubicacion, Integer.MAX_VALUE);
        }
        distancias.put(origen, 0);
        colaPrioridad.offer(new Arista1(origen, origen, 0, 0)); // Se usa 0 para la distancia y el tiempo inicialmente

        while (!colaPrioridad.isEmpty()) {
        	Arista1 aristaActual = colaPrioridad.poll();
            Ubicacion1 actual = aristaActual.getDestino();

            if (visitados.contains(actual)) continue;
            visitados.add(actual);

            if (actual.equals(destino)) {
                // Llegamos al destino, no es necesario seguir explorando
                break;
            }

            for (Arista1 arista : listaAdyacencia.get(actual)) {
                Ubicacion1 vecino = arista.getDestino();
                int peso = minimizarTiempo ? arista.getTiempo() : arista.getPeso();

                if (!visitados.contains(vecino) && distancias.get(actual) != Integer.MAX_VALUE
                        && distancias.get(actual) + peso < distancias.get(vecino)) {
                    distancias.put(vecino, distancias.get(actual) + peso);
                    colaPrioridad.offer(new Arista1(actual, vecino, peso, peso)); // Se usa el peso tanto para distancia como tiempo
                }
            }
        }

        return distancias;
    }
    
    public static int[][] floydWarshall(int[][] graph) {
        int n = graph.length;
        int[][] dist = new int[n][n];

        // Inicializar distancias con la matriz de adyacencia
        for (int i = 0; i < n; i++) {
            System.arraycopy(graph[i], 0, dist[i], 0, n);
        }

        // Aplicar el algoritmo de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }
}
