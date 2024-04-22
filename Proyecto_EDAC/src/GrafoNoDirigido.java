import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class GrafoNoDirigido {
    private static final int INF = Integer.MAX_VALUE; // Cambiado a constante de clase
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
    
    public void modificarTiempoArista(String origen, String destino, int nuevoTiempo) {
        if (!existeArista(origen, destino)) {
            System.out.println("No existe una arista entre " + origen + " y " + destino + ".");
            return;
        }

        Ubicacion ubicacionOrigen = new Ubicacion(origen);
        Ubicacion ubicacionDestino = new Ubicacion(destino);

        List<Arista> aristasOrigen = listaAdyacencia.get(ubicacionOrigen);
        for (Arista arista : aristasOrigen) {
            if (arista.getDestino().equals(ubicacionDestino)) {
                arista.setTiempo(nuevoTiempo);
                System.out.println("Tiempo de la arista entre " + origen + " y " + destino + " modificado a " + nuevoTiempo + ".");
                break;
            }
        }
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

    public void agregarArista(Ubicacion origen, Ubicacion destino, int peso, int tiempo) {
        if (!existeUbicacion(origen) || !existeUbicacion(destino)) {
            System.out.println("Una o ambas ubicaciones no existen en el grafo.");
            return;
        }

        listaAdyacencia.get(origen).add(new Arista(origen, destino, peso, tiempo));
        listaAdyacencia.get(destino).add(new Arista(destino, origen, peso, tiempo));
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
        colaPrioridad.offer(new Arista(origen, origen, 0, 0));

        while (!colaPrioridad.isEmpty()) {
            Arista aristaActual = colaPrioridad.poll();
            Ubicacion actual = aristaActual.getDestino();

            if (visitados.contains(actual)) continue;
            visitados.add(actual);

            for (Arista arista : listaAdyacencia.get(actual)) {
                Ubicacion vecino = arista.getDestino();
                int peso = arista.getPeso();
                int tiempo = arista.getTiempo();

                if (!visitados.contains(vecino) && distancias.get(actual) != Integer.MAX_VALUE
                        && distancias.get(actual) + peso < distancias.get(vecino)) {
                    distancias.put(vecino, distancias.get(actual) + peso);
                    colaPrioridad.offer(new Arista(actual, vecino, distancias.get(vecino), 0));
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
            Ubicacion origen = arista.getOrigen();
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
                matriz[i][j] = INF; // Cambiado a INF
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

    public Map<Ubicacion, Integer> planificarRuta(Ubicacion origen, Ubicacion destino, boolean minimizarTiempo) {
        Map<Ubicacion, Integer> distancias = new HashMap<>();
        PriorityQueue<Arista> colaPrioridad = new PriorityQueue<>((a, b) -> {
            if (minimizarTiempo) {
                return a.getTiempo() - b.getTiempo();
            } else {
                return a.getPeso() - b.getPeso();
            }
        });
        Set<Ubicacion> visitados = new HashSet<>();

        for (Ubicacion ubicacion : listaAdyacencia.keySet()) {
            distancias.put(ubicacion, Integer.MAX_VALUE);
        }
        distancias.put(origen, 0);
        colaPrioridad.offer(new Arista(origen, origen, 0, 0));

        while (!colaPrioridad.isEmpty()) {
            Arista aristaActual = colaPrioridad.poll();
            Ubicacion actual = aristaActual.getDestino();

            if (visitados.contains(actual)) continue;
            visitados.add(actual);

            if (actual.equals(destino)) {
                // Llegamos al destino, no es necesario seguir explorando
                break;
            }

            for (Arista arista : listaAdyacencia.get(actual)) {
                Ubicacion vecino = arista.getDestino();
                int peso = minimizarTiempo ? arista.getTiempo() : arista.getPeso();

                if (!visitados.contains(vecino) && distancias.get(actual) != Integer.MAX_VALUE
                        && distancias.get(actual) + peso < distancias.get(vecino)) {
                    distancias.put(vecino, distancias.get(actual) + peso);
                    colaPrioridad.offer(new Arista(actual, vecino, peso, peso)); // Se usa el peso tanto para distancia como tiempo
                }
            }
        }

        return distancias;
    }

    public void agregarTiempo (String origen, String destino, int tiempo) {
        // Buscar las ubicaciones correspondientes en el grafo
        Ubicacion ubicacionOrigen = obtenerUbicacion(origen);
        Ubicacion ubicacionDestino = obtenerUbicacion(destino);

        // Verificar si las ubicaciones existen en el grafo
        if (ubicacionOrigen == null || ubicacionDestino == null) {
            System.out.println("Una o ambas ubicaciones no existen en el grafo.");
            return;
        }

        // Agregar el tiempo al mapa de tiempos de la ubicación de origen
        ubicacionOrigen.agregarTiempo(ubicacionDestino, tiempo);
    }


    private Ubicacion obtenerUbicacion(String nombreUbicacion) {
        // Iterar sobre las ubicaciones del grafo y encontrar la ubicación con el nombre especificado
        for (Ubicacion ubicacion : listaAdyacencia.keySet()) {
            if (ubicacion.getNombre().equals(nombreUbicacion)) {
                return ubicacion;
            }
        }
        return null; // Si no se encuentra la ubicación, devolver null
    }
    
    
}
