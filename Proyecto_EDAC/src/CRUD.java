import java.util.*;

class DisjointSet {
    private Map<Ubicacion, Ubicacion> parent;
    private Map<Ubicacion, Integer> rank;

    public DisjointSet() {
        parent = new HashMap<>();
        rank = new HashMap<>();
    }

    public void makeSet(Ubicacion ubicacion) {
        parent.put(ubicacion, ubicacion);
        rank.put(ubicacion, 0);
    }

    public Ubicacion find(Ubicacion ubicacion) {
        if (parent.get(ubicacion) != ubicacion) {
            parent.put(ubicacion, find(parent.get(ubicacion)));
        }
        return parent.get(ubicacion);
    }

    public void union(Ubicacion ubicacion1, Ubicacion ubicacion2) {
        Ubicacion root1 = find(ubicacion1);
        Ubicacion root2 = find(ubicacion2);

        if (root1 == root2) return;

        if (rank.get(root1) < rank.get(root2)) {
            parent.put(root1, root2);
        } else if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1);
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank.get(root1) + 1);
        }
    }
}

class Ubicacion {
    private final String nombre;

    public Ubicacion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) obj;
        return Objects.equals(nombre, ubicacion.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return nombre;
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

    public void setDestino(Ubicacion destino) {
        this.destino = destino;
    }

    public void setPeso(int nuevoPeso) {
        this.peso = nuevoPeso;
    }
}

class GrafoNoDirigido {
    private final Map<Ubicacion, List<Arista>> listaAdyacencia;

    public GrafoNoDirigido() {
        listaAdyacencia = new HashMap<>();
    }

    public boolean hayUbicaciones() {
        return !listaAdyacencia.isEmpty();
    }

    public void modificarUbicacion(String nombreViejo, String nuevoNombre) {
        Ubicacion ubicacionVieja = new Ubicacion(nombreViejo);
        Ubicacion ubicacionNueva = new Ubicacion(nuevoNombre);

        if (!existeUbicacion(ubicacionVieja)) {
            System.out.println("La ubicación a modificar no existe en el grafo.");
            return;
        }

        if (existeUbicacion(ubicacionNueva)) {
            System.out.println("Ya existe una ubicación con el nuevo nombre en el grafo.");
            return;
        }

        listaAdyacencia.put(ubicacionNueva, listaAdyacencia.remove(ubicacionVieja));
        for (Ubicacion ubicacion : listaAdyacencia.keySet()) {
            List<Arista> aristas = listaAdyacencia.get(ubicacion);
            for (Arista arista : aristas) {
                if (arista.getDestino().equals(ubicacionVieja)) {
                    arista.setDestino(ubicacionNueva);
                }
            }
        }

        System.out.println("Ubicación modificada exitosamente de " + nombreViejo + " a " + nuevoNombre + ".");
    }


    public void eliminarUbicacion(String nombreUbicacion) {
        Ubicacion ubicacionEliminar = new Ubicacion(nombreUbicacion);

        if (!existeUbicacion(ubicacionEliminar)) {
            System.out.println("La ubicación a eliminar no existe en el grafo.");
            return;
        }

        listaAdyacencia.remove(ubicacionEliminar);
        for (List<Arista> aristas : listaAdyacencia.values()) {
            aristas.removeIf(arista -> arista.getDestino().equals(ubicacionEliminar));
        }

        System.out.println("Ubicación " + nombreUbicacion + " eliminada exitosamente.");
    }


    public boolean existeArista(String origen, String destino) {
        Ubicacion ubicacionOrigen = new Ubicacion(origen);
        Ubicacion ubicacionDestino = new Ubicacion(destino);

        if (!existeUbicacion(ubicacionOrigen) || !existeUbicacion(ubicacionDestino)) {
            return false;
        }

        List<Arista> aristasOrigen = listaAdyacencia.get(ubicacionOrigen);
        for (Arista arista : aristasOrigen) {
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

        Ubicacion ubicacionOrigen = new Ubicacion(origen);
        Ubicacion ubicacionDestino = new Ubicacion(destino);

        List<Arista> aristasOrigen = listaAdyacencia.get(ubicacionOrigen);
        aristasOrigen.removeIf(arista -> arista.getDestino().equals(ubicacionDestino));
        System.out.println("Arista entre " + origen + " y " + destino + " eliminada exitosamente.");
    }


    public void modificarPesoArista(String origen, String destino, int nuevoPeso) {
        if (!existeArista(origen, destino)) {
            System.out.println("No existe una arista entre " + origen + " y " + destino + ".");
            return;
        }

        Ubicacion ubicacionOrigen = new Ubicacion(origen);
        Ubicacion ubicacionDestino = new Ubicacion(destino);

        List<Arista> aristasOrigen = listaAdyacencia.get(ubicacionOrigen);
        for (Arista arista : aristasOrigen) {
            if (arista.getDestino().equals(ubicacionDestino)) {
                arista.setPeso(nuevoPeso);
                System.out.println("Peso de la arista entre " + origen + " y " + destino + " modificado a " + nuevoPeso + ".");
                break;
            }
        }
    }

    public void agregarUbicacion(Ubicacion ubicacion) {
        listaAdyacencia.put(ubicacion, new ArrayList<>());
    }

    public void agregarArista(Ubicacion origen, Ubicacion destino, int peso) {
        if (!existeUbicacion(origen) || !existeUbicacion(destino)) {
            System.out.println("Una o ambas ubicaciones no existen en el grafo.");
            return;
        }

        listaAdyacencia.get(origen).add(new Arista(destino, peso));
        listaAdyacencia.get(destino).add(new Arista(origen, peso));
    }

    public boolean existeUbicacion(Ubicacion ubicacion) {
        return listaAdyacencia.containsKey(ubicacion);
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

            for (Arista arista : listaAdyacencia.get(actual)) {
                Ubicacion vecino = arista.getDestino();
                int peso = arista.getPeso();

                if (!visitados.contains(vecino) && distancias.get(actual) != Integer.MAX_VALUE
                        && distancias.get(actual) + peso < distancias.get(vecino)) {
                    distancias.put(vecino, distancias.get(actual) + peso);
                    colaPrioridad.offer(new Arista(vecino, distancias.get(vecino)));
                }
            }
        }

        return distancias;
    }

    public List<Arista> kruskal() {
        List<Arista> arbolExpansionMinima = new ArrayList<>();
        PriorityQueue<Arista> colaAristas = new PriorityQueue<>(Comparator.comparingInt(Arista::getPeso));
        DisjointSet disjointSet = new DisjointSet();

        for (Ubicacion origen : listaAdyacencia.keySet()) {
            for (Arista arista : listaAdyacencia.get(origen)) {
                colaAristas.offer(arista);
            }
            disjointSet.makeSet(origen);
        }

        while (!colaAristas.isEmpty() && arbolExpansionMinima.size() < listaAdyacencia.size() - 1) {
            Arista arista = colaAristas.poll();
            Ubicacion origen = arista.getDestino(); // Corregido: se cambió getOrigen() por getDestino()
            Ubicacion destino = arista.getDestino();

            if (disjointSet.find(origen) != disjointSet.find(destino)) {
                arbolExpansionMinima.add(arista);
                disjointSet.union(origen, destino);
            }
        }

        return arbolExpansionMinima;
    }


    public int[][] generarMatrizAdyacencia(Ubicacion puntoOrigen) {
        List<Ubicacion> ubicaciones = new ArrayList<>(listaAdyacencia.keySet());
        int n = ubicaciones.size();
        int[][] matriz = new int[n][n];

        // Mapear ubicaciones a índices
        Map<Ubicacion, Integer> indiceMap = new HashMap<>();
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
            Ubicacion origen = ubicaciones.get(i);
            for (Arista arista : listaAdyacencia.get(origen)) {
                Ubicacion destino = arista.getDestino();
                int peso = arista.getPeso();
                matriz[i][indiceMap.get(destino)] = peso;
                matriz[indiceMap.get(destino)][i] = peso;
            }
        }

        return matriz;
    }

    public void imprimirUbicaciones() {
        System.out.println("Ubicaciones en el grafo:");
        for (Ubicacion ubicacion : listaAdyacencia.keySet()) {
            System.out.println(ubicacion.getNombre());
        }
    }
}

class AlgoritmosGrafo {
    private static final int INF = Integer.MAX_VALUE;
    public static Object prim;

    public int prim(int[][] graph) {
        int[] distance = new int[graph.length];
        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            distance[i] = INF;
            visited[i] = false;
        }

        distance[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.distance, b.distance)
        );

        pq.add(new Node(0, 0));

        int mstWeight = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visited[node.vertex]) {
                continue;
            }

            visited[node.vertex] = true;

            mstWeight += node.distance;

            for (int neighbor = 0; neighbor < graph.length; neighbor++) {
                if (graph[node.vertex][neighbor] > 0 && !visited[neighbor]) {
                    int newDistance = graph[node.vertex][neighbor];

                    if (newDistance < distance[neighbor]) {
                        distance[neighbor] = newDistance;
                        pq.add(new Node(neighbor, newDistance));
                    }
                }
            }
        }

        return mstWeight;
    }

    private class Node {
        int vertex;
        int distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}
